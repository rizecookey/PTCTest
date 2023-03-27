package net.rizecookey.ptctest.test

import net.rizecookey.ptctest.test.parser.InputLineParser
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path

val PARSERS = arrayOf(
    { line: String -> InputLineParser(line) },


)

class Protocol(file: Path) {
    private val lines: List<ProtocolLine>

    init {
        if (!Files.isRegularFile(file)) {
            throw IOException("$file is not a readable text file")
        }

        this.lines = parseLines(file)
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

        return lines
    }
}