package view.template;

public class Mode {
    private int mainCategory;
    // 0 : TimeKeeping
    // 1 : Timer
    // 2 : Stopwatch
    // 3 : Alarm
    // 4 : GlobalTime
    // 5 : SleepingTime
    // 6 : Function Change
    private int subCategory;
    // 0 : display
    // 1 : set


    public Mode(int mainCategory, int subCategory){
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
    }

    public void setMode(int mainCategory, int subCategory){
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
    }

    public int getMainCategory() {
        return mainCategory;
    }

    public int getSubCategory() {
        return subCategory;
    }
}
