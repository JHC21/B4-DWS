package controller.mode;

import controller.ClockSystem;
import controller.Utility;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.rmi.CORBA.Util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeKeepingTest {

    ClockSystem clockSystem = new ClockSystem();
    TimeKeeping timeKeeping = new TimeKeeping();

    @BeforeEach
    public void 객체_초기화(){
        clockSystem = new ClockSystem();
        timeKeeping = new TimeKeeping();
    }

    @Test
    public void 시간_설정_연도_증가_테스트(){
        /**
         * 만약 시간 설정 도중 2100년 위로 넘어간다면, 2000년 1월 1일로 돌아간다.
         */

        // 현재시간
        long current = clockSystem.clock();
        long currentTime = (long)timeKeeping.calculateTime(current)[0];
        LocalDateTime currentLocalDateTime = Instant.ofEpochMilli(currentTime).atZone(ZoneId.of("+0")).toLocalDateTime();

        int currentYear = currentLocalDateTime.getYear();
        LocalDateTime futureLocalDateTime = currentLocalDateTime.plusYears(2100-currentYear);
        long futureTime = futureLocalDateTime.atZone(ZoneId.of("+0")).toInstant().toEpochMilli();

        timeKeeping.updateTime(futureTime-currentTime);

        // 2000.01.01 00:00:00
        LocalDateTime returnLocalDateTime = LocalDateTime.of(2000,1,1,0,0,0);
        long returnTime = returnLocalDateTime.atZone(ZoneId.of("+0")).toInstant().toEpochMilli();

        long testTime = (long)timeKeeping.calculateTime(current)[0];
        LocalDateTime testLocalDateTime = Instant.ofEpochMilli(testTime).atZone(ZoneId.of("+0")).toLocalDateTime();

        assertEquals(returnTime, testTime);
    }
}
