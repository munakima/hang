package com.mycompany.app;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Test;

public class WordListTest  {

	static String[] m;
	static int i;

	@BeforeClass
	public static void setUp() throws Exception {
		 m = new String[1];
		m[0] = "Hello";
	}

	@Test
	public void getWord() {
		Random rnd = new Random();
		i = rnd.nextInt(m.length);
		String word = m[i];
		String expected = "Hello";
		assertEquals(expected, word);
		
	}
	public String ReturnWord(){
		
		return "Hello";
	}

}
