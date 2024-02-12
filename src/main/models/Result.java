package main.models;

public class Result {
    private int[] elections;
    private int score;

    public void setScore(int numCorrectQuestions){
        score=numCorrectQuestions;
    }
    public int getScore(){
        return score;
    }
}
