package com.bowling.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.bowling.model.Frame;

/**
 * Test cases to unit test Bowling scores.
 * @author Indraneel Sanikommu
 *
 */
public class BowlingServiceTest {

	BowlingService bowlingService = null;
	List<Frame> framesList = null;
	List<Frame> perfectGameFrames = null;

	@Before
	public void setUp() throws Exception {
		bowlingService = new BowlingServiceImpl();
		perfectGameFrames = getPerfectGameFrames();
		getGameFrames();
	}

	@Test
	public final void testTotalScore() {
		int totalScore = bowlingService.calculateTotalScore(framesList);
		assertEquals(158, totalScore);
	}

	@Test
	public final void testInCompleteGameScore() {
		// Test Data: 10 7 3 9 10 (4 frames) = 58
		int totalScore = bowlingService.calculateTotalScore(framesList.subList(0, 4));
		assertEquals(58, totalScore);
	}

	@Test
	public final void testPerfectGameScore() {
		int totalScore = 0;
		totalScore = bowlingService.calculateTotalScore(perfectGameFrames);
		assertEquals(300, totalScore);
	}

	private List<Frame> getPerfectGameFrames() {

		List<Frame> perfectGameFrames = new ArrayList<>();
		Frame frame = null;
		for (int i = 0; i < 10; i++) {
			frame = new Frame(10, 0);
			if (i == 9) {
				frame.setSecond(10);
				frame.setBonus(10);
			}
			perfectGameFrames.add(frame);
		}

		return perfectGameFrames;
	}

	private void getGameFrames() {
		framesList = new ArrayList<>();
		framesList.add(new Frame(10, 0));
		framesList.add(new Frame(7, 3));
		framesList.add(new Frame(9, 0));
		framesList.add(new Frame(10, 0));
		framesList.add(new Frame(0, 8));
		framesList.add(new Frame(8, 2));
		framesList.add(new Frame(0, 6));
		framesList.add(new Frame(0, 10));
		framesList.add(new Frame(10, 0));
		framesList.add(new Frame(10, 8, 2));
	}
}
