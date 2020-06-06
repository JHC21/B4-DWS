package view.template;

public class Flag {

    static final long SECOND_MILLI = 1000l;
    static final long MINUTE_MILLI = 60000l;
    static final long HOUR_MILLI = 3600000l;
    static final long DAY_MILLI = 86400000l;
    static final long MONTH28_MILLI = 2419200000l;
    static final long MONTH29_MILLI = 2505600000l;
    static final long MONTH30_MILLI = 2592000000l;
    static final long MONTH31_MILLI = 2678400000l;
    static final long MONTH_MILLI = 2629800000l;
    static final long YEAR365_MILLI = 31557600000l;
    static final long YEAR366_MILLI = 31644000000l;

    public String[] flags;
    public Flag(){
        flags = new String[33];
    }

        //0 : Time Keeping 연
        //1 : Time Keeping 월
        //2 : Time Keeping 일
        //3 : Time Keeping 요일
        //4 : Time Keeping/Alarm AM/PM (시간제) (변경불가능)
        //5 : Time Keeping/Timer/Stopwatch/Alarm 시
        //6 : Time Keeping/Timer/Stopwatch/Alarm 분
        //7 : Time Keeping/Timer/Stopwatch 초
        //8 : Timer/Stopwatch counting (시계 우상단)
        //9 : Timer/Stopwatch/Alarm/Sleeping Time activation (시계 우하단)
        //10: Stop watch의 Lap
        //11: Stop watch의 millisecond
        //12: Alarm 일
        //13: Alarm 월
        //14: Alarm 화
        //15: Alarm 수
        //16: Alarm 목
        //17: Alarm 금
        //18: Alarm 토
        //19: Alarm 번호 (시계 좌하단)
        //20: Alarm이 표시될 때 전부 (하나로 통합하고, 이걸 한번에 보여줌
        //21: global time의 내도시 / 추천 수면시간 / 목표 기상시각
        //22: global time의 내도시 시간 (시간제 포함) / 추천 수면시간1 / 목표 기상시각
        //23: global time의 남의도시 / 추천 수면시각2 / 최대 수면시간
        //24: global time의 남의도시 시간 (시간제 포함) / 추천 수면시간2 / 목표 기상시
        //25: Cheering message 전체
        //26: function list에서 customize own clock
        //27: timekeeping (초기값 제일 좌측)
        //28: timer
        //29: stopwatch
        //30: alarm
        //31: global_time
        //32: sleeping_time (초기값 제일 우측)

    //year, month에 따른 달 증감값 신경써야 함
    public static long getTimeValue(int selector){
        if(selector == 0) return 31557600000l;
        else if(selector == 1) return 2629800000l;
        else if(selector == 2) return 86400000l;
        else if(selector == 5) return 3600000l;
        else if(selector == 6) return 60000l;
        else if(selector == 7) return 1000l;
        return 0;
    }

    public static long getTimerValue(int selector) {
        if(selector == 0) return HOUR_MILLI;
        else if(selector == 1) return MINUTE_MILLI;
        else if(selector == 2) return SECOND_MILLI;
        return 0;
    }

    public static int moveTimeSelector(int selector){
        if(selector == 0) return 1;
        else if(selector == 1) return 2;
        else if(selector == 2) return 5;
        else if(selector == 5) return 6;
        else if(selector == 6) return 7;
        else if(selector == 7) return 0;
        return 0;
    }

    public static int moveTimerSelector(int selector) {
        if(selector == 0) return 1;
        else if(selector == 1) return 2;
        else if(selector == 2) return 0;
        return 0;
    }


}
