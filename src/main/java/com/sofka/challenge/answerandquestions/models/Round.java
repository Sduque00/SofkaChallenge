package com.sofka.challenge.answerandquestions.models;

public class Round {

  private int level;
  private double value;
  private double accumulate;

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public double getAccumulate() {
    return accumulate;
  }

  public void setAccumulate(double accumulate) {
    this.accumulate = accumulate;
  }

  public static Round getDefaultRound(){
    Round round = new Round();
    round.setAccumulate(0);
    round.setLevel(1);
    round.setValue(round.getLevel() * 1000);
    return round;
  }
}
