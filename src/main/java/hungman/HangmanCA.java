/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hungman;

/**
 *
 * @author Adi,Melissa, Sebastian, Damian
 */
public class HangmanCA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
             GameConsole gc = new GameConsole(); // initializes the UI.
        gc.instructions(); // prints out some text/instructions.
        gc.menu(); // shows the main menu.
    }
}
