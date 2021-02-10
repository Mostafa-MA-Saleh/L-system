class Rule(val initialChar: String, val replacement: String) {
    fun applies(c: Char): Boolean {
        return c.toString() == initialChar
    }
}