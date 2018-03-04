package io.github.dragon0.scalabf.parser

abstract class Token;

case object LeftShift extends Token
case object RightShift extends Token
case class LeftBracket(nesting:Int) extends Token
case class RightBracket(nesting:Int) extends Token
case object Plus extends Token
case object Minus extends Token
case object Dot extends Token
case object Comma extends Token

