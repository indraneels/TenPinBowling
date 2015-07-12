package com.bowling.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import com.bowling.exception.InvalidDataException;
import com.bowling.model.Frame;
import com.bowling.service.BowlingService;
import com.bowling.service.BowlingServiceImpl;

import static com.bowling.util.BowlingUtils.*;

/**
 * This is the Standalone Java class for Ten Pin Bowling Game.
 * 
 * @author Indraneel Sanikommu
 *
 */
public class TenPinBowlingMain {

	public static final int MAX_ALLOWED_FRAMES = 10;
	public static final String ENTER_INPUT = "Please enter bowling scores: ";
	public static final String EMPTY_INPUT = "No input detected. Please enter valid scores: ";
	public static final String INVALID_NUMBER_INPUT = "Invalid input, Enter numbers between 0 and 10: ";
	public static final String INVALID_INPUT = "Invalid Input, Enter valid numbers between 0 and 10: ";

	public static void main(String[] args) {

		List<Frame> frames = null;
		String bowlingScores = null;
		Scanner scanner = null;
		List<String> scores = null;
		BowlingService bowlingService = new BowlingServiceImpl();

		System.out.println(ENTER_INPUT);

		while (true) {
			try {
				scanner = new Scanner(System.in);

				// Fetch the input provided by the user.
				if (scanner.hasNextLine()) {
					bowlingScores = scanner.nextLine();
				}

				// Check if user's input is empty.
				if (bowlingScores == null || bowlingScores.trim().isEmpty()) {
					System.out.println(EMPTY_INPUT);
					continue;
				}

				scores = new ArrayList<>(
						Arrays.asList(bowlingScores.split(" ")));

				/*
				 * Remove any extra spaces included in the user input. eg:2 5 4
				 * 5, there is an extra space between 2 and 5.
				 */
				scores.removeAll(Arrays.asList("", " "));

				// Convert user input to Frames.
				frames = createFrames(scores);

				// Check if Frame count is more than allowed.
				if (frames.size() > MAX_ALLOWED_FRAMES) {
					System.out
							.println("Too many frames. Please enter upto to 10 Frames only: ");
					continue;
				}

				// Calculate total scores by calling the bowling service.
				int totalScore = bowlingService.calculateTotalScore(frames);

				System.out.println("Total Score: " + totalScore);

				System.out.println("Coninue scoring more bowling games? (Y/N)");

				if (!isTrue(scanner.next())) {
					System.out.println("Good Bye!!!");
					scanner.close();
					break;
				} else {
					System.out.println(ENTER_INPUT);
				}

			} catch (InvalidDataException ide) {
				System.out.println(ide.getMessage());
				continue;
			} finally {
				/*if (scanner != null) {
					scanner.close();
				}*/
			}
		}

	}

	/**
	 * Converts user input to Frame objects.
	 * 
	 * @param List
	 *            <String> scores
	 * @return List<Frame>
	 * @throws InvalidDataException
	 */
	private static List<Frame> createFrames(List<String> scores)
			throws InvalidDataException {

		List<Frame> frames = new ArrayList<>();
		Frame frame = null;

		Iterator<String> listItr = scores.listIterator();
		AtomicInteger sequence = new AtomicInteger(1);

		while (listItr.hasNext()) {
			frame = new Frame();
			frame.setId(sequence.getAndIncrement());
			frame.setFirst(getNextInt(listItr.next()));

			// Check if second ball is allowed
			if (listItr.hasNext() && frame.isSecondBallAllowed()) {
				frame.setSecond(getNextInt(listItr.next()));
			}
			// Check if bonus shot is allowed
			if (listItr.hasNext() && frame.isBonusShotAllowed()) {
				frame.setBonus(getNextInt(listItr.next()));
			}
			frames.add(frame);
		}

		return frames;

	}

	/**
	 * Converts String into a Numerical number. Also handles validation on the
	 * input.
	 * 
	 * @param String
	 * @return int score
	 * @throws InvalidDataException
	 */
	private static int getNextInt(String score) throws InvalidDataException {
		int nextInt = 0;
		try {
			nextInt = Integer.valueOf(score.trim());
			if (nextInt < 0 || nextInt > 10) {
				throw new InvalidDataException(INVALID_NUMBER_INPUT);
			}
		} catch (NumberFormatException nfe) {
			throw new InvalidDataException(INVALID_INPUT);
		}
		return nextInt;
	}

}
