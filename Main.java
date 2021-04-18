/*
* Main Java where Trivia GUI appears
* @author Samuel Dobrowolski, Jamie Schaffer, Joey Desantis and Christopher Sonnhalter
* @version 4.18.2021
*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Main {
 public static void main(String args[]) {
   SwingUtilities.invokeLater(new Runnable() {
     public void run() {
        new Game();
     }
   });
 }
} 