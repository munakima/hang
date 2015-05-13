/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hungman;


import java.util.ArrayList;
import java.util.Scanner;


/**
 * This is the UI of the WordGuesser game. Prints out stuff and reads in some
 * input.
 *
 * @author Adi,Melissa, Sebastian, Damian
 */
public class GameConsole {
public long tStart;
    private GameEngine ge = new GameEngine(); // initializes the Business Logic layer.
    private ArrayList<String> m = new ArrayList<String>(ge.wordLength()); // creates a new arraylist full of stars.
    private ArrayList<String> progress = new ArrayList<String>(); // the other array list with the letters we already guessed.
    boolean wordIsGuessed = false; // did we win?
    private Highscore hs = Highscore.getInstance(); // creates a new highscore object.
public long tEnd;
public double elapsedSeconds;
    private long tDelta;
    private int Endscore;
    /**
     * The main-menu method.
     */
    public void menu() {
        Scanner sc = new Scanner(System.in);
        String userInput = ""; // an empty string for the input we use in the menu feature.
        int userOption; // a variable for the user input, used in the menu feature.
        ge.resetEverything(); // resets everything.
        ge.insertLetters(); // it takes the secret word and splits it up into letters which will be put in the 'letters' array list.
        System.out.println("\nChoose an option:");
        System.out.println("1. Show Highscore");
        System.out.println("2. Start Game");
        System.out.println("0. Exit game");
        System.out.print("Enter an option: ");

        // Checks whether the user entered a number or not; if not, it doesn't gives us an error, but rather waits until the user gives a correct option.
        while (!userInput.equals("0")) {
            userInput = sc.nextLine();
            try {
                userOption = Integer.valueOf(userInput);
            } catch (NumberFormatException e) {
                userOption = 99;
            }
            //
            switch (userOption) {
                case 0:
                    // Stops the program.
                    System.out.println("Goodbye! Hope to see you next time!"); // Just a goodbye message.
                    break;
                case 1:
                    // Prints out the highscores.
                    printHighscore();
                    break;
                case 2:
                    // Starts the game.
                    playGame();
                    break;
            }
            break;
        }
    }

    /**
     * This method prints out the progress and some other text - and it also calls for the input.
     */
    public void playGame() {
        wordIsGuessed = false; // As we didn't completed the new word yet, it re-sets the value of wordIsGuessed to false.
        System.out.println("The length of the word is " + ge.wordLength() + " letters.\n");
 tStart = System.currentTimeMillis();
        for (int i = 0; i < ge.wordLength(); i++) {
            m.add("*");
            System.out.print(m.get(i));
        }
    
        guesses(); // asks for an input.
     

        if (hs.isHighscore(ge.getSecretWord(), Endscore) || hs.getWordHighscore(ge.getSecretWord()) == -1) {
             
            hs.setHighscore(ge.getSecretWord(), Endscore); // sets the highscore after the user gets the word right.
       
        }
        m.clear(); // clears the array full of stars/letters. 
       
        System.out.println("\nCongratulations! You guessed the word " + ge.getSecretWord() + " in " + ge.tries.size() + " tries.\n"); // Congrats.
//          System.out.println("Score:"+ge.points);
//          System.out.println("Time:"+elapsedSeconds);
//           System.out.println("Time:"+tDelta);
//            System.out.println("Time:"+tEnd);
//           System.out.println("Time:"+tStart);
        menu(); // goes back to the main menu.
    }

    /**
     * Prints out some text (instructions).
     */
    public void instructions() {
        System.out.println("Welcome!");
        System.out.println("This is the Word Guesser game.");
        System.out.println("In this game, you must guess a word.");
        System.out.println("Try entering different letters to see if they fit the word.");
    }

    /**
     * Asks for input; whether the whole word or just a single letter.
     */
    private void guesses() {
        Scanner sc = new Scanner(System.in);

        while (!wordIsGuessed) { // goes until the word is guessed.
            System.out.print("\nYour guess: ");
            // reads in the user input - either a letter or the whole word.
            String guess = sc.nextLine();
            char letter = guess.charAt(0);
            if (ge.canSubmit(String.valueOf(guess))) {
                if (guess.length() == ge.wordLength()) {
                    if (ge.guessWord(guess)) {
                        ge.storeGuesses(guess);
                        wordIsGuessed = true;
                        break;
                    }
                    ge.storeGuesses(guess);
                } else {
                    ge.storeGuesses(String.valueOf(letter));
                    for (int i = 0; i < ge.wordLength(); i++) {
                        if (ge.guessLetter(letter, i)) {
                            progressChange(letter, i);
                        }
                    }
                }
            } else {
                System.out.println("You already guessed that. Try another word.");
                   System.out.println("Score"+ge.points);
                   
            }
            System.out.println("Your previous guesses: " + ge.getGuesses().toString().replace("[", "").replace("]", "") + "\n");

            for (int i = 0; i < ge.wordLength(); i++) {
                System.out.print(m.get(i));
            }
            guesses(); // calls for itself.
        }
    }

    /**
     * In case we guessed a letter it changes one of the stars.
     *
     * @param guess is the letter what we guessed.
     * @param i position.
     */
    public void progressChange(char guess, int i) {
        m.set(i, String.valueOf(guess));
        isGuessed(); // checks if the word is guessed or not.
    }

    /**
     * Checks whether the word is fully guessed or not. Changes the
     * wordIsGuessed to true in case we've guessed the word.
     */
    private void isGuessed() {
        if (!m.contains("*")) {
            wordIsGuessed = true;
          EndTime();
 GiveMePoints();

        }
    }

    /**
     * This method prints out the high scores and returns to the main menu.

     */
    private void printHighscore() {
        for (int i = 0; i < hs.getHighscore().length; i++) {
            System.out.println("Word: " + hs.getHighscore()[i][0] + " | Highscore: " + hs.getHighscore()[i][1]);
     
        }
        menu();

    }

    private void GiveMePoints() {
     if(elapsedSeconds<5)
          Endscore=((int)ge.points)*3;
        if (elapsedSeconds<15)
          Endscore=((int)ge.points)*2;;
      if (elapsedSeconds>15)
          Endscore=(int)ge.points;
   
    }

    private void EndTime() {
          tEnd = System.currentTimeMillis();
tDelta = tEnd - tStart;
elapsedSeconds = tDelta/1000;
    }
}
