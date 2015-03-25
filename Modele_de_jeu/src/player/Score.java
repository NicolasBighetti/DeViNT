package player;

/**
 * 
 * Represente le score du joueur, ainsi que certaines informations complémentaires
 * @author Nicolas Bighetti
 *
 */
public class Score implements Comparable<Score>{

	/**
	 * Score total
	 */
	private int totalScore;
	
	/**
	 * Nombre de questions total
	 */
	private int numberOfQuestions;
	
	/**
	 * Nombre de réponses correct
	 */
	private int correctanwsers;
	
	/**
	 * Temps passé durant la partie
	 */
	private double elapsedTime;
	
	public Score(int numberOfQuestions, int correctAnwsers, double timeElapsed)
	{
		this.numberOfQuestions = numberOfQuestions;
		this.correctanwsers = correctAnwsers;
		this.elapsedTime = timeElapsed;
		
		processTotalScore();
	}

	/**
	 * Méthode privée qui asssigne à score totale sa valeur en fonction du nb de questions, des bonnes réponses et tu temps passé
	 */
	private void processTotalScore() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Retourne le ration bonne réponse / mauvaise réponse de cette partie
	 * @return un pourcentage entre 0 et 100 de bonne réponse
	 */
	public double ratio()
	{
		return ((correctanwsers/numberOfQuestions)+(correctanwsers%numberOfQuestions))*100;
	}

	/**
	 * @return the totalScore
	 */
	public int getTotalScore() {
		return totalScore;
	}

	/**
	 * @param totalScore the totalScore to set
	 */
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	/**
	 * @return the numberOfQuestions
	 */
	public int getNumberOfQuestions() {
		return numberOfQuestions;
	}

	/**
	 * @param numberOfQuestions the numberOfQuestions to set
	 */
	public void setNumberOfQuestions(int numberOfQuestions) {
		this.numberOfQuestions = numberOfQuestions;
	}

	/**
	 * @return the correctanwsers
	 */
	public int getCorrectanwsers() {
		return correctanwsers;
	}

	/**
	 * @param correctanwsers the correctanwsers to set
	 */
	public void setCorrectanwsers(int correctanwsers) {
		this.correctanwsers = correctanwsers;
	}

	/**
	 * @return the elapsedTime
	 */
	public double getElapsedTime() {
		return elapsedTime;
	}

	/**
	 * @param elapsedTime the elapsedTime to set
	 */
	public void setElapsedTime(double elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	@Override
	public int compareTo(Score o) {
		// TODO Auto-generated method stub
		if (this.totalScore < o.totalScore)
			return -1;
		if(this.totalScore > o.totalScore)
			return 1;
		else
		{
			//Si le temps total de o est plus petit, il a donc un meilleur score
			if(this.elapsedTime > o.elapsedTime)
				return -1;
			//Au contraire si o a un temps total plus faible, il sera mieux classé
			else if(this.elapsedTime < o.elapsedTime)
				return 1;
			else
				return 0;
		}

	}
}
