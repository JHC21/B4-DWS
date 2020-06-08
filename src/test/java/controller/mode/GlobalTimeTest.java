package controller.mode;

import controller.ClockSystem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

public class GlobalTimeTest {

    ClockSystem clockSystem = new ClockSystem();
    GlobalTime globalTime = new GlobalTime();

    @BeforeEach
    public void 객체_초기화(){
        clockSystem = new ClockSystem();
        globalTime = new GlobalTime();
    }

    @Test
    public void Global_Time_도시_데이터_받아오기_테스트(){

        //globalTime에 넣을 값 설정
        LocalDateTime testTime = LocalDateTime.of(LocalDate.of(2020, 6, 8), LocalTime.of(8, 0));
        long testLongValue = testTime.atZone(ZoneId.of("+0")).toInstant().toEpochMilli();

        //출력값 설정
        Object[] result = globalTime.getCityData(testLongValue);
        Object[] result0 = (Object[]) result[0];
        String[] myCityName = (String[]) result0[2];
        Object[] result1 = (Object[]) result[1];
        String[] otherCityName = (String[]) result1[2];

        //UTC가 기본은 9으로 설정되어 있으므로, 9에 해당되는 도시인 런던, 암스테르담, 파리를 가져와야 함
        //다른 도시의 UTC는 0이므로, {"런던", "암스테르담", "파리"}와 같아야 함
        Assertions.assertArrayEquals(new String[]{"도쿄", "서울", "평양"}, myCityName);
        Assertions.assertArrayEquals(new String[]{"런던", "암스테르담", "파리"}, otherCityName);

        //다른 도시의 UTC변경 -> 1 혹은 -1 만 넣어줄 수 있음
        //6시간 늦춤, 다른 도시의 UTC는 +6이 되어야 함
        for(int i = 0; i < 6; i ++) globalTime.updateAnotherTimeZone(1);
        result = globalTime.getCityData(testLongValue);
        result1 = (Object[]) result[1];
        otherCityName = (String[]) result1[2];

        //다른 도시는 +6 UTC 이므로, 부탄, 비슈켄트, 알마티를 가져와야 함
        Assertions.assertArrayEquals(new String[]{"부탄", "비슈켄트", "알마티"}, otherCityName);

        //내도시를 30번 이동시킴
        //지구 한바퀴를 돌아서 UTC +3이 되어야 함.
        for(int i = 0; i < 30; i ++) globalTime.updateMyTimeZone(1);
        result = globalTime.getCityData(testLongValue);
        result0 = (Object[]) result[0];
        myCityName = (String[]) result0[2];

        //내 도시는 UTC +12 이므로, 모스크바, 이스탄불, 나이로비 이어야 함
        Assertions.assertArrayEquals(new String[]{"마셜 제도", "오클랜드", "투발루"}, myCityName);

    }
}
