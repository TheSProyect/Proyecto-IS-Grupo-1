package main.models;

public class Result {
    private int[] elections;
    private float score;

    public void setScore(float numCorrectQuestions){
        score=numCorrectQuestions;
    }
    public float getScore(){
        return score;
    }
}
