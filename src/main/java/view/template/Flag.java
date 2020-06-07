package view.template;

import controller.Utility;

public class Flag {

    static final long SECOND_MILLI = 1000L;
    static final long MINUTE_MILLI = 60000L;
    static final long HOUR_MILLI = 3600000L;
    static final long DAY_MILLI = 86400000L;
    static final long MONTH28_MILLI = 2419200000L;
    static final long MONTH29_MILLI = 2505600000L;
    static final long MONTH30_MILLI = 2592000000L;
    static final long MONTH31_MILLI = 2678400000L;
    static final long MONTH_MILLI = 2629800000L;
    static final long YEAR365_MILLI = 31557600000L;
    static final long YEAR366_MILLI = 31644000000L;

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
        //22: global time의 내도시 시간 (시간제 포함) / 추천 수면시간1(시) / 목표 기상시각(시)
        //23: global time의 남의도시 / 추천 수면시각2 / 최대 수면시간
        //24: global time의 남의도시 시간 (시간제 포함) / 추천 수면시간2(시) / 목표 기상시(시)
        //25: Cheering message 전체
        //26: function list에서 customize own clock
        //27: timekeeping (초기값 제일 좌측)
        //28: timer
        //29: stopwatch
        //30: alarm
        //31: global_time
        //32: sleeping_time (초기값 제일 우측)
        //33: global time의 내도시 / 추천 수면시간(분) / 목표 기상시각(분)
        //34: global time의 남의도시 / 추천 수면시각2(분) / 최대 수면시간(분)

    //year, month에 따른 달 증감값 신경써야 함
    public static long getTimeValue(int selector, Object[] curTime, int pmValue){

        int year = Utility.millitoYear((long)curTime[1]);
        String[] timeStringFormat = (String[]) curTime[0];
        int month = Integer.parseInt(timeStringFormat[1]);
        long updateValue = 0;
        int updatedYear;
        boolean yoonYear;

        yoonYear = (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;

        if(selector == 0){              //연일 때
            if(yoonYear) updateValue = YEAR366_MILLI - 6 * HOUR_MILLI;
            else updateValue = YEAR365_MILLI - 6 * HOUR_MILLI;
        }else if(selector == 1) {       //월일 때
            if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
                updateValue = MONTH31_MILLI;
            }else if(month == 4 || month == 6 || month == 9 || month == 11){
                updateValue = MONTH30_MILLI;
            }else if(month == 2 && !yoonYear) updateValue = MONTH28_MILLI;
            else updateValue = MONTH29_MILLI;
        }
        else if(selector == 2) updateValue = DAY_MILLI;
        else if(selector == 5) updateValue = HOUR_MILLI;
        else if(selector == 6) updateValue = MINUTE_MILLI;
        else if(selector == 7) updateValue = SECOND_MILLI;

        System.out.println("Updated Value of : " + updateValue);

        updatedYear = Utility.millitoYear((long)curTime[1] + (pmValue * updateValue));
        if(updatedYear < 2000 || updatedYear > 2099 ) return 0;
        else return updateValue * pmValue;
    }

    public static long getTimerValue(int selector) {
        if(selector == 0) return HOUR_MILLI;
        else if(selector == 1) return MINUTE_MILLI;
        else if(selector == 2) return SECOND_MILLI;
        return 0;
    }

    public static long getWakeUpSleepTimeValue(int selector){
        if(selector == 22 || selector == 24) return HOUR_MILLI;
        else if(selector == 33 || selector == 34) return MINUTE_MILLI;
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
        if(selector == 5) return 6;
        else if(selector == 6) return 7;
        else if(selector == 7) return 5;
        return 0;
    }

    public static int moveAlarmSelector(int selector) {
        if(selector == 5) return 6; // 시 일 경우
        else if(selector == 6) return 12; // 분 일 경우
        else if(selector == 12) return 13; // 일 일 경우
        else if(selector == 13) return 14; // 월 일 경우
        else if(selector == 14) return 15; // 화 일 경우
        else if(selector == 15) return 16; // 수 일 경우
        else if(selector == 16) return 17; // 목 일 경우
        else if(selector == 17) return 18; // 금 일 경우
        else if(selector == 18) return 5; // 토 일 경우

        return 0;
    }
  
    public static int moveFunctionSelector(int selector){
        if(selector == 27) return 28;
        else if(selector == 28) return 29;
        else if(selector == 29) return 30;
        else if(selector == 30) return 31;
        else if(selector == 31) return 32;
        else if(selector == 32) return 27;

        return 0;
    }

    public static int moveFunctionSelectorReverse(int selector){
        if(selector == 27) return 32;
        else if(selector == 28) return 27;
        else if(selector == 29) return 28;
        else if(selector == 30) return 29;
        else if(selector == 31) return 30;
        else if(selector == 32) return 31;

        return 0;
    }
  
    public static int moveWakeUpSleepTimeSelector(int selector){
        if(selector == 22) return 33;
        else if(selector == 33) return 24;
        else if(selector == 24) return 34;
        else if (selector == 34) return 22;

        return 0;
    }
  
    public static boolean isAlarmDayOfWeek(int selector) {
        //set alarm에서 B나 D버튼이 들어와서 시, 분의 경우 증감 혹은 요일의 경우 toggle을 해야 할 때,
        //false인 경우 증감, true 인 경우 toggle을 하도록 한다.
        if(selector == 5 || selector == 6) return false;
        else return true;
    }

    public static int moveGlobalTimeSelector(int selector) {
        if(selector == 21) return 22; // myTimeZone의 hour일 경우
        else if(selector == 22) return 23; // myTimeZone의 minute일 경우
        else if(selector == 23) return 24; // anotherTimeZone의 hour일 경우
        else if(selector == 24) return 25; // anotherTimeZone의 minute일 경우

        return 0;
    }
}
