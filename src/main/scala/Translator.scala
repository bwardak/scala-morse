class Translator(sentenceToTranslate: String, morseMap: Map[String, Char], charMap: Map[Char, String]) {
  var sentenceCharArray: Array[Char] = Array()
  var morseArray: Array[String] = Array()

  def validateValidInput(): Boolean = {
    val validationCharSet = sentenceToTranslate.toLowerCase.toSet
    validationCharSet.forall { c =>
      val isValid = charMap.contains(c)
      isValid
    }
  }

  def checkIfSentenceOrMorse(): Unit = {
    if (!validateValidInput()) {
      println("\nInvalid input! There is no morse code for a character you inputted!")
    } else {
      if (!sentenceToTranslate.charAt(0).equals('.') && !sentenceToTranslate.charAt(0).equals('-')) {
        sentenceCharArray = sentenceToTranslate.toLowerCase().toCharArray
        println(stringToMorse(sentenceCharArray))
      } else {
        morseArray = sentenceToTranslate.split(" ")
        println(morseToString(morseArray))
      }
    }

  }

  def stringToMorse(sentence: Array[Char]): String = {
    sentence.flatMap(charMap.get).mkString(" ")
  }

  def morseToString(morse: Array[String]): String = {
    val translation = morse.flatMap(morseMap.get)
    var translationWithCorrectCases = translation(0).toString.toUpperCase + translation(1).toString
    for (i <- Range(2, translation.length)) {
      if (translation(i - 2).equals('.')) {
        translationWithCorrectCases += translation(i).toUpper
      } else {
        translationWithCorrectCases += translation(i)
      }

    }
    translationWithCorrectCases
  }
}

