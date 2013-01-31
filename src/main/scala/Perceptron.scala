import scala.util.Random
class Perceptron(var _weights: Seq[Double], val alpha: Double = 0.01) {

  def weights = _weights

  /* classify an instance (an instance is a Seq of Doubles) */
  def classify(instance: Seq[Double]): Int = {
    val score = weights
		  .zip(instance)
		  .map{ case (weight, featVal) => weight * featVal }
		  .sum
    if (score > 0) 1 else 0
  }

  /* PRECONDITION:
   *   Each instance must be the same size (and the size of weights)
   */
  def learn(instances: Seq[(Array[Double], Int)]): Unit = {

    def update_weights(instance: Seq[Double], label:Int): Unit = {
      _weights = _weights
		    .zipWithIndex
		    .map{ case (w, i) =>
			w + alpha * (label - classify(instance)) * instance(i)
		    }
    }

    for ((instance, label) <- instances) {
      update_weights(instance, label)
    }
  }

}






