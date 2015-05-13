/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hungman;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adi,Melissa, Sebastian, Damian
 */
public class Highscore {

    private static Highscore hs;
    private File f;
    private HashMap<String, Integer> score;

    private Highscore() {
        f = new File("highscore.txt");
        if (!f.exists()) {
            try {
                f.createNewFile();
                readStoredHighscore();
            } catch (IOException ex) {
                System.out.println("Error creating highscore. Using empty highscore.");
                setEmptyHighscore();
            }
        } else {
            readStoredHighscore();
        }

    }

    /**
     * Get a Highscore instance. The same instance is always returned.
     *
     * @return
     */
    public static Highscore getInstance() {
        if (hs == null) {
            hs = new Highscore();
        }
        return hs;
    }

    /**
     * Get all highscores.
     *
     * @return a multidimensional array of highscores
     */
    public String[][] getHighscore() {
        String[][] arrHs = new String[score.size()][2];
        int i = 0;
        for (Map.Entry<String, Integer> mp : score.entrySet()) {
            arrHs[i][0] = mp.getKey();
            arrHs[i][1] = mp.getValue().toString();
            i++;
        }
        return arrHs;
    }

    /**
     * Get the highscore for a word.
     *
     * @param word
     * @return highscore for the given word. -1 if no highscore exists.
     */
    public int getWordHighscore(String word) {
        if ((score.containsKey(word))) {
            return score.get(word);
        }
        return -1;
    }

    /**
     * Set highscore for a word.
     *
     * @param word
     
     * @param points
     */
    public void setHighscore(String word,int points) {
        score.put(word,points);
        writeHighscore();
    }

    /**
     * Test if the word and number of guesses is a new highscore.
     *
     * @param word
   
     * @param points
     * @return
     */
    public boolean isHighscore(String word, int  points) {
        if (score.containsKey(word)) {
            return points <= score.get(word);
        }
        return false;
    }

    private void writeHighscore() {
        try {
            FileOutputStream fout = new FileOutputStream(f);
            try (ObjectOutputStream oos = new ObjectOutputStream(fout)) {
                oos.writeObject(score);
            }
        } catch (Exception e) {
        }
    }

    private void setEmptyHighscore() {
        score = new HashMap<>();
    }

    private void readStoredHighscore() {

        try {
            FileInputStream fin = new FileInputStream(f);
            try (ObjectInputStream ois = new ObjectInputStream(fin)) {
                score = (HashMap<String, Integer>) ois.readObject();
            }
        } catch (Exception ex) {
            setEmptyHighscore();
            Logger.getLogger(Highscore.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
