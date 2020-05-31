package controller.mode;

public class Timer {
    private long startTime;
    private long settedTime;
    private long stackedTime;
    private int status;     //타이머가 꺼져있을때 0, 켜져있으면서 동작하지 않을때 (Stop)1, 동작할때 (start/restart) 2

    /*
    Timer도 a, c가 필요하다는 결론이 남.
    즉, 시작 시간을 Timer가 저장하게끔 함.

    예를 들어, 1시에 설정된 6분 타이머가 시작한다 하면, (toggleTimerCounting)
    startTime : 1시
    settedTime : 6분 (default)
    stackedTime : 0분
    UI상 보여지는 시간 : settedTime - (System.clock - startTime + stackedTime)   ->  이게 return값.
    running : True

    1시 2분에 멈추면 (toggleTimerCounting)
    startTime : 1시
    settedTime : 6분 (default값 저장)
    stackedTime : System.clock(1시 2분) - startTime(1시) -> 2분
    running : False

    1시 4분에 재시작하면 (toggleTimerCounting)
    startTime : 1시 4분
    stackedTime : 2분
    UI상 보여지는 시간 : settedTime - (System.clock - startTime + stackedTime)
    running : True
     */

    //결론을 내리면, getTimerValue는 System이 Timer로 현재 시간을 주면, Timer는 UI에 표시되어야 할 시간을 return한다.
    public long calculateTimerValue(long systemTime) {
        return 0;   //settedTime - (System.clock - startTime + stackedTime)
    }

    public void updateTimer(long time){
        //time을 받아서 settedTime을 업데이트 해주는 메소드
        //this.status == 1일 때만 사용할 수 있어야함.
        //if(this.statis != 1){} 처럼, 1이 아닐땐 동작하지 않도록 예외처리
    }

    public int checkTimer(long systemTIme){
        //UI상 보여지는 시간 : settedTime - (System.clock - startTime + stackedTime)이고, 따라서
        //settedTime = (System.clock - startTime + stackedTime) 이면 Timer가 울릴 시간이다.
        //int로 상태를 정의하고 return한다, 0은 inactive, 1은 active이면서 시간이 남았을때, 2가 active이면서 울릴 시간이 되었을 때

        //로직 짤때 상태를 먼저 받아오고
        //상태에 따라 if로 분기해서 시간을 계산해야 할 때만 계산한다.
        return 0; //0: 현재 안울림 & inactivate, 1: 현재 안울림 & activate, 2: 현재 울림
    }
    
    public void changeTimerCounting(){
        //타이머의 stop, restart를 toggle하는 메소드
        //this.status의 상태를 0~1로 번갈아가며 toggle해주는 것임.
    }

    public void changeTimerActivation(long systemTime){
        //타이머의 inactive, active를 toggle하는 메소드
        //this.status의 상태를 1~2로 번갈아가며 toggle해주는 것임
        //만약 timer가 멈춰있을 때 재시작하는거라면, startTime을 현재 시간으로 설정해야 함
        //if(this.status == 1){startTime = systemTime; status == 2;}

        //만약 timer가 동작중일때 멈추는거라면, stackedTime을 최신화해줘야 함
        //else{stackedTime = systemTime - startTime; status == 1;}
    }

    public long getSettedTime() {
        return this.settedTime;
    }
}
