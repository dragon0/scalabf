package io.github.dragon0.scalabf;
import io.github.dragon0.scalabf.parser._
import java.util.Scanner

object App {
    def main(args: Array[String]): Unit = {
        val in = new Scanner(System.in)
        print(": ")
        while(in.hasNext){
            val word = in.next
            val tokens = Parser.tokenize(word)
            println(tokens)
            print(": ")
        }
        println()
    }
}

