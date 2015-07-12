package com.bowling.service;

import java.util.List;

import com.bowling.model.Frame;

/**
 * This class Implements BowlingService interface.
 * 
 * @author Indraneel Sanikommu
 *
 */
public class BowlingServiceImpl implements BowlingService {

	/**
	 * Accepts a List of Bowling Frames and returns the total score.
	 * 
	 * @param List
	 *            <Frame> framesList
	 * @return int TotalScore
	 */
	@Override
	public int calculateTotalScore(List<Frame> framesList) {
		Frame currentFrame = null;
		Frame nextFrame = null;
		Frame nextNextFrame = null;
		int totalScore = 0;
		int noOfFrames = framesList.size();

		// Loop through the Frames and calculate the totalScore.
		for (int i = 0; i < noOfFrames; i++) {
			currentFrame = getFrame(framesList, i);
			//If current Frame is a Strike or Spare. get Next 2 frames to calculate the currentFrame score.
			if (currentFrame.isStrike() || currentFrame.isSpare()) {
				nextFrame = getFrame(framesList, i + 1);
				nextNextFrame = getFrame(framesList, i + 2);
			}

			currentFrame.setScore(calculateCurrentFrameScore(currentFrame,
					nextFrame, nextNextFrame));
			totalScore += currentFrame.getScore();
		}

		return totalScore;
	}

	/**
	 * Accepts Current, Next and NextToNext Frames and returns the score for
	 * Current Frame.
	 * 
	 * @param currentFrame
	 * @param nextFrame
	 * @param nextNextFrame
	 * @return int currentFrameScore
	 */
	private int calculateCurrentFrameScore(Frame currentFrame, Frame nextFrame,
			Frame nextNextFrame) {

		// NOTE: If a Frame has Strike, First ball contains 10 and Second ball
		// 0.

		// Generally Frame score is the total of First ball + Second ball +
		// Bonus shot scores.
		// Strike and Spare conditions are handled later in this method.
		int currentFrameScore = currentFrame.getFirst()
				+ currentFrame.getSecond() + currentFrame.getBonus();

		if (currentFrame.isStrike() && nextFrame != null) {
			if (nextFrame.isStrike() && nextNextFrame != null) {
				// Rule a: If 3 consecutive Frames have Strike, then score for
				// the current Frame is 30 (10+10+10)
				// Rule b: If 2 consecutive Frames have Strikes, then third
				// frame 4,3 = 24 (10+10+4)
				currentFrameScore += nextFrame.getFirst()
						+ nextNextFrame.getFirst();
			} else {
				// Rule: If current Frame has a String and nextFrame has 4,3,
				// then score for current frame is 17 (10+4+3)
				currentFrameScore += nextFrame.getFirst()
						+ nextFrame.getSecond();
			}
		} else if (currentFrame.isSpare() && nextFrame != null) {
			// Rule: If current frame is a Spare, then add the next Frames'
			// First ball score.
			// eg: 10 4 3 = 14
			currentFrameScore += nextFrame.getFirst();
		}

		return currentFrameScore;
	}

	/**
	 * Returns Frame object based on the index.
	 * 
	 * @param List
	 *            <Frame> framesList
	 * @param int currentIndex
	 * @return
	 */
	private Frame getFrame(List<Frame> framesList, int currentIndex) {
		Frame nextFrame = null;
		// Check if the currentIndex is with in the range.
		if (currentIndex < framesList.size())
			nextFrame = framesList.get(currentIndex);

		return nextFrame;
	}
}
