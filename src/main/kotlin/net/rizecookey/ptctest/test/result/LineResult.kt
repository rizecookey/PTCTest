package net.rizecookey.ptctest.test.result

class LineResult : SingleResult {
    val expectation: String
    val actual: String
    val type: Type

    private constructor(expectation: String, actual: String, type: Type) : super(false) {
        this.expectation = expectation
        this.actual = actual
        this.type = type
    }
    private constructor(line: String, type: Type) : super(true) {
        this.expectation = line
        this.actual = this.expectation
        this.type = type
    }

    companion object Factory {
        @JvmStatic
        fun success(line: String, type: Type): LineResult {
            return LineResult(line, type)
        }

        @JvmStatic
        fun fail(expectation: String, actual: String, type: Type): LineResult {
            return LineResult(expectation, actual, type)
        }
    }

    enum class Type {
        OUTPUT, INPUT, COMMENT
    }
}