package cs472.w3d5.controller;

import cs472.w3d5.model.Quiz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Quiz")
public class QuizServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        Object o = session.getAttribute("quiz");

        if(o != null){
            Quiz quiz = (Quiz)o;
            String answer = request.getParameter("txtAnswer");
            genQuizPage(quiz, out, true, answer);
        }
        else{
            Quiz quiz = new Quiz();
            session.setAttribute("quiz", quiz);
            genQuizPage(quiz, out, false, null);
        }
    }

    private void genQuizPage(Quiz sessQuiz, PrintWriter out, boolean initialized, String answer) {

        boolean error = false;
        if (initialized) {
            if (sessQuiz.isCorrect(answer.trim())) {
                error = false;
                sessQuiz.scoreAnswer();
                if (sessQuiz.isOver()) {
                    genQuizOverPage(out);
                    return;
                }
            } else {
                error = true;
            }
        }

        out.print("<html>");
        out.print("<head>");
        out.print("	<title>NumberQuiz</title>");
        out.print("</head>");
        out.print("<body>");
        out.print("	<form method='get'>");
        out.print("		<h3>Have fun with NumberQuiz!</h3>");
        out.print("<p>Your current score is: ");
        out.print(sessQuiz.getNumCorrect() + "</br></br>");
        out.print("<p>Enter the answer for the given equation! ");
        out.print(sessQuiz.getCurrentQuestion() + "</p>");

        out.print("<p>Your answer:<input type='text' name='txtAnswer' value='' /></p> ");

        /* if incorrect, then print out error message */
        if (error && (answer != null)) {  //REFACTOR?-- assumes answer null only when first open page
            out.print("<p style='color:red'>Your last answer was not correct! Please try again</p> ");
        }
        out.print("<p><input type='submit' name='btnNext' value='Next' /></p> ");

        out.print("</form>");
        out.print("</body></html>");
    }

    private void genQuizOverPage(PrintWriter out) {
        out.print("<html> ");
        out.print("<head >");
        out.print("<title>NumberQuiz is over</title> ");
        out.print("</head> ");
        out.print("<body> ");
        out.print("<p style='color:red'>The number quiz is over!</p>	</body> ");
        out.print("</html> ");
    }
}
