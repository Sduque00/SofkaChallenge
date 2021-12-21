package com.sofka.challenge.answerandquestions.controllers;

import static com.sofka.challenge.answerandquestions.controllers.Resources.getDate;

import com.sofka.challenge.answerandquestions.models.Category;
import com.sofka.challenge.answerandquestions.models.Game;
import com.sofka.challenge.answerandquestions.models.Question;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ConnectionDB {

  public static final String USER_DB = "root";
  public static final String PASSWORD_DB = "";
  public static final String NAME_DB = "answerandquestions";
  public static final String HOSTNAME_DB = "localhost";
  public static final String DRIVER_DB = "com.mysql.cj.jdbc.Driver";
  public static final String PORT_DB = "3306";
  public static final String URL_DRIVER_DB = "jdbc:mysql://" + HOSTNAME_DB + ":" + PORT_DB + "/" + NAME_DB + "?serverTimezone=UTC";
  private static Connection connection = null;
  private static DefaultTableModel tableModel;

  public static List<Question> getQuestions(String query) {

    List<Question> questions = new ArrayList<>();

    try {

      PreparedStatement preparedStatement = connection.prepareStatement(query);
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {

        List<String> options = new ArrayList<>();

        options.add(resultSet.getString("option_1"));
        options.add(resultSet.getString("option_2"));
        options.add(resultSet.getString("option_3"));
        options.add(resultSet.getString("answer"));

        questions.add(new Question(
            resultSet.getInt("id"),
            resultSet.getString("question"),
            resultSet.getString("answer"),
            options,
            resultSet.getInt("level")
        ));
      }

    } catch (SQLException e) {
      System.out.println("Error:" + e.getMessage());
    }

    return questions;
  }

  public static List<Category> getCategories(String query) {

    List<Category> categories = new ArrayList<>();

    try {
      connection = DriverManager.getConnection(URL_DRIVER_DB, USER_DB, PASSWORD_DB);

      PreparedStatement preparedStatement = connection.prepareStatement(query);
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        categories.add(new Category(resultSet.getString("name"), getQuestions("SELECT * FROM question WHERE id_category = "+resultSet.getInt("id"))));
      }

    } catch (SQLException e) {
      System.out.println("Error categories:" + e.getMessage());
    } finally {

      try {
        if (null != connection) {
          connection.close();
          System.out.println("closed");
        }
      } catch (SQLException e) {
        System.out.println("Database not found Sorry, there was an error. Try again later.");
      }
    }

    return categories;
  }

  public static void saveGame(Game game){

    String query = "INSERT INTO game (player,round,status,accumulate,date,id_question) VALUES (?,?,?,?,?,?)";

    try {
      connection = DriverManager.getConnection(URL_DRIVER_DB, USER_DB, PASSWORD_DB);
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, game.getPlayer());
      preparedStatement.setInt(2, game.getRound());
      preparedStatement.setString(3, game.getStatus());
      preparedStatement.setDouble(4, game.getAccumulate());
      preparedStatement.setString(5, getDate());
      preparedStatement.setInt(6, game.getIdQuestion());
      preparedStatement.execute();

      JOptionPane.showMessageDialog(null, "Se ha guardado el registro");

    } catch (SQLException e) {
      System.out.println("Error:" + e.getMessage());
    }finally {

      try {
        if (null != connection) {
          connection.close();
          System.out.println("DB closed");
        }
      } catch (SQLException e) {
        System.out.println("Database not found Sorry, there was an error. Try again later.");
      }
    }
  }


  public static boolean readTable(JTable tabla, String query) {

    try {
      connection = DriverManager.getConnection(URL_DRIVER_DB, USER_DB, PASSWORD_DB);

      PreparedStatement preparedStatement = connection.prepareStatement(query);
      ResultSet resultSet = preparedStatement.executeQuery();
      ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
      ArrayList<Object[]> data = new ArrayList<>();
      while (resultSet.next()) {
        Object[] rows = new Object[resultSetMetaData.getColumnCount()];
        for (int i = 0; i < rows.length; i++) {
          rows[i] = resultSet.getObject(i + 1);
        }
        data.add(rows);
      }

      if (data.isEmpty()) {
        System.out.println("Tabla vacía");
        return false;
      } else {
        tableModel = (DefaultTableModel) tabla.getModel();
        IntStream.range(0, data.size()).forEach(i -> tableModel.addRow(data.get(i)));
        return true;
      }

    } catch (SQLException e) {
      System.out.println("Database not found Sorry, there was an error. Try again later.");
    } finally {

      try {
        if (null != connection) {
          connection.close();
        }
      } catch (SQLException e) {
        System.out.println("Database not found Sorry, there was an error. Try again later.");
      }
    }
    return false;
  }

}
