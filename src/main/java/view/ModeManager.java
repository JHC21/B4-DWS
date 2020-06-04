package view;

import controller.ClockSystem;
import view.template.Flag;

public class ModeManager {

    // System system = new System();

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
        //이 값을 displayManager에 표시될 수 있게끔 값을 가공해서
        //Strin배열로 넘겨주고, 배열에는
        //표시될 위치를 표시해주면 됨
        Flag flag = new Flag();
        flag.flags[0] = "Test";
        //Flag 클래스 보고 값을 가공해 return

        return flag.flags;
    }

    public String[] setTime(ClockSystem clockSystem){
        //setTime에 해당하는 값을 가공해서 넘겨주기만 하면 됨

        Flag flag = new Flag();

        return flag.flags;
    }

    public int[] displayTimer(ClockSystem clockSystem){
        Object[] tiemr = clockSystem.getTimer();
        //어쩌구저쩌구 값 처리
        int[] value= new int[5];
        return value;

    }

}
