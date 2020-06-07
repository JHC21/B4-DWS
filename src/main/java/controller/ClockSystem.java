package controller;

import controller.mode.*;

import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public class ClockSystem {
    private SleepingTime sleepingTime;
    private FunctionList functionList;
    private TimeKeeping timeKeeping;
    private GlobalTime globalTime;
    private Alarm[] alarms;
    private Timer timer;
    private StopWatch stopWatch;

    public ClockSystem(){
        sleepingTime = new SleepingTime();
        functionList = new FunctionList();
        timeKeeping = new TimeKeeping();
        alarms = new Alarm[4];
        for(int i = 0; i < 4; i++){
            alarms[i] = new Alarm();
        }
        globalTime = new GlobalTime();
        timer = new Timer();
        stopWatch = new StopWatch();
    }

    //초기 설계했던 CD에서의 clock과 동일한 역할을 함. 이 함수를 호출할시 자동으로 시스템의 현재시간을 밀리세컨드 단위로 리턴
    public long clock(){
        return java.lang.System.currentTimeMillis();
    }


    //getNow 시리즈
    public int getNowAlarm(long currentTime) {
        // checkAlarm을 각각 알람마다 호출한다.(for문)
        int checker = 0; int checkPosition = 0;
        for(int i = 0; i < 4; i++){
            int val = this.alarms[i].checkAlarm(currentTime);
            if(val > checker){
                checker = val; checkPosition = i;
            }
        }

        if(checker == 0 || checker == 1){
            return checker;
        }else{
            return checkPosition;
        }

        /*현재 알람 울림여부, 현재 알람 activate 여부*/
        /*만약 알람이 울릴 경우 해당 알람의 넘버를 return하긴 하는데, 이건 좀 논의가 필요해보임 */
    }
    public int getNowSleeping(long currentTime) {
        // checkSleeping을 호출한다

        /*현재 sleeping time울림 여부, 현재 sleeping time activate 여부*/
        return this.sleepingTime.checkSleeping(currentTime);
    }
    public int getNowTimer(long currentTime) {
        // checkTimer를 호출한다
        return this.timer.checkTimer(currentTime);
        /*현재 timer울림 여부, 현재 timer activate 여부*/
    }


    //UI가 시스템에 요청하는 메소드. (Time Keeping에서 현재 시간의 값을 받아옴)
    //timeKeeping
    public Object[] getTime() {
        return this.timeKeeping.calculateTime(this.clock());
        //현재 시간을 받아온다 (Time Keeping의 time과 System의 clock을 더해서 return
        //TimeKeeping.time + System.clock의 값과, TimeKeeping.timeformat을 return 해줘야 한다.
    }
    public void changeTimeFormat() {
        this.timeKeeping.toggleTimeFormat();
        // UI가 System에 Time Format 변경을 요청할 때 호출되는 메소드
        // TimeKeeping의 toggleTimeFormat을 호출함
    }
    public void setTime(long updateValue) {
        this.timeKeeping.updateTime(updateValue);
        // updateTime(updateValue)을 호출한다.
    }


    //timer
    public Object[] getTimer(){
        Object[] value = new Object[2];
        value[0] = this.timer.calculateTimerValue(this.clock()); // long
        value[1] = this.timer.getState(); // off : 0, on & stop : 1 , on & run : 2
        return value;
        /*startTime : 1시
        settedTime : 6분 (default)
        stackedTime : 0분
        UI상 보여지는 시간 : settedTime - (System.clock - startTime + stackedTime)   ->  이게 return값.
        running : True*/
        //여기서,
        // 1. UI에서 표시해줘야 할 UI상 보여지는 시간 : settedTime - (System.clock - startTime + stackedTime)
        // 2. On/Off 상태 (Timer.status값)를 전달해줘야 함
    }
    public void setTimer(long updateValue) {
        this.timer.updateTimer(updateValue);
        // updateTimer(updateValue)를 호출한다.
    }
    public long getTimerSetted() {
        return this.timer.getSettedTime();
    }
    public void toggleTimerCounting() {
        this.timer.changeTimerCounting(this.clock());
        //changeTimerCounting()을 호출한다.
    }
    public void toggleTimerActivation() {
        this.timer.changeTimerActivation(this.clock());
        //changeTimerActivation(long clock)을 호출한다.
    }


    //stopwatch
    public Object[] getStopWatchTime() {
        Object[] value = new Object[3];
        value[0] = this.stopWatch.calculateStopWatch(this.clock()); // long
        value[1] = this.stopWatch.getLap(); // long
        value[2] = this.stopWatch.getStopWatchState(); // int
        // calculateStopWatch(long clock)를 호출한다
        // getLap()을 호출한다
        // 위의 두 값을 배열로 묶어 리턴한다
        if((long)value[0] > 86400000L) { // 24시간을 초과하였을 경우
            toggleStopWatchState(); // counting을 멈추고(counting중에만 24시간을 넘어갈 수가 있다)
            value[0] = 86400000L; // display에는 24시간 값으로 stopWatchTime을 설정해 버린다
        }

        return value;
    }
    public int checkStopWatchState() {
        return this.stopWatch.getStopWatchState();
    }
    public void lapStopWatch() {
        this.stopWatch.setLap(this.clock());
    }
    public void toggleStopWatchState() {
        this.stopWatch.changeStopWatchState(this.clock());
    }
    public void resetStopWatch() {
        this.stopWatch.setReset(this.clock());
    }


    //alarm
    public Object[] getAlarm(int alarmNum){
        Object[] value = new Object[3];
        value[0] = this.alarms[alarmNum].getAlarmingDay(); // boolean[7]
        value[1] = this.alarms[alarmNum].getAlarmingTime(); // LocalTime
        value[2] = this.alarms[alarmNum].getState(); // 0 : off, 1 : on
        //UI에 뿌려질 Alarm의 상태를 전달해주는 메소드
        //활성화정보, 요일, 시각 return 해야 함.
        return value;
    }
    public void setAlarm(int number, boolean[] alarmingDay, LocalTime updateValue) {
        //updateAlarm(alarmingDay, updateValue)를 호출한다.
        this.alarms[number].updateAlarmValue(alarmingDay,updateValue);
    }
    public void toggleAlarmActivation(int number) {
        this.alarms[number].changeAlarmActivation();
        // number에 해당하는 알람에 changeAlarmActivation()을 호출
    }


    //global time
    public Object[] getGlobalTime() {
        //UI에 뿌려질 GlobalTime의 상태를 전달해주는 메소드
        //this.globalTime.updateTimeValue(this.timeKeeping.getTimeValue());
        // CityData[0]: {myTimeZone 시, myTimeZone 분, 도시 이름}
        // CityData[1]: {anotherTimeZone 시, anotherTimeZone 분, 도시 이름}
        return this.globalTime.getCityData(this.clock());
    }
    public void setMyTimeZone(int updateValue) {
        // updateMyTimeZone(updateValue)을 실행한다.
        this.globalTime.updateMyTimeZone(updateValue);
    }
    public void setAnotherTimeZone(int updateValue) {
        // updateAnotherTimeZone(updateValue)을 실행한다.
        this.globalTime.updateAnotherTimeZone(updateValue);
    }
    public void enterGlobalTime() {
        this.globalTime.updateTimeValue(this.timeKeeping.getTimeValue());
    }


    //Display Sleeping Time에서 호출하는 메소드
    //sleeping time
    public Object[] getSleepingTime(long currentTime){
        //UI에 뿌려질 sleeping time의 상태를 전달해주는 메소드
        //calculateSleepingTime()을 통해 추천 수면시간 1, 2를 보내줌.
        //두 값 모두 SleepingTime에서 받아와야 함
        Object[] temp = new Object[2];
        temp[0] = currentTime;
        temp[1] = null;
        Object[] sleepingTime = this.sleepingTime.calculateSleepingTime(temp);

        return sleepingTime;
    }
    //Set Sleeping Time에서 호출하는 메소드
    public LocalTime[] getSleepingTimeValue(){
        //set sleeping time에서 UI에 뿌려질 정보를 전달해주는 메소드
        //getSleepTime(), getWakeUpTime()을 통해 수면시간, 기상시각을 보내줘야함.

        LocalTime localTime[] = new LocalTime[2];
        localTime[0] = this.sleepingTime.getSleepTime();
        localTime[1] = this.sleepingTime.getWakeUpTime();
        return localTime; // 0 : sleep time, 1 : wakeup time
    }
    public void toggleCheeringMessageReceiving() {
        // toggleSleepingTimeState()를 호출한다.
        this.sleepingTime.toggleSleepingTimeState();
    }
    public void setWakeUpTime(int type, int value) {
        // updateWakeUpTime(updateValue)를 실행한다.
        this.sleepingTime.updateWakeUpTime(type, value);
    }
    public void setSleepTime(int type, int value) {
        // updateSleepTime(updateValue)를 실행한다.
        this.sleepingTime.updateSleepTime(type, value);
    }


    //function list
    public int[] getFunctionList(){
        //UI에 뿌려질 Function List의 순서를 전달해주는 메소드
        return this.functionList.getFunctions();
    }
    public void moveItem(int location, int direction) {
        //updateItemPosition(location, direction)을 호출한다
        this.functionList.updateItemPosition(location, direction);
    }
    
    public void ringTimer(){
        //Timer가 울릴 시간이 되었을때 소리울리는 메소드
        //의미 없음
    }

    public void ringAlarm(){
        //Alarm이 울릴 시간이 되었을때 소리울리는 메소드
        //의미 없음
    }

    public void ringSleepingTime(){
        //SleepingTime이 울릴 시간이 되었을때 소리울리는 메소드
        //의미 없음
    }
    
    /*public void showAlarming(){
        //알람이 울릴 시기가 되었을 때 알람이 울리는 것을 뜻함. UI에서 처리해줘야 할 가능성이 크기 때문에 System에선 구현하지 않을 수 있어 주석처리
     */

    /*public void showAlarming(){
        //알람이 울릴 시기가 되었을 때 알람이 울리는 것을 뜻함. UI에서 처리해줘야 할 가능성이 크기 때문에 System에선 구현하지 않을 수 있어 주석처리
     */

}
