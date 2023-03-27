package net.rizecookey.ptctest.test.parser

import net.rizecookey.ptctest.test.line.CommentLine
import java.util.regex.Pattern

class CommentLineParser(line: String) : RegexParser<CommentLine>(line, Pattern.compile("^ *# ?(?<comment>.*)")) {
    override fun parse(): CommentLine {
        return CommentLine(matcher.group("comment"))
    }
}