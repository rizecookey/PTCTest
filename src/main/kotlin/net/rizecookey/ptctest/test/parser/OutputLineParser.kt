package net.rizecookey.ptctest.test.parser

import net.rizecookey.ptctest.test.OutputLine
import java.util.regex.Pattern

class OutputLineParser(line: String) : RegexParser<OutputLine>(line, Pattern.compile("^(?<output>.*)$")) {
    override fun parse(): OutputLine {
        return OutputLine(matcher.group("output"))
    }
}