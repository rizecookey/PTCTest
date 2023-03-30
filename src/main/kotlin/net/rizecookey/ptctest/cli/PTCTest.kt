package net.rizecookey.ptctest.cli

import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.default
import kotlinx.cli.required
import net.rizecookey.ptctest.test.TestSetup
import net.rizecookey.ptctest.test.result.LineResult
import net.rizecookey.ptctest.test.result.SingleResult
import net.rizecookey.ptctest.test.result.UnexpectedOutputResult
import org.fusesource.jansi.Ansi
import org.fusesource.jansi.Ansi.ansi
import java.io.IOException
import java.nio.file.InvalidPathException
import java.nio.file.Path
import java.nio.file.Paths

const val ERROR = "Error: "
fun main(args: Array<String>) {
    val parser = ArgParser("PTCTest")

    val command by parser.option(ArgType.String, shortName = "c", description = "Command to execute program")
        .required()
    val workingDirectory by parser.option(ArgType.String, shortName = "d", description = "The working directory for the process")
        .default("")
    val protocol by parser.option(ArgType.String, shortName = "p", description = "Location of protocol to test with")
        .required()

    parser.parse(args)

    val protocolPath: Path
    try {
        protocolPath = Paths.get(protocol)
    } catch (e: InvalidPathException) {
        printError("'$protocol' is not a valid path")
        return
    }
    val workingDirPath: Path
    try {
        workingDirPath = Paths.get(workingDirectory)
    } catch (e: InvalidPathException) {
        printError("'$workingDirectory' is not a valid path")
        return
    }

    val setup: TestSetup
    try {
        setup = TestSetup(command, protocolPath, workingDirPath)
    } catch (exception: IOException) {
        exception.message?.let { printError(it) }
        return
    }

    val handle = setup.createHandle()
    handle.onResult { result -> result.log { println(it) } }
    handle.start()

    println()

    val result = handle.result!!
    val resultMessage = if (result.success) {
        ansi()
            .fgBrightGreen()
            .a("success")
            .reset()
            .toString()
    } else {
        val failed = result.results.filter { !it.success }.count()
        ansi()
            .fgBrightRed()
            .a("failure, $failed mismatched lines")
            .reset()
            .toString()
    }

    println("Result: $resultMessage")
}

fun printError(error: String) {
    System.err.println(ERROR + error)
}

/*
 Not sure if this is the best solution, but I want to keep
 IO and logic separated
 */
fun SingleResult.log(logger: (String) -> Unit) {
    when (this) {
        SingleResult.PREMATURE_END -> {
            logger(ansi()
                .fgBright(Ansi.Color.RED)
                .a("Program output ended prematurely! Did it crash?")
                .reset()
                .toString())
        }
        is UnexpectedOutputResult -> {
            logger(ansi()
                .fgBright(Ansi.Color.RED)
                .a("Protocol has ended, however program is still outputting text!")
                .fgBright(Ansi.Color.MAGENTA)
                .a(this.excessOutput)
                .reset()
                .toString())
        }
        is LineResult -> {
            logger(ansi()
                .fgBright(when (this.type) {
                    LineResult.Type.OUTPUT -> Ansi.Color.WHITE
                    LineResult.Type.INPUT -> Ansi.Color.GREEN
                    LineResult.Type.COMMENT -> Ansi.Color.BLUE
                })
                .a(when (this.type) {
                    LineResult.Type.OUTPUT -> actual
                    LineResult.Type.INPUT -> "> $actual"
                    LineResult.Type.COMMENT -> "# $actual"
                })
                .reset()
                .toString())
            if (!this.success) {
                logger(ansi()
                    .fgBright(Ansi.Color.RED)
                    .a("Expected: ${this.expectation}")
                    .reset()
                    .toString())
            }
        }
    }
}