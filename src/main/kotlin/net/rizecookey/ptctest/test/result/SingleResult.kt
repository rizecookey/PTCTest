package net.rizecookey.ptctest.test.result

open class SingleResult(val success: Boolean) {
    companion object Preset {
        val PREMATURE_END = SingleResult(false)
    }
}