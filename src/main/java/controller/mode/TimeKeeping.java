package controller.mode;

import controller.Utility;

public class TimeKeeping {
    private boolean timeformat;     //true가 12시간제, false가 24시간제
    private long time;

    public TimeKeeping(){
        time = 3600000L * 9 ;
        timeformat = true;
    }

    //System이 TimeKeeping한테 요청
    //UI에서 받은 값의 System에 따른 오차값을 TimeKeeping에 저장하는 함수
    public void updateTime(long diffValue) {
        // System.out.println("Update complete : " + diffValue);
        // System.out.println("BeforeUpdate : " + this.time);
        this.time += diffValue;
        // System.out.println("AfterUpdate ; " + this.time);
        // System.out.println(Instant.ofEpochMilli(this.time).atZone(ZoneId.of("+0")).toLocalDateTime());
        //TimeKeeping의 time값을 변경해준다.
    }

    public long getTime(){
        return this.time;
    }

    public Object[] calculateTime(long clock) {
        Object[] value = new Object[2];
        if(Utility.millitoYear(this.time + clock) >= 2100){
            time = (30 * 31557600000L - 12 * 3600000L) - clock;
        }
        value[0] = this.time + clock;
        value[1] = timeformat;
        //System.out.println();
        return value;
    }

    public void toggleTimeFormat() {

        timeformat = !timeformat;
        //TimeKeeping의 timeformat을 toggle한다.
    }

    public long getTimeValue() {

        return this.time;
    }
}

