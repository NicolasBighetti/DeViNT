package musiquizz;

/**
 * Modélise un joueur
 * Un joueur est différent d'un profil dans le fait qu'il est en train de jouer
 * Il possède donc un score propre
 * @author Nicolas Bighetti
 *
 */
public class Player {

	private int goodAnswers;
	
	private int totalQuestions;
	
	private int reactionTimeMS;
	
	private int buzzerKey;
	
	public Player(int buzzerKey)
	{
		goodAnswers = 0;
		totalQuestions = 0;
		reactionTimeMS = 0;
		this.setBuzzerKey(buzzerKey);
	}

	/**
	 * @return the goodAnswers
	 */
	public int getGoodAnswers() {
		return goodAnswers;
	}

	/**
	 * @param goodAnswers the goodAnswers to set
	 */
	public void setGoodAnswers(int goodAnswers) {
		this.goodAnswers = goodAnswers;
	}

	/**
	 * @return the totalQuestions
	 */
	public int getTotalQuestions() {
		return totalQuestions;
	}

	/**
	 * @param totalQuestions the totalQuestions to set
	 */
	public void setTotalQuestions(int totalQuestions) {
		this.totalQuestions = totalQuestions;
	}

	/**
	 * @return the reactionTime
	 */
	public int getReactionTime() {
		return reactionTimeMS;
	}

	/**
	 * @param reactionTime the reactionTime to set
	 */
	public void setReactionTime(int reactionTime) {
		this.reactionTimeMS = reactionTime;
	}

	/**
	 * @return the buzzerKey
	 */
	public int getBuzzerKey() {
		return buzzerKey;
	}

	/**
	 * @param buzzerKey the buzzerKey to set
	 */
	public void setBuzzerKey(int buzzerKey) {
		this.buzzerKey = buzzerKey;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + buzzerKey;
		result = prime * result + goodAnswers;
		result = prime * result + reactionTimeMS;
		result = prime * result + totalQuestions;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (buzzerKey != other.buzzerKey)
			return false;
		if (goodAnswers != other.goodAnswers)
			return false;
		if (reactionTimeMS != other.reactionTimeMS)
			return false;
		if (totalQuestions != other.totalQuestions)
			return false;
		return true;
	}
}
