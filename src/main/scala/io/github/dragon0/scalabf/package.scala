package io.github.dragon0
import scala.annotation.tailrec
import scalabf.parser._
import scalabf.machine._

package object scalabf {
    def parseList2ProgramList(tokens:Seq[Token]): Seq[Command] = {
        @tailrec
        def rec(tokens:Seq[Token], commands:Seq[Command]): Seq[Command] = {
            if(tokens.isEmpty){
                commands
            }
            else {
                val tok = tokens.head
                val com = tok match {
                    case LeftShift => PointerLeft
                    case RightShift => PointerRight
                    case LeftBracket(nesting) => JumpZero(nesting)
                    case RightBracket(nesting) => JumpNonZero(nesting)
                    case Plus => IncrementCell
                    case Minus => DecrementCell
                    case Dot => OutputCell
                    case Comma => ReadCell
                }
                rec(tokens.tail, commands :+ com)
            }
        }
        rec(tokens, Array[Command]())
    }
}

