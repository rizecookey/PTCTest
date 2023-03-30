package net.rizecookey.ptctest.test.line

import net.rizecookey.ptctest.process.TestProcessHandle
import net.rizecookey.ptctest.test.result.LineResult

class CommentLine(val comment: String) : ProtocolLine {
    override fun handle(executor: TestProcessHandle) {
        executor.addResult(LineResult.success(comment, LineResult.Type.COMMENT))
    }
}