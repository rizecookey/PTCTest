package net.rizecookey.ptctest.process

import net.rizecookey.ptctest.test.TestResult
import net.rizecookey.ptctest.test.TestSetup
import net.rizecookey.ptctest.test.line.LineCheckResult
import org.fusesource.jansi.Ansi.Color
import org.fusesource.jansi.Ansi.ansi
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class TestProcessHandle(val process: Process, val protocol: TestSetup) {
    val output: OutputStreamWriter = OutputStreamWriter(process.outputStream)
    val input: BufferedReader = BufferedReader(InputStreamReader(process.inputStream))

    private val lines: MutableList<LineCheckResult> = arrayListOf()
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

        for (line in protocol.lines) {
            if (this.hasEnded()) break
            line.handle(this)
        }

        this.collectResults()
    }

    fun stop() {
        this.state = State.ENDED
    }

    private fun collectResults() {
        this.result = TestResult(lines)
        this.state = State.ENDED
    }

    fun log(result: LineCheckResult) {
        this.lines.add(result)

        val color = when (result.type) {
            LineCheckResult.Type.MATCHING_OUTPUT -> Color.WHITE
            LineCheckResult.Type.MISMATCHING_OUTPUT -> Color.RED
            LineCheckResult.Type.INPUT -> Color.GREEN
            LineCheckResult.Type.COMMENT -> Color.BLUE
        }

        println(ansi().fgBright(color).a(result.line).reset())
    }
}