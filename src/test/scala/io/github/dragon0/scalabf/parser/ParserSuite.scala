package io.github.dragon0.scalabf.parser
import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class LibrarySuite extends FunSuite {

  test("parser interprets <") {
    def parser = new Parser()
    parser.tokenize('<') match {
        case Some(LeftShift) => succeed
        case _ => fail
    }
  }

  test("parser interprets >") {
    def parser = new Parser()
    parser.tokenize('>') match {
        case Some(RightShift) => succeed
        case _ => fail
    }
  }

  test("parser interprets [") {
    def parser = new Parser()
    parser.tokenize('[') match {
        case Some(LeftBracket) => succeed
        case _ => fail
    }
  }

  test("parser interprets ]") {
    def parser = new Parser()
    parser.tokenize(']') match {
        case Some(RightBracket) => succeed
        case _ => fail
    }
  }

  test("parser interprets +") {
    def parser = new Parser()
    parser.tokenize('+') match {
        case Some(Plus) => succeed
        case _ => fail
    }
  }

  test("parser interprets -") {
    def parser = new Parser()
    parser.tokenize('-') match {
        case Some(Minus) => succeed
        case _ => fail
    }
  }

  test("parser interprets .") {
    def parser = new Parser()
    parser.tokenize('.') match {
        case Some(Dot) => succeed
        case _ => fail
    }
  }

  test("parser interprets ,") {
    def parser = new Parser()
    parser.tokenize(',') match {
        case Some(Comma) => succeed
        case _ => fail
    }
  }

  test("parser interprets unrecognized =") {
    def parser = new Parser()
    parser.tokenize('=') match {
        case None => succeed
        case _ => fail
    }
  }

  test("parser interprets string <<") {
    def parser = new Parser()
    parser.tokenize("<<") match {
        case Right(seq) => assert(seq === Array(LeftShift, LeftShift))
        case _ => fail
    }
  }

  test("parser interprets string <>[]+-.,") {
    def parser = new Parser()
    parser.tokenize("<>[]+-.,") match {
        case Right(seq) => assert(seq === Array(
                LeftShift,
                RightShift,
                LeftBracket,
                RightBracket,
                Plus,
                Minus,
                Dot,
                Comma
            ))
        case _ => fail
    }
  }

  test("parser interprets string <<<<<a>>>>>") {
    def parser = new Parser()
    parser.tokenize("<<<<<a>>>>>") match {
        case Left(ut) => assert(ut === UnrecognizedToken('a', 6))
        case _ => fail
    }
  }

  test("parser ignores whitespace in string < <") {
    def parser = new Parser()
    parser.tokenize("< <") match {
        case Right(seq) => assert(seq === Array(LeftShift, LeftShift))
        case _ => fail
    }
  }

}

