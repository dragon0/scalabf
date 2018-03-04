package io.github.dragon0.scalabf.parser
import scala.annotation.tailrec

object Parser {
    def tokenize(c: Char): Option[Token] = {
        c match {
            case '<' => Some(LeftShift)
            case '>' => Some(RightShift)
            case '[' => Some(LeftBracket)
            case ']' => Some(RightBracket)
            case '+' => Some(Plus)
            case '-' => Some(Minus)
            case '.' => Some(Dot)
            case ',' => Some(Comma)
            case _ => None
        }
    }

    def tokenize(s: String): Seq[Token] = {
        @tailrec
        def rec(i: Int, seq: Seq[Token]): Seq[Token] = {
            if(i >= s.length){
                seq
            }
            else tokenize(s(i)) match {
                case Some(tok) => rec(i+1, seq :+ tok)
                case None => rec(i+1, seq)
            }
        }
        rec(0, Array[Token]())
    }
}

