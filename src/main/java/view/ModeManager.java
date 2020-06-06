package view;

import controller.Utility;
import controller.ClockSystem;
import view.template.Flag;

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
        String[] value = new String[4];
        /*
        0 : hour
        1 : minute
        2 : second
        3 : counting
        activate는 checker에서 할 수 있을 듯
        */
        int[] timerTime = Utility.milliToTimeFormat((long)timer[0]);
        value[0] = String.format("02d", timerTime[3]); // hour
        value[1] = String.format("02d", timerTime[4]); // minute
        value[2] = String.format("02d", timerTime[5]); // second

        int temp = (int)timer[1];
        if(temp == 2) { // on & activate
            value[3] = "ON";
        }
        else {
            //off or inactivate
            value[3] = "OFF";
        }
        return value;
    }
    public String[] setTimer(ClockSystem clockSystem) {
        long timerValue = clockSystem.getTimerSetted();
        String[] value = new String[3]; // 9는 임시 값
        /*
        0 : hour
        1 : minute
        2 : second
        activate는 checker에서 할 수 있을 듯
        */

        int[] timerTime = Utility.milliToTimeFormat((long)timerValue);
        value[0] = String.format("02d", timerTime[3]); // hour
        value[1] = String.format("02d", timerTime[4]); // minute
        value[2] = String.format("02d", timerTime[5]); // second

        return value;
    }
}
