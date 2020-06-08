package controller.mode;

import controller.ClockSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SleepingTimeTest {
    ClockSystem clockSystem = new ClockSystem();
    SleepingTime sleepingTime = new SleepingTime();

    @BeforeEach
    public void 객체_초기화(){
        clockSystem = new ClockSystem();
        sleepingTime = new SleepingTime();
    }

    @Test
    public void 수면시각_업데이트_테스트(){
        /**
         * 기존에 7시간 30분이었던 수면시간을 6시간으로 업데이트한다.
         */

        sleepingTime.updateSleepTime(1, 0);
        for(int i = 0 ; i < 30 ; i++){
            sleepingTime.updateSleepTime(0, 0);
        }

        LocalTime testSleepTime = sleepingTime.getSleepTime();
        LocalTime returnSleepTime = LocalTime.of(6,0);

        assertEquals(returnSleepTime, testSleepTime);

    }

    @Test
    public void 기상시각_업데이트_테스트(){

        /**
         * 기존에 8시 10분으로 초기화 된 기상시각을 9시로 업데이트한다.
         */

        sleepingTime.updateWakeUpTime(1,1);
        for(int i = 0 ; i < 10 ; i ++)
            sleepingTime.updateWakeUpTime(0, 0);

        LocalTime testWakeUpTime = sleepingTime.getWakeUpTime();
        LocalTime returnWakeUpTime = LocalTime.of(9,0);

        assertEquals(returnWakeUpTime, testWakeUpTime);

    }

    @Test
    public void 최대_수면시각_테스트(){

        System.out.println(sleepingTime.getWakeUpTime());
        /**
         * 기존에 8시간 분이었던 수면시각을 12시간 30분으로 업데이트 한다
         */
        for(int i = 0 ; i < 5 ; i++)
            sleepingTime.updateSleepTime(1,1);
        System.out.println(sleepingTime.getSleepTime());
        LocalTime testSleepTime = sleepingTime.getSleepTime();
        LocalTime returnSleepTime = LocalTime.of(1,30);

        assertEquals(returnSleepTime, testSleepTime);

    }

    @Test
    public void 최소_수면시각_테스트(){

        /**
         * 기존에 7시간 30분이었던 수면시각을 30분으로 업데이트 한다
         */


        System.out.println(sleepingTime.getSleepTime());

        for(int i = 0 ; i < 6 ; i++)
            sleepingTime.updateSleepTime(1,0);
        System.out.println(sleepingTime.getSleepTime());

        LocalTime testSleepTime = sleepingTime.getSleepTime();
        LocalTime returnSleepTime = LocalTime.of(1,30);

        assertEquals(returnSleepTime, testSleepTime);

    }

    @Test
    public void 적정_수면시간_반환_테스트_1(){

        // 2020년 6월 8일 13시 31분 50초
        Object[] dummyTime = new Object[]{1591590710000L, 1};
        Object[] sleepTimes = sleepingTime.calculateSleepingTime(dummyTime);


        LocalTime firstTime = (LocalTime) sleepTimes[0];
        LocalTime secondTime = (LocalTime) sleepTimes[1];

        assertEquals(firstTime.getHour(), 5);
        assertEquals(firstTime.getMinute(), 10);
        assertEquals(secondTime.getHour(), 6);
        assertEquals(secondTime.getMinute(), 40);
    }

    @Test
    public void 적정_수면시간_반환_테스트_2(){
        //TODO: calculatingSleepTime 하나 이상 null일 때
    }

    @Test
    public void 응원_메세지_활성화_테스트(){

        /**
         * 현재 응원 메세지 송신이 활성화 되었는지 안 되었는지 확인한다.
         * 활성화 되어있지 않다가 활성화 됨
         */

        sleepingTime.toggleSleepingTimeState();
        int state = sleepingTime.checkSleeping((long)clockSystem.getTime()[0]);

        assertNotEquals(state, 0);
    }

}
