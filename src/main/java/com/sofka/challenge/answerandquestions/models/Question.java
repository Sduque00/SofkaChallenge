package com.sofka.challenge.answerandquestions.models;

import java.util.List;

public class Question {

  private int id;
  private String question;
  private String answer;
  private List<String> options;
  private int level;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public List<String> getOptions() {
    return options;
  }

  public void setOptions(List<String> options) {
    this.options = options;
  }

  public Question(int id, String question, String answer, List<String> options, int level) {
    this.setId(id);
    this.question = question;
    this.answer = answer;
    this.options = options;
    this.level = level;
  }
}
