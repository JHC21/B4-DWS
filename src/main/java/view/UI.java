package view;

import controller.ClockSystem;
import org.omg.PortableServer.THREAD_POLICY_ID;
import view.handler.MyMouseEvent;
import view.template.Mode;

import javax.swing.*;
import java.util.logging.Logger;


public class UI {

    DisplayManager displayManager;
    ModeManager modeManager;
    ClockSystem system;

    MyMouseEvent event;

    // TODO: UI가 갖고 있는 현재 시간(long)
    Mode currentState;

    public void checker(int[] value){
        //value에 따라 각각의 값들을 처리함
        //displayManager에 넘길 값들을 정의하고
        //ClcokSystem의 값들을 다시 재정의해주고 하는 작업을
        //모두 여기서 처
    }

    public void systemWatching(Mode mode) throws Exception{

        while(true){
            // TODO: mode.mainCategory : 0~6까지 추가
            // 어떤 버튼이 눌렸는지를 여기서 받아와야함
            String pressed = event.getPressed();

            /*
            System.out.println("Now, this is UI:" + pressed);
            if(pressed.equals("B")){
                System.out.println("B was pressed");
                System.exit(0);
            }
             */

            //알람, 수면시간, 타이머가 울릴 시간인지 체크하고, 울릴 시간일 때 울리는 부분
            checker(modeManager.checker(system));

            if (mode.getMainCategory() == 0){
                // Timekeeping
                if(mode.getSubCategory() == 0){
                    displayManager.display(modeManager.displayTime(system));
                    //displaymanaer한테
                    //[system.getTime(), 시간제,  value[2~4]
                    //을 던져주면, displayManager가 알아서 표시해주는거

                    //button이 눌렸을 때 그에 따른 처리도 각각의 분기마다 다 넣어줘야함
                    //ex) C버튼이 눌리면 mode의 value를 바꿔줘야함

                    if(pressed.equals("A")){ mode.setMode(0, 1); }
                    if(pressed.equals("B")){ system.changeTimeFormat(); }
                    if(pressed.equals("C")){ mode.setMode(1, 0); }

                }else if(mode.getSubCategory() == 1){
                    displayManager.display(modeManager.setTime(system));

                    //displaymanaer한테
                    //[system.getTime(), 시간제,  value[2~4]
                    //을 던져주면, displayManager가 알아서 표시해주는거

                    if(pressed.equals("A")){ mode.setMode(0, 0); }
                    if(pressed.equals("B")) {
                        //int 선택자 = displayManager에서 선택자를 가져오는거
                        //int 값 = displayManager에서 선택자에 해당하는 값을 가져옴
                        //displayManager.setValue(displayManager.get선택자(), ++displayManager.get값())
                    }
                    if(pressed.equals("D")) {
                        //int 선택자 = displayManager에서 선택자를 가져오는거
                        //int 값 = displayManager에서 선택자에 해당하는 값을 가져옴
                        //displayManager.setValue(displayManager.get선택자(), --displayManager.get값())
                    }
                    if(pressed.equals("C")) {
                        //int 선택자 = displayManager에서 선택자를 가져오는거
                        //int 값 = displayManager에서 선택자에 해당하는 값을 가져옴
                        //displayManager.setValue(++displayManager.get선택자(), displayManager.get값())
                    }
                    // Set Time
                    // 공통적으로 예외처리를 해줘야함 (displayManager에서)
                }
            }else if(mode.getMainCategory() == 1) {
                //Timer
                if(mode.getSubCategory() == 0){

                }

            }

        }
    }

    public void initTemplate(){

    }

    public UI() throws Exception{
        // currentState = "DISPLAY_TIME";

        displayManager = new DisplayManager();
        modeManager = new ModeManager();
        event = displayManager.myMouseEvent;
        system = new ClockSystem();




        currentState = new Mode(0,0);
        systemWatching(currentState);

    }
}
