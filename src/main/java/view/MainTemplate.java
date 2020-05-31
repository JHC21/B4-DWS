package view;

import javax.swing.*;
import java.awt.*;

import view.template.RoundJPanel;

import javax.swing.*;
import java.awt.*;


public class MainTemplate extends JFrame {

    // JButtons
    String[] btnNames = { "A", "B", "C", "D"};
    JButton[] buttons = new JButton[btnNames.length];
    int[] btnX = new int[]{60, 400, 60, 400};
    int[] btnY = new int[]{125, 125, 275, 275};

    JButton aButton = new JButton("A");
    JButton bButton = new JButton("B");
    JButton cButton = new JButton("C");
    JButton dButton = new JButton("D");

    // JPanel
    RoundJPanel outerPanel = new RoundJPanel();
    RoundJPanel innerPanel = new RoundJPanel();
    JPanel functionPanel = new JPanel();

    // Color
    Color outerPanelColor = new java.awt.Color(220, 220, 220);
    Color innerPanelColor = new java.awt.Color(0, 0, 0);
    // Color innerPanelColor = new java.awt.Color(255, 255, 255);


    // Icons
    String[] iconNames = { "timekeeping_inactive", "timer_inactive", "stop_watch_inactive", "alarm_inactive",
            "global_time_inactive", "sleeping_time_inactive" };
    ImageIcon[] icons;
    JLabel[] labelIcons;

    public ImageIcon[] getResizedIcon(){

        icons = new ImageIcon[6];
        for(int i = 0 ; i < 6 ; i++){
            String iconFile = String.format("assets/%s.png", iconNames[i]);
            ImageIcon originIcon = new ImageIcon(iconFile);
            Image originImg = originIcon.getImage();
            Image changedImg= originImg.getScaledInstance(30, 30, Image.SCALE_SMOOTH );
            icons[i] = new ImageIcon(changedImg);
        }
        return icons;

    }

    public JLabel[] setIcons(ImageIcon[] icons){

        labelIcons = new JLabel[6];

        for(int i = 0 ; i < 6 ; i++){
            labelIcons[i] = new JLabel(icons[i],SwingUtilities.CENTER);
            labelIcons[i].setLayout(null);
            labelIcons[i].setBounds(15+(i*28), 250, 100, 100);
            labelIcons[i].setSize(30,30);
            labelIcons[i].setVisible(true);
        }
        return labelIcons;
    }

    public void setInnerPanel(){

        innerPanel.setLayout(null);
        innerPanel.setBounds(15,15,270,320);
        innerPanel.setSize(270,320);
        innerPanel.setOpaque(false);
        innerPanel.setBackground(innerPanelColor);

        icons = getResizedIcon();
        labelIcons = setIcons(icons);

        for(JLabel icon: labelIcons)
            innerPanel.add(icon);

        innerPanel.setVisible(true);

        // now function
        // 날짜
        JLabel date = new JLabel("20/05/03 (SUN)");
        date.setLayout(null);
        date.setForeground(Color.WHITE);
        date.setBounds(50, 15, 150, 150);
        date.setFont(new Font("NanumSquare", Font.BOLD,22));
        date.setFont(date.getFont().deriveFont(22.f));
        date.setSize(200,50);
        date.setVisible(true);

        // AM
        JLabel clockTypeAM = new JLabel("AM");
        clockTypeAM.setLayout(null);
        clockTypeAM.setForeground(Color.WHITE);
        clockTypeAM.setBounds(30, 115, 150, 150);
        clockTypeAM.setFont(new Font("NanumSquare", Font.BOLD, 20));
        // date.setFont(date.getFont().deriveFont(20.f));
        clockTypeAM.setSize(100,50);
        clockTypeAM.setVisible(true);

        //PM
        JLabel clockTypePM = new JLabel("PM");
        clockTypePM.setLayout(null);
        clockTypePM.setForeground(Color.GRAY);
        clockTypePM.setBounds(30, 145, 150, 150);
        clockTypePM.setFont(new Font("NanumSquare", Font.BOLD, 20));
        // date.setFont(date.getFont().deriveFont(20.f));
        clockTypePM.setSize(100,50);
        clockTypePM.setVisible(true);

        // Time
        JLabel time = new JLabel("04:49");
        time.setLayout(null);
        time.setForeground(Color.WHITE);
        time.setBounds(70, 55, 200, 200);
        time.setFont(new Font("NanumSquare", Font.BOLD, 50));
        // date.setFont(date.getFont().deriveFont(20.f));
        time.setSize(200,200);
        time.setVisible(true);

        // Second
        JLabel second = new JLabel("16");
        second.setLayout(null);
        second.setForeground(Color.WHITE);
        second.setBounds(210, 145, 100, 50);
        second.setFont(new Font("NanumSquare", Font.BOLD, 20));
        // date.setFont(date.getFont().deriveFont(20.f));
        second.setSize(100,50);
        second.setVisible(true);

        innerPanel.add(date);
        innerPanel.add(clockTypeAM);
        innerPanel.add(clockTypePM);
        innerPanel.add(time);
        innerPanel.add(second);

    }

    public void setOuterPanel(){

        outerPanel.setLayout(null);
        outerPanel.setBounds(100,75,300,350);
        outerPanel.setOpaque(false);
        outerPanel.setBackground(outerPanelColor);

    }

    public void setButtons(){

        for(int i = 0 ; i < 4 ; i ++){
            buttons[i] = new JButton(btnNames[i]);
            buttons[i].setBounds(btnX[i], btnY[i], 40, 100);
            buttons[i].setBackground(outerPanelColor);
            buttons[i].setOpaque(true);
            // buttons[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            buttons[i].setBorderPainted(true);
        }
    }

    public MainTemplate() {

        super("Sleepy clock");

        getContentPane().setBackground(Color.WHITE);
        getContentPane().add(outerPanel);
        getContentPane().add(innerPanel);

        setOuterPanel();
        setInnerPanel();
        setButtons();

        outerPanel.add(innerPanel);

        for(JButton btn: buttons)
            getContentPane().add(btn);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);
        setLayout(null);
        setVisible(true);
    }
}
