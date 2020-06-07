package controller.mode;

public class StopWatch {
    private long startTime;
    private long stackedTime;
    private int state; //Stop watch가 동작하지 않을때 (Stop)0, 동작할때 (start/restart) 1
    private long lap;

    public long getStartTime(){
        return this.startTime;
    }

    public StopWatch(){
        stackedTime = 0L;
        state = 0;
    }


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

    //결론을 내리면, getStopWatchValue는
    // state가 1일 땐 System이 StopWatch로 현재 시간을 주면, StopWatch는 UI에 표시되어야 할 시간을 return한다.
    // state가 0일 땐 무조건 0을 return해야 한다 (stackedTime이 0이니 stackedTime을 return하면 좋을듯)


    public long calculateStopWatch(long systemTime) {
        if(this.state == 1){ return systemTime - this.startTime + this.stackedTime; }
        else{ return stackedTime; }
        //System.clock - startTime + stackedTime
    }

    public long getLap() {
        return this.lap;
        //현재 lap을 리턴
    }

    //Lap을 찍는 함수.
    public void setLap(long systemTime){
        if(this.state == 1) {
            this.lap = systemTime - this.startTime + this.stackedTime;
        }else {
            System.out.println("동작하지 않음");
        }
        //System.time을 받아서
        //lap = System.clock - startTime;
        //return lap 해주면 됨
        //this.status == 1일 때만 동작하게끔 예외처리
    }

    public void setReset(long systemTime){
        if(this.state == 0){
            this.stackedTime = 0;
            this.startTime = systemTime;
            this.lap = 0;
        }else{
            System.out.println("동작하지 않음");
        }
        //UI에서 찍힐 값을 return해야 하므로, 0을 return하고
        //startTime, stackedTime을 0으로 초기화해주고
        //this.status를 0으로 비꿔준다 (1일때만 동작가능하게 예외처리)
    }


    public void changeStopWatchState(long systemTime){
        if(this.state == 1){
            this.state = 0;
            this.stackedTime = systemTime - this.startTime + this.stackedTime;
        }else /*if state == 0*/{
            this.state = 1;
            this.startTime = systemTime;
        }
        //StopWatch의 stop, restart를 toggle하는 메소드
        //this.status의 상태를 0~1로 번갈아가며 toggle해주는 것임
        //1에서 0으로 갈때는 resetStopWatch를 수행해야
    }

    public int getStopWatchState() {
        return this.state;
    }
}
