/**
* This class contains everything for the GUI and the Trivia Game
* @author Sam Dobrowolski, Jamie Schaffer, Joseph Desantis, Christopher Sonnhalter
* @version 4.18.2021
*/

//all imports
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*; 
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class Game implements ActionListener{

  JButton enterName;
  JButton nextButton;
  JButton exitButton;
  JButton submitButton;
  JRadioButton answerChoice1;
  JRadioButton answerChoice2;
  JRadioButton answerChoice3;
  JRadioButton answerChoice4;
  JLabel name, welcome, ask, answer, correctAnswer, totalScore;
  JTextField nameField;

  int score = 0;

  ArrayList <Question> triviaQuestions = new ArrayList<Question>();

/** 
@method
Default method to read data and create trivia game. sets up all details in the frame and adds data from ArrayList.
*/

Game() {
  int score;
  String fileName;
  FileReader myFile;

  fileName = "triviaQuestions.txt";
  String question = "", answer1 = "", answer2 = "", answer3 = "", answer4 = "", rightAnswer = "", userScore = "";

try{
  myFile = new FileReader(fileName);
  BufferedReader reader = new BufferedReader(myFile);

  while (reader.ready()){
    question = reader.readLine();
    answer1 = reader.readLine();
    answer2 = reader.readLine();
    answer3 = reader.readLine();
    answer4 = reader.readLine();
    rightAnswer = reader.readLine();
    userScore = reader.readLine();

    Question aQuestion = new Question(question, answer1, answer2, answer3, answer4, Integer.parseInt(rightAnswer), Integer.parseInt(userScore));
    triviaQuestions.add(aQuestion);
    }
  reader.close();
  }
catch (IOException excpt) {
  System.out.println("Something went wrong: " + excpt);
}

FileWriter toWriteFile;
try{
  toWriteFile = new FileWriter("score.txt");
  BufferedWriter result = new BufferedWriter(toWriteFile);

  result.flush();
  result.close();
  }
catch (IOException exception) {
  exception.printStackTrace(); // I've used this at work to help identify errors easier
}

try {
  toWriteFile = new FileWriter("score.txt");
  BufferedWriter result = new BufferedWriter(toWriteFile);
  result.write("Player Score: " + userScore);
  result.newLine();

  result.flush();
  result.close();
}
catch (IOException exception) {
  exception.printStackTrace();
}

JFrame frame = new JFrame("Tech Trivia");
frame.setLayout(new FlowLayout());
frame.setSize(500, 300);

answerChoice1 = new JRadioButton(triviaQuestions.get(0).getAnswer1());
answerChoice2 = new JRadioButton(triviaQuestions.get(0).getAnswer2());
answerChoice3 = new JRadioButton(triviaQuestions.get(0).getAnswer3());
answerChoice4 = new JRadioButton(triviaQuestions.get(0).getAnswer4());

enterName = new JButton("Enter Game");
submitButton = new JButton("Submit Answer");
nextButton = new JButton("Next Question");
exitButton = new JButton("Exit Game");

nameField = new JTextField(15);
nameField.setActionCommand("TF");
nameField.addActionListener(this);

enterName.addActionListener(this);
submitButton.addActionListener(this);
nextButton.addActionListener(this);
exitButton.addActionListener(this);

nameField = new JTextField("Enter your name here: ");
welcome = new JLabel("Welcome to this Tech Trivia Game");
totalScore = new JLabel("Your point total score is: ");

ask = new JLabel(triviaQuestions.get(0).getQuestion());
answer = new JLabel("");

frame.add(enterName);
frame.add(nextButton);
frame.add(exitButton);
frame.add(submitButton);
frame.add(name);
frame.add(welcome);
frame.add(ask);
frame.add(answer);
frame.add(correctAnswer);
frame.add(totalScore);
frame.add(nameField);
frame.add(answerChoice1);
frame.add(answerChoice2);
frame.add(answerChoice3);
frame.add(answerChoice4);

frame.setVisible(true);
submitButton.setVisible(true);
nextButton.setVisible(true);
}

int num = 0;

/* @Uses arraylist to move to next question while resetting each answer choice.
*/
void NextQuestion() {
  if (num < triviaQuestions.size()) {
    ask.setText("");
    answerChoice1.setText("");
    answerChoice2.setText("");
    answerChoice3.setText("");
    answerChoice4.setText("");
    answer.setText("");

    num++;

    ask.setText(triviaQuestions.get(num).getQuestion());
    answerChoice1.setText(triviaQuestions.get(num).getAnswer1());
    answerChoice2.setText(triviaQuestions.get(num).getAnswer2());
    answerChoice3.setText(triviaQuestions.get(num).getAnswer3());
    answerChoice4.setText(triviaQuestions.get(num).getAnswer4());

    System.out.println(triviaQuestions.get(num).getQuestion());
    System.out.println(triviaQuestions.get(num).getAnswer1());
    System.out.println(triviaQuestions.get(num).getAnswer2());
    System.out.println(triviaQuestions.get(num).getAnswer3());
    System.out.println(triviaQuestions.get(num).getAnswer4());
  }

  else {
    welcome.setText("Game Complete!");
  }
}

/* allows buttons to work as specified
*/

public void actionPerformed(ActionEvent ae) {
  int answer1 = 1;
  int answer2 = 2;
  int answer3 = 3;
  int answer4 = 4;

  if (ae.getActionCommand().equals("Submit Answer")) {
    if (answerChoice1.isSelected() && triviaQuestions.get(num).getRightAnswer() == 1) {
      answer.setText("Correct! You've earned 5 points!");
      score += triviaQuestions.get(num).getUserScore();
    }

    else if (answerChoice2.isSelected() && triviaQuestions.get(num).getRightAnswer() == 2) {
      answer.setText("Correct! You've earned 5 points!");
      score += triviaQuestions.get(num).getUserScore();
    }
    
    else if (answerChoice3.isSelected() && triviaQuestions.get(num).getRightAnswer() == 3) {
      answer.setText("Correct! You've earned 5 points!");
      score += triviaQuestions.get(num).getUserScore();
    }

    else if (answerChoice4.isSelected() && triviaQuestions.get(num).getRightAnswer() == 4) {
      answer.setText("Correct! You've earned 5 points!");
      score += triviaQuestions.get(num).getUserScore();
    }

    else {
      answer.setText("Sorry, that answer is incorrect :(");
    }

    totalScore.setText("Total Points: " + score);
  }

  else if (ae.getActionCommand().equals("Next Question")) {
    NextQuestion();
    triviaQuestions.get(num).getRightAnswer();
    triviaQuestions.get(num).getUserScore();
  }

  else if (ae.getActionCommand().equals("Enter Game")) {
    String playerName = nameField.getText();
    welcome.setText("Welcome " + playerName +" to the Tech Trivia Game!");
  }

  else if (ae.getActionCommand().equals("Exit Game")) {
    System.exit(0); //system.exit() simply shuts down the instance running (imitating leaving the game).
  }

  else {
    answer.setText("Please hit Next Question or Submit");
  }
}
}