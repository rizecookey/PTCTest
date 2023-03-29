package net.rizecookey.ptctest.test.line

import net.rizecookey.ptctest.process.TestProcessHandle

class InputLine(val input: String) : ProtocolLine {
    override fun handle(executor: TestProcessHandle) {
        executor.output.write(input + System.lineSeparator())

        executor.log(LineCheckResult("> $input", LineCheckResult.Type.INPUT))
    }
}