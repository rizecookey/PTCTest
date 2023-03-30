package net.rizecookey.ptctest.test.parser

import net.rizecookey.ptctest.test.line.RegexMatchedLine
import java.util.regex.Pattern

class RegexMatchedLineParser(line: String) : RegexParser<RegexMatchedLine>(line,
    Pattern.compile("^<r(?<regex>.*)$")) {
    override fun parse(): RegexMatchedLine {
        if (!matches()) {
            throw IllegalStateException("Matcher didn't match")
        }

        return RegexMatchedLine(Pattern.compile(matcher.group("regex")))
    }
}