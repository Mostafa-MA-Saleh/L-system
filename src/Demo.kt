import java.io.File

object Demo {
    @JvmStatic
    fun main(args: Array<String>) {
        if (args.isEmpty()) {
            println("Usage: l-systems.jar FILE_PATH")
            return
        }
        val program = File(args[0]).readText()
        val tokens = Lexer(program).tokenize()
        val parser = Parser(tokens).parse()
        val output = Executor(parser.axiom, parser.rules).run(parser.iterations)
        println(output)
    }
}