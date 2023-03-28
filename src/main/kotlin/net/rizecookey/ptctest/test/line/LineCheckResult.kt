package net.rizecookey.ptctest.test.line

class LineCheckResult {
    val lines: List<Pair<String, Type>>

    constructor(line: String, type: Type) {
        this.lines = listOf(Pair(line, type))
    }

    constructor(line: Pair<String, Type>, vararg lines: Pair<String, Type>) {
        val total = arrayListOf(line)
        total.addAll(lines)
        this.lines = total.toList()
    }

    fun isSuccessful(): Boolean {
        return lines.all { line -> line.second != Type.MISMATCHING_OUTPUT }
    }

    enum class Type {
        MATCHING_OUTPUT, MISMATCHING_OUTPUT, INPUT, COMMENT
    }
}