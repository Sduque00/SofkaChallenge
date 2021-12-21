package com.sofka.challenge.answerandquestions.models;

import javax.swing.table.DefaultTableModel;

public class Table extends DefaultTableModel {

  public boolean isCellEditable(int row, int column) {
    return false;
  }
}