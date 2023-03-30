package net.rizecookey.ptctest.test.line

import net.rizecookey.ptctest.process.TestProcessHandle
import net.rizecookey.ptctest.test.result.LineResult
import net.rizecookey.ptctest.test.result.SingleResult
import java.io.IOException

class InputLine(val input: String) : ProtocolLine {
    override fun handle(executor: TestProcessHandle) {
        val output = executor.output
        try {
            output.write(input + System.lineSeparator())
            output.flush()
        } catch (e: IOException) {
            executor.addResult(SingleResult.PREMATURE_END)
            executor.stop()
            return
        }

        executor.addResult(LineResult.success(input, LineResult.Type.INPUT))
    }
}