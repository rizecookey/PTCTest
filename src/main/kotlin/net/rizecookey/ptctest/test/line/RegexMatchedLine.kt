package net.rizecookey.ptctest.test.line

import java.util.regex.Pattern

class RegexMatchedLine(val pattern: Pattern) : OutputLine(pattern.pattern()) {
    override fun matches(receivedLine: String): Boolean {
        return pattern.matcher(receivedLine).matches()
    }
}