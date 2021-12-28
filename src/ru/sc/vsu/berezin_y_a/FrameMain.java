package ru.sc.vsu.berezin_y_a;


import ru.sc.vsu.berezin_y_a.Util.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;


public class FrameMain extends JFrame {
    private JPanel panelMain;
    private JButton buttonExecute;
    private JSpinner spinnerShag;
    private JLabel labelImg;
    private JCheckBox upOrDownCheckBox;
    private JSpinner spinnerSize;
    private JButton buttonSave;
    private JFileChooser fileChooserSave;
    private JMenuBar menuBarMain;
    private JMenu menuLookAndFeel;

    public FrameMain() {
        this.setTitle("Task 12");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        spinnerShag.setValue(5);
        spinnerSize.setValue(500);
        this.setBounds(200, 100, 100, 100);
        this.pack();

        fileChooserSave = new JFileChooser();

        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Graphics file", "jpg");

        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");

        menuBarMain = new JMenuBar();
        setJMenuBar(menuBarMain);

        menuLookAndFeel = new JMenu();
        menuLookAndFeel.setText("View");
        menuBarMain.add(menuLookAndFeel);
        SwingUtils.initLookAndFeelMenu(menuLookAndFeel);

        this.pack();

        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {

                        Icon icon=labelImg.getIcon();
                        BufferedImage img = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
                        Graphics2D g2d = img.createGraphics();
                        icon.paintIcon(null, g2d, 0,0);
                        g2d.dispose();
                        String file = fileChooserSave.getSelectedFile().getPath();
                        if (!file.toLowerCase().endsWith(".png")) {
                            file += ".png";
                        }
                        Draw.writeImageToFile(file, img);
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });



        buttonExecute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int shag = (int) spinnerShag.getValue();
                    int size = (int) spinnerSize.getValue();
                    boolean on = upOrDownCheckBox.isSelected();
                    BufferedImage img = new BufferedImage(size,size,BufferedImage.TYPE_INT_BGR);
                    Graphics2D g2d=img.createGraphics();
                    Draw.paintRectangle(g2d,img.getWidth(),img.getHeight(),shag,on);
                    labelImg.setIcon(new ImageIcon(img));
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
