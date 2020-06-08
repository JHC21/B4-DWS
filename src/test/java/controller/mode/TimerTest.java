package controller.mode;

import controller.ClockSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TimerTest {
    ClockSystem clockSystem = new ClockSystem();
    Timer timer = new Timer();

    private long startTime;
    private long settedTime = 120000L;
    private long stackedTime;

    @Test
    public void 타이머_활성화_및_시작_테스트(){

        /***
         * 비활성화 되어있는 타이머를 활성화시킨 뒤 (0 -> 1),
         * 활성화 된 타이머를 2분으로 셋팅한 뒤 동작시킨다 (1 -> 2)
         *
         */

        long currentTime = (long)clockSystem.getTime()[0];
        timer.changeTimerActivation();
        assertEquals(timer.getState(), 1);

        timer.updateTimer(this.settedTime);

        currentTime = (long)clockSystem.getTime()[0];
        timer.changeTimerCounting(currentTime);
        this.startTime = currentTime;

        assertEquals(2, timer.getState());
    }

    @Test
    public void 타이머_정지_테스트() throws Exception{

        long currentTime = (long)clockSystem.getTime()[0];
        timer.changeTimerActivation();

        timer.updateTimer(this.settedTime);

        currentTime = (long)clockSystem.getTime()[0];
        timer.changeTimerCounting(currentTime);
        this.startTime = currentTime;

        Thread.sleep(5000);

        currentTime = (long)clockSystem.getTime()[0];
        timer.changeTimerCounting(currentTime);
        assertEquals(1, timer.getState()); // 정지

        this.stackedTime += currentTime - this.startTime;

        long nowTimerTime = this.settedTime-this.stackedTime;

        assertEquals(timer.calculateTimerValue(currentTime), nowTimerTime);

    }

    @Test
    public void 타이머_재시작_테스트() throws Exception{
        long currentTime = (long)clockSystem.getTime()[0];
        timer.changeTimerActivation();

        timer.updateTimer(this.settedTime);

        currentTime = (long)clockSystem.getTime()[0];
        timer.changeTimerCounting(currentTime);
        this.startTime = currentTime;

        Thread.sleep(5000);

        currentTime = (long)clockSystem.getTime()[0];
        timer.changeTimerCounting(currentTime);
        assertEquals(1, timer.getState()); // 정지

        this.stackedTime += currentTime - this.startTime;

        Thread.sleep(2000);

        currentTime = (long)clockSystem.getTime()[0];
        timer.changeTimerCounting(currentTime);
        this.startTime = currentTime;

        assertEquals(2, timer.getState());

        long nowTimerTime2 = this.settedTime - (currentTime - this.startTime + this.stackedTime);

        assertEquals(timer.calculateTimerValue(currentTime), nowTimerTime2);

    }
}
