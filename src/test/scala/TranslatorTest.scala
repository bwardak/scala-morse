import org.scalatest.BeforeAndAfter
import org.scalatest.funspec.AnyFunSpec

class TranslatorTest extends AnyFunSpec with BeforeAndAfter{
  var data: TranslatorData = _
  var translator: Translator = _

  before {
    data = new TranslatorData
  }

  describe("Translator") {
    it("should convert string to Morse code") {
      val input = "hello"
      translator = new Translator(input, data.morseToEnglish, data.englishToMorse)
      assert(translator.stringToMorse(input.toCharArray) == ".... . .-.. .-.. ---")
    }

    it("should convert Morse code to string") {
      val input = ".... . .-.. .-.. ---"
      translator = new Translator(input, data.morseToEnglish, data.englishToMorse)
      assert(translator.morseToString(input.split(" ")) == "Hello")
    }

    it("should detect English sentence and convert to Morse code") {
      val input = "hello"
      translator = new Translator(input, data.morseToEnglish, data.englishToMorse)
      translator.checkIfSentenceOrMorse()
    }

    it("should detect Morse code and convert to English sentence") {
      val input = ".... . .-.. .-.. ---"
      translator = new Translator(input, data.morseToEnglish, data.englishToMorse)
      translator.checkIfSentenceOrMorse()
    }
  }
}
