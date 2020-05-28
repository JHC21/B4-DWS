package controller.mode;

public class GlobalTime {
    private String[][] cityName;
    private long time;
    private int myTimeZone;
    private int anotherTimeZone;


    public void getTimeValue(long systemTime, Object[] timeKeepingValue){
        //입력으로 들어오는 것은 System.Clock과, System.getTime() 이다.
        //이 값을 이용하여 GlobalTime.time의 값을 최신화한다.

    }


    public void updateAnotherTimeZone(int updateValue){
        //입력으로 들어오는 값은 +1/-1이다. 즉, 시간대가 한 번 바뀔때마다 값을 바꿔줘야 하므로, updateValue가 두 개의 값이 아닐 경우는 없다.
        //this.anotherTimeZone += updateValue
        //this.time도 변경해주는것.
    }

    public void updateMyTimeZone(int updateValue){
        //입력으로 들어오는 값은 +1/-1이다. 즉, 시간대가 한 번 바뀔때마다 값을 바꿔줘야 하므로, updateValue가 두 개의 값이 아닐 경우는 없다.
        //this.myTimeZone += updateValue
    }




}
