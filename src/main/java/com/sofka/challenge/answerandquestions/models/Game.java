package com.sofka.challenge.answerandquestions.models;

import java.util.Date;

public class Game {

  private int id;
  private String player;
  private int round;
  private String status;
  private double accumulate;
  private int idQuestion;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getPlayer() {
    return player;
  }

  public void setPlayer(String player) {
    this.player = player;
  }

  public int getRound() {
    return round;
  }

  public void setRound(int round) {
    this.round = round;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public double getAccumulate() {
    return accumulate;
  }

  public void setAccumulate(double accumulate) {
    this.accumulate = accumulate;
  }

  public int getIdQuestion() {
    return idQuestion;
  }

  public void setIdQuestion(int idQuestion) {
    this.idQuestion = idQuestion;
  }

  public Game(String player, int round, String status, double accumulate, int idQuestion) {
    this.player = player;
    this.round = round;
    this.status = status;
    this.accumulate = accumulate;
    this.idQuestion = idQuestion;
  }
}
