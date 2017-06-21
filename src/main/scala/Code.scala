import org.apache.spark.rdd.RDD

/**
  * @author VsSekorin
  */
class Code(text: RDD[String]) {

  val code: RDD[String] =
    text
      .filter(str => !Code.wordComment.exists(str.trim startsWith))
      .filter(_.trim.length > 1)

  def numberOf(pattern: String): Long =
    code
      .filter(_ matches pattern)
      .count
}

object Code {
  val wordComment = List("/*", "*", "//")
}