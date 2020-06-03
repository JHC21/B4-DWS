package view;

import controller.System;
import view.template.Mode;


public class UI {

    DisplayManager displayManager;
    ModeManager modeManager;
    ButtonHandler buttonHandler;

    System system;

    // TODO: UI가 갖고 있는 현재 시간(long)
    Mode currentState;

    public void systemWatching(Mode mode){

        while(true){
            // TODO: mode.mainCategory : 0~6까지 추가
            if (mode.mainCategory == 0){
                // Timekeeping
                if(mode.subCategory == 0){
                    // Display Time
                    // Object data = modeManager.displayTime();
                    // if(data == 1) // diplay.showAlarming()
                }else if(mode.subCategory == 1){
                    // Set Time
                    // modeManager.setTime();
                }
            }else if(mode.mainCategory == 0){

            }

        }
    }

    public UI(){
        // currentState = "DISPLAY_TIME";

        displayManager = new DisplayManager();
        modeManager = new ModeManager();
        buttonHandler = new ButtonHandler();
        system = new System();

        currentState = new Mode(0,0);
        systemWatching(currentState);

    }
}
