package net.rizecookey.ptctest.test.parser

import java.util.regex.Matcher
import java.util.regex.Pattern

abstract class RegexParser<T>(line: String, pattern: Pattern) : LineParser<T>(line) {
    val matcher: Matcher
    init {
        matcher = pattern.matcher(line)
    }

    override fun matches(): Boolean {
        return matcher.matches()
    }
}