package controller;

import java.time.*;

public class Utility {

    public static int milliToDay(long time) {
        //long형식으로 들어온 시간에서 요일을 추출해서 return
        //0~6까지 각각 일월화수목금토
        return milliToTimeFormat(time)[7];
    }

    public static LocalTime milliToLocalTime(long time) {
        //long형식으로 들어온 시간에서 시각을 추출해서 return
        int[] temp = milliToTimeFormat(time);
        int hour = temp[3];
        int minute = temp[4];
        int second = temp[5];
        return LocalTime.of(hour, minute, second);
    }

    public static int millitoYear(long time){
        LocalDateTime currentLocalDateTime = Instant.ofEpochMilli(time).atZone(ZoneId.of("+0")).toLocalDateTime();
        return currentLocalDateTime.getYear();
    }

    public static String[] millitoTimeFormat_test(long time) {
        String[] timeFormat = new String[9];
        DayOfWeek temp;

        LocalDateTime currentLocalDateTime = Instant.ofEpochMilli(time).atZone(ZoneId.of("+0")).toLocalDateTime();

        timeFormat[0] = String.valueOf(currentLocalDateTime.getYear()).substring(2, 4);
        timeFormat[1] = String.format("%02d", currentLocalDateTime.getMonthValue());
        timeFormat[2] = String.format("%02d", currentLocalDateTime.getDayOfMonth());
        timeFormat[3] = String.format("%02d", currentLocalDateTime.getHour());
        timeFormat[4] = String.format("%02d", currentLocalDateTime.getMinute());
        timeFormat[5] = String.format("%02d", currentLocalDateTime.getSecond());
        timeFormat[6] = String.valueOf((int) (time % 1000));
        temp = currentLocalDateTime.getDayOfWeek();
        if (temp.equals(DayOfWeek.SUNDAY)) timeFormat[7] = "일";
        else if (temp.equals(DayOfWeek.MONDAY)) timeFormat[7] = "월";
        else if (temp.equals(DayOfWeek.TUESDAY)) timeFormat[7] = "화";
        else if (temp.equals(DayOfWeek.WEDNESDAY)) timeFormat[7] = "수";
        else if (temp.equals(DayOfWeek.THURSDAY)) timeFormat[7] = "목";
        else if (temp.equals(DayOfWeek.FRIDAY)) timeFormat[7] = "금";
        else if (temp.equals(DayOfWeek.SATURDAY)) timeFormat[7] = "토";

        return timeFormat;
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

        LocalDateTime currentLocalDateTime = Instant.ofEpochMilli(time).atZone(ZoneId.of("+0")).toLocalDateTime();

        timeFormat[0] = currentLocalDateTime.getYear();
        timeFormat[1] = currentLocalDateTime.getMonthValue();
        timeFormat[2] = currentLocalDateTime.getDayOfYear();
        timeFormat[3] = currentLocalDateTime.getHour();
        timeFormat[4] = currentLocalDateTime.getMinute();
        timeFormat[5] = currentLocalDateTime.getSecond();
        timeFormat[6] = (int) (time % 1000);
        temp = currentLocalDateTime.getDayOfWeek();
        if (temp.equals(DayOfWeek.SUNDAY)) timeFormat[7] = 0;
        else if (temp.equals(DayOfWeek.MONDAY)) timeFormat[7] = 1;
        else if (temp.equals(DayOfWeek.TUESDAY)) timeFormat[7] = 2;
        else if (temp.equals(DayOfWeek.WEDNESDAY)) timeFormat[7] = 3;
        else if (temp.equals(DayOfWeek.THURSDAY)) timeFormat[7] = 4;
        else if (temp.equals(DayOfWeek.FRIDAY)) timeFormat[7] = 5;
        else if (temp.equals(DayOfWeek.SATURDAY)) timeFormat[7] = 6;

        return timeFormat;
    }

    public static boolean checkAtLeastOne(boolean[] days){
        int counter = 0;
        for(int i = 0; i < 7; i++){
            if(days[i]) counter += 1;
        }
        return counter > 1;
    }

    public static String alarmCheeringFormatting(long currentTime, boolean currentTimeFormat) {
        //String 형식은 "20 05 03 일    06:30"
        //12시간제일때는 "20 05 03 일 AM 06:30"

        String[] currentTimeString = Utility.millitoTimeFormat_test(currentTime);
        int[] currentTimeInt = Utility.milliToTimeFormat(currentTime);

        String timeFormat;
        if(currentTimeFormat) { // 12시간제
            if(currentTimeInt[3] < 12) timeFormat = "오전";
            else timeFormat = "오후";
        }
        else {// 24시간제일 때는 AM/PM 표시 안함
            timeFormat = "  ";
        }
        String temp = String.format("%s %s %s %s %s %s:%s",
                currentTimeString[0],
                currentTimeString[1],
                currentTimeString[2],
                currentTimeString[7],
                timeFormat,
                currentTimeString[3],
                currentTimeString[4]);

        return temp;

    }
}