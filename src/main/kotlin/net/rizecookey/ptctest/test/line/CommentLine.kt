package net.rizecookey.ptctest.test.line

import net.rizecookey.ptctest.process.TestProcessHandle

class CommentLine(val line: String) : ProtocolLine {
    override fun handle(executor: TestProcessHandle): LineCheckOutput {
        TODO("Not yet implemented")
    }
}