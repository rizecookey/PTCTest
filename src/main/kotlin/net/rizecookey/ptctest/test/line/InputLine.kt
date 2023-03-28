package net.rizecookey.ptctest.test.line

import net.rizecookey.ptctest.process.TestProcessHandle

class InputLine(val input: String) : ProtocolLine {
    override fun handle(executor: TestProcessHandle): LineCheckResult {
        executor.output.write(input + System.lineSeparator())

        return LineCheckResult("> $input", LineCheckResult.Type.INPUT)
    }
}