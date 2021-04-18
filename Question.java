/**
*This class prints out the questions
* @author Sam Dobrowolski, Jamie Schaffer, Joseph Desantis, Christopher Sonnhalter
* @version 4.18.2021
*/

class Question{
  public String question;
  public String answer1;
  public String answer2;
  public String answer3;
  public String answer4;
  public int rightAnswer;
  public int userScore;

/** Constructor
* @param
*/
Question(String aQuestion, String aAnswer1, String aAnswer2, String aAnswer3, String aAnswer4, int aRightAnswer, int aUserScore){
  question = aQuestion;
  aAnswer1 = answer1;
  aAnswer2 = answer2;
  aAnswer3 = answer3;
  aAnswer4 = answer4;
  rightAnswer = aRightAnswer;
  userScore = aUserScore;
}

/**
* Accessor Methods that returns question to users
@param getQuestion()
*/

String getQuestion(){
  return question;
}

String getAnswer1(){
  return answer1;
}

String getAnswer2(){
  return answer2;
}

String getAnswer3(){
  return answer3;
}

String getAnswer4(){
  return answer4;
}

int getRightAnswer(){
  return rightAnswer;
}

int getUserScore(){
  return userScore;
}
}