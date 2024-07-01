import org.mockito.Mockito._
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.BeforeAndAfter

class TranslatorServiceTest extends AnyFunSpec with BeforeAndAfter {

  var mockTranslator: Translator = _
  var service: TranslatorService = _

  before {
    mockTranslator = mock(classOf[Translator])
    service = new TranslatorService(mockTranslator)
  }

  describe("TranslatorService") {

    it("should translate a character to Morse code") {
      val result = service.translateCharacter('a')
      assert(result == "a -> .-")
    }

    it("should translate a string to Morse code") {
      val input = "hello"
      when(mockTranslator.stringToMorse(input.toCharArray)).thenReturn(".... . .-.. .-.. ---")

      val result = service.translateString(input)
      assert(result == "hello -> .... . .-.. .-.. ---")
      verify(mockTranslator).stringToMorse(input.toCharArray)
    }

    it("should throw an exception for unsupported characters") {
      val exception = intercept[IllegalArgumentException] {
        service.translateCharacter('$')
      }
      assert(exception.getMessage == "Unsupported characters found: $")
    }

    it("should validate input and throw exception for unsupported characters in string") {
      val input = "hello£"
      val exception = intercept[IllegalArgumentException] {
        service.translateString(input)
      }
      assert(exception.getMessage == "Unsupported characters found: £")
    }
  }
}
