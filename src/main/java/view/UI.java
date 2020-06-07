package view;

import controller.ClockSystem;
import controller.Utility;
import view.handler.MyMouseEvent;
import view.template.Flag;
import view.template.Mode;

import javax.swing.*;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.logging.Logger;


public class UI {

    DisplayManager displayManager;
    ModeManager modeManager;
    ClockSystem system;

    MyMouseEvent event;

    int[] currentTimeInt = new int[8];
    String[] currentTimeString = new String[9];
    long currentTime;
    long alarmTime;
    long lastPressedTime;
    Mode currentState;
    int alarmNumber;

    public void systemWatching(Mode mode) throws Exception{

        System.out.println(mode.getMainCategory());
        System.out.println(mode.getSubCategory());
        int location = 0;
        int function_num = 0;
        int depth = 0;
        int[] checkerList = {0, 0, 0};
        this.alarmNumber = 0;
        alarmTime = 0;
        lastPressedTime = 0;
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
            currentTime = (long)system.getTime()[0];
            if(!pressed.equals("default value")) {
                lastPressedTime = currentTime;
            }
            if(checkerList[0] == 2) {
                if(!pressed.equals("default value")) { // turn off alarm manually
                    checkerList[0] = 1;
                }
                if(currentTime > alarmTime + 5000) { // turn off alarm automatically
                    checkerList[0] = 1;
                }
                continue;
            } // 여기에 알람 소리를 끄는 로직을 추가할 수 있을 듯
            if(checkerList[1] == 2) {
                if(!pressed.equals("default value")) { // turn off alarm manually
                    checkerList[1] = 1;
                }
                if(currentTime > alarmTime + 20000) { // turn off alarm automatically
                    checkerList[1] = 1;
                }
                continue;
            } // 여기에 sleeping time소리를 끌 수 있는 로직을 추가할 수 있을 듯

            currentTimeString = Utility.millitoTimeFormat_test(currentTime);
            currentTimeInt = Utility.milliToTimeFormat(currentTime);
            checkerList = modeManager.checker(system);
            if(checkerList[0] == 2 || checkerList[1] == 2) { // show alarming, show cheering message
                //String 형식은 "20 05 03 일    06:30"
                //12시간제일때는 "20 05 03 일 AM 06:30"
                String timeFormat;
                if((boolean)system.getTime()[1]) { // 12시간제
                    if(currentTimeInt[3] < 12) timeFormat = "AM";
                    else timeFormat = "PM";
                }
                else {// 24시간제일 때는 AM/PM 표시 안함
                    timeFormat = "  ";
                }
                String temp = String.format("%s %s %s %s %s %s:%s",
                        currentTimeString[0],
                        currentTimeString[1],
                        currentTimeString[2],
                        currentTimeString[7],
                        timeFormat,
                        currentTimeString[3],
                        currentTimeString[4]);

                if(checkerList[0] == 2) {
                    displayManager.displayShowAlarming(temp);
                    system.ringAlarm();
                }
                else if(checkerList[1] == 2) {
                    //displayManager.cheeringMessageShowAll(temp); 위의 displayShowAlarming()과 같은 메소드가 구현되어 있지 않음
                    system.ringSleepingTime();
                }
                continue;
            }
            else if(checkerList[2] == 2) { // ring timer
                system.ringTimer();
            }
            //여기까지 왔다는 것은 alarm이나 sleeping time이 울리지 않는다는 의미입니다.
            //이 부분을 통해 displayManager에서 function list를 표시합니다.
            //displayManager.displayFunctionList(system.getFunctionList(), checkerList);

            //back to base
            if(mode.getSubCategory() == 1) {
                if(currentTime > lastPressedTime + 300000) {
                    mode.exitSub();
                }
            }
            if(mode.getMainCategory() == 6) {
                if(currentTime > lastPressedTime + 300000) {
                    mode.setMainCategory(system.getFunctionList()[0]);
                }
            }


            //현재 카테고리가 display종류이고 C버튼이 눌렸을 때
            //function List에 질의해 다음 function을 가져옴 -> 이거만 따로 써줘야 할 필요가 있음

            if(pressed.equals("C") && mode.getSubCategory() == 0 && mode.getMainCategory() != 6){
                displayManager.cleanDisplay();
                mode.moveFunctionSelector();
                mode.setMainCategory(system.getFunctionList()[mode.getFunctionSelector()]);


                if(mode.getMainCategory() == 4) { // global time
                    // 이름없는 system method
                    // 20번 ID에서 1,2 번 system operation을 여기서 해줘야 함
                    if(displayManager.getSelector() != 21 || displayManager.getSelector() != 23) {
                        displayManager.setSelector(21);
                    }
                }
            }

            if(pressed.equals("E")){
                displayManager.cleanDisplay();
                mode.setMainCategory(6);
                mode.setSubCategory(0);
            }


            if (mode.getMainCategory() == 0) {
                if(displayManager.getSelector() > 8) displayManager.setSelector(0);
                // Timekeeping
                if (mode.getSubCategory() == 0) {
                    displayManager.displayTime((String[])modeManager.displayTime(system)[0]);

                    if(pressed.equals("A")) {
                        displayManager.notDisplayIcon();
                        displayManager.cleanDisplay();
                        mode.enterSub();
                    }
                    if(pressed.equals("B")) system.changeTimeFormat();
                    // C버튼이 눌리는 처리는 맨 위에

                } else if(mode.getSubCategory() == 1) {
                    displayManager.displaySelector();
                    Object[] curTime = modeManager.displayTime(system);
                    displayManager.displayTime((String[])curTime[0]);

                    // displaymanaer한테
                    // system.getTime(), 시간제,  value[2~4]
                    // 을 던져주면, displayManager가 알아서 표시해주는거

                    if(pressed.equals("A")) {
                        mode.exitSub();
                        displayManager.cleanDisplay();
                        displayManager.displayIcon();
                        displayManager.setSelector(0);
                        displayManager.notDisplaySelector();
                    }
                    if(pressed.equals("B")) {
                        system.setTime(Flag.getTimeValue(displayManager.getSelector(), curTime, 1));
                    }
                    if(pressed.equals("D")) {
                        system.setTime(Flag.getTimeValue(displayManager.getSelector(), curTime, -1));
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
                if(displayManager.getSelector() < 5 || displayManager.getSelector() > 10) displayManager.setSelector(5);

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
                        int timerState = (int)system.getTimer()[1];
                        if(!(timerState == 0 || timerState == 2)){
                            displayManager.notDisplayIcon();
                            displayManager.cleanDisplay();
                            mode.enterSub();
                        }

                    }
                    if(pressed.equals("B")) {
                        //pause&restart
                        system.toggleTimerCounting();
                    }
                    if(pressed.equals("D")) {
                        //active&inactive
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
                        displayManager.cleanDisplay();
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
                int temp = displayManager.getSelector();
                if(!((temp > 4 && temp < 19) && (temp < 7 || temp > 12))) displayManager.setSelector(12);

                //Alarm
                if(mode.getSubCategory() == 0){
                    //display Alarm
                    displayManager.displayAlarm(modeManager.displayAlarm(system, this.alarmNumber));
                    if(pressed.equals("A")) {
                        //set alarm
                        displayManager.notDisplayIcon();
                        displayManager.cleanDisplay();
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
                        displayManager.cleanDisplay();
                    }
                    if(pressed.equals("B") || pressed.equals("D")) {
                        //increase/decrease value & toggle
                        int tempSelector = displayManager.getSelector();
                        LocalTime updateValue = LocalTime.of(0, 0);

                        if(12 <= tempSelector && tempSelector <= 18) { // toggle
                            ((boolean[])alarmValue[3])[tempSelector - 12] = !((boolean[])alarmValue[3])[tempSelector - 12];
                        }
                        else { // increase value
                            if(pressed.equals("B")) { // increase value
                                if (tempSelector == 5) {
                                    updateValue = updateValue.plusHours(1);
                                }
                                else updateValue = updateValue.plusMinutes(1); // tempSelector == 6
                            }
                            else { // pressed.equals("D") decrease value
                                if (tempSelector == 5) updateValue = updateValue.minusHours(1);
                                else updateValue = updateValue.minusMinutes(1); // tempSelector == 6
                            }
                        }
                        system.setAlarm(this.alarmNumber, (boolean[])alarmValue[3], updateValue);
                    }
                    if(pressed.equals("C")) {
                        //change pointer position
                        // System.out.print("before : " + displayManager.getSelector());
                        displayManager.setSelector(Flag.moveAlarmSelector(displayManager.getSelector()));
                        // System.out.print("  after : " + displayManager.getSelector() + "\n");
                        //int 선택자 = displayManager에서 선택자를 가져오는거
                        //int 값 = displayManager에서 선택자에 해당하는 값을 가져옴
                        //displayManager.setValue(++displayManager.get선택자(), displayManager.get값())
                    }
                }
            } else if(mode.getMainCategory() == 4) {
                //GlobalTime
//                System.out.println("displayManager의 selector: " + displayManager.getSelector());
                displayManager.displayGlobalTime(modeManager.displayGlobalTime(system));

                // 계속 이 루프로 들어가게 됨 ㅠㅠ
                // 엉뚱한 곳에 selector가 있으면 selector를 내 도시에 맞춰주기
                if(displayManager.getSelector() != 21 || displayManager.getSelector() != 23) {
                    displayManager.setSelector(21);
                    System.out.println("set selector to 21");
                }
                displayManager.displaySelector();
//                System.out.println("Selector: " + displayManager.getSelector());

                // another city time 안 보이는거 해결하기!

                if(mode.getSubCategory() == 0) {
                    // display globalTime  (global Time은 set이 없음)
                    if(pressed.equals("A")) { // change pointer position
                        System.out.println("변경 전 선택자: " + displayManager.getSelector());
                        displayManager.setSelector(Flag.moveGlobalTimeSelector(displayManager.getSelector()));
                        System.out.println("변경 후 선택자: " + displayManager.getSelector());
                    }
                    // 이건 제대로 되는 것 같음
                    if(pressed.equals("B")) { // increase value
                        // 선택자가 현재 가리키고 있는 곳의 값을 1 증가
                        int currentSelector = displayManager.getSelector(); // 선택자가 현재 가리키고 있는 곳을 알아옴
                        if(currentSelector == 21) { // 내 도시
                            system.setMyTimeZone(1);
//                            system.setMyTimeZone(Flag.getGlobalTimeValue(displayManager.getSelector()));
                        } else if(currentSelector == 23){ // 다른 도시
                            system.setAnotherTimeZone(1);
                        }
                    }

                    // C 버튼이 눌리는 경우는 function list로 이동하므로 위에서 이미 처리함

                    // 이건 제대로 되는 것 같음
                    if(pressed.equals("D")) { // decrease value
                        // 선택자가 현재 가리키고 있는 곳의 값을 1 감소
                        int currentSelector = displayManager.getSelector(); // 선택자가 현재 가리키고 있는 곳을 알아옴
                        if(currentSelector == 21) {
                            system.setMyTimeZone(-1);
                        } else if(currentSelector == 23){
                            system.setAnotherTimeZone(-1);
                        }
                    }
                }
            }else if(mode.getMainCategory() == 5){
                //SleepingTime
                int getSelector = displayManager.getSelector();
                if(getSelector != 22 && getSelector != 27 && getSelector != 24 && getSelector != 28){
                    displayManager.setSelector(22); }
                if(mode.getSubCategory() == 0){
                    //display sleeping time
                    // displayManager.displayTime(modeManager.displayTime(system));
                    displayManager.displaySleepingTime(modeManager.displaySleepingTime(system));

                    // Set sleeping time
                    if(pressed.equals("A")){
                        mode.enterSub();
                        displayManager.notDisplayIcon();
                        displayManager.cleanDisplay();
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
                        displayManager.displayIcon();
                        displayManager.cleanDisplay();
                    }

                    // Increase value
                    if(pressed.equals("B")){

                        if(getSelector == 22 || getSelector == 27){
                            system.setSleepTime(Flag.getWakeUpSleepTimeValue(getSelector), 1);
                        }else if(getSelector == 24 || getSelector == 28){
                            system.setWakeUpTime(Flag.getWakeUpSleepTimeValue(getSelector), 1);
                        }

                    }

                    // Decrease value
                    if(pressed.equals("D")){
                        if(getSelector == 22 || getSelector == 27){
                            system.setSleepTime(Flag.getWakeUpSleepTimeValue(getSelector), -1);
                        }else if(getSelector == 24 || getSelector == 28) {
                            system.setWakeUpTime(Flag.getWakeUpSleepTimeValue(getSelector), -1);
                        }
                    }

                    // Change selector position
                    if(pressed.equals("C")){
                        // System.out.println(getSelector);
                        displayManager.setSelector(Flag.moveWakeUpSleepTimeSelector(getSelector));
                    }
                }


            }else if(mode.getMainCategory() == 6){
                int getSelector = displayManager.getSelector();
                if(getSelector < 31) displayManager.setSelector(31);
                //Function Change
                if(mode.getSubCategory() == 0) {
                    //customize your own clock을 먼저 보여줘야한다.
                    displayManager.displayFunctionListEdit();
                    displayManager.displayIcon();
                    displayManager.displaySelector();

                    //move selected item to left
                    if(pressed.equals("A")){
                        displayManager.changeIconPosition(true);
                        system.moveItem(displayManager.getSelector()-31, -1);
                        displayManager.setSelector(Flag.moveFunctionSelectorReverse(displayManager.getSelector()));
                        System.out.println(displayManager.getSelector());
                    }

                    //move selected item to rightmost
                    if(pressed.equals("B")){
                        displayManager.changeIconPosition(false);
                        system.moveItem(displayManager.getSelector()-31 , 1);
                        displayManager.setSelector(Flag.moveFunctionSelector(displayManager.getSelector()));
                        System.out.println(displayManager.getSelector());
                    }
                    if(pressed.equals("C")){
                        displayManager.notDisplaySelector();
                        displayManager.cleanDisplay();
                        System.out.println("TEST:" + Arrays.toString(system.getFunctionList()));
                        mode.setMainCategory(system.getFunctionList()[0]);
                    }

                    if(pressed.equals("D")){
                        displayManager.setSelector(Flag.moveFunctionSelector(displayManager.getSelector()));
                    }

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


