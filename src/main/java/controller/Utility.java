package controller;

import java.time.*;

public class Utility {

    static final long SECOND_MILLI = 1000;
    static final long MINUTE_MILLI = 60000;
    static final long HOUR_MILLI = 3600000;
    static final long DAY_MILLI = 86400000;

    public static int milliToDay(long time){
        //long형식으로 들어온 시간에서 요일을 추출해서 return
        //0~6까지 각각 일월화수목금토
        return milliToTimeFormat(time)[7];
    }

    public static LocalTime milliToLocalTime(long time){
        //long형식으로 들어온 시간에서 시각을 추출해서 return
        int hour = milliToTimeFormat(time)[3];
        int minute = milliToTimeFormat(time)[4];
        int second = milliToTimeFormat(time)[5];
        LocalTime localTime = LocalTime.of(hour, minute, second);
        return localTime;
    }

    public static int[] milliToTimeFormat(long time) {
        //int 배열을 넘겨준다.
        //0 : 연
        //1 : 월
        //2 : 일
        //3 : 시
        //4 : 분
        //5 : 초
        //6 : ms
        //7 : 요일 (일월화수목금토 0 _ 6)

        int[] timeFormat = new int[8];
        DayOfWeek temp;

        LocalDateTime currentLocalDateTime = Instant.ofEpochMilli(time).atZone(ZoneId.systemDefault()).toLocalDateTime();

        timeFormat[0] = currentLocalDateTime.getYear();
        timeFormat[1] = currentLocalDateTime.getMonthValue();
        timeFormat[2] = currentLocalDateTime.getDayOfYear();
        timeFormat[3] = currentLocalDateTime.getHour();
        timeFormat[4] = currentLocalDateTime.getMinute();
        timeFormat[5] = currentLocalDateTime.getSecond();
        timeFormat[6] = (int)(time % 1000);
        temp = currentLocalDateTime.getDayOfWeek();
        if(temp.equals(DayOfWeek.SUNDAY)) timeFormat[7] = 0;
        else if(temp.equals(DayOfWeek.MONDAY)) timeFormat[7] = 1;
        else if(temp.equals(DayOfWeek.TUESDAY)) timeFormat[7] = 2;
        else if(temp.equals(DayOfWeek.WEDNESDAY)) timeFormat[7] = 3;
        else if(temp.equals(DayOfWeek.THURSDAY)) timeFormat[7] = 4;
        else if(temp.equals(DayOfWeek.FRIDAY)) timeFormat[7] = 5;
        else if(temp.equals(DayOfWeek.SATURDAY)) timeFormat[7] = 6;

        return timeFormat;
    }
}