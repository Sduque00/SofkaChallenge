package com.sofka.challenge.answerandquestions.views;

import static com.sofka.challenge.answerandquestions.controllers.ConnectionDB.getCategories;
import static com.sofka.challenge.answerandquestions.controllers.ConnectionDB.saveGame;
import static com.sofka.challenge.answerandquestions.controllers.Resources.MEDIUM;
import static com.sofka.challenge.answerandquestions.controllers.Resources.getDate;
import static com.sofka.challenge.answerandquestions.models.Round.getDefaultRound;
import static java.awt.Font.BOLD;

import com.sofka.challenge.answerandquestions.controllers.Resources;
import com.sofka.challenge.answerandquestions.models.Category;
import com.sofka.challenge.answerandquestions.models.Game;
import com.sofka.challenge.answerandquestions.models.Question;
import com.sofka.challenge.answerandquestions.models.Round;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class GameView extends JDialog implements ActionListener {

  private JComboBox<String> categoryList;
  private JTextField player;
  private List<JButton> responses = new ArrayList<>();
  private JButton start, exit;
  private JLabel lblQuestion, lblAccumulate, lblLevel;
  private Resources resources = new Resources();
  private static Round round = getDefaultRound();
  private List<Category> categories;
  private static Question currentQuestion;

  public GameView(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  private void createComponents() {

    setLayout(null);
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    categories = getCategories("SELECT * FROM category");

    JLabel title = resources.getLabel("Category", Color.RED, this, new Font("Times New Roman", BOLD, 20));
    title.setBounds(30, 10, 86, 30);

    start = resources.getButton("Start", getContentPane().getBackground(), this, this);
    start.setBounds(30, 160, 86, 30);

    exit = resources.getButton("Close", getContentPane().getBackground(), this, this);
    exit.setBounds(140, 160, 86, 30);

    categoryList = new JComboBox<>();
    categoryList.setBounds(30, 50, 220, 30);
    categoryList.setFont(MEDIUM);
    add(categoryList);

    DefaultComboBoxModel<String> defaultComboBoxModel = new DefaultComboBoxModel();
    defaultComboBoxModel.addElement("Select a option");

    categories.forEach(category -> {
      categoryList.addItem(category.getName());
      defaultComboBoxModel.addElement(category.getName());
    });

    categoryList.setModel(defaultComboBoxModel);

    player = new JTextField();
    player.setBounds(30, 100, 220, 40);
    add(player);

    lblQuestion = resources.getLabel("", Color.BLACK, this, new Font("Times New Roman", BOLD, 20));
    lblQuestion.setBounds(320, 30, 400, 60);
    lblQuestion.setVisible(false);

    lblAccumulate = resources.getLabel("", Color.BLACK, this,
        new Font("Times New Roman", BOLD, 20));
    lblAccumulate.setBounds(30, 300, 200, 30);
    lblAccumulate.setVisible(false);

    lblLevel = resources.getLabel("", Color.BLACK, this, new Font("Times New Roman", BOLD, 20));
    lblLevel.setBounds(30, 350, 200, 30);
    lblLevel.setVisible(false);

    responses.add(resources.getButton("", new Color(255, 255, 255), this, this));
    responses.get(0).setBounds(320, 100, 400, 30);
    responses.get(0).setVisible(false);

    responses.add(resources.getButton("", new Color(255, 255, 255), this, this));
    responses.get(1).setBounds(320, 180, 400, 30);
    responses.get(1).setVisible(false);

    responses.add(resources.getButton("", new Color(255, 255, 255), this, this));
    responses.get(2).setBounds(320, 260, 400, 30);
    responses.get(2).setVisible(false);

    responses.add(resources.getButton("", new Color(255, 255, 255), this, this));
    responses.get(3).setBounds(320, 340, 400, 30);
    responses.get(3).setVisible(false);

    add(responses.get(0));
    add(responses.get(1));
    add(responses.get(2));
    add(responses.get(3));
  }

  public void start(JFrame parent) {
    setSize(750, 500);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Game");
    parent.setVisible(false);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == exit) {
      dispose();
    } else if (e.getSource() == start) {
      if ("Start".equals(start.getText())){
        System.out.println(1);
        btnStart();
      }else {
        System.out.println(2);
        btnExit();
      }

    } else {

      for (int i = 0; i < responses.size(); i++) {

        if (e.getSource() == responses.get(i)) {
          checkQuestion(responses.get(i).getText(), i);
        }
      }
    }
  }

  private void btnExit(){

    saveGame(new Game(
        player.getText(),
        round.getLevel(),
        "Cobarde",
        round.getAccumulate(),
        currentQuestion.getId()
    ));
    reset();
  }

  private void btnStart() {

    if ("Select a option".equals(categoryList.getSelectedItem())) {
      JOptionPane.showMessageDialog(null, "Selecciona una categoría");
    } else if (player.getText().isEmpty()) {
      JOptionPane.showMessageDialog(null, "Ingresa un nombre");
    } else {
      getNextQuestion();
    }
  }

  private void checkQuestion(String response, int index) {

    responses.forEach(r -> r.setBackground(Color.red));

    if (response.equals(currentQuestion.getAnswer())) {
      answerCorrect(index);
    } else {
      answerIncorrect();
    }
  }

  private void answerCorrect(int index) {

    responses.get(index).setBackground(Color.GREEN);

    if (round.getLevel() < 5) {

      JOptionPane.showMessageDialog(null, "Respuesta correcta");

      responses.forEach(r -> {
        r.setBackground(getContentPane().getBackground());
        r.setVisible(false);
      });

      round.setLevel(round.getLevel() + 1);
      round.setAccumulate(round.getAccumulate() + round.getValue());
      round.setValue(round.getLevel() * 1000);
      getNextQuestion();
    } else {

      JOptionPane.showMessageDialog(null, player.getText() + " eres el campeón de la champions!!");

      saveGame(new Game(
          player.getText(),
          round.getLevel(),
          "Winner",
          round.getAccumulate(),
          currentQuestion.getId()
      ));

      reset();
    }
  }

  private void answerIncorrect() {

    responses.forEach(r -> {

      if (r.getText().equals(currentQuestion.getAnswer())) {
        r.setBackground(Color.GREEN);
      }
    });

    JOptionPane.showMessageDialog(null, "Respuesta incorrecta");

    saveGame(new Game(
        player.getText(),
        round.getLevel(),
        "Loser",
        round.getAccumulate(),
        currentQuestion.getId()
    ));

    reset();
  }

  private void getNextQuestion() {

    Category categorySelected = categories.stream()
        .filter(category -> category.getName().equals(categoryList.getSelectedItem())).findFirst()
        .get();
    currentQuestion = categorySelected.getQuestions().stream()
        .filter(q -> q.getLevel() == round.getLevel()).findFirst().get();

    lblQuestion.setText(currentQuestion.getQuestion());
    lblQuestion.setVisible(true);

    lblLevel.setText("Level: " + round.getLevel() + " [$" + round.getValue() + "]");
    lblLevel.setVisible(true);

    lblAccumulate.setText("Accumulate: $" + round.getAccumulate());
    lblAccumulate.setVisible(true);
    start.setText("Stop");

    exit.setEnabled(false);
    categoryList.setEnabled(false);
    player.setEditable(false);

    List<String> options = currentQuestion.getOptions();
    Collections.shuffle(options);

    for (int i = 0; i < responses.size(); i++) {
      responses.get(i).setText(options.get(i));
      responses.get(i).setVisible(true);
    }
  }

  private void reset() {

    responses.forEach(r -> {
      r.setBackground(getContentPane().getBackground());
      r.setVisible(false);
    });
    lblQuestion.setVisible(false);
    lblAccumulate.setVisible(false);
    lblLevel.setVisible(false);
    categoryList.setEnabled(true);
    player.setEditable(true);
    exit.setEnabled(true);
    start.setText("Start");
    player.setText("");
    categoryList.setSelectedIndex(0);
    round.setLevel(1);
    round.setAccumulate(0);
    round.setValue(1000);
  }
}
