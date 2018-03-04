package io.github.dragon0.scalabf.parser
import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class LibrarySuite extends FunSuite {

    test("parser interprets <") {
        Parser.tokenize('<') match {
            case Some(LeftShift) => succeed
            case _ => fail
        }
    }

    test("parser interprets >") {
        Parser.tokenize('>') match {
            case Some(RightShift) => succeed
            case _ => fail
        }
    }

    test("parser interprets [") {
        Parser.tokenize('[') match {
            case Some(LeftBracket) => succeed
            case _ => fail
        }
    }

    test("parser interprets ]") {
        Parser.tokenize(']') match {
            case Some(RightBracket) => succeed
            case _ => fail
        }
    }

    test("parser interprets +") {
        Parser.tokenize('+') match {
            case Some(Plus) => succeed
            case _ => fail
        }
    }

    test("parser interprets -") {
        Parser.tokenize('-') match {
            case Some(Minus) => succeed
            case _ => fail
        }
    }

    test("parser interprets .") {
        Parser.tokenize('.') match {
            case Some(Dot) => succeed
            case _ => fail
        }
    }

    test("parser interprets ,") {
        Parser.tokenize(',') match {
            case Some(Comma) => succeed
            case _ => fail
        }
    }

    test("parser interprets unrecognized =") {
        Parser.tokenize('=') match {
            case None => succeed
            case _ => fail
        }
    }

    test("parser interprets string <<") {
        assert(Parser.tokenize("<<") === Array(LeftShift, LeftShift))
    }

    test("parser interprets string <>[]+-.,") {
        assert(Parser.tokenize("<>[]+-.,") === Array(
            LeftShift,
            RightShift,
            LeftBracket,
            RightBracket,
            Plus,
            Minus,
            Dot,
            Comma
        ))
    }

    test("parser interprets string <<<<<a>>>>>") {
        assert(Parser.tokenize("<<<<<a>>>>>") === Array(
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
        assert(Parser.tokenize("< <") === Array(LeftShift, LeftShift))
    }

}

