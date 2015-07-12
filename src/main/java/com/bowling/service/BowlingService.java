package com.bowling.service;

import java.util.List;

import com.bowling.model.Frame;

/**
 * This Interface exposes Bowling Game methods.
 * @author Indraneel Sanikommu
 *
 */
public interface BowlingService {
	
	/**
	 * Accepts a List of Bowling Frames and returns the total score.
	 * 
	 * @param List<Frame> framesList
	 * @return int TotalScore
	 */
	public int calculateTotalScore(List<Frame> framesList); 

}
