package controller;

import controller.mode.*;

public class System {
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
        return new Object[]{0 /*TimeKeeping.time + System.clock의 값*/, 0 /*TimeKeeping.timeformat*/};
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
        return new Object[]{0 /*UI상 보여지는 시간 : settedTime - (System.clock - startTime + stackedTime)*/, 0 /*On/Off 상태 (Timer.status값)*/};
    }

    public Object[] getAlarm(){
        //UI에 뿌려질 Alarm의 상태를 전달해주는 메소드
        //활성화정보, 요일, 시각 return 해야 함.
        return new Object[]{0 /*활성화정보 - int형*/, 0 /*요일정보 - boolean[]형*/, 0 /*시각정보 - long형*/};
    }


    public Object[] getGlobalTime(){
        //UI에 뿌려질 GlobalTime의 상태를 전달해주는 메소드
        //내도시 시간, 내도시 UTC, 내도시 이름, 남의도시 시간, 남의도시 UTC, 남의도시 이름 6개 return 해줘야함
        //myTime : TimeKeeping에서 받아와야함 getTime()
        //myUTC, myCityName 모두 GlobalTime에서 받아와야함
        //남의도시 시간 : Object[]{0 = System.Clock + GlobalTime.time, 0 = myTime[1]}
        //남의도시UTC, 남의도시CityName 모두 GlobalTime에서 받아와야함
        return new Object[]{0 /*내도시 시간*/, 0 /*내도시 시간제*/, 0 /*내도시 이름*/, 0 /*남의도시 시간*/, 0 /*남의도시 시간제*/, 0/*남의도시 이름*/};
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
    
    /*public void showAlarming(){
        //알람이 울릴 시기가 되었을 때 알람이 울리는 것을 뜻함. UI에서 처리해줘야 할 가능성이 크기 때문에 System에선 구현하지 않을 수 있어 주석처리
     */

    /*public void showAlarming(){
        //알람이 울릴 시기가 되었을 때 알람이 울리는 것을 뜻함. UI에서 처리해줘야 할 가능성이 크기 때문에 System에선 구현하지 않을 수 있어 주석처리
     */

}
