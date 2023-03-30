package net.rizecookey.ptctest.test

import net.rizecookey.ptctest.process.TestProcessHandle
import net.rizecookey.ptctest.test.line.ProtocolLine
import net.rizecookey.ptctest.test.parser.PARSERS
import net.rizecookey.ptctest.test.parser.SPLIT_PATTERN
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path

class TestSetup(startCommand: String, protocol: Path, val workingDirectory: Path) {
    val commandArgs: List<String>
    val lines: List<ProtocolLine>

    init {
        if (!Files.isRegularFile(protocol)) {
            throw IOException("$protocol is not a readable text file")
        }
        if (!Files.isDirectory(workingDirectory)) {
            throw IOException("$workingDirectory is not a valid directory")
        }

        this.commandArgs = extractArgs(startCommand)
        this.lines = parseLines(protocol)
    }

    private fun parseLines(file: Path): List<ProtocolLine> {
        val lines = ArrayList<ProtocolLine>()

        for (line in Files.readAllLines(file)) {
            for (parser in PARSERS) {
                val parserObj = parser(line)
                if (parserObj.matches()) {
                    lines.add(parserObj.parse())
                    break
                }
            }
        }

        return lines.toList()
    }

    private fun extractArgs(command: String): List<String> {
        val matcher = SPLIT_PATTERN.matcher(command)
        val argsList = arrayListOf<String>()

        while (matcher.find()) {
            argsList.add(matcher.group("arg"))
        }

        return argsList
    }

    fun createHandle(): TestProcessHandle {
        val process = ProcessBuilder(commandArgs)
            .directory(workingDirectory.toAbsolutePath().toFile())
            .redirectErrorStream(true)
            .start()
        return TestProcessHandle(process, this)
    }
}