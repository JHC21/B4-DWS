package controller.mode;

import java.sql.Time;

public class TimeKeeping {
    private boolean timeformat;     //true가 12시간제, false가 24시간제
    private long time;

    public TimeKeeping(){
        time = 0;
        timeformat = true;
    }

    //System이 TimeKeeping한테 요청
    //UI에서 받은 값의 System에 따른 오차값을 TimeKeeping에 저장하는 함수
    public void updateTime(long diffValue) {

        this.time += diffValue;
        System.out.println(this.time);
        //TimeKeeping의 time값을 변경해준다.
    }

    public Object[] calculateTime(long clock) {

        Object[] value = new Object[2];
        value[0] = this.time + clock;
        value[1] = timeformat;
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

