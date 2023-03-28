package net.rizecookey.ptctest.test.line

import net.rizecookey.ptctest.process.TestProcessHandle

interface ProtocolLine {
    fun handle(executor: TestProcessHandle): LineCheckResult
}