package controller.mode;

import controller.Utility;

import java.time.LocalTime;

public class SleepingTime {
    private LocalTime sleepTime;    //수면시간 (최대 12시간)
    private LocalTime wakeUpTime;   //기상시각
    private int status;         //0이면 inactive, 1이면 active상태.글 (1이면 cheering message를 받는 상태임)

    static final long SECOND_LONG = 1000L;
    static final long MINUTE_LONG = 60000L;
    static final long HOUR_LONG = 3600000L;

    public SleepingTime(){
        this.sleepTime = LocalTime.of(7, 30);
        this.wakeUpTime = LocalTime.of(8,10);
        this.status = 0;
    }


    public Object[] calculateSleepingTime(Object[] getTime){
        //수면시간과, 기상시각, 현재시간(getTime이용)(을 계산해 최적 수면시간 1, 2를 return하는 메소드
        //수면 시간들은 1시간 반 간격으로 나타남
        //바깥에 보여지기엔 시간, 분만 표시되지만, 30초에 울릴 것이므로 x시간 x분 30초의 형태이다.
        Object[] localTime = new Object[3];//{ *최적 수면시간 1, 최적 수면시간 2, cheering message 수신여부*/}
        long currentTime = (long)getTime[0];
        long wakeUpTimeLong;
        long[] sleepingTimeMap = new long[9];
        long validSleepingTimeAmount; // 현재 조건에서 sleeping time의 최대 개수

        //일어날 시간을 현재 시간에서 날짜 정보만 남긴다.
        wakeUpTimeLong = (currentTime / 1000 / 60 / 60 / 24);
        wakeUpTimeLong *= (1000 * 60 * 60 * 24);
        // 그 뒤 일어나야 할 시각을 long 형태로 구한다.
        wakeUpTimeLong += (wakeUpTime.getHour() * HOUR_LONG + wakeUpTime.getMinute() * MINUTE_LONG + 30 * SECOND_LONG);
        //만약 일어나야 할 시각이 현재 시각보다 작다면 24시간을 더해 준다. (오늘의 기상 시각은 이미 지났으므로 내일의 기상 시각을 가져오는 것)
        if(wakeUpTimeLong < currentTime) wakeUpTimeLong += (24 * HOUR_LONG);

        //sleepTime을 long으로 변환해서, 90분을 long으로 변환한 것을 나누면, sleepTime이 90의 몇 배인지 알 수 있다.
        //그만큼이 valid한 sleeping time의 개수이다.
        validSleepingTimeAmount = (sleepTime.getHour() * HOUR_LONG + sleepTime.getMinute() * MINUTE_LONG) / (90 * MINUTE_LONG);

        System.out.println(Arrays.toString(sleepingTimeMap));

        //기상 시각부터 유효한 sleeping time의 개수만큼 올라가면서 현재 조건에서 가능한 sleeping time을 모두 찾는다.
        //불가능한 경우 일단 0을 넣는다.
        sleepingTimeMap[0] = 0; // 가능한 sleeping time이 2개 미만일 때 사용됨
        for(int i = 1; i < 9; i++) { // 1부터 8까지 유효한 sleeping time이 저장된다.
            if(i <= validSleepingTimeAmount) {
                //한 시간 반 씩 빼서 sleepingTimeMap에 저장한다.
                sleepingTimeMap[i] = wakeUpTimeLong - ((HOUR_LONG + 30 * MINUTE_LONG) * i);
            }
            else {
                sleepingTimeMap[i] = 0;
            }
        }

        for(int i = 8; i >= 1; i--) {
            if (sleepingTimeMap[i] != 0 && sleepingTimeMap[i] > currentTime) {
                //유효한 sleeping time이며, 현재 시간이 sleeping time보다 처음 작다면 그것이 최적 수면 시간 1이다.
                localTime[0] = Utility.milliToLocalTime(sleepingTimeMap[i]);
                localTime[1] = Utility.milliToLocalTime(sleepingTimeMap[i - 1]);
                break;
            }
        }

        localTime[2] = this.status;

        return localTime;
    }

    public void updateWakeUpTime(int type, int value){
        //기상시각을 변경하는 메소드, UI에서 System을 거쳐 호출함
        //받아온 값을 토대로 this.wakeUpTime을 변경
        if(type == 1){
            if(value == 1) wakeUpTime = wakeUpTime.plusHours(1);
            else wakeUpTime = wakeUpTime.minusHours(1);
        }else if(type == 0){
            if(value == 1) wakeUpTime = wakeUpTime.plusMinutes(1);
            else wakeUpTime = wakeUpTime.minusMinutes(1);
        }
    }

    public void updateSleepTime(int type, int value){
        //수면시간을 변경하는 메소드, UI에서 System을 거쳐 호출함
        //받아온 값을 토대로 this.sleepTime을 변경
        if(type == 1){ //시
            if(value == 1) this.sleepTime = this.sleepTime.plusHours(1);
            else this.sleepTime = sleepTime.minusHours(1);
        }else if(type == 0){ // 분
            if(value == 1) this.sleepTime = this.sleepTime.plusMinutes(1);
            else this.sleepTime = this.sleepTime.minusMinutes(1);
        }
        if(this.sleepTime.isAfter(LocalTime.of(12,0)))
            this.sleepTime = LocalTime.of(1, 30);
        if(this.sleepTime.isBefore(LocalTime.of(1,30)))
            this.sleepTime = LocalTime.of(12, 0);
    }

    public void toggleSleepingTimeState(){
        //this.status의 상태를 0~1로 토
        if(this.status == 0) this.status = 1;
        else this.status = 0;

    }


    public int checkSleeping(long currentTime){
        //calculateSleepTime()을 호출해 최적의 수면시간을 받아옴
        //getTime의 값을 받아와 0: 현재 안울림 & inactivate, 1: 현재 안울림 & activate, 2: 현재 울림을 return함.
        if(this.status == 0) return 0; // 만약 inactivate 상태라면 0을 리턴하면 된다.

        //Object[]를 더미로 만들어서 parameter를 충족시켰다. 단순히 sleepingTime을 구하는 과정이다.
        Object[] temp = new Object[2];
        temp[0] = currentTime;
        temp[1] = null;
        LocalTime sleepingTime = (LocalTime) calculateSleepingTime(temp)[0];

        //long인 currentTime을 LocalTime으로 바꿔 비교를 진행하려 한다.
        //LocalTime currentLocalTime = Instant.ofEpochMilli(currentTime).atZone(ZoneId.systemDefault()).toLocalTime();
        LocalTime currentLocalTime = Utility.milliToLocalTime(currentTime);

        if(currentLocalTime == sleepingTime) return 2;
        else return 1;

        //0: 현재 안울림 & inactivate, 1: 현재 안울림 & activate, 2: 현재 울림
    }

    public LocalTime getSleepTime() {
        return this.sleepTime;
    }

    public LocalTime getWakeUpTime() {
        return this.wakeUpTime;
    }





}
