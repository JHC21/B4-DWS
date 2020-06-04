package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// import view.UI;

public class ButtonHandler implements ActionListener {

    int test;

    ButtonHandler(int test){
        this.test = test;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) { //액션이벤트가 발생됬을떄 수행하는 동작

        String nowButton = arg0.getActionCommand();
        System.out.println(nowButton);

        //TODO: C button 길게 눌렀을 때 처리해야 하는 로직 추가하기
        if(nowButton.equals("A")){
            // display.getButton(A)
            // this.buttonAPressed(nowState);
        }else if(nowButton.equals("B")){

            // this.buttonBPressed(nowState);
        }else if(nowButton.equals("C")){
            // this.buttonCPressed(nowState);
        }else if(nowButton.equals("D")){
            // this.buttonDPressed(nowState);
        }
    }
}
