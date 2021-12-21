package com.sofka.challenge.answerandquestions.controllers;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Resources {

  public static final Font MEDIUM = new Font("Cambria", Font.PLAIN, 18);

  public JButton getButton(String name, Color color, ActionListener listener, Container container) {
    JButton button = new JButton(name);
    button.setBackground(color);
    container.add(button);
    button.addActionListener(listener);
    return button;
  }

  public JLabel getLabel(String text, Color color, Container container, Font font) {
    JLabel label = new JLabel(text, SwingConstants.CENTER);
    label.setForeground(color);
    label.setFont(font);
    container.add(label);
    return label;
  }

  public URL getImage(String image) {
    return this.getClass().getResource("/" + image);
  }

  public static String getDate() {

    Calendar date = new GregorianCalendar();
    DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);

    return dateFormat.format(date.getTime()) + " - " + date.get(Calendar.HOUR_OF_DAY) + ":"
        + date.get(Calendar.MINUTE);
  }
}