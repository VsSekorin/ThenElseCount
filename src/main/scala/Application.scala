import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

/**
  * @author VsSekorin
  */
object Application {

  val wordComment = List("/*", "*", "//")

  def numberIf(code: RDD[String]) =
    code
      .filter(_.matches(".*\\Wif\\s*\\(.*"))
      .count

  def numberElse(code: RDD[String]) =
    code
      .filter(_.matches(".*\\s+else\\s+.*"))
      .count

  def main(args: Array[String]) = {
    val context = new SparkContext("local", "Then Else Count")
    val path = "files/**"
    val data = context textFile path cache
    val code =
      data
        .filter(str => !wordComment.exists(str.trim startsWith))
        .filter(_.length > 1)
    val countIf = numberIf(code)
    val countElse = numberElse(code)
    println(s"If: $countIf, Else: $countElse, Diff: ${countIf - countElse}")
  }
}
