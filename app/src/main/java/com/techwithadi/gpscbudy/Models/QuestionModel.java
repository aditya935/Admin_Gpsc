package com.techwithadi.gpscbudy.Models;

public class QuestionModel {
    private String Question;
    private String OptionA;
    private String OptionB;
    private String OptionC;
    private String OptionD;
    private int CurrectAns;
    private int SelectedAns;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getOptionA() {
        return OptionA;
    }

    public void setOptionA(String optionA) {
        OptionA = optionA;
    }

    public String getOptionB() {
        return OptionB;
    }

    public void setOptionB(String optionB) {
        OptionB = optionB;
    }

    public String getOptionC() {
        return OptionC;
    }

    public void setOptionC(String optionC) {
        OptionC = optionC;
    }

    public String getOptionD() {
        return OptionD;
    }

    public void setOptionD(String optionD) {
        OptionD = optionD;
    }

    public int getCurrectAns() {
        return CurrectAns;
    }

    public void setCurrectAns(int currectAns) {
        CurrectAns = currectAns;
    }


    public int getSelectedAns() {
        return SelectedAns;
    }

    public void setSelectedAns(int selectedAns) {
        SelectedAns = selectedAns;
    }

    public QuestionModel(String question, String optionA, String optionB, String optionC, String optionD, int currectAns , int SelectedAns , int status) {
        Question = question;
        OptionA = optionA;
        OptionB = optionB;
        OptionC = optionC;
        OptionD = optionD;
        CurrectAns = currectAns;
        this.SelectedAns=SelectedAns;
        this.status=status;
    }
}
