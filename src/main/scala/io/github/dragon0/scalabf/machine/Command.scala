package io.github.dragon0.scalabf.machine

abstract class Command

case object PointerLeft extends Command
case object PointerRight extends Command
case object IncrementCell extends Command
case object DecrementCell extends Command
case object OutputCell extends Command
case object ReadCell extends Command
case class JumpZero(nesting:Int) extends Command
case class JumpNonZero(nesting:Int) extends Command

