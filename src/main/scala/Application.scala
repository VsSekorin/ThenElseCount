import org.apache.spark.SparkContext

/**
 * @author VsSekorin
 */
object Application {

  def main(args: Array[String]): Unit = {
    val context = new SparkContext("local", "Then Else Count")
    val path = "files/**"
    val data = context textFile path cache
    val code = new Code(data)
    val countIf = code numberEquals "if"
    val countElse = code numberEquals "else"
    val countTernary = code numberContains "?"
    println(
      s"If: $countIf, " +
      s"Else: $countElse, " +
      s"Ternary: $countTernary, " +
      s"Diff: ${countIf - countElse - countTernary}"
    )
  }
}
