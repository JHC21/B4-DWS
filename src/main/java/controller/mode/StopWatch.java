package controller.mode;

public class StopWatch {
    private long startTime;
    private long stackedTime;
    private int state; //Stop watch가 동작하지 않을때 (Stop)0, 동작할때 (start/restart) 1
    private long lap;


    /*
    예를 들어, 1시에 설정된 스탑워치가 동작한다고 하면,
    startTime : 1시
    stackedTime : 0
    UI상 보여지는 시간 : System.clock - startTime  ->  이게 return값.
    running : True

    1시 2분에 멈추면 (toggleStopWatchCounting)
    startTime : 1시
    stackedTime : System.clock(1시 2분) - startTime(1시) -> 2분
    UI상 보여지는 시간 : System.clock - startTime
    running : False

    1시 4분에 재시작하면 (toggleStopWatchCounting)
    startTime : 1시 4분으로 변경
    stackedTime : 2분
    UI상 보여지는 시간 : System.clock - startTime + stackedTime
    running : True
     */

    //결론을 내리면, getStopWatchValue는 System이 StopWatch로 현재 시간을 주면, StopWatch는 UI에 표시되어야 할 시간을 return한다.
    public long calculateStopWatch(long systemTime) {
        return 0;   //System.clock - startTime + stackedTime
    }

    public long getLap() {

        return 0; // 현재 lap을 리턴한다.
    }

    //Lap을 찍는 함수.
    public void setLap(long lapTime){
        //System.time을 받아서
        //lap = System.clock - startTime;
        //return lap 해주면 됨
        //this.status == 2일 때만 동작하게끔 예외처리
    }


    public void resetStopWatch(){
        //UI에서 찍힐 값을 return해야 하므로, 0을 return하고
        //startTime, stackedTime을 0으로 초기화해주고
        //this.status를 0으로 비꿔준다 (1일때만 동작가능하게 예외처리)
    }


    public void changeStopWatchState(){
        //StopWatch의 stop, restart를 toggle하는 메소드
        //this.status의 상태를 0~1로 번갈아가며 toggle해주는 것임
        //1에서 0으로 갈때는 resetStopWatch를 수행해야
    }

    public int getStopWatchState() {
        return this.state;
    }
}
