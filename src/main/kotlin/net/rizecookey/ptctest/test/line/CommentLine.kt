package net.rizecookey.ptctest.test.line

import net.rizecookey.ptctest.process.TestProcessHandle

class CommentLine(val comment: String) : ProtocolLine {
    override fun handle(executor: TestProcessHandle): LineCheckResult {
        return LineCheckResult("# $comment", LineCheckResult.Type.COMMENT)
    }
}