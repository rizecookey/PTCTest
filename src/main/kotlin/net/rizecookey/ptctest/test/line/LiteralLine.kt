package net.rizecookey.ptctest.test.line

class LiteralLine(expectedOutput: String) : OutputLine(expectedOutput) {
    override fun matches(receivedLine: String): Boolean {
        return expectedOutput == receivedLine
    }
}