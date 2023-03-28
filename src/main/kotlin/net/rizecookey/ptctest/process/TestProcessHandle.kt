package net.rizecookey.ptctest.process

import net.rizecookey.ptctest.test.Protocol
import net.rizecookey.ptctest.test.TestResult
import net.rizecookey.ptctest.test.line.LineCheckResult
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class TestProcessHandle(val process: Process, val protocol: Protocol) {
    val output: OutputStreamWriter = OutputStreamWriter(process.outputStream)
    val input: InputStreamReader = InputStreamReader(process.inputStream)

    var result: TestResult? = null
        private set
    var state: State = State.INITIALIZED
        private set

    enum class State {
        INITIALIZED, RUNNING, ENDED
    }

    fun hasEnded(): Boolean {
        return state == State.ENDED
    }

    fun start() {
        this.state = State.RUNNING

        val results = arrayListOf<LineCheckResult>()
        for (line in protocol.lines) {
            results.add(line.handle(this))
        }

        this.result = TestResult(results)
        this.state = State.ENDED
    }
}