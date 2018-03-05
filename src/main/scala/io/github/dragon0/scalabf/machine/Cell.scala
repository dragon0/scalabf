package io.github.dragon0.scalabf.machine

class Cell {
    var value: Byte = 0

    var right: Option[Cell] = None
    var left: Option[Cell] = None

    def repr = value & 0xFF
}

