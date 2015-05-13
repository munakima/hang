package com.mycompany.app;

import static org.junit.Assert.*;
import hungman.Highscore;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

public class HighscoreTest {
	public static Highscore hs;

	public HashMap<String, Integer> score;

	@Before
	public void setup(){
		score =new  HashMap<String, Integer>() ;
		score.put("Hello", 1);

	}
	
	@Test
	public void getHighscore(){
		 String[][] arrHs = new String[score.size()][2];
	    
	        for (Map.Entry<String, Integer> mp : score.entrySet()) {
	        	arrHs[0][0] = mp.getKey();
	            arrHs[0][1] = mp.getValue().toString();
	            System.out.println(arrHs[0][0]);
	            System.out.print(arrHs[0][1]);
	           
	            assertEquals(arrHs[0][0] , "Hello");
		        assertEquals(arrHs[0][1] , 1);
	        }
	       // Map.Entry<String, Integer> mp = (Entry<String, Integer>) score.entrySet();   
	}
//	@Test
//	public void getWordHighscore(){
//		assertThat(score, hasItem("Hello"));
//}
}
