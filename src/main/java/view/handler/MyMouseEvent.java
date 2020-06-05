package view.handler;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyMouseEvent implements MouseListener {

    long pressedTime;
    long releasedTime;
    long time;

    JButton pressed;
    String clickedButton;

    public MyMouseEvent(String name){
        this.pressed = new JButton(name);
        this.clickedButton = "default value";
    }

    public String getPressed(){
        //System.out.println(this.pressed.getActionCommand());
        //return this.pressed.getActionCommand();
        String value = clickedButton;
        clickedButton = "default value";
        return value;
    }

    public void mousePressed(MouseEvent e) {
        // System.out.println("Mouse Pressed");
        pressedTime = System.currentTimeMillis();
        // System.out.println(pressedTime);
        this.pressed = (JButton)e.getSource();
    }
    public void mouseReleased(MouseEvent e) {
        // System.out.println("Mouse Released");
        releasedTime = System.currentTimeMillis();
        // System.out.println(releasedTime);

        time = releasedTime-pressedTime;
        // System.out.println(time);

        this.pressed = (JButton) e.getSource();

        clickedButton = this.pressed.getActionCommand();

        // TODO: mouseLongClicked 구현하기
        // if(time > 2000) mouseLongCicked();
    }

    public void mouseLongCicked(){
        System.out.println("Mouse Long Clicked");

    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {

    }
    public void mouseExited(MouseEvent e){

    }
}
