import org.apache.spark.SparkContext

/**
  * @author VsSekorin
  */
object Application {

  val strWithIf = ".*\\Wif\\s*\\(.*"
  val strWithElse = ".*\\s+else\\s+.*"

  def main(args: Array[String]): Unit = {
    val context = new SparkContext("local", "Then Else Count")
    val path = "files/**"
    val data = context textFile path cache
    val code = new Code(data)
    val countIf = code numberOf strWithIf
    val countElse = code numberOf strWithElse
    println(s"If: $countIf, Else: $countElse, Diff: ${countIf - countElse}")
  }
}
