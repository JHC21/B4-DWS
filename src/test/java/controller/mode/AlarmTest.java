package controller.mode;

import controller.ClockSystem;
import controller.Utility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.annotation.processing.SupportedAnnotationTypes;
import java.time.*;



public class AlarmTest {

    ClockSystem clockSystem = new ClockSystem();
    Alarm alarm = new Alarm();

    @BeforeEach
    public void 객체_초기화(){
        clockSystem = new ClockSystem();
        alarm = new Alarm();
    }

    @Test
    public void 알람_울리는지_테스트(){
        //알람 값 설정 (기본값 08:00시, 일~토 모두 활성화된 상태로 설정)
        alarm.updateAlarmValue(new boolean[]{true, true, true, true, true, true, true}, LocalTime.of(0, 0));
        alarm.changeAlarmActivation();

        //알람에 넣을 값 설정
        LocalDateTime testTime = LocalDateTime.of(LocalDate.of(2020, 6, 8), LocalTime.of(8, 0));
        long testLongValue = testTime.atZone(ZoneId.of("+0")).toInstant().toEpochMilli();

        //알람이 울려야 하는지 테스트
        int result = alarm.checkAlarm(testLongValue);

        Assertions.assertEquals(2, result);
    }

    @Test
    public void 알람_값_업데이트_테스트(){

        //현재 알람 값에 8시간 더함
        alarm.updateAlarmValue(new boolean[]{true, true, true, true, true, true, true}, LocalTime.of(8, 0));

        //기본이 8시이므로, 알람은 16시여야 함.
        int alarmHour = alarm.getAlarmingTime().getHour();

        Assertions.assertEquals(16, alarmHour);
    }
}
