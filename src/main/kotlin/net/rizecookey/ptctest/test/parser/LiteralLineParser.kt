package net.rizecookey.ptctest.test.parser

import net.rizecookey.ptctest.test.line.LiteralLine
import net.rizecookey.ptctest.test.line.OutputLine
import java.util.regex.Pattern

class LiteralLineParser(line: String) : RegexParser<OutputLine>(line, Pattern.compile("^(<l|)(?<output>.*)$")) {
    override fun parse(): OutputLine {
        if (!matches()) {
            throw IllegalStateException("Matcher didn't match")
        }

        return LiteralLine(matcher.group("output"))
    }
}