package com.mycompany.app;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GameEngineTest {
	public ArrayList<String> tries = new ArrayList<String>();
	static WordListTest w = new WordListTest();
	private static String currentWord = w.ReturnWord(); // gets a new word.
	public static ArrayList<String> letters = new ArrayList<String>();
	static String testWord;
	static String testWord2;
	static char testLetter;
	static char testLetter2;
	
	public double points;
	 @Before
     public void init(){
            testWord="Hello";
            testWord2="He";
            testLetter='h';
            testLetter2='e';
     }
	
	@BeforeClass
	public static void insertLetters() {
		System.out.println("Running insert letters...");
		for (int i = 0; i < currentWord.length(); i++) {
			letters.add(String.valueOf(currentWord.charAt(i)));
			System.out.println("add:"+String.valueOf(currentWord.charAt(i)));
		}
	}
	@Test
	public void wordLength() {
		System.out.println("");
		System.out.println("Running wordLength...");
		assertEquals(5, currentWord.length());
		System.out.println("expected length:5,word Length:" + currentWord.length());
	}

	
	@Test
	public void getSecretWord() {
		System.out.println("");
		System.out.println("Running getSecretWord...");
		assertEquals(testWord, currentWord);
		System.out.println("expected :"+testWord+",currentWord:"+ currentWord);
		
	}

	@Test
	public void guessWordTrue() {
		System.out.println("");
		System.out.println("Running guessWordTrue...");
		assertTrue(currentWord.equalsIgnoreCase(testWord));
		System.out.println("expected :true,test:"
				+ currentWord.equalsIgnoreCase(testWord));
	}

	@Test
	public void guessWordFalse() {
		System.out.println("");
		System.out.println("Running guessWordFalse...");
		assertFalse(currentWord.equalsIgnoreCase(testWord2));
		System.out.println("expected :false,test:"
				+ currentWord.equalsIgnoreCase(testWord2));
	}

	@Test
	public void guessLetterTrue() {
		System.out.println("");
		System.out.println("Running guessLetterTrue...");
		assertTrue(letters.get(0).equalsIgnoreCase(String.valueOf(testLetter)));
		points = points + 1;
		System.out.println("expected :true,test:"
				+ letters.get(0).equalsIgnoreCase(String.valueOf(testLetter)));
	}

	@Test
	public void guessLetterFalse() {
		System.out.println("");
		System.out.println("Running guessLetterFalse...");
		
		assertFalse(letters.get(0).equalsIgnoreCase(String.valueOf(testLetter2)));
		System.out.println("expected :false,test:"
				+ letters.get(0).equalsIgnoreCase(String.valueOf(testLetter2)));
	
	}
	@Test
	public void storeGuesses() {
		System.out.println("");
		System.out.println("Running storeGuesses...");
		
		tries.add(String.valueOf(testLetter));
		System.out.println("tries add:"+testLetter);
	}
//	@Test
//	public void getGuesses() {
//		assertThat(tries, Contains(String.valueOf(testLetter)));
//	}
//	
	@Test
	public void getScore() {
		System.out.println("");
		System.out.println("Running getScore...");
		points = points + 1;
		assertEquals(1.0, points,1);
		System.out.println("expected :1.0,testScore:"+ points);
		
	}

	@Test
	public void canSubmit() {
		System.out.println("");
		System.out.println("Running canSubmit...");
		tries.add(String.valueOf(testLetter));

		assertNotNull(tries);
		
		for (String a : tries) {
				assertTrue(a.equalsIgnoreCase("h"));	
				System.out.println("expected :h,testScore:"+ a);				
		}	
	}

	@Test
	public void resetEverything() {
		currentWord = w.ReturnWord();
		tries.clear();
		letters.clear();
		points = 0;
	}
	

}
