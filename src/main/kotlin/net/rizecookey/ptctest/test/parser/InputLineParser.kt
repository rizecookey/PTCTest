package net.rizecookey.ptctest.test.parser

import net.rizecookey.ptctest.test.line.InputLine
import java.util.regex.Pattern

class InputLineParser(line: String) : RegexParser<InputLine>(line, Pattern.compile("^> (?<input>.*)$")) {
    override fun parse(): InputLine {
        if (!matches()) {
            throw IllegalStateException("Matcher didn't match")
        }

        return InputLine(matcher.group("input"))
    }
}