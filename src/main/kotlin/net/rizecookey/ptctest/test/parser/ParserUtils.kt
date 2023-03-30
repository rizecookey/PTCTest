package net.rizecookey.ptctest.test.parser

import net.rizecookey.ptctest.test.line.ProtocolLine
import java.util.regex.Pattern

val PARSERS
    get() = arrayOf<(String) -> RegexParser<out ProtocolLine>>(
        { InputLineParser(it) },
        { CommentLineParser(it) },
        { RegexMatchedLineParser(it) },
        { LiteralLineParser(it) }
    ).toList()

val SPLIT_PATTERN: Pattern = Pattern.compile("(?<arg>(\"(.*)\")|(\\S+))")