package cs472.w3d5.model;

public class Question {
    private int number1;
    private int number2;
    private String operator;
    private int result;

    public Question(int number1, String operator, int number2) {
        this.number1 = number1;
        this.number2 = number2;
        this.operator = operator;

        if (operator == "+") {
            this.result = number1 + number2;
        } else if (operator == "-"){
            this.result = number1 - number2;
        }else if(operator == "*") {
            this.result = number1 * number2;
        }else if(operator == "^") {
            this.result = (int) Math.pow(number1, number2);
        }else
            this.result = 0;
    }

    public int getResult() {
        return result;
    }

    public String getQuestion(){
        return +number1 + " " + operator + " " + number2 + " ?";
    }
}
