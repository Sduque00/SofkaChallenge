package com.sofka.challenge.answerandquestions.views;

import com.sofka.challenge.answerandquestions.controllers.Resources;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Settings extends JDialog implements ActionListener {

  private JButton addQuestions, addCategories;
  private Resources resources = new Resources();

  public Settings(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  private void createComponents() {

    setLayout(null);

    addCategories = resources.getButton("Add Category", getContentPane().getBackground(), this, this);
    addCategories.setBounds(20, 200, 110, 30);

    addQuestions = resources.getButton("Add Question", getContentPane().getBackground(), this, this);
    addQuestions.setBounds(150, 200, 110, 30);
  }

  public void start(JFrame parent) {
    setSize(300, 300);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Settings");
    parent.setVisible(false);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == addCategories){
      JOptionPane.showMessageDialog(null, "Pantalla para agregar categorías");
    }else if (e.getSource() == addQuestions){
      JOptionPane.showMessageDialog(null, "Pantalla para agregar preguntas");
    }
  }
}
