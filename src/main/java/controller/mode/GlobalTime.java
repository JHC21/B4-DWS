package controller.mode;
import java.time.LocalTime;

public class GlobalTime {

    private long time;
    private int myTimeZone;
    private int anotherTimeZone;
    private final String[][] cityName =  { // UTC -12 ~ 14
            {"베이커섬", "하울랜드섬", ""}, // UTC -12
            {"니우에", "미도웨이 제도", ""}, // UTC -11
            {"호놀룰루", "쿡 제도", "타히티"}, // UTC -10
            {"알류산열도", "앵커리지", "갬비어제도"}, // UTC -9
            {"LA", "빅토리아", "시애틀"}, // UTC -8
            {"덴버", "시카고", "치와와주"}, // UTC -7
            {"리틀 록", "시카고", "위니펙"}, // UTC -6
            {"뉴욕", "바하마", "에콰도르"}, // UTC -5
            {"가이아나", "파라과이", ""}, // UTC -4
            {"브라질리아", "산티아고", ""}, // UTC -3
            {"샌드위치제도", "", ""}, // UTC -2
            {"카보베르데", "", ""}, // UTC -1
            {"런던", "암스테르담", "파리"}, // UTC 0
            {"가나", "리스본", "아이슬란드"}, // UTC +1
            {"아테네", "예루살렘", "카이로"}, // UTC +2
            {"모스크바", "이스탄불", "나이로비"}, // UTC +3
            {"두바이", "모리셔스", "트빌리시"}, // UTC +4
            {"몰디브", "타슈켄트", ""}, // UTC +5
            {"부탄", "비슈켄트", "알마티"}, // UTC +6
            {"라오스", "몽골", "방콕"}, // UTC +7
            {"싱가포르", "중국", "캔버라"}, // UTC +8
            {"도쿄", "서울", "평양"},// UTC +9
            {"괌", "블라디보스톡", "시드니"}, // UTC +10
            {"사할린", "솔로몬 제도", ""}, // UTC +11
            {"마셜 제도", "오클랜드", "투발루"}, // UTC +12
            {"남극점", "사모아", "통가"}, // UTC +13
            {"크리스마스섬", "", ""} // UTC +14
    };

    public GlobalTime() {
        this.anotherTimeZone = 0;
        this.myTimeZone = 9;
    }
    public Object[] getCityData(long clock){
        //입력으로 들어오는 것은 System.Clock과, System.getTime() 이다.
        //이 값을 이용하여 GlobalTime.time의 값을 최신화한다.
        //myTimeZone의 시간과 도시 이름들, anotherTimeZone의 시간과 도시이름들을 리턴

        Object[] returnData = new Object[2];
//        this.time += clock;

        // myTime과 anotherTime을 시/분으로 변환
        LocalTime myLocalTime = controller.Utility.milliToLocalTime(clock + time);
        int myLocalHour = myLocalTime.getHour(); // myTimeZone의 시. 일단 24시간제로 받아오고 시간제는 보여주는 쪽에서 처리함
        int myLocalMinute = myLocalTime.getMinute(); // myTimeZone의 분
        LocalTime anotherLocalTime = controller.Utility.milliToLocalTime(clock + time + ((anotherTimeZone - myTimeZone) * 3600000));
        int anotherLocalHour = anotherLocalTime.getHour(); // anotherTimeZone의 시. 일단 24시간제로 받아오고 시간제는 보여주는 쪽에서 처리함
        int anotherLocalMinute = anotherLocalTime.getMinute(); // anotherTimeZone의 분
        // myTimeZone의 시간과 도시 이름들, anotherTimeZone의 이름과 도시 이름들을 리턴
        returnData[0] = new Object[]{myLocalHour, myLocalMinute, this.cityName[myTimeZone + 12]}; //  UTC는 -12부터 시작하기 때문!
        returnData[1] = new Object[]{anotherLocalHour, anotherLocalMinute, this.cityName[anotherTimeZone + 12]};

        return returnData;
    }

    public void updateAnotherTimeZone(int updateValue){
        // updateValue는 myTimeZone과의 UTC 차이를 의미한다.
        // anotherTimeZone을 변경하면, myTimeZone과의 몇 시간 차이가 나는지가 업데이트 되는 것이다.
        // 결과적으로 화면에 나타낼 때에는 time + anotherTimeZone * 3,600,000된 값에 해당하는 시와 분을 계산해 출력한다.
        // 그래서 this.time을 변경할 필요 없이 anotherTimeZone 값만 업데이트 하면 됨

        if(this.anotherTimeZone == -12 && updateValue == -1) {
            this.anotherTimeZone = 14;
        } else if(this.anotherTimeZone == 14 && updateValue == 1) {
            this.anotherTimeZone = -12;
        } else {
            this.anotherTimeZone += updateValue;
        }
    }

    public void updateMyTimeZone(int updateValue){
        // updateValue는 UTC의 증감폭이다.
        // myTimeZone의 값을 변경하면, time의 값이 증감한 시간대만큼 변화한다. 즉, updateValue에 해당하는 밀리초만큼 time을 증감시킨다.

        if(this.myTimeZone == -12 && updateValue == -1) {
            this.myTimeZone = 14;
        } else if(this.myTimeZone == 14 && updateValue == 1) {
            this.myTimeZone = -12;
        } else {
            this.myTimeZone += updateValue; // UTC를 의미. int 타입 (hour)
        }
        //updateAnotherTimeZone(updateValue);
//        this.time += updateValue * 3600000; // 1 hr == 3,600,000 millisec
    }

    public void updateTimeValue(long time) {
        this.time = time;
    }

}
