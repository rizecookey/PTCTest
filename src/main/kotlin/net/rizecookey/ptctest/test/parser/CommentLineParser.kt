package net.rizecookey.ptctest.test.parser

import net.rizecookey.ptctest.test.line.CommentLine
import java.util.regex.Pattern

class CommentLineParser(line: String) : RegexParser<CommentLine>(line, Pattern.compile("^ *# ?(?<comment>.*)")) {
    override fun parse(): CommentLine {
        if (!matches()) {
            throw IllegalStateException("Matcher didn't match")
        }

        return CommentLine(matcher.group("comment"))
    }
}