package io.github.dragon0.scalabf.machine
import java.io.{InputStream, PrintStream}

class Cpu(val program:Program, inp:InputStream, out:PrintStream) {
    val memory = new Memory

    private def outputCell(): Unit = {
        out.print(memory.head.repr.toChar)
    }

    private def inputCell(): Unit = {
        val c = inp.read()
        if(c >= 0){
            memory.head.value = (c & 0xFF).toByte
        }
    }

    def step(): Option[Command] = {
        program.currentCommand match {
            case PointerLeft => memory.pointerLeft(); program.advance
            case PointerRight => memory.pointerRight(); program.advance
            case IncrementCell => memory.incrementCell(); program.advance
            case DecrementCell => memory.decrementCell(); program.advance
            case OutputCell => outputCell(); program.advance
            case ReadCell => inputCell(); program.advance
            case JumpZero(nesting:Int) => {
                if(memory.head.value == 0) program.forwardToMatching(nesting)
                else program.advance
            }
            case JumpNonZero(nesting:Int) => {
                if(memory.head.value != 0) program.backToMatching(nesting)
                else program.advance
            }
        }
    }

}

