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
    (1 until burnin + n * thinning)
      .scanLeft(currState)((state, _) => nextState(state))       /* generate all samples */
      .drop(burnin)                                              /* drop burn in */
      .zipWithIndex                                              /* select thinning */
      .filter({ case (s, ind) => ind % thinning == 0})
      .unzip._1
  }

}
