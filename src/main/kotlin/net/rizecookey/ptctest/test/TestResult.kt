package net.rizecookey.ptctest.test

import net.rizecookey.ptctest.test.result.SingleResult

data class TestResult(val results: List<SingleResult>) {
    val success: Boolean
        get() = results.all { results -> results.success }
}