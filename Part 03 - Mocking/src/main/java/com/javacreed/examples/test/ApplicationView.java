package com.javacreed.examples.test;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;

public class ApplicationView extends JFrame implements View {

  private static final long serialVersionUID = -7343389333426996183L;

  private Presenter presenter;

  private Action openAction;

  public ApplicationView() {
    initActions();
    initMenu();
    initToolbar();
  }

  private Presenter getPresenter() {
    if (presenter == null) {
      throw new IllegalStateException("The presenter is not set");
    }
    return presenter;
  }

  private void initActions() {
    openAction = new AbstractAction("Open", new ImageIcon(ApplicationView.class.getResource("/icons/open.png"))) {
      private static final long serialVersionUID = -8733978494447301385L;

      @Override
      public void actionPerformed(final ActionEvent e) {
        getPresenter().onOpen();
      }
    };
  }

  private void initMenu() {
    final JMenuBar menubar = new JMenuBar();
    setJMenuBar(menubar);

    final JMenu file = menubar.add(new JMenu("File"));
    file.add(openAction);
  }

  private void initToolbar() {
    final JToolBar toolBar = new JToolBar();
    add(toolBar, BorderLayout.PAGE_START);

    toolBar.add(openAction);
  }

  @Override
  public void setPresenter(final Presenter presenter) {
    this.presenter = presenter;
  }

  @Override
  public void showMessage(final String title, final String message) {
    JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
  }

  @Override
  public File showOpenFileChooser(final File selectedFile) {
    final JFileChooser fileChooser = new JFileChooser();
    fileChooser.setSelectedFile(selectedFile);
    if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
      return fileChooser.getSelectedFile();
    }

    return null;
  }

  @Override
  public void showWarning(final String title, final String message) {
    JOptionPane.showMessageDialog(this, message, title, JOptionPane.WARNING_MESSAGE);
  }
}
