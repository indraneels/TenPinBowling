package com.bowling.model;


/**
 * This class represents a Frame in the Bowling game.
 * 
 * @author Indraneel Sanikommu
 *
 */
public class Frame {

	public Frame() {
	}

	public Frame(int id, int first, int second) {
		setId(id);
		setFirst(first);
		setSecond(second);
	}

	public Frame(int id, int first, int second, int bonus) {
		this(id, first, second);
		setBonus(bonus);
	}

	private int id;
	private int first;
	private int second;
	private int bonus;
	private int score;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Contains the score of the First ball. In case of Strike, First contains
	 * 10 and Second contains 0. Default value 0.
	 */
	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	/** Contains the score of the Second ball. Default value 0. */
	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	/**
	 * Bonus will populated ONLY in case of 10th Frame and if Frame has a Strike
	 * or Spare. Default value 0.
	 */
	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Frame{ " + "Frame Number: " + getId() + ", First ball: "
				+ getFirst() + ", Second ball: " + getSecond()
				+ ", Bonus shot: " + getBonus() + " }";
	}

	/********* Convenience Methods **********/

	public boolean isStrike() {
		return getFirst() == 10;
	}

	public boolean isSpare() {
		return (getFirst() + getSecond()) == 10;
	}

	public boolean isLastFrame() {
		return getId() == 10;
	}

	/**
	 * Bonus shot is allowed only for 10th Frame and the Frame has either Strike
	 * or Spare
	 */
	public boolean isBonusShotAllowed() {
		return isLastFrame() && (isStrike() || isSpare());
	}

	/**
	 * Second ball is allowed only if there is NO strike in the Frame or for
	 * 10th Frame even of the Strike is involved.
	 */
	public boolean isSecondBallAllowed() {
		return !isStrike() || isBonusShotAllowed();
	}

}
