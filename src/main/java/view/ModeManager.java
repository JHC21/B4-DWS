package view;

import controller.System;

public class ModeManager {

    System system = new System();

    // TODO: 버튼확인
    public Object displayTime(System system){
        Object[] time = system.getTime();
        Object currentTime = time[0]; // 리턴할 때 담겨야 할 값
        int nowAlarm = system.getNowAlarm((long)currentTime);
        // nowAlarm이 0 또는 1일 때는 리턴값에 0 또는 1이 포함되어야 한다
        if (nowAlarm == 2){
            // system.ringAlarm()
            // this.showAlarming()
            return new Integer(1);
        }

        int nowSleeping = system.getNowSleeping((long)currentTime);
        if(nowSleeping == 2){
            // system.ringSleepingTime()
            // this.showCheeringMessage()
            return new Integer(1);
        }


        return new Object();

    }

    public Object setTime(System system){
        return new Object();
    }

}
