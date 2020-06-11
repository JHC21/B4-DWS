package view;

import controller.ClockSystem;
import controller.Utility;
import view.handler.MyMouseEvent;
import view.template.Buzzer;
import view.template.Flag;
import view.template.Mode;

import java.time.LocalTime;
import java.util.Arrays;


public class UI {

    static final String customize = "customize your own watch";

    DisplayManager displayManager;
    ModeManager modeManager;
    ClockSystem system;
    Buzzer buzzer;
    MyMouseEvent event;

    long currentTime;
    boolean currentTimeFormat; // true : 12시간제, false : 24시간제
    long alarmTime; // 알람이 울리게 된 시간. showAlarming, showCheeringMessage이전에 불리며, turn off automatically를 위해 사용된다.
    long lastPressedTime; // back to base의 조건으로 사용된다
    Mode mode;
    int alarmNumber;
    StringBuilder customText = new StringBuilder(customize); // customize your own watch를 랜덤으로 구현하기 위한 변수 공간이다.
    int[] checkerList = {0, 0, 0}; // 0: alarm, 1: sleeping time, 2: timer
    int[] functionList = {0, 1, 2, 3, 4, 5};

    public void systemWatching() throws Exception{

        System.out.println(mode.getMainCategory());
        System.out.println(mode.getSubCategory());
        int curMainCategory = 0;
        int curSubCategory = 0;
        this.alarmNumber = 0;
        alarmTime = 0;
        lastPressedTime = 0;

        displayManager.setSelector(21); // 기본값을 global time의 selector로 두고 있다

        // buzzer를 일단 start 시킨다. 이후 이 thread는 wait 상태에 돌입하고, 우리가 사용할 때 notify시키면 된다.
        // buzzer를 끌 때는 buzzer.pauseThread()를 호출하면 된다.
        buzzer.start();


        while(true){
            // TODO: mode.mainCategory : 0~6까지 추가
            // 어떤 버튼이 눌렸는지를 여기서 받아와야함
            String pressed = event.getPressed();
            functionList = system.getFunctionList();

            //if(!pressed.equals("default value")) System.out.println("Pressed : " + pressed + "  MainCategory : " + mode.getMainCategory() + "  SubCategory : " + mode.getSubCategory());

            //if(pressed.equals("default value")) System.out.println("Pressed : " + pressed + "  MainCategory : " + mode.getMainCategory() + "  SubCategory : " + mode.getSubCategory());

            // System.out.println("Now, this is UI:" + pressed);
            // if(pressed.equals("B")){
            //    System.out.println("B was pressed");
            //    System.exit(0);
            //}

            //알람, 수면시간, 타이머가 울릴 시간인지 체크하고, 울릴 시간일 때 울리는 부분
            //여기서 alarm, sleeping, timer의 각각 on/off여부를 가져와야 함
            //즉 checker의 리턴 값을 만들고 매 루트마다 저장하여야 할 듯 합니다
            //저장된 값들은 미래에 function list 표시를 위해 사용됩니다
            Object[] tempGetTime = system.getTime();
            currentTime = (long)tempGetTime[0];
            currentTimeFormat = (boolean)tempGetTime[1];

            if(!pressed.equals("default value")) {
                lastPressedTime = currentTime;
            }
            if(checkerList[0] == 2) {
                if(!pressed.equals("default value") || currentTime > alarmTime + 5000) { // turn off alarm manually & automatically
                    checkerList[0] = 1;
                    buzzer.pauseThread();
                    displayManager.displayIcon(functionList, checkerList);
                    displayManager.cleanDisplay();
                }
                continue;
            }
            if(checkerList[1] == 2) {
                if(!pressed.equals("default value") || currentTime > alarmTime + 20000) { // turn off cheering message manually & automatically
                    checkerList[1] = 1;
                    buzzer.pauseThread();
                    displayManager.displayIcon(functionList, checkerList);
                    displayManager.cleanDisplay();
                }
                continue;
            }

            checkerList = modeManager.checker(system);
            if(checkerList[0] == 2 || checkerList[1] == 2) { // show alarming, show cheering message
                //String 형식은 "20 05 03 일    06:30"
                //12시간제일때는 "20 05 03 일 AM 06:30"

                String temp = Utility.alarmCheeringFormatting(currentTime, currentTimeFormat);

                displayManager.notDisplayIcon();
                displayManager.cleanDisplay();
                buzzer.threadNotify();
                alarmTime = currentTime;

                if(checkerList[0] == 2) {
                    displayManager.displayShowAlarming(temp);
                    system.ringAlarm();
                }
                else if(checkerList[1] == 2) {
                    displayManager.displayCheeringMessage(temp);
                    system.ringSleepingTime();
                }
                continue;
            }
            else if(checkerList[2] == 2) { // ring timer
                system.ringTimer();
                buzzer.beeep();
            }

            //여기까지 왔다는 것은 alarm이나 sleeping time이 울리지 않는다는 의미입니다.
            //이 부분을 통해 displayManager에서 function list를 표시합니다.
            //displayManager.displayFunctionList(system.getFunctionList(), checkerList);

            //back to base
            if(currentTime > lastPressedTime + 300000) {
                if (curMainCategory == 1) {
                    this.exitingSub();
                }
                if (curMainCategory == 6) {
                    displayManager.displayIcon(functionList, checkerList);
                    displayManager.notDisplaySelector();
                    displayManager.cleanDisplay();
                    mode.setMainCategory(system.getFunctionList()[0]);
                }
            }


            //현재 카테고리가 display종류이고 C버튼이 눌렸을 때
            //function List에 질의해 다음 function을 가져옴 -> 이거만 따로 써줘야 할 필요가 있음

            if(pressed.equals("C") && curSubCategory == 0 && curMainCategory != 6){
                System.out.println("Pressed C in Display XXX");
                displayManager.cleanDisplay();
                displayManager.notDisplaySelector();
                mode.moveFunctionSelector();
                mode.setMainCategory(system.getFunctionList()[mode.getFunctionSelector()]);


                if(curMainCategory == 4) { // global time
                    // 이름없는 system method
                    // 20번 ID에서 1,2 번 system operation을 여기서 해줘야 함
                    system.enterGlobalTime();
                    if(displayManager.getSelector() != 21 || displayManager.getSelector() != 23) {
                        displayManager.setSelector(21);
                    }
                }
            }

            if(pressed.equals("E")){
                int temp = 0;
                displayManager.cleanDisplay();
                customText = new StringBuilder(customize);
                for(int i = 0; i < customText.length(); i++) {
                    if(customText.charAt(i) != ' ') {
                        temp = (int)(Math.random() * 2);
                        if(temp == 1) customText.setCharAt(i, (char)(customText.charAt(i) - ('a' - 'A')));
                    }
                }
                mode.setMainCategory(6);
                mode.setSubCategory(0);
            }


            //if(!pressed.equals("default value")) System.out.println("SECOND Pressed : " + pressed + "  MainCategory : " + curMainCategory + "  SubCategory : " + curSubCategory);
            //if(pressed.equals("default value")) System.out.println("Pressed : " + pressed + "  MainCategory : " + curMainCategory + "  SubCategory : " + curSubCategory);
            //System.out.println(Arrays.toString(functionList) + Arrays.toString(checkerList));
            curMainCategory = mode.getMainCategory();
            curSubCategory = mode.getSubCategory();

            if (curMainCategory == 0) {
                //if(displayManager.getSelector() > 8) displayManager.setSelector(0);
                // Timekeeping
                if (curSubCategory == 0) {
                    displayManager.displayTime((String[])modeManager.displayTime(system)[0]);
                    displayManager.displayIcon(functionList, checkerList);

                    if(pressed.equals("A")) {
                        this.enteringSub(0);
                    }
                    if(pressed.equals("B")) system.changeTimeFormat();
                    // C버튼이 눌리는 처리는 맨 위에

                } else if(curSubCategory == 1) {
                    displayManager.displaySelector();
                    Object[] curTime = modeManager.displayTime(system);
                    displayManager.displayTime((String[])curTime[0]);

                    // displaymanaer한테
                    // system.getTime(), 시간제,  value[2~4]
                    // 을 던져주면, displayManager가 알아서 표시해주는거

                    if(pressed.equals("A")) {
                        this.exitingSub();
                    }
                    if(pressed.equals("B")) {
                        //System.out.println("pressedB");
                        long updateValue = Flag.getTimeValue(displayManager.getSelector(), curTime, 1);
                        system.setTime(updateValue);
                        lastPressedTime += updateValue; // 시간이 변하는 만큼 lastPressedTime을 같이 맞춰 주어야 back to base가 잘못 발동되지 않는다

                    }
                    if(pressed.equals("D")) {
                        //System.out.println("pressedD");
                        long updateValue = Flag.getTimeValue(displayManager.getSelector(), curTime, -1);
                        system.setTime(updateValue);
                        lastPressedTime += updateValue;
                    }
                    if(pressed.equals("C")) {
                        displayManager.setSelector(Flag.moveTimeSelector(displayManager.getSelector()));

                        //int 선택자 = displayManager에서 선택자를 가져오는거
                        //int 값 = displayManager에서 선택자에 해당하는 값을 가져옴
                        //displayManager.setValue(++displayManager.get선택자(), displayManager.get값())
                    }
                    // Set Time
                }
            }else if(curMainCategory == 1){
                //if(displayManager.getSelector() < 5 || displayManager.getSelector() > 10) displayManager.setSelector(5);

                //Timer
                if(curSubCategory == 0){
                    //display Timer
                    //화면 표시
                    displayManager.displayTimer(modeManager.displayTimer(system));
                    displayManager.displayIcon(functionList, checkerList);

                    if(pressed.equals("A")) {
                        //set timer
                        if(checkerList[2] == 1){ // active 상태인 경우만 set으로 넘어갈 수 있음
                            this.enteringSub(5);
                        }

                    }
                    if(pressed.equals("B")) {
                        //pause&restart
                        if(!((long)system.getTimer()[0] == 0))system.toggleTimerCounting();
                    }
                    if(pressed.equals("D")) {
                        //active&inactive
                        system.toggleTimerActivation();
                    }
                }else if(curSubCategory == 1){
                    //set Timer
                    displayManager.displaySelector();
                    displayManager.setTimer(modeManager.setTimer(system));
                    if(pressed.equals("A")) {
                        //display timer
                        this.exitingSub();
                    }
                    if(pressed.equals("B")) {
                        //increase value
                        system.setTimer(Flag.getTimerValue(displayManager.getSelector(), system.getTimerSetted(), 1));
                    }
                    if(pressed.equals("D")) {
                        //decrease value
                        system.setTimer(Flag.getTimerValue(displayManager.getSelector(), system.getTimerSetted(), -1));
                    }
                    if(pressed.equals("C")) {
                        //change pointer position
                        displayManager.setSelector(Flag.moveTimerSelector(displayManager.getSelector()));
                        //int 선택자 = displayManager에서 선택자를 가져오는거
                        //int 값 = displayManager에서 선택자에 해당하는 값을 가져옴
                        //displayManager.setValue(++displayManager.get선택자(), displayManager.get값())
                    }

                }
            }else if(curMainCategory == 2){
                //StopWatch
                if(curSubCategory == 0) {
                    //display StopWatch  (stopWatch는 set이 없음)
                    displayManager.displayStopWatch(modeManager.displayStopWatch(system));
                    displayManager.displayIcon(functionList, checkerList);

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
            }else if(curMainCategory == 3){
                //Alarm
                if(curSubCategory == 0){
                    //display Alarm
                    displayManager.displayAlarm(modeManager.displayAlarm(system, this.alarmNumber));
                    displayManager.displayIcon(functionList, checkerList);
                    if(pressed.equals("A")) {
                        //set alarm
                        this.enteringSub(12);
                    }
                    if(pressed.equals("B")) {
                        //move to next alarm
                        this.alarmNumber++;
                        if(this.alarmNumber >= 4) this.alarmNumber = this.alarmNumber%4;
                    }
                    if(pressed.equals("D")) {
                        //toggle active/inactive
                        system.toggleAlarmActivation(this.alarmNumber);
                    }
                }else if(curSubCategory == 1){
                    //set Alarm
                    displayManager.displaySelector();
                    Object[] alarmValue = modeManager.displayAlarm(system, this.alarmNumber);
                    displayManager.setAlarm(alarmValue);
                    if(pressed.equals("A")) {
                        //display alarm
                        this.exitingSub();
                    }
                    if(pressed.equals("B") || pressed.equals("D")) {
                        //increase/decrease value & toggle
                        int tempSelector = displayManager.getSelector();
                        LocalTime updateValue = LocalTime.of(0, 0);

                        if(12 <= tempSelector && tempSelector <= 18) { // toggle
                            //System.out.println(Integer.parseInt((String)alarmValue[5]));
                            //System.out.println(Arrays.toString((boolean[])alarmValue[3]));
                            if(Utility.checkAtLeastOne((boolean[])alarmValue[3])) {
                                ((boolean[])alarmValue[3])[tempSelector - 12] = !((boolean[])alarmValue[3])[tempSelector - 12];
                            }
                            //System.out.println(Arrays.toString((boolean[])alarmValue[3]));
                            //System.out.println("    ");

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
                        //System.out.println("updateValueHour: " + updateValue.getHour() + '\n' + "updateValueMin: " + updateValue.getMinute());
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
            } else if(curMainCategory == 4) {
                //GlobalTime
                //System.out.println("displayManager의 selector: " + displayManager.getSelector());
                displayManager.displaySelector();
                displayManager.displayGlobalTime(modeManager.displayGlobalTime(system));
                displayManager.displayIcon(functionList, checkerList);

                /*
                // 엉뚱한 곳에 selector가 있으면 selector를 내 도시에 맞춰 주기
                if(displayManager.getSelector() != 21 && displayManager.getSelector() != 23) {
                    displayManager.setSelector(21);
                    System.out.println("set selector to 21");
                }
                 */

                if(curSubCategory == 0) {
                    // display globalTime  (global Time은 set이 없음)
                    if(pressed.equals("A")) { // change pointer position
                        displayManager.setSelector(Flag.moveGlobalTimeSelector(displayManager.getSelector()));
                    }
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
            }else if(curMainCategory == 5){
                //SleepingTime
                int getSelector = displayManager.getSelector();
                //if(getSelector != 22 && getSelector != 27 && getSelector != 24 && getSelector != 28){
                    //displayManager.setSelector(22); }
                if(curSubCategory == 0){
                    //display sleeping time
                    // displayManager.displayTime(modeManager.displayTime(system));
                    displayManager.displaySleepingTime(modeManager.displaySleepingTime(system));
                    displayManager.displayIcon(functionList, checkerList);

                    // Set sleeping time
                    if(pressed.equals("A")){
                        this.enteringSub(21);
                    }

                    // B버튼이 눌렸을 때는 아무것도 실행되지 않음

                    // C버튼이 눌렸을 때는 맨 위에서 처리

                    // Turn on/off (cheering message 수신 여부)
                    if(pressed.equals("D")){
                        system.toggleCheeringMessageReceiving();
                    }

                }else if(curSubCategory == 1){
                    //set sleeping time
                    //Set sleeping time
                    displayManager.displaySelector();
                    displayManager.displaySleepingTime(modeManager.displaySleepingTimeValue(system));

                    //Move to sleeping time
                    if(pressed.equals("A")){
                        this.exitingSub();
                    }

                    // Increase value
                    if(pressed.equals("B")){

                        if(getSelector == 22 || getSelector == 27){
                            // type: 0-분, 1-시 value: 값
                            system.setWakeUpTime(Flag.getWakeUpSleepTimeValue(getSelector), 1);
                        }else if(getSelector == 24 || getSelector == 28){
                            system.setSleepTime(Flag.getWakeUpSleepTimeValue(getSelector), 1);
                        }

                    }

                    // Decrease value
                    if(pressed.equals("D")){
                        if(getSelector == 22 || getSelector == 27){
                            system.setWakeUpTime(Flag.getWakeUpSleepTimeValue(getSelector), -1);
                        }else if(getSelector == 24 || getSelector == 28) {
                            system.setSleepTime(Flag.getWakeUpSleepTimeValue(getSelector), -1);
                        }
                    }

                    // Change selector position
                    if(pressed.equals("C")){
                        // System.out.println(getSelector);
                        displayManager.setSelector(Flag.moveWakeUpSleepTimeSelector(getSelector));
                    }
                }


            }else if(curMainCategory == 6){
                int getSelector = displayManager.getSelector();
                if(getSelector < 31) displayManager.setSelector(31);
                //Function Change
                if(curSubCategory == 0) {
                    //customize your own clock을 먼저 보여줘야한다.
                    displayManager.displayFunctionListEdit(customText.toString(), functionList);
                    displayManager.displaySelector();

                    //move selected item to left
                    if(pressed.equals("A")){
                        displayManager.changeIconPosition(true);
                        system.moveItem(displayManager.getSelector()-31, -1);
                        displayManager.setSelector(Flag.moveFunctionSelectorReverse(displayManager.getSelector()));
                    }

                    //move selected item to rightmost
                    if(pressed.equals("B")){
                        displayManager.changeIconPosition(false);
                        system.moveItem(displayManager.getSelector()-31 , 1);
                        displayManager.setSelector(Flag.moveFunctionSelector(displayManager.getSelector()));
                        // System.out.println(displayManager.getSelector());
                    }
                    if(pressed.equals("C")){
                        displayManager.notDisplaySelector();
                        displayManager.setSelector(21);
                        displayManager.cleanDisplay();
                        displayManager.displayIcon(functionList, checkerList);
                        System.out.println("TEST:" + Arrays.toString(system.getFunctionList()));
                        mode.setMainCategory(system.getFunctionList()[0]);
                        if(mode.getMainCategory() == 4) system.enterGlobalTime();   //바뀐 이후의 값을 바로 알아내야 하기에 여긴 mode.getMainCategory를 사용함
                        mode.setFunctionSelector(0);
                    }

                    if(pressed.equals("D")){
                        displayManager.setSelector(Flag.moveFunctionSelector(displayManager.getSelector()));
                    }

                    //display function list
                }
            }

            //if(!pressed.equals("default value")) System.out.println("THIRD Pressed : " + pressed + "  MainCategory : " + mode.getMainCategory() + "  SubCategory : " + curSubCategory);
            //if(pressed.equals("default value")) System.out.println("Pressed : " + pressed + "  MainCategory : " + mode.getMainCategory() + "  SubCategory : " + curSubCategory);


            //Thread.sleep(1000);
        }
    }

    public UI() throws Exception{
        // currentState = "DISPLAY_TIME";

        displayManager = new DisplayManager();
        modeManager = new ModeManager();
        event = displayManager.myMouseEvent;
        system = new ClockSystem();
        mode = new Mode();
        buzzer = new Buzzer();

        systemWatching();

    }

    public void enteringSub(int selector) {
        displayManager.notDisplayIcon();
        displayManager.setSelector(selector);
        displayManager.cleanDisplay();
        displayManager.displaySelector();
        mode.enterSub();
    }
    public void exitingSub() {
        displayManager.notDisplaySelector();
        displayManager.setSelector(21);
        displayManager.cleanDisplay();
        displayManager.displayIcon(functionList, checkerList);
        mode.exitSub();
    }
}


