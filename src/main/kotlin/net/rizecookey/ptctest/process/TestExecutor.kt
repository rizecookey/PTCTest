package net.rizecookey.ptctest.process

import net.rizecookey.ptctest.test.Protocol

fun run(startCommand: String, protocol: Protocol): TestProcessHandle {
    val process = Runtime.getRuntime().exec(startCommand)
    val handle = TestProcessHandle(process, protocol)

    for (line in protocol.lines) {
        line.handle(handle)
    }

    return handle
}