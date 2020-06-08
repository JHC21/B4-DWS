package controller.mode;

import controller.ClockSystem;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class StopWatchTest {

    ClockSystem clockSystem = new ClockSystem();
    StopWatch stopWatch = new StopWatch();

    /*
    @BeforeEach
    public void 객체_초기화(){
        clockSystem = new ClockSystem();
        stopWatch = new StopWatch();
    }
     */

    private long startTime;
    private long stackedTime;

    @Test
    public void 스탑워치_상태_테스트(){

        stopWatch.changeStopWatchState((long)clockSystem.getTime()[0]);
        boolean nowState = (stopWatch.getStopWatchState() ==1);
        this.startTime = (long)clockSystem.getTime()[0];

        assertEquals(true, nowState);

    }

    @Test
    public void 스탑워치_정지_테스트(){
        long currentTime = (long)clockSystem.getTime()[0];
        stopWatch.changeStopWatchState(currentTime);
        boolean nowState = (stopWatch.getStopWatchState() ==0);
        this.stackedTime = currentTime-this.startTime+this.stackedTime;

        assertEquals(false, nowState);
    }


    @Test
    public void 스탑워치_재시작_테스트(){

        long currentTime = (long)clockSystem.getTime()[0];

        stopWatch.changeStopWatchState(currentTime);
        boolean nowState = (stopWatch.getStopWatchState() == 1);
        this.startTime = currentTime;

        currentTime = (long)clockSystem.getTime()[0];

        long calculateTime = stopWatch.calculateStopWatch(currentTime);
        long testTime = currentTime-this.startTime+this.stackedTime;

        assertEquals(true, nowState);
        assertEquals(testTime, calculateTime);

    }

    @Test
    public void 스탑워치_랩_테스트(){

        boolean nowState = (stopWatch.getStopWatchState() == 0);

        long currentTime = (long)clockSystem.getTime()[0];
        stopWatch.setLap(currentTime);

        long lap = stopWatch.getLap();

        assertEquals(true, nowState);
        assertEquals(lap, 0);
    }

    @Test
    public void 스탑워치_리셋_테스트(){
        long currentTime = (long)clockSystem.getTime()[0];

        stopWatch.changeStopWatchState(currentTime);
        boolean nowState = (stopWatch.getStopWatchState() == 0);
        this.startTime = currentTime;

        stopWatch.setReset(currentTime);

        assertEquals(false, nowState);
        assertEquals(0, stopWatch.getLap());
        assertEquals(0, stopWatch.calculateStopWatch(currentTime));


    }

}
