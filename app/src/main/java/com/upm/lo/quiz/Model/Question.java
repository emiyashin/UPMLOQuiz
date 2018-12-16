package com.upm.lo.quiz.Model;

public class Question {

    public String question, option1, option2, option3, option4, answer, questionCount;
    public int intLO;
    public static String [] LO;
    public static int [] questionArray;

    static {
        LO = new String[6];
        LO[0] = "Knowledge";
        LO[1] = "Comprehension";
        LO[2] = "Application";
        LO[3] = "Analysis";
        LO[4] = "Synthesis";
        LO[5] = "Evaluation";
    }

    static {
        questionArray = new int[3];
    }

    public Question(String question, String option1, String option2, String option3, String option4, String answer, String questionCount, int intLO) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
        this.intLO = intLO;
        this.questionCount = questionCount;
    }

    public Question() {

    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getIntLO() {
        return intLO;
    }

    public void setIntLO(int intLO) {
        this.intLO = intLO;
    }

    public String getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(String questionCount) {
        this.questionCount = questionCount;
    }
}
