import scala.util.Random
import org.scalatest.FunSuite

class HmmTest extends FunSuite {

  val rand = new Random()

  private def normalize(l: Array[Double]): Array[Double] = {
    val sum = l.sum
    l.map(_ / sum)
  }

  /* Generate a random NxM matrix with normalized rows */
  private def genRandMat(N: Int, M: Int) : Array[Array[Double]] = {
    val T = new Array[Array[Double]](N)

    for (idx <- 0 to N-1) {
      T(idx) = normalize(Array.fill(M)(rand.nextDouble()))
    }

    return T
  }

  /* Generate random Hmm */
  private def genRandHmm(numToks: Int, numStates: Int): Hmm = {
    val T  = genRandMat(numStates, numStates)
    val A  = genRandMat(numStates, numToks)
    val pi = genRandMat(1, numStates)(0)

    return new Hmm(pi, T, A)
  }

  /* Test randomly generated Hmms:
   *   1) Generate 2 Hmms
   *   2) Generate 2 observation strings
   *   3) Use the decode function to predict which Hmm generated the string
   */
  test("Testing the Forward Algorithm") {
    val numToks   = rand.nextInt(5) + 3  // Between 3 and 7 output token types
    val numStates = rand.nextInt(5) + 2
    val lengthSeq = rand.nextInt(100) + 10000

    val Hmm1 = genRandHmm(numToks, numStates)
    val Hmm2 = genRandHmm(numToks, numStates)

    val obsSeq1 = Hmm1.genObsSeq(lengthSeq)
    val obsSeq2 = Hmm2.genObsSeq(lengthSeq)

    // println(Hmm1.forward(obsSeq1))
    // println(Hmm1.forward(obsSeq1))
    // println(Hmm1.forward_func(obsSeq1))
    // println(Hmm1.forward(obsSeq2))
    // println(Hmm1.forward(obsSeq2))
    // println(Hmm1.forward_func(obsSeq2))


    // This isn't the greatest test...
    assert(Hmm1.forward(obsSeq1) > Hmm2.forward(obsSeq1), "Sequence " +
      "generated from Hmm1 more likely to have come from Hmm2")
    assert(Hmm2.forward(obsSeq2) > Hmm1.forward(obsSeq2), "Sequence " +
      "generated from Hmm2 more likely to have come from Hmm1")
  }

  def TEST_Hmm_decode(): Unit = {

  }
}
