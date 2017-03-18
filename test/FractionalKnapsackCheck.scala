import org.scalacheck.Prop.forAll
import org.scalacheck.Test.Parameters
import org.scalacheck.{Gen, Properties}
import scala.math.Ordering._

object FractionalKnapsackCheck extends Properties("FractionalKnapsack"){

  val genItems: Gen[Int] = Gen.choose(1, 10)
  val genValues: Gen[Int] = Gen.choose(0,2000000)
  val listOfLoot: Gen[(Array[Int], Array[Int])] =
    for {
      size <- genItems
      values <- Gen.containerOfN[Array, Int](size, genValues)
      weights <- Gen.containerOfN[Array, Int](size, genValues)
    } yield (values, weights)

  property("be less than or equal to maximum value") =
    forAll(genItems, listOfLoot){ (capacity, list)  =>
      val orderedValues = list._1.zip(list._2).sorted(new Ordering[(Int, Int)] {
        override def compare(x: (Int, Int), y: (Int, Int)): Int = Double.compare(y._1 * 1.0 / y._2 * 1.0, x._1 * 1.0 / x._2 * 1.0)
      })
      val assertion = FractionalKnapsack.getOptimalValue(capacity,list._1,list._2) <= (orderedValues.head._1 * 1.0 / orderedValues.head._2 * 1.0)  * capacity
      if (!assertion) {
        System.out.println(s"Capacity: $capacity Values: ${list._1.mkString(",")} Weights: ${list._1.mkString(",")}")
      }
      assertion
    }

  override def overrideParameters(p: Parameters): Parameters = p.withMinSuccessfulTests(10000)
}
