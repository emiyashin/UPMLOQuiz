package com.upm.lo.quiz.Model;

public class Quiz {

    public int q1, q2, q3;

    public Quiz(int q1, int q2, int q3) {
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
    }

    public Quiz() {

    }

    public int getQ1() {
        return q1;
    }

    public void setQ1(int q1) {
        this.q1 = q1;
    }

    public int getQ2() {
        return q2;
    }

    public void setQ2(int q2) {
        this.q2 = q2;
    }

    public int getQ3() {
        return q3;
    }

    public void setQ3(int q3) {
        this.q3 = q3;
    }

}
