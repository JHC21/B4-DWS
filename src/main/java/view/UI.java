package view;

import controller.ClockSystem;
import org.omg.PortableServer.THREAD_POLICY_ID;
import view.handler.MyMouseEvent;
import view.template.Flag;
import view.template.Mode;

import javax.swing.*;
import java.net.PasswordAuthentication;
import java.util.logging.Logger;


public class UI {

    DisplayManager displayManager;
    ModeManager modeManager;
    ClockSystem system;

    MyMouseEvent event;

    // TODO: UI가 갖고 있는 현재 시간(long)
    Mode currentState;
    int alarmNumber;

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
        this.alarmNumber = 0;
        while(true){
            // TODO: mode.mainCategory : 0~6까지 추가
            // 어떤 버튼이 눌렸는지를 여기서 받아와야함
            String pressed = event.getPressed();


            // System.out.println("Now, this is UI:" + pressed);
            // if(pressed.equals("B")){
            //    System.out.println("B was pressed");
            //    System.exit(0);
            //}

            //알람, 수면시간, 타이머가 울릴 시간인지 체크하고, 울릴 시간일 때 울리는 부분
            //여기서 alarm, sleeping, timer의 각각 on/off여부를 가져와야 함
            //즉 checker의 리턴 값을 만들고 매 루트마다 저장하여야 할 듯 합니다
            //저장된 값들은 미래에 function list 표시를 위해 사용됩니다
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

                    if(pressed.equals("A")) {
                        displayManager.notDisplayIcon();
                        mode.enterSub();
                    }
                    if(pressed.equals("B")) system.changeTimeFormat();
                    // C버튼이 눌리는 처리는 맨 위에

                } else if(mode.getSubCategory() == 1) {
                    displayManager.displaySelector();
                    displayManager.displayTime(modeManager.displayTime(system));

                    // displaymanaer한테
                    // system.getTime(), 시간제,  value[2~4]
                    // 을 던져주면, displayManager가 알아서 표시해주는거

                    if(pressed.equals("A")) {
                        mode.exitSub();
                        displayManager.displayIcon();
                        displayManager.setSelector(0);
                        displayManager.notDisplaySelector();
                    }
                    if(pressed.equals("B")) {
                        system.setTime(Flag.getTimeValue(displayManager.getSelector()));
                    }
                    if(pressed.equals("D")) {
                        system.setTime(-1 * Flag.getTimeValue(displayManager.getSelector()));
                    }
                    if(pressed.equals("C")) {
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
                    //화면 표시
                    displayManager.displayTimer(modeManager.displayTimer(system));
                    // timer의 actiavte 관련은 checker() 가 할 수 있을 듯?
                    // 혹은 timer, alarm, sleeping time에 대해 check하는 과정을 삽입해야 함
                    // 아직 안함

                    if(pressed.equals("A")) {
                        //set timer
                        displayManager.notDisplayIcon();//on&off 관련인가요?
                        mode.enterSub();
                    }
                    if(pressed.equals("B")) {
                        //pause&restart
                        //예외 처리 필요할 듯
                        system.toggleTimerCounting();
                    }
                    if(pressed.equals("D")) {
                        //active&inactive
                        //예외 처리 필요할 듯
                        system.toggleTimerActivation();
                    }
                }else if(mode.getSubCategory() == 1){
                    //set Timer
                    displayManager.displaySelector();
                    displayManager.displayTimer(modeManager.setTimer(system));
                    if(pressed.equals("A")) {
                        //display timer
                        mode.exitSub();
                        displayManager.displayIcon(); //on&off 관련인가요?
                        displayManager.setSelector(0);
                        displayManager.notDisplaySelector();
                    }
                    if(pressed.equals("B")) {
                        //increase value
                        system.setTimer(Flag.getTimerValue(displayManager.getSelector()));
                    }
                    if(pressed.equals("D")) {
                        //decrease value
                        system.setTimer(-1 * Flag.getTimerValue(displayManager.getSelector()));
                    }
                    if(pressed.equals("C")) {
                        //change pointer position
                        displayManager.setSelector(Flag.moveTimerSelector(displayManager.getSelector()));
                        //int 선택자 = displayManager에서 선택자를 가져오는거
                        //int 값 = displayManager에서 선택자에 해당하는 값을 가져옴
                        //displayManager.setValue(++displayManager.get선택자(), displayManager.get값())
                    }

                }
            }else if(mode.getMainCategory() == 2){
                //StopWatch
                if(mode.getSubCategory() == 0) {
                    //display StopWatch  (stopWatch는 set이 없음)
                    displayManager.displayStopWatch(modeManager.displayStopWatch(system));

                    if(pressed.equals("A")) {
                        //Lap time
                        system.lapStopWatch();
                    }
                    if(pressed.equals("B")) {
                        //pause&active
                        system.toggleStopWatchState();
                    }
                    if(pressed.equals("D")) {
                        //reset&none
                        if((int)system.getStopWatchTime()[2] == 0) {//예외 처리
                            system.resetStopWatch();
                        }
                    }
                }
            }else if(mode.getMainCategory() == 3){
                //Alarm
                if(mode.getSubCategory() == 0){
                    //display Alarm
                    //timekeeping에서 timeformat을 가져오는 과정이 필요함
                    displayManager.displayAlarm(modeManager.displayAlarm(system, this.alarmNumber));
                    if(pressed.equals("A")) {
                        //set alarm
                        displayManager.notDisplayIcon();
                        mode.enterSub();
                    }
                    if(pressed.equals("B")) {
                        //move to next alarm
                        this.alarmNumber++;
                        if(this.alarmNumber == 4) this.alarmNumber = 0;
                    }
                    if(pressed.equals("D")) {
                        //toggle active/inactive
                        system.toggleAlarmActivation(this.alarmNumber);
                    }
                }else if(mode.getSubCategory() == 1){
                    //set Alarm
                    displayManager.displaySelector();
                    //timekeeping에서 timeformat을 가져와 alarmValue에 저장하는 과정이 필요함
                    String[] alarmValue = modeManager.displayAlarm(system, this.alarmNumber);
                    displayManager.displayAlarm(alarmValue);
                    if(pressed.equals("A")) {
                        //display alarm
                        mode.exitSub();
                        displayManager.displayIcon(); //on&off 관련인가요?
                        displayManager.setSelector(0);
                        displayManager.notDisplaySelector();
                    }
                    if(pressed.equals("B")) {
                        //increase value & toggle
                        if(Flag.isAlarmDayOfWeek(displayManager.getSelector())) { // toggle
                            system.setAlarm(this.alarmNumber, );
                        }
                        else { // increase value

                        }
                    }
                    if(pressed.equals("D")) {
                        //decrease value & toggle
                    }
                    if(pressed.equals("C")) {
                        //change pointer position
                        displayManager.setSelector(Flag.moveAlarmSelector(displayManager.getSelector()));
                        //int 선택자 = displayManager에서 선택자를 가져오는거
                        //int 값 = displayManager에서 선택자에 해당하는 값을 가져옴
                        //displayManager.setValue(++displayManager.get선택자(), displayManager.get값())
                    }
                }
            }else if(mode.getMainCategory() == 4){
                //GlobalTime
                if(mode.getSubCategory() == 0){
                    //display globalTime  (global Time은 set이 없음)
                    if(pressed.equals("A")) { // change pointer position
                        displayManager.displaySelector(); // 먼저 선택자를 보여주고
                        displayManager.setSelector(Flag.moveGlobalTimeSelector(displayManager.getSelector()));
                    }
                    if(pressed.equals("B")) { // increase value
                        // 선택자가 현재 가리키고 있는 곳의 값을 1 증가
                        int currentSelector = displayManager.getSelector(); // 선택자가 현재 가리키고 있는 곳을 알아옴
                        if(currentSelector == 22) { // 내 도시
                            system.setMyTimeZone(1);
//                            system.setMyTimeZone(Flag.getGlobalTimeValue(displayManager.getSelector()));
                        } else if(currentSelector == 24){ // 다른 도시
                            system.setAnotherTimeZone(1);
                        }
                    }

                    // C 버튼이 눌리는 경우는 function list로 이동하므로 위에서 이미 처리함

                    if(pressed.equals("D")) { // decrease value
                        // 선택자가 현재 가리키고 있는 곳의 값을 1 감소
                        int currentSelector = displayManager.getSelector(); // 선택자가 현재 가리키고 있는 곳을 알아옴
                        if(currentSelector == 22) {
                            system.setMyTimeZone(-1);
                        } else {
                            system.setAnotherTimeZone(-1);
                        }
                    }
                }
            }else if(mode.getMainCategory() == 5){
                //SleepingTime
                if(mode.getSubCategory() == 0){
                    //display sleeping time
                    // displayManager.displayTime(modeManager.displayTime(system));
                    displayManager.displaySleepingTime(modeManager.displaySleepingTime(system));

                    // Set sleeping time
                    if(pressed.equals("A")){
                        displayManager.notDisplayIcon();
                        mode.enterSub();
                    }

                    // B버튼이 눌렸을 때는 아무것도 실행되지 않음

                    // C버튼이 눌렸을 때는 맨 위에서 처리

                    // Turn on/off (cheering message 수신 여부)
                    if(pressed.equals("D")){
                        system.toggleCheeringMessageReceiving();
                    }

                }else if(mode.getSubCategory() == 1){
                    //set sleeping time
                    //Set sleeping time
                    displayManager.displaySelector();
                    displayManager.displaySleepingTime(modeManager.displaySleepingTimeValue(system));

                    //Move to sleeping time
                    if(pressed.equals("A")){
                        mode.exitSub();
                        displayManager.setSelector(21);
                        displayManager.notDisplaySelector();
                    }

                    // Increase value
                    if(pressed.equals("B")){
                        system.setTime(Flag.getWakeUpSleepTimeValue(displayManager.getSelector()));
                    }

                    // Decrease value
                    if(pressed.equals("D")){
                        system.setTime(-1 * Flag.getWakeUpSleepTimeValue(displayManager.getSelector()));
                    }

                    // Change function position
                    if(pressed.equals("C")){
                        displayManager.setSelector(Flag.moveWakeUpSleepTimeSelector(displayManager.getSelector()));
                    }
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


