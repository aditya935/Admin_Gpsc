package com.techwithadi.gpscbudy.Models;

public class TestModel {
    private String TestId;
    private int Topscore;
    private Long Time;

    public TestModel(String testId, int topscore, Long time) {
        TestId = testId;
        Topscore = topscore;
        Time = time;
    }

    public String getTestId() {
        return TestId;
    }

    public void setTestId(String testId) {
        TestId = testId;
    }

    public int getTopscore() {
        return Topscore;
    }

    public void setTopscore(int topscore) {
        Topscore = topscore;
    }

    public Long getTime() {
        return Time;
    }

    public void setTime(Long time) {
        Time = time;
    }
}
