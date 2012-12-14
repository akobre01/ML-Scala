import scala.util.Random
class Perceptron(var _weights:Array[Double], val alpha: Double = 0.01) {

  def weights = _weights

  /* classify an instance (an instance is a list of Doubles) */
  def classify(instance: Array[Double]): Int = {
    val score = weights.zip(instance).map({case (x,y) => x * y}).sum
    if (score > 0) 1 else 0
  }

  /* PRECONDITION:
   *   Each instance must be the same size (and the size of w)
   */
  def learn(instances: List[(Array[Double], Int)]): Unit = {

    def update_weights(instance:Array[Double], label:Int): Unit = {
      _weights.zipWithIndex.map({ case (w,i) =>
		      w + alpha * (label - classify(instance)) * instance(i)})
    }

    for ((instance, label) <- instances) {
      update_weights(instance, label)
    }
  }

}






