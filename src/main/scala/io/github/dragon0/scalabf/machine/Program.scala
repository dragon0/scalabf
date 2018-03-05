package io.github.dragon0.scalabf.machine
import scala.annotation.tailrec

class Program(val commands: Seq[Command]) {
    require(commands.size != 0)
    var currentCommand: Command = commands(0)
    var currentIndex = 0

    def advance(): Option[Command] = {
        currentIndex = currentIndex + 1
        if(currentIndex < commands.size) {
            currentCommand = commands(currentIndex)
            Some(currentCommand)
        }
        else {
            None
        }
    }

    def backToMatching(nesting:Int): Option[Command] = {
        @tailrec
        def rec(n:Int): Option[Command] = {
            if(n < 0) {
                None
            }
            else commands(n) match {
                case JumpZero(nest) if(nest == nesting) => {
                    currentIndex = n
                    currentCommand = commands(n)
                    Some(currentCommand)
                }
                case _ => rec(n-1)
            }
        }
        rec(currentIndex)
    }

    def forwardToMatching(nesting:Int): Option[Command] = {
        @tailrec
        def rec(n:Int): Option[Command] = {
            if(n >= commands.size) {
                None
            }
            else commands(n) match {
                case JumpNonZero(nest) if(nest == nesting) => {
                    currentIndex = n
                    currentCommand = commands(n)
                    Some(currentCommand)
                }
                case _ => rec(n+1)
            }
        }
        rec(currentIndex)
    }

}

