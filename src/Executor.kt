import java.util.*

class Executor(private val axiom: String, private val rules: List<Rule>) {
    fun run(iterations: Int): String {
        var current = axiom
        for (i in 0 until iterations) {
            var transformed = ""
            for (char in current) {
                val applicableRules = rules.filter { it.applies(char) }
                applicableRules.forEach { transformed += it.replacement }
                if (transformed.isBlank()) transformed += char
                current = transformed
            }
        }
        return current
    }
}