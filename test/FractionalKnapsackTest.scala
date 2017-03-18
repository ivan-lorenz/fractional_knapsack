import org.scalactic.Equality
import org.scalatest.FlatSpec

class FractionalKnapsackTest extends FlatSpec {

  behavior of "FractionalKnapsack"

  implicit val doubleEquality = new Equality[Double] {
    override def areEqual(a: Double, b: Any): Boolean = b match {
      case c: Double => Math.abs(a - c) <=  .0001
      case _ => false
    }
  }

  it should "compute fractional knapsack for a case" in {
    assert(FractionalKnapsack.getOptimalValue(50, Array(60,100,120), Array(20,50,30)) === 180.000)
  }

  it should "compute fractional knapsack for b case" in {
    assert(FractionalKnapsack.getOptimalValue(10, Array(500), Array(30)) === 166.6667)
  }

  it should "compute fractional knapsack for c case" in {
    assert(FractionalKnapsack.getOptimalValue(7, Array(1072232), Array(1072232)) === 7.0)
  }


}
