/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hungman;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * This is the Business Logic layer of the WordGuesser game. It does the magic.
 *
 * @author Adi,Melissa, Sebastian, Damian
 */
public class GameEngine {

	public ArrayList<String> tries = new ArrayList<String>(); // previous tries.
	public ArrayList<String> letters = new ArrayList<String>(); // contains the
																// individual
																// letters of
																// the secret
																// word.
	private WordList words = new WordList();
	private String currentWord = words.getWord(); // gets a new word.
	public double points;

	/**
	 * This method checks for the length of the word.
	 *
	 * @return the length of the secret word.
	 */
	public int wordLength() {

		return (currentWord.length());
	}

	/**
	 * It's a getter for the word.
	 *
	 * @return the secret word.
	 */
	public String getSecretWord() {
		return (currentWord);
	}

	/**
	 * This method stores the individual letters of the secret word in a
	 * different array list.
	 */
	public void insertLetters() {
		for (int i = 0; i < currentWord.length(); i++) {
			letters.add(String.valueOf(currentWord.charAt(i)));
		}
	}

	/**
	 * It checks if whether we guessed the correct word or not.
	 *
	 * @param guess
	 *            The users guess.
	 * @return true in case we got the correct word; false in case we're not.
	 */
	boolean guessWord(String guess) {
		if (currentWord.equalsIgnoreCase(guess)) {

			return true;

		} else {
			return false;
		}
	}

	/**
	 * It checks whether we guessed a letter that's in the secret word or not.
	 *
	 * @param guess
	 *            is the letter what we guessed.
	 * @param i
	 *            it is the position used in the for loop in the UI.
	 * @return true in case if we got it right; false in case we're not.
	 */
	public boolean guessLetter(char guess, int i) {
		if (letters.get(i).equalsIgnoreCase(String.valueOf(guess)) == true) {
			points = points + 1;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * It stores the individual guesses in the 'tries' array list.
	 *
	 * @param guess
	 *            our guess.
	 */
	void storeGuesses(String guess) {
		tries.add(guess);

	}

	/**
	 * This method returns the array list with the previous tries.
	 *
	 * @return 'tries' array list.
	 */
	public ArrayList<String> getGuesses() {
		return tries;
	}

	public double getScore() {
		return points;
	}

	/**
	 * Checks whether if we already guessed that letter/word or not.
	 *
	 * @param guess
	 *            our guess.
	 * @return true in case we already guess that letter; false if not.
	 */
	public boolean canSubmit(String guess) {
		if (!tries.isEmpty()) {
			for (String a : tries) {
				if (a.equalsIgnoreCase(guess)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Resets everything - gets a new word, deletes the arrays used with the
	 * other word.
	 */
	public void resetEverything() {
		currentWord = words.getWord();
		tries.clear();
		letters.clear();
		points = 0;

	}

}
