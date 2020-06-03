package controller;

import controller.mode.*;

import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.ArrayList;

public class ClockSystem {
    private SleepingTime sleepingTime;
    private FunctionList functionList;
    private TimeKeeping timeKeeping;
    private Alarm[] alarms;
    private GlobalTime globalTime;
    private Timer timer;
    private StopWatch stopWatch;

    private long clock = java.lang.System.currentTimeMillis();
    //이 Clock은, Clock.mills()의 return값을 담는 것임. 아직

    //UI가 시스템에 요청하는 메소드. (Time Keeping에서 현재 시간의 값을 받아옴)
    public Object[] getTime() {
        //현재 시간을 받아온다 (Time Keeping의 time과 System의 clock을 더해서 return
        //TimeKeeping.time + System.clock의 값과, TimeKeeping.timeformat을 return 해줘야 한다.
        return new Object[]{(long)0 /*TimeKeeping.time + System.clock의 값*/, (long)0 /*TimeKeeping.timeformat*/};
    }

    public void setTime(long updateValue) {
        // updateTime(updateValue)을 호출한다.
    }

    public void setTimer(long updateValue) {
        // updateTimer(updateValue)를 호출한다.
    }

    public void setAlarm(boolean[] alarmingDay, LocalTime updateValue) {
        //updateAlarm(alarmingDay, updateValue)를 호출한다.
    }

    public void setMyTimeZone(int updateValue) {
        // updateMyTimeZone(updateValue)을 실행한다.
    }

    public void setAnotherTimeZone(int updateValue) {
        // updateAnotherTimeZone(updateValue)을 실행한다.
    }

    public void setWakeUpTime(LocalTime updateValue) {
        // updateWakeUpTime(updateValue)를 실행한다.
    }

    public void setSleepTime(LocalTime updateValue) {
        // updateSleepTime(updateValue)를 실행한다.
    }

    public void changeTimeFormat() {
        // UI가 System에 Time Format 변경을 요청할 때 호출되는 메소드
        // TimeKeeping의 toggleTimeFormat을 호출함
    }

    public Object[] getTimer(){
        /*startTime : 1시
        settedTime : 6분 (default)
        stackedTime : 0분
        UI상 보여지는 시간 : settedTime - (System.clock - startTime + stackedTime)   ->  이게 return값.
        running : True*/
        //여기서,
        // 1. UI에서 표시해줘야 할 UI상 보여지는 시간 : settedTime - (System.clock - startTime + stackedTime)
        // 2. On/Off 상태 (Timer.status값)를 전달해줘야 함
        // 이는 calculateTimerValue()를 실행하여 얻어진다.
        return new Object[]{0 /*UI상 보여지는 시간 : settedTime - (System.clock - startTime + stackedTime)*/, 0 /*On/Off 상태 (Timer.status값)*/};
    }

    public long getTimerSetted() {
        // Timer에 getSettedTime()을 호출해서 리턴함

        return 0;
    }

    public void toggleTimerCounting() {
        //changeTimerCounting()을 호출한다.
    }

    public void toggleTimerActivation() {
        //changeTimerActivation(long clock)을 호출한다.
    }

    public Object[] getAlarm(){
        //UI에 뿌려질 Alarm의 상태를 전달해주는 메소드
        //활성화정보, 요일, 시각 return 해야 함.
        return new Object[]{0 /*활성화정보 - int형*/, 0 /*요일정보 - boolean[]형*/, 0 /*시각정보 - long형*/};
    }

    public int getNowAlarm(long currentTime) {
        // checkAlarm을 각각 알람마다 호출한다.(for문)

        /*현재 알람 울림여부, 현재 알람 activate 여부*/
        return 0;
    }

    public int getNowSleeping(long currentTime) {
        // checkSleeping을 호출한다

        /*현재 sleeping time울림 여부, 현재 sleeping time activate 여부*/
        return 0;
    }

    public int getNowTimer(long currentTime) {
        // checkTimer를 호출한다

        return 0; /*현재 timer울림 여부, 현재 timer activate 여부*/
    }

    public Object[] getStopWatchTime() {
        // calculateStopWatch(long clock)를 호출한다
        // getLap()을 호출한다
        // 위의 두 값을 배열로 묶어 리턴한다

        return new Object[]{}; // 계산된 stopwatch time, Lap time을 리턴
    }

    public int checkStopWatchState() {
        //getStopWatchState()를 호출한다.

        return 0;
    }

    public void lapStopWatch(long lapTime) {
        // setLap(long lapTime)을 호출한다.
    }

    public void toggleStopWatchState() {
        //changeStopWatchState()를 호출한다.
    }

    public void resetStopWatch() {
        // resetStopWatch()를 호출한다.
    }

    public Object[] moveToNextAlarm(int number) {
        //number로 들어온 알람의 getAlarmingDay(), getAlarmingTime()을 각각 호출한다.
        //number로 들어온 알람의 alarmingDay와 alarmingTime을 배열로 묶어서 리턴한다.

        return new Object[]{};
    }

    public void toggleAlarmActivation(int number) {
        // number에 해당하는 알람에 changeAlarmActivation()을 호출
    }

    public Object[] getGlobalTime() {
        //UI에 뿌려질 GlobalTime의 상태를 전달해주는 메소드
        //내도시 시간, 내도시 UTC, 내도시 이름, 남의도시 시간, 남의도시 UTC, 남의도시 이름 6개 return 해줘야함
        //myTime : TimeKeeping에서 받아와야함 getTime()
        //myUTC, myCityName 모두 GlobalTime에서 받아와야함
        //남의도시 시간 : Object[]{0 = System.Clock + GlobalTime.time, 0 = myTime[1]}
        //남의도시UTC, 남의도시CityName 모두 GlobalTime에서 받아와야함
        return new Object[]{0 /*내도시 시간*/, 0 /*내도시 시간제*/, 0 /*내도시 이름*/, 0 /*남의도시 시간*/, 0 /*남의도시 시간제*/, 0/*남의도시 이름*/};
    }

    //Display Sleeping Time에서 호출하는 메소드
    public Object[] getSleepingTime(){
        //UI에 뿌려질 GlobalTime의 상태를 전달해주는 메소드
        //calculateSleepingTime()을 통해 추천 수면시간 1, 2를 보내줌.
        //두 값 모두 SleepingTime에서 받아와야 함
        return new Object[]{0 , 0};
    }
    
    //Set Sleeping Time에서 호출하는 메소드
    public LocalTime[] getSleepingTimeValue(){
        //set sleeping time에서 UI에 뿌려질 정보를 전달해주는 메소드
        //getSleepTime(), getWakeUpTime()을 통해 수면시간, 기상시각을 보내줘야함.

        LocalTime localTime[] = new LocalTime[]{ LocalTime.now()/*수면시간*/, LocalTime.now()/*기상시각*/};
        return localTime;
    }

    public void toggleCheeringMessageReceiving() {
        // toggleSleepingTimeState()를 호출한다.
    }


    public ArrayList<Object> getFunctionList(){
        //UI에 뿌려질 Function List의 순서를 전달해주는 메소드
        return new ArrayList<Object>();
    }

    public void moveItem(int location, int direction) {
        //updateItemPosition(location, direction)을 호출한다
    }

    
    //초기 설계했던 CD에서의 clock과 동일한 역할을 함. 이 함수를 호출할시 자동으로 시스템의 현재시간을 밀리세컨드 단위로 리턴
    public long getClock(){
        return java.lang.System.currentTimeMillis();
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
