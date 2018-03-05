package io.github.dragon0.scalabf.machine

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ProgramSuite extends FunSuite {

    test("Program requires commands") {
        assertThrows[IllegalArgumentException] {
            new Program(Array[Command]())
        }
    }

    test("Creating program with single command") {
        val program = new Program(Array(IncrementCell))
        assert(program.currentCommand === IncrementCell)
    }

    test("Creating program with two commands") {
        val program = new Program(Array(IncrementCell, DecrementCell))
        assert(program.currentCommand === IncrementCell)
        assert(program.advance() === Some(DecrementCell))
        assert(program.currentCommand === DecrementCell)
    }

    test("Advancing off the end of the command sequence") {
        val program = new Program(Array(IncrementCell, DecrementCell))
        assert(program.currentCommand === IncrementCell)
        assert(program.advance() === Some(DecrementCell))
        assert(program.currentCommand === DecrementCell)
        assert(program.advance() === None)
    }

    test("Move forward in correctly nested jumps") {
        val program = new Program(Array(JumpZero(1), JumpNonZero(1)))
        assert(program.forwardToMatching(1) === Some(JumpNonZero(1)))
        assert(program.currentIndex === 1)
    }

    test("Move forward in correctly nested jumps with intermediate commands") {
        val program = new Program(Array(JumpZero(1), ReadCell, JumpNonZero(1)))
        assert(program.forwardToMatching(1) === Some(JumpNonZero(1)))
        assert(program.currentIndex === 2)
    }

    test("Move backward in correctly nested jumps") {
        val program = new Program(Array(JumpZero(1), JumpNonZero(1)))
        assert(program.advance() !== None)
        assert(program.backToMatching(1) === Some(JumpZero(1)))
        assert(program.currentIndex === 0)
    }

    test("Move backward in correctly nested jumps with intermediate commands") {
        val program = new Program(Array(JumpZero(1), ReadCell, JumpNonZero(1)))
        assert(program.advance() !== None)
        assert(program.advance() !== None)
        assert(program.backToMatching(1) === Some(JumpZero(1)))
        assert(program.currentIndex === 0)
    }

    test("Move forward in incorrectly nested jumps") {
        val program = new Program(Array(JumpZero(1), JumpZero(2), JumpNonZero(1)))
        assert(program.forwardToMatching(2) === None)
    }

    test("Move backward in incorrectly nested jumps") {
        val program = new Program(Array(JumpZero(1), JumpNonZero(2), JumpNonZero(1)))
        assert(program.advance() !== None)
        assert(program.backToMatching(2) === None)
    }

}

