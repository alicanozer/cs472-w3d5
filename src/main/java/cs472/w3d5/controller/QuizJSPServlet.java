package cs472.w3d5.controller;

import cs472.w3d5.model.Quiz;
import cs472.w3d5.model.SessionData;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/QuizJSPServlet")
public class QuizJSPServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionData quiz = new SessionData();

        quiz.quiz = new Quiz();
        quiz.currentQuestion = quiz.quiz.getCurrentQuestion();
        quiz.score = quiz.quiz.getNumCorrect();
        quiz.error = false;
        quiz.answer = null;
        quiz.attempt = 0;
        quiz.hint = quiz.quiz.getCurrentAnswer();
        quiz.grade = "NC";

        HttpSession session = request.getSession();
        session.setAttribute("sdata", quiz);

        RequestDispatcher dispatcher = request.getRequestDispatcher("ShowQuestion.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Object o = session.getAttribute("sdata");
        String answer = request.getParameter("txtAnswer");

        if (o != null) {
            SessionData quiz = (SessionData) o;

            if (request.getParameter("btnNext") != null) {

                quiz.currentQuestion = quiz.quiz.getCurrentQuestion();
                quiz.score = quiz.quiz.getNumCorrect();
                quiz.error = false;
                quiz.answer = answer;
                quiz.hint = quiz.quiz.getCurrentAnswer();

                if (answer != null) {
                    if (quiz.quiz.isCorrect(answer)) {
                        quiz.quiz.scoreAnswer();
                        quiz.currentQuestion = quiz.quiz.getCurrentQuestion();
                        quiz.score = quiz.quiz.getNumCorrect();
                        quiz.attempt = 0;
                        quiz.hint = quiz.quiz.getCurrentAnswer();
                        quiz.calculateGrade();
                    } else {
                        quiz.attempt++;
                        quiz.error = true;
                        if(quiz.attempt == 3)
                            quiz.quiz.goNextQuestion();
                    }
                }

            } else if (request.getParameter("btnRestart") != null) {
                quiz.quiz = new Quiz();
                quiz.currentQuestion = quiz.quiz.getCurrentQuestion();
                quiz.score = quiz.quiz.getNumCorrect();
                quiz.error = false;
                quiz.answer = null;
                quiz.attempt = 0;
                quiz.hint = quiz.quiz.getCurrentAnswer();
                quiz.grade = "NC";
            }
            if (quiz.quiz.isOver()){
                RequestDispatcher dispatcher = request.getRequestDispatcher("Result.jsp");
                dispatcher.forward(request, response);
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("ShowQuestion.jsp");
        dispatcher.forward(request, response);
    }
}
