package net.rizecookey.ptctest.test.line

class LineCheckResult(val line: String, val type: Type) {
    fun isSuccessful(): Boolean {
        return type != Type.MISMATCHING_OUTPUT
    }

    enum class Type {
        MATCHING_OUTPUT, MISMATCHING_OUTPUT, INPUT, COMMENT
    }
}