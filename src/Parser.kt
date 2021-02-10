import java.util.*

class Parser(private val tokens: List<Token>) {
    private var position = 0
    fun parse(): Result {
        val rules: MutableList<Rule> = ArrayList()
        var axiom: String? = null
        var iterations: Int? = null
        while (tokens.size > position) {
            while (true) {
                if (tokens[position].type !== Token.Type.LINE) {
                    break
                } else {
                    position++
                }
            }
            when {
                tokens[position].type == Token.Type.AXIOM
                        && tokens.getOrNull(position + 1)?.type == Token.Type.EQUALS
                        && tokens.getOrNull(position + 2)?.type == Token.Type.STRING -> {
                    axiom = tokens[position + 2].text
                    position += 3
                }
                tokens[position].type == Token.Type.ITERATIONS
                        && tokens.getOrNull(position + 1)?.type == Token.Type.EQUALS
                        && tokens.getOrNull(position + 2)?.type == Token.Type.NUMBER -> {
                    iterations = tokens[position + 2].text.toInt()
                    position += 3
                }
                tokens.getOrNull(position)?.type == Token.Type.RULE
                        && tokens.getOrNull(position + 1)?.type == Token.Type.EQUALS
                        && tokens.getOrNull(position + 2)?.type == Token.Type.STRING
                        && tokens.getOrNull(position + 3)?.type == Token.Type.ARROW
                        && tokens.getOrNull(position + 4)?.type == Token.Type.STRING -> {
                    val rule = Rule(tokens[position + 2].text, tokens[position + 4].text)
                    rules.add(rule)
                    position += 5
                }
                tokens.getOrNull(position)?.type === Token.Type.EOF -> break
                else -> throw RuntimeException("Syntax Error: ${tokens[position + 1]}")
            }
        }
        return Result(
                axiom ?: throw RuntimeException("Syntax Error: Missing Axiom"),
                rules,
                iterations ?: throw RuntimeException("Syntax Error: Missing Iterations")
        )
    }

    class Result(val axiom: String, val rules: List<Rule>, val iterations: Int)
}