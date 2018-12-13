package com.upm.lo.quiz.Model;

public class Quiz {

    public String q1, q2, q3, quizCount;

    public Quiz(String q1, String q2, String q3, String quizCount) {
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
        this.quizCount = quizCount;
    }

    public Quiz() {

    }

    public String getQ1() {
        return q1;
    }

    public void setQ1(String q1) {
        this.q1 = q1;
    }

    public String getQ2() {
        return q2;
    }

    public void setQ2(String q2) {
        this.q2 = q2;
    }

    public String getQ3() {
        return q3;
    }

    public void setQ3(String q3) {
        this.q3 = q3;
    }

    public String getQuizCount() {
        return quizCount;
    }

    public void setQuizCount(String quizCount) {
        this.quizCount = quizCount;
    }
}
