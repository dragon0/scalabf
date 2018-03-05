package io.github.dragon0.scalabf.machine

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MemorySuite extends FunSuite {

    test("new Memory creates new cell") {
        val memory = new Memory
        assert(memory.head.value === 0)
    }

    test("advancing pointer left creates new cell") {
        val memory = new Memory
        memory.pointerLeft()
        assert(memory.head.value === 0)
        assert(memory.head.right.get.value === 0)
    }

    test("advancing pointer right creates new cell") {
        val memory = new Memory
        memory.pointerRight()
        assert(memory.head.value === 0)
        assert(memory.head.left.get.value === 0)
    }

    test("incrementing cell works") {
        val memory = new Memory
        memory.incrementCell()
        assert(memory.head.value === 1)
    }

    test("decrementing cell works") {
        val memory = new Memory
        memory.decrementCell()
        assert(memory.head.value === -1)
    }

}

