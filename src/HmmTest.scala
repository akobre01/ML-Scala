import scala.util.Random

class HmmTest {

  val rand = new Random()

  def normalize(l: Array[Double]): Array[Double] = {
    val sum = l.sum
    l.map(_ / sum)
  }

  // Generate a random NxM matrix with normalized rows
  def genRandMat(N: Int, M: Int) : Array[Array[Double]] = {
    val T = new Array[Array[Double]](N)

    var i = 0
    var j = 0
    while (i < N) {
      T(i) = new Array[Double](M)
      j = 0
      while (j < M) {
	T(i)(j) = rand.nextDouble()
	j += 1
      }
      T(i) = normalize(T(i))
      i   += 1
    }

    return T
  }

  def TEST_Hmm_forward(): Unit = {
    val numToks   = rand.nextInt(5) + 3  // Between 3 and 7 output token types
    val numStates = rand.nextInt(5) + 2
    val lengthSeq = rand.nextInt(10) + 5

    val T1  = genRandMat(numStates, numStates)
    val A1  = genRandMat(numStates, numToks)
    val pi1 = genRandMat(1, numStates)(0)

    val T2  = genRandMat(numStates, numStates)
    val A2  = genRandMat(numStates, numToks)
    val pi2 = genRandMat(1, numStates)(0)

    val Hmm1 = new Hmm(pi1, T1, A1)
    val Hmm2 = new Hmm(pi2, T2, A2)

    val obsSeq1 = Hmm1.genObsSeq(lengthSeq)
    val obsSeq2 = Hmm2.genObsSeq(lengthSeq)

    // This isn't the greatest test...
    assert(Hmm1.forward(obsSeq1) > Hmm2.forward(obsSeq1))
    assert(Hmm2.forward(obsSeq2) > Hmm1.forward(obsSeq2))
  }

  def TEST_Hmm_decode(): Unit = {

  }
}
