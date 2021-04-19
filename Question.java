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
Question(String question, String answer1, String answer2, String answer3, String answer4, int rightAnswer, int userScore){
  this.question = question;
  this.answer1 = answer1;
  this.answer2 = answer2;
  this.answer3 = answer3;
  this.answer4 = answer4;
  this.rightAnswer = rightAnswer;
  this.userScore = userScore;
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