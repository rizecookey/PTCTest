package net.rizecookey.ptctest.test.parser

import java.util.regex.Pattern

val PARSERS
    get() = arrayOf(
        { line: String -> InputLineParser(line) },
        { line: String -> CommentLineParser(line) },
        { line: String -> OutputLineParser(line) }
    ).toList()

val SPLIT_PATTERN = Pattern.compile("(?<arg>(\"(.*)\")|(\\S+))")