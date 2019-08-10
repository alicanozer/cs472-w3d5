package cs472.w3d5.model;

public class SessionData {
    public String currentQuestion;
    public boolean error;
    public String answer;
    public int score;
    public int attempt;
    public int hint;
    public String grade;
    public Quiz quiz;

    public void calculateGrade(){
        if(score < 1)
            grade="NC";
        else if(score < 2)
            grade="C";
        else if(score < 3)
            grade="B";
        else if(score < 4)
            grade="C";
        else if(score < 5)
            grade="A";
        else if(score < 6)
            grade="A+";
    }
}
