package view;

import controller.Utility;
import controller.ClockSystem;
import view.template.Flag;

import java.time.LocalTime;
import java.util.Arrays;

public class ModeManager {

    public int[] checker(ClockSystem clockSystem){
        long currentTime = (long)clockSystem.getTime()[0];
        int[] value = new int[3];
        value[0] = clockSystem.getNowAlarm(currentTime);
        value[1] = clockSystem.getNowSleeping(currentTime);
        value[2] = clockSystem.getNowTimer(currentTime);

        return value;
    }


    // TODO: 버튼확인
    public String[] displayTime(ClockSystem clockSystem){
        Object[] time = clockSystem.getTime();
        String[] timeFormat = Utility.millitoTimeFormat_test((long)time[0]);
        if((Boolean) time[1]){
            int temp = Integer.parseInt(timeFormat[3]);
            if(temp > 12){
                timeFormat[3] = String.format("%02d", temp - 12);
                timeFormat[8] = "오후";
            }
            else{timeFormat[8] = "오전";}
        }else{
            timeFormat[8] = null;
        }
        //이 값을 displayManager에 표시될 수 있게끔 값을 가공해서
        //String배열로 넘겨주고, 배열에는
        //표시될 위치를 표시해주면 됨
        return timeFormat;
    }

    public String[] displayTimer(ClockSystem clockSystem){
        Object[] timer = clockSystem.getTimer(); // 0 : long, 1 : state
        //어쩌구저쩌구 값 처리
        String[] timeFormat = new String[5];
        /*
        0 : hour
        1 : minute
        2 : second
        3 : counting
        4 : activate는 checker에서 할 수 있을 듯
        */
        int[] timerTime = Utility.milliToTimeFormat((long)timer[0]);
        timeFormat[0] = String.format("%02d", timerTime[3]); // hour
        timeFormat[1] = String.format("%02d", timerTime[4]); // minute
        timeFormat[2] = String.format("%02d", timerTime[5]); // second

        int temp = (int)timer[1];
        if(temp == 2) { // on & activate
            timeFormat[3] = "ON";
        }
        else {
            //off or inactivate
            timeFormat[3] = "OFF";
        }
        return timeFormat;
    }

    public String[] setTimer(ClockSystem clockSystem) {
        long timerValue = clockSystem.getTimerSetted();
        String[] timeFormat = new String[3]; // 9는 임시 값
        /*
        0 : hour
        1 : minute
        2 : second
        activate는 checker에서 할 수 있을 듯
        */

        int[] timerTime = Utility.milliToTimeFormat((long)timerValue);
        timeFormat[0] = String.format("%02d", timerTime[3]); // hour
        timeFormat[1] = String.format("%02d", timerTime[4]); // minute
        timeFormat[2] = String.format("%02d", timerTime[5]); // second

        return timeFormat;
    }

    public String[] displayStopWatch(ClockSystem clockSystem) {
        //S : stopwatch, L : Lap
        /*
        0 : S시
        1 : S분
        2 : S초
        3 : Sms
        4 : L시
        5 : L분
        6 : L초
        7 : Lms
        8 : pause/start
        9 : activate
         */
        //activate에 대해 이야기 해 보아야 함(function list에 어떻게 표현해야 할 지)
        String[] timeFormat = new String[10]; // 리턴 값

        Object[] stopwatchValue = clockSystem.getStopWatchTime(); // system으로부터 아래 값들을 가져옴
        // 0 : stopwatchTime(long), 1 : lapTime(long), 2 : state(int)

        int[] stopwatchTime = Utility.milliToTimeFormat((long)stopwatchValue[0]); // long to timeFormat 변환
        int[] lapTime = Utility.milliToTimeFormat((long)stopwatchValue[1]); // long to timeFormat 변환
        int temp = (int)stopwatchValue[2]; // state => 0 : pause, 1 : start

        timeFormat[0] = String.format("%2d",stopwatchTime[3]); // S시
        timeFormat[1] = String.format("%2d",stopwatchTime[4]); // S분
        timeFormat[2] = String.format("%2d",stopwatchTime[5]); // S초
        timeFormat[3] = String.format("%3d",stopwatchTime[6]); // Sms
        timeFormat[4] = String.format("%2d",lapTime[3]); // L시
        timeFormat[5] = String.format("%2d",lapTime[4]); // L분
        timeFormat[6] = String.format("%2d",lapTime[5]); // L초
        timeFormat[7] = String.format("%3d",lapTime[6]); // Lms
        if(temp == 0) {
            timeFormat[8] = "OFF";
            //timeFormat[9] = "OFF";
        }
        else { // temp == 1
            timeFormat[8] = "ON";
            //timeFormat[9] = "ON";
        }

        return timeFormat;
    }

    public Object[] displayAlarm(ClockSystem clockSystem, int alarmNo) {
        /*
        0 : 시
        1 : 분
        2 : AM/PM
        3 : 요일 boolean 배열
        4 : activate
        5 : alarm number
         */
        Object[] timeFormat = new String[6];

        // 0 : time(long), 1 : timeFormat
        Object[] timeValue = clockSystem.getTime();
        // 0 : alarming day(boolean[7]), 1 : alarming time(LocalTime), 2 : state(0 : off, 1 : on)
        Object[] alarmValue = clockSystem.getAlarm(alarmNo);

        timeFormat[0] = String.format("%02d", ((LocalTime)alarmValue[1]).getHour());
        timeFormat[1] = String.format("%02d", ((LocalTime)alarmValue[1]).getMinute());
        if((boolean)timeValue[1]){
            int temp = Utility.milliToTimeFormat((long)timeValue[0])[3];
            if(temp > 12){
                timeFormat[0] = String.format("%02d", temp - 12);
                timeFormat[2] = "오후";
            }
            else{timeFormat[2] = "오전";}
        }else{
            timeFormat[2] = null;
        }
        timeFormat[3] = alarmValue[0]; // boolean 배열
        if((int)alarmValue[2] == 0) timeFormat[4] = "OFF";
        else timeFormat[4] = "ON";
        timeFormat[5] = String.format("%d", alarmNo);
        return timeFormat;
    }
}
