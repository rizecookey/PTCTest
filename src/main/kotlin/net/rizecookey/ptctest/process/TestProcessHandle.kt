package net.rizecookey.ptctest.process

import net.rizecookey.ptctest.test.Protocol
import java.io.InputStreamReader
import java.io.OutputStreamWriter

data class TestProcessHandle(val process: Process, val protocol: Protocol) {
    val output: OutputStreamWriter
    val input: InputStreamReader

    init {
        output = OutputStreamWriter(process.outputStream)
        input = InputStreamReader(process.inputStream)
    }
}