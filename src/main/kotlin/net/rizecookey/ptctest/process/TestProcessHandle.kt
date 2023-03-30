package net.rizecookey.ptctest.process

import net.rizecookey.ptctest.test.TestResult
import net.rizecookey.ptctest.test.TestSetup
import net.rizecookey.ptctest.test.result.SingleResult
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class TestProcessHandle(val process: Process, val protocol: TestSetup) {
    val output: OutputStreamWriter = OutputStreamWriter(process.outputStream)
    val input: BufferedReader = BufferedReader(InputStreamReader(process.inputStream))

    private val results: MutableList<SingleResult> = arrayListOf()
    private val resultCallbacks: MutableList<(SingleResult) -> Unit> = arrayListOf()
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

    fun onResult(callback: (SingleResult) -> Unit) {
        resultCallbacks.add(callback)
    }

    fun start() {
        this.state = State.RUNNING

        for (line in protocol.lines) {
            if (this.hasEnded()) break
            line.handle(this)
        }

        this.collectResults()
    }

    fun stop() {
        this.state = State.ENDED
        this.output.close()
        this.input.close()
    }

    private fun collectResults() {
        this.result = TestResult(results)
        this.state = State.ENDED
    }

    fun addResult(result: SingleResult) {
        this.results.add(result)

        this.resultCallbacks.forEach { it(result) }
    }
}