package net.rizecookey.ptctest.test.line

import net.rizecookey.ptctest.process.TestProcessHandle

class OutputLine(val expectedOutput: String) : ProtocolLine {
    override fun handle(executor: TestProcessHandle) {
        var receivedLine = ""

        var fullLineReceived = false
        while (!fullLineReceived) {
            val c = executor.input.read()
            if (c == -1) {
                executor.log(LineCheckResult(receivedLine, LineCheckResult.Type.MATCHING_OUTPUT))
                executor.log(
                    LineCheckResult("Early termination detected!", LineCheckResult.Type.MISMATCHING_OUTPUT)
                )
                return
            }

            receivedLine += c.toChar()

            fullLineReceived = receivedLine.endsWith(System.lineSeparator())
        }

        executor.log(LineCheckResult(receivedLine, LineCheckResult.Type.MATCHING_OUTPUT))

        if (receivedLine != expectedOutput) {
            executor.log(LineCheckResult("Expected: $expectedOutput", LineCheckResult.Type.MISMATCHING_OUTPUT))
        }
    }
}