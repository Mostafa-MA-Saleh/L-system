class Token(val text: String, val type: Type) {
    enum class Type(val value: Char?) {
        STRING('\"'),
        EQUALS('='),
        AXIOM('A'),
        RULE('R'),
        ARROW('>'),
        COMMENT('#'),
        LINE('\n'),
        EOF(null),
        ITERATIONS('I'),
        NUMBER('\'');

        companion object {
            fun from(value: Char) = values().first { it.value == value }
        }
    }

    override fun toString(): String {
        return "Text: $text, type: ${type.name}"
    }
}
