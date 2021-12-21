package com.sofka.challenge.answerandquestions.views;

import static com.sofka.challenge.answerandquestions.controllers.ConnectionDB.readTable;

import com.sofka.challenge.answerandquestions.models.Table;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.stream.IntStream;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class RecordsTable extends JDialog {

  private Table model = new Table();
  public final JTable viewTable = new JTable(model);
  private String[] columns = {"ID", "PLAYER", "ROUND", "STATUS", "ACCUMULATE", "DATE", "QUESTION"};

  public RecordsTable(JFrame parent, boolean modal) {
    super(parent, modal);
    parent.setVisible(false);
    createComponents();
  }

  private void createComponents() {

    for (String column : columns) {
      model.addColumn(column);
    }

    viewTable.getTableHeader().setReorderingAllowed(false);
    JScrollPane scroll = new JScrollPane(viewTable);
    getContentPane().add(scroll, BorderLayout.CENTER);

    int[] sizes = {20, 50, 20, 30, 100, 30, 20};
    IntStream.range(0, viewTable.getColumnCount())
        .forEach(i -> viewTable.getColumnModel().getColumn(i).setPreferredWidth(sizes[i]));

    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    pack();

    TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(model);
    viewTable.setRowSorter(rowSorter);

    IntStream.range(0, columns.length).forEach(i -> {
      DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
      tcr.setHorizontalAlignment(SwingConstants.CENTER);
      viewTable.getColumnModel().getColumn(i).setCellRenderer(tcr);
    });

    repaint();
  }

  @Override
  public void paint(Graphics g) {
    Dimension d = getSize();
    Dimension m = getMaximumSize();
    boolean resize = d.width > m.width || d.height > m.height;
    d.width = Math.min(m.width, d.width);
    d.height = Math.min(m.height, d.height);
    if (resize) {
      Point p = getLocation();
      setVisible(false);
      setSize(d);
      setLocation(p);
      setVisible(true);
    }
    super.paint(g);
  }

  public void cleanTable() {

    while (model.getRowCount() > 0) {
      model.removeRow(0);
    }
  }

  public void showRecords(){

    cleanTable();

    try {
      readTable(viewTable, "SELECT * FROM game");
    } catch (Exception e1) {
      System.out.println("Error");
    }

    setVisible(false);
    setSize(830, 400);
    setLocationRelativeTo(null);
    setMinimumSize(new Dimension(830, 400));
    setMaximumSize(new Dimension(1280, 720));
    setTitle("Registros de juegos");
    setVisible(true);
  }

}