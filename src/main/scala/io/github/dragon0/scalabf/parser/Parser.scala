package io.github.dragon0.scalabf.parser
import scala.annotation.tailrec

class Parser {
    private var nesting = 0
    def tokenize(c: Char): Option[Token] = {
        c match {
            case '<' => Some(LeftShift)
            case '>' => Some(RightShift)
            case '[' => {
                nesting = nesting + 1
                Some(LeftBracket(nesting))
            }
            case ']' => {
                val ret = Some(RightBracket(nesting))
                nesting = nesting - 1
                ret
            }
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

