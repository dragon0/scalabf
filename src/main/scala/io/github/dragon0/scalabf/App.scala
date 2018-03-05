package io.github.dragon0.scalabf;
import scala.annotation.tailrec
import io.github.dragon0.scalabf.parser._
import io.github.dragon0.scalabf.machine._
import java.util.Scanner
import java.io.File

object App {
    def main(args: Array[String]): Unit = {
        if(args.size != 1){
            System.err.println("usage: scalabf script")
            System.exit(1)
        }

        val file = new File(args(0))
        if(!file.canRead()){
            System.err.println("Could not open " + args(0))
            System.exit(2)
        }

        val programText = new Scanner(file).useDelimiter("\\Z").next();
        val parser = new Parser
        val tokens = parser.tokenize(programText)
        val commands = parseList2ProgramList(tokens)
        val program = new Program(commands)
        val cpu = new Cpu(program, System.in, System.out)

        @tailrec
        def run(): Unit = {
            cpu.step() match {
                case None => ()
                case _ => run()
            }
        }
        run()
    }

    def repl(): Unit = {
        val in = new Scanner(System.in)
        val parser = new Parser()
        print(": ")
        while(in.hasNext){
            val word = in.next
            val tokens = parser.tokenize(word)
            println(tokens)
            print(": ")
        }
        println()
    }
}

