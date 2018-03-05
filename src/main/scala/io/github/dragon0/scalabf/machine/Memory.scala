package io.github.dragon0.scalabf.machine

class Memory {
    var head = new Cell

    def pointerLeft(): Unit = {
        head.left match {
            case Some(cell) => head = cell
            case None => {
                var cell = new Cell
                cell.right = Some(head)
                head.left = Some(cell)
                head = cell
            }
        }
    }

    def pointerRight(): Unit = {
        head.right match {
            case Some(cell) => head = cell
            case None => {
                var cell = new Cell
                cell.left = Some(head)
                head.right = Some(cell)
                head = cell
            }
        }
    }

    def incrementCell(): Unit = {
        head.value = (head.value + 1).toByte
    }

    def decrementCell(): Unit = {
        head.value = (head.value - 1).toByte
    }

}

