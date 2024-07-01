class TranslatorService(translator: Translator) {
  private val data = new TranslatorData
  private val charMap = data.englishToMorse

  def translateCharacter(char: Char): String = {
    validateInput(char.toString)
    val translation = charMap.get(char.toLower) match {
      case Some(morse) => morse
      case None => throw new IllegalArgumentException(s"Unsupported character: $char")
    }
    s"$char -> $translation"
  }

  def translateString(input: String): String = {
    validateInput(input)
    val translation: String = translator.stringToMorse(input.toCharArray)
    s"$input -> $translation"
  }

  private def validateInput(input: String): Unit = {
    val unsupportedChars = input.filter(ch => !charMap.contains(ch.toLower))
    if (unsupportedChars.nonEmpty) {
      throw new IllegalArgumentException(s"Unsupported characters found: ${unsupportedChars.mkString(", ")}")
    }
  }
}
