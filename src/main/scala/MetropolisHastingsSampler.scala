import scala.util.Random

/**
 * Created by akobren on 3/13/14.
 */
class MetropolisHastingsSampler[STATE](f: STATE => Double,
                                       proposal: STATE => STATE,
                                       transitionProb: (STATE, STATE) => Double) {

  private def nextState(currentState: STATE): STATE = {
    val proposedState = proposal(currentState)
    val acceptanceProb = (f(currentState) * transitionProb(currentState, proposedState)) / (f(proposedState) * transitionProb(proposedState, currentState))
    if (acceptanceProb >= 1) proposedState
    else if (Random.nextDouble() < acceptanceProb) proposedState
    else currentState
  }

  def getChainOfLengthN(n: Int, currState: STATE, burnin: Int = 100, thinning: Int = 100): Seq[STATE] = {
    def helper(n: Int, s: STATE, samples: Seq[STATE] = Seq.empty[STATE]): Seq[STATE] = n match {
      case 0 => samples
      case _ => { val ns = nextState(currState); helper(n-1, ns, samples :+ ns) }
    }
    helper(burnin + n*thinning, currState).drop(burnin).zipWithIndex.filter({ case (s, ind) => ind % thinning == 0 }).unzip._1
  }

}
