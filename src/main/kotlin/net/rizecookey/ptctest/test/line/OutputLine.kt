package net.rizecookey.ptctest.test.line

import net.rizecookey.ptctest.process.TestProcessHandle

class OutputLine(val expectedOutput: String) : ProtocolLine {
    override fun handle(executor: TestProcessHandle) {
        val receivedLine = executor.input.readLine()

        if (receivedLine == null) {
            executor.log(LineCheckResult("End of output stream reached! Did your program crash?",
                LineCheckResult.Type.MISMATCHING_OUTPUT))
            executor.stop()
            return
        }

        executor.log(LineCheckResult(receivedLine, LineCheckResult.Type.MATCHING_OUTPUT))

        if (receivedLine != expectedOutput) {
            executor.log(LineCheckResult("Expected: $expectedOutput", LineCheckResult.Type.MISMATCHING_OUTPUT))
        }
    }
}