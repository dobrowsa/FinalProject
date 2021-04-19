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

public class Game implements ActionListener {

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
  ButtonGroup group;

  int score = 0;

  ArrayList < Question > techQuestions = new ArrayList < Question > ();

  /** 
  @method
  Default method to read data and create trivia game. sets up all details in the frame and adds data from ArrayList.
  */

  Game() {
    String fileName;
    FileReader myFile;

    fileName = "triviaQuestions.txt";
    String question = "", answer1 = "", answer2 = "", answer3 = "", answer4 = "", rightAnswer = "", pointValue = "";

    try {
      myFile = new FileReader(fileName);
      BufferedReader reader = new BufferedReader(myFile);

      while (reader.ready()) {
        question = reader.readLine();
        answer1 = reader.readLine();
        answer2 = reader.readLine();
        answer3 = reader.readLine();
        answer4 = reader.readLine();
        rightAnswer = reader.readLine();
        pointValue = reader.readLine();

        Question aQuestion = new Question(question, answer1, answer2, answer3, answer4, Integer.parseInt(rightAnswer), Integer.parseInt(pointValue));
        techQuestions.add(aQuestion);
      }
      reader.close();
    } catch (IOException excpt) {
      System.out.println("Something went wrong: " + excpt);
    }

    FileWriter toWriteFile;
    try {
      toWriteFile = new FileWriter("score.txt");
      BufferedWriter result = new BufferedWriter(toWriteFile);

      result.flush();
      result.close();
    } catch (IOException exception) {
      exception.printStackTrace(); // I've used this at work to help identify errors easier
    }

    try {
      toWriteFile = new FileWriter("score.txt");
      BufferedWriter result = new BufferedWriter(toWriteFile);
      result.write("Player Score: " + pointValue);
      result.newLine();

      result.flush();
      result.close();
    } catch (IOException exception) {
      exception.printStackTrace();
    }

    JFrame frame = new JFrame("Tech Trivia");
    frame.setLayout(new FlowLayout());
    frame.setSize(700, 300);

    answerChoice1 = new JRadioButton(techQuestions.get(0).getAnswer1());
    answerChoice2 = new JRadioButton(techQuestions.get(0).getAnswer2());
    answerChoice3 = new JRadioButton(techQuestions.get(0).getAnswer3());
    answerChoice4 = new JRadioButton(techQuestions.get(0).getAnswer4());

    group = new ButtonGroup();
    group.add(answerChoice1);
    group.add(answerChoice2);
    group.add(answerChoice3);
    group.add(answerChoice4);

    enterName = new JButton("Enter Name");
    enterName.setFont(new Font("Times New Roman", Font.BOLD, 16));
    submitButton = new JButton("Submit Answer");
    nextButton = new JButton("Next Question");
    exitButton = new JButton("Exit Game");
    exitButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
    exitButton.setForeground(Color.RED);

    nameField = new JTextField(15);
    nameField.setActionCommand("TF");
    nameField.addActionListener(this);

    enterName.addActionListener(this);
    submitButton.addActionListener(this);
    nextButton.addActionListener(this);
    exitButton.addActionListener(this);

    name = new JLabel("Enter your name here: ");
    name.setForeground(Color.RED);

    nameField = new JTextField("               ");
    welcome = new JLabel("Welcome to this Tech Trivia Game");
    totalScore = new JLabel("Your point total score is: ");

    ask = new JLabel(techQuestions.get(0).getQuestion() + " This Question is worth " + techQuestions.get(0).getPointValue() + " points");
    answer = new JLabel("");
    correctAnswer = new JLabel("");

    frame.add(welcome);
    frame.add(name);
    frame.add(nameField);
    frame.add(enterName);
    frame.add(exitButton);
    frame.add(ask);
    frame.add(answer);
    frame.add(correctAnswer);
    frame.add(answerChoice1);
    frame.add(answerChoice2);
    frame.add(answerChoice3);
    frame.add(answerChoice4);
    frame.add(nextButton);
    frame.add(submitButton);
    frame.add(totalScore);

    frame.setVisible(true);
    ask.setVisible(false);
    welcome.setVisible(false);
    totalScore.setVisible(false);
    answerChoice1.setVisible(false);
    answerChoice2.setVisible(false);
    answerChoice3.setVisible(false);
    answerChoice4.setVisible(false);
    answer.setVisible(false);
    submitButton.setVisible(false);
    nextButton.setVisible(false);
  }

  int num = 0;

