package net.rizecookey.ptctest.test.line

import net.rizecookey.ptctest.process.TestExecutor

interface ProtocolLine {
    fun handle(executor: TestExecutor): LineCheckOutput
}