package net.rizecookey.ptctest.test.line

import net.rizecookey.ptctest.process.TestProcessHandle
import net.rizecookey.ptctest.test.result.LineResult
import net.rizecookey.ptctest.test.result.SingleResult

abstract class OutputLine(val expectedOutput: String) : ProtocolLine {
    override fun handle(executor: TestProcessHandle) {
        val receivedLine = executor.input.readLine()

        if (receivedLine == null) {
            executor.addResult(SingleResult.PREMATURE_END)
            executor.stop()
            return
        }

        if (!matches(receivedLine)) {
            executor.addResult(LineResult.fail(expectedOutput, receivedLine, LineResult.Type.OUTPUT))
        } else {
            executor.addResult(LineResult.success(receivedLine, LineResult.Type.OUTPUT))
        }
    }

    protected abstract fun matches(receivedLine: String): Boolean
}