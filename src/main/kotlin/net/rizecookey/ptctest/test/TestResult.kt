package net.rizecookey.ptctest.test

import net.rizecookey.ptctest.test.line.LineCheckResult

data class TestResult(val lineResults: List<LineCheckResult>) {
    fun isSuccessful(): Boolean {
        return lineResults.all { result -> result.isSuccessful() }
    }
}