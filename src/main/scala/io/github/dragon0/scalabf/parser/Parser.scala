package io.github.dragon0.scalabf.parser
import scala.annotation.tailrec

class Parser {
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

    def tokenize(s: String): Either[UnrecognizedToken, Seq[Token]] = {
        @tailrec
        def rec(i: Int, seq: Seq[Token]): Either[UnrecognizedToken, Seq[Token]] = {
            if(i >= s.length){
                Right(seq)
            }
            else{
                val c = s(i)
                if(Character.isWhitespace(c)) rec(i+1, seq)
                else tokenize(c) match {
                    case Some(tok) => rec(i+1, seq :+ tok)
                    case None => Left(UnrecognizedToken(c, i+1))
                }
            }
        }
        rec(0, Array[Token]())
    }
}

