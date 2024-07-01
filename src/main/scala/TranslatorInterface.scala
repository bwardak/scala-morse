import scala.io.StdIn.readLine

class TranslatorInterface {
  val data = new TranslatorData
  def openingMessage(): Unit = {
    println("Morse Code Translator")
  }

  def inputToTranslate(): Unit = {
    println("\n- Enter something in english or morse to translate.\n- Your sentence should only be A-Z a-z 0-9\n\nInput: ")
    val input = readLine()
    val translator = new Translator(input, data.morseToEnglish, data.englishToMorse)
    translator.checkIfSentenceOrMorse()
    println("\nDo you want to translate something else? (y/n)")
    val choice = readLine().toLowerCase()
    if (choice.equals("y")) {
      inputToTranslate()
    } else {
      println("Bye!")
    }

  }
}

