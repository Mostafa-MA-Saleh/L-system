import java.util.*


class Lexer(val source: String) {

    private enum class State {
        DEFAULT, STRING, COMMENT, NUMBER
    }

    fun tokenize() = arrayListOf<Token>().apply {
        var token = ""
        var state = State.DEFAULT
        var lines = 0

        for (char in source) {
            if (char == ' ') continue
            if (char == '\n') lines++
            when (state) {
                State.DEFAULT -> if (char in Token.Type.values().map { it.value }) {
                    val tokenType = Token.Type.from(char)
                    state = when (tokenType) {
                        Token.Type.STRING -> State.STRING
                        Token.Type.COMMENT -> State.COMMENT
                        Token.Type.NUMBER -> State.NUMBER
                        else -> {
                            add(Token(char.toString(), tokenType, lines))
                            State.DEFAULT
                        }
                    }
                }
                State.NUMBER -> if (char == '\'') {
                    add(Token(token, Token.Type.NUMBER, lines))
                    token = ""
                    state = State.DEFAULT
                } else {
                    token += char
                }
                State.STRING -> if (char == '"') {
                    add(Token(token, Token.Type.STRING, lines))
                    token = ""
                    state = State.DEFAULT
                } else {
                    token += char
                }
                State.COMMENT -> if (char == '\n') {
                    state = State.DEFAULT
                }
            }
        }
    }
}