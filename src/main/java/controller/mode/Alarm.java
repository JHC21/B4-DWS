package controller.mode;

import controller.Utility;

import java.time.LocalTime;

public class Alarm {
    private boolean[] alarmingDay;
    //alarmingTime은 java.time.LocalTime에서 시/분만 저장하는 것임. 나머지 요일별에 따른 설정은 alarmingDay에 저장
    private LocalTime alarmingTime;
    private int state; //0일때가 꺼져있고, 1일때가 켜져있는 상태

    public Alarm(){
        alarmingDay = new boolean[]{true, true, true, true, true, true, true};
        alarmingTime = LocalTime.of(8, 0);
        state = 0;
    }

    public boolean[] getAlarmingDay() {
        return this.alarmingDay;
    }

    public LocalTime getAlarmingTime() {
        return this.alarmingTime;
    }

    public int getState() {
        return state;
    }

    public void changeAlarmActivation(){
        if(this.state == 1) this.state = 0;
        else /*if state == 0*/ this.state = 1;
        //Alarm의 상태를 변경, active/inactive 상태 toggle
        //this.activated를 0~1 로 변경.
    }

    public void updateAlarmValue(boolean[] alarmingDay, LocalTime alarmingTime){
        this.alarmingDay = alarmingDay;
        this.alarmingTime = this.alarmingTime.plusHours(alarmingTime.getHour());
        this.alarmingTime = this.alarmingTime.plusMinutes(alarmingTime.getMinute());
        //set alarm에서 알람의 값을 변경해주는 메소드, 받아온 값을 저장해줌
        //this.alarmingDay = alarmingDay;   this.alarmingTime = alarmingTime;
    }

    public int checkAlarm(long time){
        //시스템의 time을 가져와
        int alarmingDay = Utility.milliToDay(time);
        LocalTime alarmingTime = Utility.milliToLocalTime(time);

        if(this.state == 0){
            return state;
        }else{
            if(this.alarmingDay[alarmingDay] && this.alarmingTime == alarmingTime){
                return 2;
            }else{
                return 1;
            }
        }

        //if(this.alarmingDay[alarmingDay] == true && this.alarmingTime == alarmingTime && this.state == 1){ 알람이 울려야 함 - return true; }
        //else{ 울리지 않음 return false; }
        // 0: 현재 안울림 & inactivate, 1: 현재 안울림 & activate, 2: 현재 울림
    }
}
