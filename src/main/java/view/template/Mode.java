package view.template;

public class Mode {
    public int mainCategory;
    // 0 : TimeKeeping
    // 1 : Timer
    // 2 : Stopwatch
    // 3 : Alarm
    // 4 : GlobalTime
    // 5 : SleepingTime
    // 6 : Function Change
    public int subCategory;
    // 0 : display
    // 1 : set


    public Mode(int mainCategory, int subCategory){
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
    }

}
