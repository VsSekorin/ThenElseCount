import org.apache.spark.rdd.RDD

/**
 * @author VsSekorin
 */
class Code(text: RDD[String]) {

  private val code: RDD[String] =
    text
      .flatMap(_ split "(?=//)")
      .filter(str => !Code.wordComment.exists(str.trim startsWith))
      .flatMap(_ split "\\s+")
      .flatMap(_ split "\\(")
      .flatMap(_ split "\\{")
      .flatMap(_ split "\\}")
      .map(_ trim)
      .filter(_.length > 0)

  private def numberOf(predicate: String => Boolean): Long =
    code filter predicate count

  def numberEquals(word: String): Long = numberOf(_ equals word)

  def numberContains(word: String): Long = numberOf(_ contains word)
}

object Code {
  val wordComment = List("/*", "*", "//")
}
