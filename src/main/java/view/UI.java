package view;

import controller.ClockSystem;
import org.omg.PortableServer.THREAD_POLICY_ID;
import view.handler.MyMouseEvent;
import view.template.Flag;
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

        System.out.println(mode.getMainCategory());
        System.out.println(mode.getSubCategory());
        int location = 0;
        int function_num = 0;
        int depth = 0;
        while(true){
            // TODO: mode.mainCategory : 0~6까지 추가
            // 어떤 버튼이 눌렸는지를 여기서 받아와야함
            String pressed = event.getPressed();
            System.out.println(pressed);


            // System.out.println("Now, this is UI:" + pressed);
            // if(pressed.equals("B")){
            //    System.out.println("B was pressed");
            //    System.exit(0);
            //}


            //알람, 수면시간, 타이머가 울릴 시간인지 체크하고, 울릴 시간일 때 울리는 부분
            //checker(modeManager.checker(system));


            //현재 카테고리가 display종류이고 C버튼이 눌렸을 때
            //function List에 질의해 다음 function을 가져옴 -> 이거만 따로 써줘야 할 필요가 있음
            if(pressed.equals("C") && mode.getSubCategory() == 0){
                mode.moveFunctionSelector();
                mode.setMainCategory(system.getFunctionList()[mode.getFunctionSelector()]);
            }


            if (mode.getMainCategory() == 0) {
                // Timekeeping
                if (mode.getSubCategory() == 0) {
                    displayManager.displayTime(modeManager.displayTime(system));

                    if(pressed.equals("A")) mode.enterSub();
                    if (pressed.equals("B")) system.changeTimeFormat();
                    // C버튼이 눌리는 처리는 맨 위에

                } else if (mode.getSubCategory() == 1) {
                    displayManager.display(modeManager.displayTime(system));

                    // displaymanaer한테
                    // system.getTime(), 시간제,  value[2~4]
                    // 을 던져주면, displayManager가 알아서 표시해주는거

                    if (pressed.equals("A")) mode.exitSub();
                    if (pressed.equals("B")) {
                        system.setTime(Flag.getTimeValue(displayManager.getSelector()));
                    }
                    if (pressed.equals("D")) {
                        system.setTime(-1 * Flag.getTimeValue(displayManager.getSelector()));
                    }
                    if (pressed.equals("C")) {
                        displayManager.setSelector(Flag.moveTimeSelector(displayManager.getSelector()));
                        //int 선택자 = displayManager에서 선택자를 가져오는거
                        //int 값 = displayManager에서 선택자에 해당하는 값을 가져옴
                        //displayManager.setValue(++displayManager.get선택자(), displayManager.get값())
                    }
                    // Set Time
                    // 공통적으로 예외처리를 해줘야함 (displayManager에서)
                }
            }else if(mode.getMainCategory() == 1){
                //Timer
                if(mode.getSubCategory() == 0){
                    //display Timer
                }else if(mode.getSubCategory() == 1){
                    //set Timer
                }
            }else if(mode.getMainCategory() == 2){
                //StopWatch
                if(mode.getSubCategory() == 0) {
                    //display StopWatch  (stopWatch는 set이 없음)
                }
            }else if(mode.getMainCategory() == 3){
                //Alarm
                if(mode.getSubCategory() == 0){
                    //display Alarm
                }else if(mode.getSubCategory() == 1){
                    //set Alarm
                }
            }else if(mode.getMainCategory() == 4){
                //GlobalTime
                if(mode.getSubCategory() == 0){
                    //display globalTime  (global Time은 set이 없음)
                }
            }else if(mode.getMainCategory() == 5){
                //SleepingTime
                if(mode.getSubCategory() == 0){
                    //display sleeping time
                }else if(mode.getSubCategory() == 1){
                    //set sleeping time
                }
            }else if(mode.getMainCategory() == 6){
                //Function Change
                if(mode.getSubCategory() == 0) {
                    //display function list
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
        currentState = new Mode();

        systemWatching(currentState);

    }
}


