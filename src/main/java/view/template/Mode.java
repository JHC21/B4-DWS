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

    private int functionSelector;


    public int getMainCategory() {
        return mainCategory;
    }

    public int getSubCategory() {
        return subCategory;
    }

    public int getFunctionSelector() {
        return functionSelector;
    }

    public void setMainCategory(int mainCategory) {
        this.mainCategory = mainCategory;
    }

    public void setSubCategory(int subCategory) {
        this.subCategory = subCategory;
    }

    public void setFunctionSelector(int functionSelector) {
        this.functionSelector = functionSelector;
    }

    public void moveFunctionSelector(){
        if(this.functionSelector == 3){
            this.functionSelector = 0;
        }else{
            this.functionSelector += 1;
        }
    }

    public void enterSub(){
        this.subCategory = 1;
    }

    public void exitSub(){
        this.subCategory = 0;
    }

    public Mode(){
        mainCategory = 4;
        subCategory = 0;
    }

}
