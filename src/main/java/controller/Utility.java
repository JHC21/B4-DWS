package controller;

import java.time.LocalTime;

public class Utility {

    public static int milliToDay(long time){
        //long형식으로 들어온 시간에서 요일을 추출해서 return
        //0~6까지 각각 일월화수목금토
        return 0;
    }

    public static LocalTime milliToLocalTime(long time){
        //long형식으로 들어온 시간에서 시각을 추출해서 return
        LocalTime localTime = LocalTime.now();
        return localTime;
    }
}
