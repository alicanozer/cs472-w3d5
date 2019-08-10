package cs472.w3d5.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Quiz {

    private int numberOfQuestions;
    private int numberOfCorrects;
    private int currentQuestionIndex;
    private List<Question> questions;


    public Quiz(){
        this.numberOfQuestions=0;
        this.numberOfCorrects=0;
        this.currentQuestionIndex=0;

        questions = new ArrayList<>();
        Random r = new Random();
        String[] operands = new String[]{"+", "-", "*", "^"};

        questions.add(new Question(3, "^",2));
        numberOfQuestions++;

        for (int i = 0; i < 4; i++) {
            questions.add(new Question(r.nextInt(15)+1,operands[r.nextInt(4)],r.nextInt(15)+1));
            numberOfQuestions++;
        }
    }


    public boolean isCorrect(String s){
        try {
            return (questions.get(currentQuestionIndex).getResult() == Integer.parseInt(s.trim()));
        }catch (NumberFormatException e) {
            return false;
        }
    }

    public void scoreAnswer(){
        this.numberOfCorrects++;
        this.currentQuestionIndex++;
    }

    public int getNumCorrect(){
        return this.numberOfCorrects;
    }

    public String getCurrentQuestion(){
        return questions.get(currentQuestionIndex).getQuestion();
    }

    public void goNextQuestion(){
        this.currentQuestionIndex++;
    }

    public int getCurrentAnswer(){
        return questions.get(currentQuestionIndex).getResult();
    }

    public boolean isOver() {
        return numberOfQuestions == currentQuestionIndex;
    }

}
