package net.rizecookey.ptctest.test

import net.rizecookey.ptctest.Output
import net.rizecookey.ptctest.process.TestExecutor

interface ProtocolLine {
    fun handle(executor: TestExecutor): Output
}