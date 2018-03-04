package io.github.dragon0.scalabf.parser
import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class LibrarySuite extends FunSuite {

    test("parser interprets <") {
        new Parser().tokenize('<') match {
            case Some(LeftShift) => succeed
            case _ => fail
        }
    }

    test("parser interprets >") {
        new Parser().tokenize('>') match {
            case Some(RightShift) => succeed
            case _ => fail
        }
    }

    test("parser interprets [") {
        new Parser().tokenize('[') match {
            case Some(LeftBracket(1)) => succeed
            case _ => fail
        }
    }

    test("parser interprets ]") {
        new Parser().tokenize(']') match {
            case Some(RightBracket(0)) => succeed
            case _ => fail
        }
    }

    test("parser interprets +") {
        new Parser().tokenize('+') match {
            case Some(Plus) => succeed
            case _ => fail
        }
    }

    test("parser interprets -") {
        new Parser().tokenize('-') match {
            case Some(Minus) => succeed
            case _ => fail
        }
    }

    test("parser interprets .") {
        new Parser().tokenize('.') match {
            case Some(Dot) => succeed
            case _ => fail
        }
    }

    test("parser interprets ,") {
        new Parser().tokenize(',') match {
            case Some(Comma) => succeed
            case _ => fail
        }
    }

    test("parser interprets unrecognized =") {
        new Parser().tokenize('=') match {
            case None => succeed
            case _ => fail
        }
    }

    test("parser interprets string <<") {
        assert(new Parser().tokenize("<<") === Array(LeftShift, LeftShift))
    }

    test("parser interprets string <>[]+-.,") {
        assert(new Parser().tokenize("<>[]+-.,") === Array(
            LeftShift,
            RightShift,
            LeftBracket(1),
            RightBracket(1),
            Plus,
            Minus,
            Dot,
            Comma
        ))
    }

    test("parser interprets string <<<<<a>>>>>") {
        assert(new Parser().tokenize("<<<<<a>>>>>") === Array(
            LeftShift,
            LeftShift,
            LeftShift,
            LeftShift,
            LeftShift,
            RightShift,
            RightShift,
            RightShift,
            RightShift,
            RightShift
        ))
    }

    test("parser ignores whitespace in string < <") {
        assert(new Parser().tokenize("< <") === Array(LeftShift, LeftShift))
    }

    test("parser recognizes nesting in [[]]") {
        assert(new Parser().tokenize("[[]]") === Array(
            LeftBracket(1),
            LeftBracket(2),
            RightBracket(2),
            RightBracket(1)
        ))
    }

}