  /* @Uses arraylist to move to next question while resetting each answer choice.
   */
  void NextQuestion() {
      ask.setText("");
      answerChoice1.setText("");
      answerChoice2.setText("");
      answerChoice3.setText("");
      answerChoice4.setText("");
      answer.setText("");

      num++;

      ask.setText(techQuestions.get(num).getQuestion() + " This Question is worth " + techQuestions.get(0).getPointValue() + " points");
      answerChoice1.setText(techQuestions.get(num).getAnswer1());
      answerChoice2.setText(techQuestions.get(num).getAnswer2());
      answerChoice3.setText(techQuestions.get(num).getAnswer3());
      answerChoice4.setText(techQuestions.get(num).getAnswer4());

      System.out.println(techQuestions.get(num).getQuestion());
      System.out.println(techQuestions.get(num).getAnswer1());
      System.out.println(techQuestions.get(num).getAnswer2());
      System.out.println(techQuestions.get(num).getAnswer3());
      System.out.println(techQuestions.get(num).getAnswer4());

      submitButton.setVisible(true);
  }
  

  /* allows buttons to work as specified
   */

  public void actionPerformed(ActionEvent ae) {
    int answer1 = 1;
    int answer2 = 2;
    int answer3 = 3;
    int answer4 = 4;

    if (ae.getActionCommand().equals("Submit Answer")) {
      if (answerChoice1.isSelected() && techQuestions.get(num).getRightAnswer() == 1) {
        answer.setText("Correct! You've earned 5 points!");
        answer.setForeground(Color.GREEN);
        score += techQuestions.get(num).getPointValue();
        submitButton.setVisible(false);
        nextButton.setVisible(true);
      } else if (answerChoice2.isSelected() && techQuestions.get(num).getRightAnswer() == 2) {
        answer.setText("Correct! You've earned 5 points!");
        answer.setForeground(Color.GREEN);
        score += techQuestions.get(num).getPointValue();
        submitButton.setVisible(false);
        nextButton.setVisible(true);
      } else if (answerChoice3.isSelected() && techQuestions.get(num).getRightAnswer() == 3) {
        answer.setText("Correct! You've earned 5 points!");
        answer.setForeground(Color.GREEN);
        score += techQuestions.get(num).getPointValue();
        submitButton.setVisible(false);
        nextButton.setVisible(true);
      } else if (answerChoice4.isSelected() && techQuestions.get(num).getRightAnswer() == 4) {
        answer.setText("Correct! You've earned 5 points!");
        answer.setForeground(Color.GREEN);
        score += techQuestions.get(num).getPointValue();
        submitButton.setVisible(false);
        nextButton.setVisible(true);
      } else {
        answer.setText("Sorry, that answer is incorrect :(");
        answer.setForeground(Color.RED);
        submitButton.setVisible(false);
        nextButton.setVisible(true);
      }

      totalScore.setText("Total Points: " + score);

    } else if (ae.getActionCommand().equals("Next Question")) {
      if (num < techQuestions.size() - 1) {
        group.clearSelection(); //clears the already clicked button for prior question
        NextQuestion();
        techQuestions.get(num).getRightAnswer();
        techQuestions.get(num).getPointValue();
        name.setVisible(false);
        nameField.setVisible(false);
        enterName.setVisible(false);
        welcome.setVisible(false);
        nextButton.setVisible(false);
      } else {
        welcome.setText("Game Complete! You ended with " + score + " points!");
      ask.setVisible(false);
      welcome.setVisible(true);
      totalScore.setVisible(false);
      answerChoice1.setVisible(false);
      answerChoice2.setVisible(false);
      answerChoice3.setVisible(false);
      answerChoice4.setVisible(false);
      answer.setVisible(false);
      submitButton.setVisible(false);
      nextButton.setVisible(false);
      exitButton.setVisible(false);
      }
    } else if (ae.getActionCommand().equals("Enter Name")) {
      String playerName = nameField.getText();
      welcome.setText("Welcome" + playerName + " to this Tech Trivia Game!");
      name.setVisible(false);
      nameField.setVisible(false);
      enterName.setVisible(false);
      ask.setVisible(true);
      welcome.setVisible(true);
      totalScore.setVisible(true);
      answerChoice1.setVisible(true);
      answerChoice2.setVisible(true);
      answerChoice3.setVisible(true);
      answerChoice4.setVisible(true);
      answer.setVisible(true);
      submitButton.setVisible(true);
      exitButton.setVisible(true);
    } else if (ae.getActionCommand().equals("Exit Game")) {

      welcome.setText("Game Complete! You ended with " + score + " points!");
      ask.setVisible(false);
      welcome.setVisible(true);
      totalScore.setVisible(false);
      answerChoice1.setVisible(false);
      answerChoice2.setVisible(false);
      answerChoice3.setVisible(false);
      answerChoice4.setVisible(false);
      answer.setVisible(false);
      submitButton.setVisible(false);
      nextButton.setVisible(false);
      exitButton.setVisible(false);
    }
  }
}