package com.sofka.challenge.answerandquestions.views;

import static java.awt.Font.BOLD;

import com.sofka.challenge.answerandquestions.controllers.Resources;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Index extends JFrame implements ActionListener {

  private JLabel title, image;
  private JButton start, records, exit, settings;
  private Resources resources = new Resources();

  public Index() {
    createComponents();
  }

  protected static void run() {

    Index index = new Index();

    index.setSize(400, 400);
    index.setResizable(false);
    index.setLocationRelativeTo(null);
    index.setTitle("Answer & Question");
    index.setVisible(true);
  }

  private void createComponents() {

    setLayout(null);

    title = resources.getLabel("Answers & Questions", Color.BLACK, this,
        new Font("Times New Roman", BOLD, 30));
    title.setBounds(40, 20, 300, 40);

    start = resources.getButton("Start", getContentPane().getBackground(), this, this);
    start.setBounds(30, 110, 86, 30);

    records = resources.getButton("Records", getContentPane().getBackground(), this, this);
    records.setBounds(30, 160, 86, 30);

    settings = resources.getButton("Settings", getContentPane().getBackground(), this, this);
    settings.setBounds(30, 210, 86, 30);

    exit = resources.getButton("Exit", getContentPane().getBackground(), this, this);
    exit.setBounds(30, 260, 86, 30);

    image = resources.getLabel("", getContentPane().getBackground(), this, null);
    image.setBounds(150, 120, 200, 200);
    image.setIcon(new ImageIcon(resources.getImage("index.png")));
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == exit) {
      System.exit(0);
    }else if (e.getSource() == records){
      new RecordsTable(this, true).showRecords();
      setVisible(true);
    }else if (e.getSource() == start){
      new GameView(this, true).start(this);
      setVisible(true);
    }else if (e.getSource() == settings){
      new Settings(this, true).start(this);
      setVisible(true);
    }
  }
}


