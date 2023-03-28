package net.rizecookey.ptctest.test.line

import net.rizecookey.ptctest.process.TestProcessHandle

class OutputLine(val expectedOutput: String) : ProtocolLine {
    override fun handle(executor: TestProcessHandle): LineCheckResult {
        var receivedLine = ""

        var fullLineReceived = false
        while (!fullLineReceived) {
            val c = executor.input.read()
            if (c == -1) {
                return LineCheckResult("Early termination detected!", LineCheckResult.Type.MISMATCHING_OUTPUT)
            }

            receivedLine += c.toChar()

            fullLineReceived = receivedLine.endsWith(System.lineSeparator())
        }

        return if (receivedLine != expectedOutput) {
            LineCheckResult(
                Pair(receivedLine, LineCheckResult.Type.MATCHING_OUTPUT),
                Pair("Expected: $expectedOutput", LineCheckResult.Type.MISMATCHING_OUTPUT)
            )
        } else {
            LineCheckResult(receivedLine, LineCheckResult.Type.MATCHING_OUTPUT)
        }
    }
}