package net.rizecookey.ptctest.process

import net.rizecookey.ptctest.test.Protocol

fun executeProtocolTest(startCommand: String, protocol: Protocol): TestProcessHandle {
    val process = Runtime.getRuntime().exec(startCommand)
    val handle = TestProcessHandle(process, protocol)

    handle.start()

    return handle
}