package view;

import controller.ClockSystem;
import controller.Utility;
import view.handler.MyMouseEvent;
import view.template.Flag;
import view.template.Mode;

import javax.swing.*;
import java.time.LocalTime;
import java.util.logging.Logger;


public class UI {

    DisplayManager displayManager;
    ModeManager modeManager;
    ClockSystem system;

    MyMouseEvent event;

    // TODO: UI가 갖고 있는 현재 시간(long)
    Mode currentState;
    int alarmNumber;

    public void systemWatching(Mode mode) throws Exception{

        System.out.println(mode.getMainCategory());
        System.out.println(mode.getSubCategory());
        int location = 0;
        int function_num = 0;
        int depth = 0;
        int[] checkerList = new int[3];
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
            String[] currentTimeFormat = Utility.millitoTimeFormat_test((long)system.getTime()[0]);
            int[] currentTime = Utility.milliToTimeFormat((long)system.getTime()[0]);
            checkerList = modeManager.checker(system);
            if(checkerList[0] == 2 || checkerList[1] == 2) { // show alarming, show cheering message
                system.ringAlarm();
                //String 형식은 "20 05 03 일    06:30"
                //12시간제일때는 "20 05 03 일 AM 06:30"
                String timeFormat;
                if((boolean)system.getTime()[1]) { // 12시간제
                    if(currentTime[3] < 12) timeFormat = "AM";
                    else timeFormat = "PM";
                }
                else {// 24시간제일 때는 AM/PM 표시 안함
                    timeFormat = "  ";
                }
                String temp = String.format("%s %s %s %s %s %s:%s",
                        currentTimeFormat[0],
                        currentTimeFormat[1],
                        currentTimeFormat[2],
                        currentTimeFormat[7],
                        timeFormat,
                        currentTimeFormat[3],
                        currentTimeFormat[4],
                        currentTimeFormat[5]);

                if(checkerList[0] == 2) {
                    displayManager.displayShowAlarming(temp);
                    system.ringAlarm();
                    checkerList[0] = 1;
                }
                else if(checkerList[1] == 2) {
                    //displayManager.cheeringMessageShowAll(temp); 위의 displayShowAlarming()과 같은 메소드가 구현되어 있지 않음
                    system.ringSleepingTime();
                    checkerList[1] = 0;
                }
            }
            else if(checkerList[2] == 2) { // ring timer
                system.ringTimer();
                checkerList[2] = 1;
            }
            //이 부분을 통해 displayManager에서 function list를 표시합니다.
            //displayManager.displayFunctionList(system.getFunctionList(), checkerList);


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
                        displayManager.notDisplayIcon();
                        mode.enterSub();
                    }
                    if(pressed.equals("B")) {
                        //pause&restart
                        system.toggleTimerCounting();
                    }
                    if(pressed.equals("D")) {
                        //active&inactive
                        if(checkerList[2] != 0) // inactive 상태에서는 안 됨
                            system.toggleTimerActivation();
                    }
                }else if(mode.getSubCategory() == 1){
                    //set Timer
                    displayManager.displaySelector();
                    displayManager.setTimer(modeManager.setTimer(system));
                    if(pressed.equals("A")) {
                        //display timer
                        mode.exitSub();
                        displayManager.displayIcon();
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
                    Object[] alarmValue = modeManager.displayAlarm(system, this.alarmNumber);
                    displayManager.setAlarm(alarmValue);
                    if(pressed.equals("A")) {
                        //display alarm
                        mode.exitSub();
                        displayManager.displayIcon();
                        displayManager.setSelector(0);
                        displayManager.notDisplaySelector();
                    }
                    if(pressed.equals("B") || pressed.equals("D")) {
                        //increase/decrease value & toggle
                        int tempSelector = displayManager.getSelector();
                        LocalTime updateValue = LocalTime.of(0, 0);

                        //string을 다시 LocalTime으로 바꿈...ㅠㅠ
                        if(alarmValue[2] == null)  // 24시간제
                            updateValue.plusHours((int)alarmValue[0]);
                        else { // 12시간제
                            if(alarmValue[2].equals("오후")) updateValue.plusHours((int)alarmValue[0] + 12);
                            else updateValue.plusHours((int)alarmValue[0]);
                        }
                        updateValue.plusMinutes((int)alarmValue[1]);

                        if(12 <= tempSelector && tempSelector <= 18) { // toggle
                            ((boolean[])alarmValue[3])[tempSelector - 12] = !((boolean[])alarmValue[3])[tempSelector - 12];
                        }
                        else { // increase value
                            if(pressed.equals("B")) { // increase value
                                if (tempSelector == 5) updateValue.plusHours(1);
                                else updateValue.plusMinutes(1); // tempSelector == 6
                            }
                            else { // pressed.equals("D") decrease value
                                if (tempSelector == 5) updateValue.minusHours(1);
                                else updateValue.minusMinutes(1); // tempSelector == 6
                            }
                        }
                        system.setAlarm(this.alarmNumber, (boolean[])alarmValue[3], updateValue);
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
                    if(pressed.equals("A")) {
                    }
                    if(pressed.equals("B")) {
                    }
                    if(pressed.equals("D")) {
                    }
                    if(pressed.equals("C")) {
                    }
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


