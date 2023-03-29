package net.rizecookey.ptctest.cli

import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.default
import kotlinx.cli.required
import net.rizecookey.ptctest.test.TestSetup
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

    setup.execute()
}

fun printError(error: String) {
    System.err.println(ERROR + error)
}