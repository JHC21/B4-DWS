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

        if(value[2] == 2) System.out.println("Timer Activated!");

        return value;
    }


    // TODO: 버튼확인
    public Object[] displayTime(ClockSystem clockSystem){
        Object[] value = new Object[2];
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
            timeFormat[8] = "  ";
        }
        //이 값을 displayManager에 표시될 수 있게끔 값을 가공해서
        //String배열로 넘겨주고, 배열에는
        //표시될 위치를 표시해주면 됨
        value[0] = timeFormat;
        value[1] = time[0];

        return value;
    }

    public String[] displayTimer(ClockSystem clockSystem){
        Object[] timer = clockSystem.getTimer(); // 0 : long, 1 : state
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

        if((int)timer[1] == 0){
            return new String[]{"false", "false", "false", "false", "OFF"};
        }

        int temp = (int)timer[1];
        if(temp == 2) { // on & activate
            timeFormat[3] = "ON";
        }
        else {
            //off or inactivate
            timeFormat[3] = "OFF";
        }
        timeFormat[4] = String.valueOf(timer[1]);
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
        String[] timeFormat = new String[9]; // 리턴 값

        Object[] stopwatchValue = clockSystem.getStopWatchTime(); // system으로부터 아래 값들을 가져옴
        // 0 : stopwatchTime(long), 1 : lapTime(long), 2 : state(int)

        int[] stopwatchTime = Utility.milliToTimeFormat((long)stopwatchValue[0]); // long to timeFormat 변환
        int[] lapTime = Utility.milliToTimeFormat((long)stopwatchValue[1]); // long to timeFormat 변환

        timeFormat[0] = String.format("%02d",stopwatchTime[3]); // S시
        timeFormat[1] = String.format("%02d",stopwatchTime[4]); // S분
        timeFormat[2] = String.format("%02d",stopwatchTime[5]); // S초
        timeFormat[3] = String.format("%03d",stopwatchTime[6]); // Sms
        timeFormat[4] = String.format("%02d",lapTime[3]); // L시
        timeFormat[5] = String.format("%02d",lapTime[4]); // L분
        timeFormat[6] = String.format("%02d",lapTime[5]); // L초
        timeFormat[7] = String.format("%03d",lapTime[6]); // Lms
        timeFormat[8] = String.valueOf((int)stopwatchValue[2]);

        return timeFormat;
    }

    public Object[] displayAlarm(ClockSystem clockSystem, int alarmNo) {
        /*
        0 : 시
        1 : 분
        2 : 오전/PM
        3 : 요일 boolean 배열
        4 : activate
        5 : alarm number
         */
        Object[] timeFormat = new Object[6];

        // 0 : time(long), 1 : timeFormat
        Object[] timeValue = clockSystem.getTime();
        // 0 : alarming day(boolean[7]), 1 : alarming time(LocalTime), 2 : state(0 : off, 1 : on)
        Object[] alarmValue = clockSystem.getAlarm(alarmNo);

        int alarmHour = ((LocalTime)alarmValue[1]).getHour();
        timeFormat[0] = String.format("%02d", ((LocalTime)alarmValue[1]).getHour());
        timeFormat[1] = String.format("%02d", ((LocalTime)alarmValue[1]).getMinute());
        if((boolean)timeValue[1]){
            if(alarmHour > 12){
                timeFormat[0] = String.format("%02d", ((LocalTime)alarmValue[1]).getHour() - 12);
                timeFormat[2] = "오후";
            } else{
                timeFormat[2] = "오전";
            }
        }else{
            timeFormat[2] = "  ";
        }
        timeFormat[3] = alarmValue[0]; // boolean 배열
        if((int)alarmValue[2] == 0) timeFormat[4] = "OFF";
        else timeFormat[4] = "ON";
        timeFormat[5] = String.format("%d", alarmNo);

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
        6: anotherTimeZone의 시간제
        7: anotherTimeZone의 hour
        8: anotherTimeZone의 minute
        9: anotherTimeZone의 도시 1
        10: anotherTimeZone의 도시 2
        11: anotherTimeZone의 도시 3
        */

        // Object[] globalTime이 Object[]들의 배열이므로 바로 접근하지 말고 하나씩 가져왔음
        Object[] globalTime = clockSystem.getGlobalTime();

        Object[] myTimeData = (Object[]) globalTime[0]; // 내 도시 관련 모든 것들
        Object[] anotherTimeData = (Object[]) globalTime[1]; // 다른 도시 관련 모든 것들
        String[] myCityNames = (String[])(myTimeData[2]); // 내 도시 이름들
        String[] anotherCityNames = (String[])(anotherTimeData[2]); // 다른 도시 이름들

        String[] globalTimeFormat = new String[12]; // 리턴해줄 형식

        Object[] time = clockSystem.getTime(); // 시간제를 알아오기 위해 받아옴
        // myTime
        if((boolean) time[1]){ // 12시간제인 경우
            int temp = (int)myTimeData[0]; //  내 도시의 시
            if(temp > 12){ // 12시간제에서의 오후
                globalTimeFormat[1] = String.format("%02d", temp - 12); // 내 도시의 시
                globalTimeFormat[0] = "오후"; // 내 도시의 시간제
            }
            else{ // 12간제에서의 오전
                globalTimeFormat[1] =String.format("%02d", myTimeData[0]); // 내 도시의 시
                globalTimeFormat[0] = "오전";
            }
        } else { // 24시간제
            globalTimeFormat[1] = String.format("%02d", myTimeData[0]);
            globalTimeFormat[0] = null; // 24시간제
        }
        globalTimeFormat[2] = String.format("%02d", myTimeData[1]); // 내 도시의 분
        globalTimeFormat[3] = myCityNames[0]; // 내 도시 1
        globalTimeFormat[4] = myCityNames[1]; // 내 도시 2
        globalTimeFormat[5] = myCityNames[2]; // 내 도시 3

        //anotherTime
        if((boolean) time[1]){ // 12시간제인 경우
            int temp = (int)anotherTimeData[0]; // 다른 도시의 시
            if(temp > 12) { // 12시간제에서의 오후
                globalTimeFormat[7] = String.format("%02d", temp - 12); // 다른 도시의 시
                globalTimeFormat[6] = "오후";
            } else { // 12시간제에서의 오전
                globalTimeFormat[7] = String.format("%02d", temp); //다른 도시의 시
                globalTimeFormat[6] = "오전";
            }
        } else { // 24시간제
            globalTimeFormat[7] = String.valueOf(anotherTimeData[0]); // 다른 도시의 시
            globalTimeFormat[6] = null; // 24시간제
        }
        globalTimeFormat[8] = String.format("%02d", anotherTimeData[1]); // 분
        globalTimeFormat[9] = anotherCityNames[0]; // 다른 도시 1
        globalTimeFormat[10] = anotherCityNames[1]; // 다른 도시 2
        globalTimeFormat[11] = anotherCityNames[2]; // 다른 도시 3
//        System.out.println("다른 도시 시간제: " + globalTimeFormat[6]);
//        System.out.println("다른 도시 시: " + globalTimeFormat[7]);
//        System.out.println("다른 도시 분: " + globalTimeFormat[8]);
//        System.out.println("다른 도시 1: " + globalTimeFormat[9]);
//        System.out.println("다른 도시 2: " + globalTimeFormat[10]);
//        System.out.println("다른 도시 3: " + globalTimeFormat[11]);

        return globalTimeFormat;
    }


    public String[] displaySleepingTime(ClockSystem clockSystem){

        long currentTime = (long)clockSystem.getTime()[0];
        boolean currentFormat = (boolean)clockSystem.getTime()[1];

        //LocalTime으로 받아옴(0: 추천 수면시간 1, 1: 추천 수면시간 2)
        Object[] sleepingTime = clockSystem.getSleepingTime(currentTime);

        String[] timeFormat = new String[9];

        LocalTime first = (LocalTime)sleepingTime[0];
        LocalTime second = (LocalTime)sleepingTime[1];
        Integer turned = (Integer)sleepingTime[2];

        timeFormat[0] = "<html>추천<br>수면시각1</html>";
        timeFormat[4] = "<html>추천<br>수면시각2</html>";
        // timeFormat[1] => Timeformat
        timeFormat[2] = String.format("%02d",first.getHour());
        timeFormat[3] = String.format("%02d",first.getMinute());
        // timeFormat[5] => Timeformat
        timeFormat[6] = String.format("%02d",second.getHour());
        timeFormat[7] = String.format("%02d",second.getMinute());
        // timeFormat[8]

        if(currentFormat){

            int tmpFirstTime = Integer.parseInt(timeFormat[2]);
            int tmpSecondTime = Integer.parseInt(timeFormat[6]);

            if(tmpFirstTime > 12){
                timeFormat[2] = String.format("%02d", tmpFirstTime - 12);
                timeFormat[1] = "오후";
            }else{
                timeFormat[1] = "오전";
            }

            if(tmpSecondTime > 12){
                timeFormat[6] = String.format("%02d", tmpFirstTime - 12);
                timeFormat[5] = "오후";
            }else{
                timeFormat[5] = "오전";
            }

        }else{
            timeFormat[5] = "  ";
            timeFormat[1] = "  ";
        }

        if(turned == 0) timeFormat[8] = "OFF";
        else if(turned == 1) timeFormat[8] = "ON";

        return timeFormat;
    }

    public String[] displaySleepingTimeValue(ClockSystem clockSystem){
        String[] timeFormat = new String[9];

        LocalTime[] sleepingTimeValue = clockSystem.getSleepingTimeValue();

        boolean currentFormat = (boolean)clockSystem.getTime()[1];

        timeFormat[0] = "<html>목표<br>기상시각</html>";
        timeFormat[4] = "<html>최대<br>수면시간</html>";

        LocalTime wakeUp = sleepingTimeValue[1];
        LocalTime sleep =  sleepingTimeValue[0];

        // timeFormat[1] => Timeformat
        timeFormat[2] = String.format("%02d",wakeUp.getHour());
        timeFormat[3] = String.format("%02d",wakeUp.getMinute());
        // timeFormat[5] => Timeformat
        timeFormat[6] = String.format("%02d",sleep.getHour());
        timeFormat[7] = String.format("%02d",sleep.getMinute());

        if(currentFormat){
            int tmpTime = Integer.parseInt(timeFormat[2]);
            if(tmpTime > 12){
                timeFormat[2] = String.format("%02d", tmpTime - 12);
                timeFormat[1] = "오후";
            }else{
                timeFormat[1] = "오전";
            }
        }else{
            timeFormat[5] = "  ";
            timeFormat[1] = "  ";
        }

        timeFormat[8] = "  ";

        return timeFormat;

    }
}
