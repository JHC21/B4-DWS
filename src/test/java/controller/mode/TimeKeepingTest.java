package controller.mode;

import controller.ClockSystem;
import controller.Utility;
import org.junit.jupiter.api.Test;

import javax.rmi.CORBA.Util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeKeepingTest {

    ClockSystem clockSystem = new ClockSystem();
    TimeKeeping timeKeeping = new TimeKeeping();

    @Test
    public void 시간_설정_연도_증감_테스트(){
        /**
         * 만약 시간 설정 도중 2100년을 넘어간다면, 2000년 1월 1일로 돌아간다.
         * 위의 경우 UTC+0 기준으로 비교하기에 1999-12-31T15:00를 리턴해야 한다.
         */

        // 현재시간
        long current = clockSystem.clock();
        long currentTime = (long)timeKeeping.calculateTime(current)[0];
        LocalDateTime currentLocalDateTime = Instant.ofEpochMilli(currentTime).atZone(ZoneId.of("+0")).toLocalDateTime();
        System.out.println(currentLocalDateTime);

        int currentYear = currentLocalDateTime.getYear();
        LocalDateTime futureLocalDateTime = currentLocalDateTime.plusYears(2100-currentYear);
        long futureTime = futureLocalDateTime.atZone(ZoneId.of("+0")).toInstant().toEpochMilli();

        timeKeeping.updateTime(futureTime-currentTime);
        System.out.println(futureLocalDateTime);

        // 2000.01.01 00:00:00
        LocalDateTime returnLocalDateTime = currentLocalDateTime.minusYears(currentYear-2000);
        long returnTime = returnLocalDateTime.atZone(ZoneId.of("+0")).toInstant().toEpochMilli();
        System.out.println(returnLocalDateTime);

        long testTime = (long)timeKeeping.calculateTime(current)[0];
        LocalDateTime testLocalDateTime = Instant.ofEpochMilli(testTime).atZone(ZoneId.of("+0")).toLocalDateTime();
        System.out.println(testLocalDateTime);

        assertEquals(returnTime, testTime);
    }
}
