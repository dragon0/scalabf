package io.github.dragon0.scalabf.parser

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
}

