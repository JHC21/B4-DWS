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
            //timeFormat[9] = "OFF"; // functionList에 어떻게 표시될까?
        }
        else { // temp == 1
            timeFormat[8] = "ON";
            //timeFormat[9] = "ON"; // functionList에 어떻게 표시될까?
        }

        return timeFormat;
    }

    public String[] displayAlarm(ClockSystem clockSystem, int alarmNo) {
        /*
        0 : 시
        1 : 분
        2 : AM/PM // 일단 UI의 역할로 넘겼습니다.
        3 : 일
        4 : 월
        5 : 화
        6 : 수
        7 : 목
        8 : 금
        9 : 토
        10 : activate
        11 : alarm number
         */
        String[] timeFormat = new String[12];

        // 0 : alarming day(boolean[7]), 1 : alarming time(LocalTime), 2 : state(0 : off, 1 : on)
        Object[] alarmValue = clockSystem.getAlarm(alarmNo);

        timeFormat[0] = String.format("%02d", ((LocalTime)alarmValue[1]).getHour());
        timeFormat[1] = String.format("%02d", ((LocalTime)alarmValue[1]).getMinute());
        if(((boolean[])alarmValue[0])[0]) timeFormat[3] = "일"; else timeFormat[3] = "false";
        if(((boolean[])alarmValue[0])[1]) timeFormat[4] = "월"; else timeFormat[3] = "false";
        if(((boolean[])alarmValue[0])[2]) timeFormat[5] = "화"; else timeFormat[3] = "false";
        if(((boolean[])alarmValue[0])[3]) timeFormat[6] = "수"; else timeFormat[3] = "false";
        if(((boolean[])alarmValue[0])[4]) timeFormat[7] = "목"; else timeFormat[3] = "false";
        if(((boolean[])alarmValue[0])[5]) timeFormat[8] = "금"; else timeFormat[3] = "false";
        if(((boolean[])alarmValue[0])[6]) timeFormat[9] = "토"; else timeFormat[3] = "false";
        if((int)alarmValue[2] == 0) timeFormat[10] = "OFF";
        else timeFormat[10] = "ON";
        timeFormat[11] = String.format("%d", alarmNo);
        return timeFormat;
    }

    public String[] displayGlobalTime(ClockSystem clockSystem) {
        /*
        0: myTimeZone의 시간제
        1: myTimeZone의 hour
        2: myTimeZone의 minute
        3: myTimeZone의 도시 1
        4: myTimeZone의 도시 2
        5: myTimeZone의 도시 3

        5: anotherTimeZone의 hour
        6:  anotherTimeZone의 minute
        7: anotherTimeZone의 도시 1
        8: anotherTimeZone의 도시 2
        9: anotherTimeZone의 도시 3
        */

        // Object[] globalTime이 Object[]들의 배열이므로 바로 접근하지 말고 하나씩 가져왔음
        Object[] globalTime = clockSystem.getGlobalTime(false);
        Object[] myTimeData = (Object[]) globalTime[0];
        Object[] anotherTimeData = (Object[]) globalTime[1];
        String[] myCityNames = (String[])(myTimeData[2]);
        String[] anotherCityNames = (String[])(anotherTimeData[2]);
        String globalTimeFormat[] = new String[12]; // 리턴해줄 형식

        Object[] time = clockSystem.getTime(); // 시간제를 알아오기 위해 받아옴
        // myTime
        if((Boolean) time[1]){ // 12시간제인 경우
            int temp = Integer.parseInt((String)myTimeData[0]);
            if(temp > 12){
                globalTimeFormat[1] = String.format("%02d", temp - 12);
                globalTimeFormat[0] = "오후";
            }
            else{ globalTimeFormat[0] = "오전"; }
        } else {
            globalTimeFormat[0] = null; // 24시간제
        }
        globalTimeFormat[2] = (String)myTimeData[1]; // 분
        globalTimeFormat[3] = myCityNames[0]; // 내 도시 1
        globalTimeFormat[4] = myCityNames[1]; // 내 도시 2
        globalTimeFormat[5] = myCityNames[2]; // 내 도시 3
        //anotherTime
        if((Boolean) time[1]){ // 12시간제인 경우
            int temp = Integer.parseInt((String)anotherTimeData[0]);
            if(temp > 12){
                globalTimeFormat[7] = String.format("%02d", temp - 12);
                globalTimeFormat[6] = "오후";
            }
            else{ globalTimeFormat[7] = "오전"; }
        } else {
            globalTimeFormat[6] = null; // 24시간제
        }
        globalTimeFormat[8] = (String)anotherTimeData[1]; // 분
        globalTimeFormat[9] = anotherCityNames[0]; // 다른 도시 1
        globalTimeFormat[10] = anotherCityNames[1]; // 다른 도시 2
        globalTimeFormat[11] = anotherCityNames[2]; // 다른 도시 3

        return globalTimeFormat;
    }
}
