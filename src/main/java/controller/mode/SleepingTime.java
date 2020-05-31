package controller.mode;

import controller.Utility;

import java.time.LocalTime;

public class SleepingTime {
    private LocalTime sleepTime;    //수면시간
    private LocalTime wakeUpTime;   //기상시각
    private int status;         //0이면 inactive, 1이면 active상태.글 (1이면 cheering message를 받는 상태임)


    public LocalTime[] calculateSleepingTime(Object[] getTime){
        //수면시간과, 기상시각, 현재시간(getTime이용)(을 계산해 최적 수면시간 1, 2를 return하는 메소드
        //1시간 반 기준으로 보여줌
        LocalTime localTime[] = new LocalTime[]{ LocalTime.now()/*최적 수면시간 1*/, LocalTime.now()/*최적 수면시간 2*/};
        return localTime;
    }


    public void updateWakeUpTime(LocalTime time){
        //기상시각을 변경하는 메소드, UI에서 System을 거쳐 호출함
        //받아온 값을 토대로 this.wakeUpTime을 변경
    }

    public void updateSleepTime(LocalTime time){
        //수면시간을 변경하는 메소드, UI에서 System을 거쳐 호출함
        //받아온 값을 토대로 this.sleepTime을 변경
    }

    public void toggleSleepingTimeState(){
        //this.status의 상태를 0~1로 토
    }


    public int checkSleeping(long currentTime){
        //calculateSleepTime()을 호출해 최적의 수면시간을 받아옴
        //getTime의 값을 받아와 수면시간과 일치하면 true, 아니면 false를 return함.
        return 0; // 0: 현재 안울림 & inactivate, 1: 현재 안울림 & activate, 2: 현재 울림
    }

    public LocalTime getSleepTime() {
        return this.sleepTime;
    }

    public LocalTime getWakeUpTime() {
        return this.wakeUpTime;
    }





}
