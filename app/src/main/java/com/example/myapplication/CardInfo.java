package com.example.myapplication;

public class CardInfo {

    private String word;
    private String definition;
    private int answer;

    public CardInfo() {}

    public CardInfo(String word, String definition, int answer) {
        this.word = word;
        this.definition = definition;
        this.answer = answer;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
