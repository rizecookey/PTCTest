package net.rizecookey.ptctest.test.parser

abstract class LineParser<T>(val line: String) {
    abstract fun matches(): Boolean
    abstract fun parse(): T
}