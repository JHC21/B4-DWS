package view;

import view.handler.MyMouseEvent;
import view.template.RoundJPanel;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class DisplayManager extends JFrame{


    public void timeKeepingYear(String value){                      //0 : Time Keeping 연
        if(value.equals("false")) value = null;
        segments[0].setText(value);
    }
    public void timeKeepingMonth(String value){                     //1 : Time Keeping 월
        if(value.equals("false")) value = null;
        segments[1].setText(value);
    }
    public void timeKeepingDay(String value){                       //2 : Time Keeping 일
        if(value.equals("false")) value = null;
        segments[2].setText(value);
    }
    public void timeKeepingWeekDay(String value){                   //3 : Time Keeping 요일
        if(value.equals("false")) value = null;
        segments[3].setText(value);
        }
    public void timeKeepingAlarmAMFM(String value){                 //4 : Time Keeping/Alarm AM/PM (시간제) (변경불가능)
        if(value.equals("false")) value = null;
        segments[4].setText(value);
    }
    public void timeKeepingTimerStopWatchAlarmHour(String value){   //5 : Time Keeping/Timer/Stopwatch/Alarm 시
        segments[5].setText(value);
    }
    public void timeKeepingTimerStopWatchAlarmMinute(String value){ //6 : Time Keeping/Timer/Stopwatch/Alarm 분
        segments[6].setText(value);
    }
    public void timeKeepingTimerStopWatchSecond(String value){      //7 : Time Keeping/Timer/Stopwatch 초
        segments[7].setText(value);
    }
    public void timerStopWatchCountingState(String value){          //8 : Timer/Stopwatch counting (시계 우상단)
        segments[8].setText(value);
    }
    public void timerStopWatchAlarmSleepingActivationState(String value){   //9 : Timer/Stopwatch/Alarm/Sleeping Time activation (시계 우하단)
        segments[9].setText(value);
    }
    public void stopWatchLap(String value){                         //10: Stop watch의 Lap
        segments[10].setText(value);
    }
    public void stopWatchMilliSecond(String value){                 //11: Stop watch의 millisecond
        segments[11].setText(value);
        }
    public void alarmSunday(String value){                          //12: Alarm 일
        segments[12].setText(value);
    }
    public void alarmMonday(String value){                          //13: Alarm 월
        segments[13].setText(value);
    }
    public void alarmTuesday(String value){                         //14: Alarm 화
        segments[14].setText(value);
    }
    public void alarmWednesday(String value){                       //15: Alarm 수
        segments[15].setText(value);
    }
    public void alarmThursday(String value){                        //16: Alarm 목
        segments[16].setText(value);
    }
    public void alarmFriday(String value){                          //17: Alarm 금
        segments[17].setText(value);
    }
    public void alarmSaturday(String value){                        //18: Alarm 토
        segments[18].setText(value);
    }
    public void alarmNumber(String value){                          //19: Alarm 번호 (시계 좌하단)
        segments[19].setText(value);
    }
    public void showAlarming(String value){                         //20: showAlarming
        String innerText = "<html><div style='border:1px solid red width:80 height:80' >" +
                "<div style='padding:0 50px 0 50px; display:block;'>"+
                "<img src='file:assets/alarm_active.png' width=100 height=100></div>"+
                "<div style='width:180px; height:30px; display:block; font-size:22px; text-align:center;'>20 05 03 일</div>" +
                "<div style='width:180px; height:50px; display:block; font-size:27px; text-align:center;'><b style='font-size:22px;'>AM</b> 06:30</div>" +
                "</div></html>";
    }
    public void strings1(String value){                             //21: global time의 내도시 / 추천 수면시간 / 목표 기상시각
        String innerText = "<html>뉴욕<br>마이애미<br>토론토<br></html>";
    }
    public void strings2(String value){                             //22: global time의 내도시 시간 (시간제 포함) / 추천 수면시간1 / 목표 기상시각
        String innerText = "<html><span style='font-size: 20px;'>AM $nbsp</span><span style='font-size:30px;'>03:01</span></html>";
    }
    public void cityorsleepingwakeuptimedata1(String value){        //23: global time의 남의도시 / 추천 수면시각2 / 최대 수면시간
        String innerText = "<html>뉴욕<br>마이애미<br>토론토<br></html>";
    }
    public void cityorsleepingwakeuptimedata2(String value){        //24: global time의 남의도시 시간 (시간제 포함) / 추천 수면시간2 / 목표 기상시
        String innerText = "<html><span style='font-size: 20px;'>AM $nbsp</span><span style='font-size:30px;'>03:01</span></html>";
    }
    public void cheeringMessageShowAll(String value){               //25: Cheering message 전체
        String innerText = "<html><div style='border:1px solid red width:80 height:80' >" +
                "<div style='height:80px; text-align:center; display:block;'>이제 잘 시간이에요<br>오늘도 수고 많았어요^^b</div>"+
                "<div style='width:180px; height:30px; display:block; font-size:22px; text-align:center;'>20 05 03 일</div>" +
                "<div style='width:180px; height:50px; display:block; font-size:27px; text-align:center;'><b style='font-size:22px;'>AM</b> 06:30</div>" +
                "</div></html>";
    }
    public void chstomizeOwnClock(String value){                    //26: function list에서 customize own clock
        String innerText = "<html><div style='text-align:center;'>Customize<br>Your Own Clock</div></html>";
    }
    public void icon1(String value){                                //27: 아이콘1 (제일 좌측)
        segments[27].setText(value);
    }
    public void icon2(String value){                                //28: 아이콘2
        segments[28].setText(value);
    }
    public void icon3(String value){                                //29: 아이콘3
        segments[29].setText(value);
    }
    public void icon4(String value){                                //30: 아이콘4
        segments[30].setText(value);
    }
    public void icon5(String value){                                //31: 아이콘5
        segments[31].setText(value);
    }
    public void icon6(String value){                                //32: 아이콘6 (제일 우측)
        segments[32].setText(value);
    }

    interface DisplayAction{ void display(String value); }
    private DisplayAction[] displays = new DisplayAction[]{
            new DisplayAction() { public void display(String value) { timeKeepingYear(value);                            } },
            new DisplayAction() { public void display(String value) { timeKeepingMonth(value);                           } },
            new DisplayAction() { public void display(String value) { timeKeepingDay(value);                             } },
            new DisplayAction() { public void display(String value) { timeKeepingWeekDay(value);                         } },
            new DisplayAction() { public void display(String value) { timeKeepingAlarmAMFM(value);                       } },
            new DisplayAction() { public void display(String value) { timeKeepingTimerStopWatchAlarmHour(value);         } },
            new DisplayAction() { public void display(String value) { timeKeepingTimerStopWatchAlarmMinute(value);       } },
            new DisplayAction() { public void display(String value) { timeKeepingTimerStopWatchSecond(value);            } },
            new DisplayAction() { public void display(String value) { timerStopWatchCountingState(value);                } },
            new DisplayAction() { public void display(String value) { timerStopWatchAlarmSleepingActivationState(value); } },
            new DisplayAction() { public void display(String value) { stopWatchLap(value);                               } },
            new DisplayAction() { public void display(String value) { stopWatchMilliSecond(value);                       } },
            new DisplayAction() { public void display(String value) { alarmSunday(value);                                } },
            new DisplayAction() { public void display(String value) { alarmMonday(value);                                } },
            new DisplayAction() { public void display(String value) { alarmTuesday(value);                               } },
            new DisplayAction() { public void display(String value) { alarmWednesday(value);                             } },
            new DisplayAction() { public void display(String value) { alarmThursday(value);                              } },
            new DisplayAction() { public void display(String value) { alarmFriday(value);                                } },
            new DisplayAction() { public void display(String value) { alarmSaturday(value);                              } },
            new DisplayAction() { public void display(String value) { alarmNumber(value);                                } },
            new DisplayAction() { public void display(String value) { showAlarming(value);                               } },
            new DisplayAction() { public void display(String value) { strings1(value);                                   } },
            new DisplayAction() { public void display(String value) { strings2(value);                                   } },
            new DisplayAction() { public void display(String value) { cityorsleepingwakeuptimedata1(value);              } },
            new DisplayAction() { public void display(String value) { cityorsleepingwakeuptimedata2(value);              } },
            new DisplayAction() { public void display(String value) { cheeringMessageShowAll(value);                     } },
            new DisplayAction() { public void display(String value) { chstomizeOwnClock(value);                          } },
            new DisplayAction() { public void display(String value) { icon1(value);                                      } },
            new DisplayAction() { public void display(String value) { icon2(value);                                      } },
            new DisplayAction() { public void display(String value) { icon3(value);                                      } },
            new DisplayAction() { public void display(String value) { icon4(value);                                      } },
            new DisplayAction() { public void display(String value) { icon5(value);                                      } },
            new DisplayAction() { public void display(String value) { icon6(value);                                      } },
    };




    int selector = 0;

    // JButtons
    String[] btnNames = { "A", "B", "C", "D"};
    JButton[] buttons = new JButton[btnNames.length];
    int[] btnX = new int[]{60, 400, 60, 400};
    int[] btnY = new int[]{125, 125, 275, 275};

    JButton aButton = new JButton("A");
    JButton bButton = new JButton("B");
    JButton cButton = new JButton("C");
    JButton dButton = new JButton("D");

    // JPanel
    RoundJPanel outerPanel = new RoundJPanel();
    RoundJPanel innerPanel = new RoundJPanel();
    JPanel functionPanel = new JPanel();

    // Color
    Color outerPanelColor = new java.awt.Color(220, 220, 220);
    Color innerPanelColor = new java.awt.Color(0, 0, 0);
    // Color innerPanelColor = new java.awt.Color(255, 255, 255);


    // Icons
    String[] iconNames = { "timekeeping_active", "timer_active", "stop_watch_active", "alarm_active",
            "global_time_active", "sleeping_time_active" };
    ImageIcon[] icons;
    JLabel[] labelIcons;

    //Event Listener
    MyMouseEvent myMouseEvent = new MyMouseEvent("TEST");

    // Segments
    JLabel[] segments;



    public ImageIcon[] getResizedIcon(){

        icons = new ImageIcon[6];
        for(int i = 0 ; i < 6 ; i++){
            String iconFile = String.format("assets/%s.png", iconNames[i]);
            ImageIcon originIcon = new ImageIcon(iconFile);
            Image originImg = originIcon.getImage();
            Image changedImg= originImg.getScaledInstance(30, 30, Image.SCALE_SMOOTH );
            icons[i] = new ImageIcon(changedImg);
        }
        return icons;

    }

    public JLabel[] setIcons(ImageIcon[] icons){

        labelIcons = new JLabel[6];

        for(int i = 0 ; i < 6 ; i++){
            labelIcons[i] = new JLabel(icons[i],SwingUtilities.CENTER);
            labelIcons[i].setLayout(null);
            labelIcons[i].setBounds(15+(i*28), 250, 100, 100);
            labelIcons[i].setSize(30,30);
            labelIcons[i].setVisible(true);
        }
        return labelIcons;
    }

    public void setInnerPanel(){

        innerPanel.setLayout(null);
        innerPanel.setBounds(15,15,270,320);
        innerPanel.setSize(270,320);
        innerPanel.setOpaque(false);
        innerPanel.setBackground(innerPanelColor);

        icons = getResizedIcon();
        labelIcons = setIcons(icons);

        for(JLabel icon: labelIcons)
            innerPanel.add(icon);

        innerPanel.setVisible(true);
    }

    // setBound(x,y,width, height), font(bold(1), size), setSize(width, height), label
    public int[][] jLabelData = new int[][]{
            {60, 20, 50, 50, 25, 50, 50},       //0
            {100, 20, 50, 50, 25, 50, 50},      //1
            {140, 20, 50, 50, 25, 50, 50},      //2
            {180, 20, 50, 50, 25, 50, 50},      //3
            {20, 130, 50, 50, 25, 50, 50},      //4
            {70, 80, 150, 150, 50, 150, 150},   //5
            {140, 80, 150, 150, 50, 150, 150},  //6
            {205, 140, 40, 40, 30, 40, 40},     //7
            {205, 30, 40, 40, 30, 40, 40},      //8
            {205, 240, 50, 50, 25, 50, 50},     //9
            {93, 95, 180, 30, 22, 180, 30},     //10
            {205, 170, 50, 50, 20, 50, 50},     //11
            {30, 25, 50, 50, 23, 50, 50},       //12
            {60, 25, 50, 50, 23, 50, 50},       //13
            {90, 25, 50, 50, 23, 50, 50},       //14
            {120, 25, 50, 50, 23, 50, 50},      //15
            {150, 25, 50, 50, 23, 50, 50},      //16
            {180, 25, 50, 50, 23, 50, 50},      //17
            {210, 25, 50, 50, 23, 50, 50},      //18
            {215, 70, 50, 50, 23, 50, 50},      //19
            {20, 0, 200, 300, 20, 200, 300},    //20
            {20, 40, 100, 150, 15, 100, 100},   //21
            {90, 85, 200, 40, 20, 200, 40},     //22
            {20, 110, 100, 150, 15, 100, 100},  //23
            {90, 155, 200, 40, 20, 200, 40},    //24
            {20, 0, 200, 300, 20, 200, 300},    //25
            {20, 0, 250, 300, 30, 400, 200}     //26
    };

    public void setOuterPanel(){

        outerPanel.setLayout(null);
        outerPanel.setBounds(100,75,300,350);
        outerPanel.setOpaque(false);
        outerPanel.setBackground(outerPanelColor);

    }

    public void setButtons(){

        for(int i = 0 ; i < 4 ; i ++){
            buttons[i] = new JButton(btnNames[i]);
            buttons[i].setBounds(btnX[i], btnY[i], 40, 100);
            buttons[i].setBackground(outerPanelColor);
            buttons[i].setOpaque(true);
            buttons[i].setBorderPainted(true);
            buttons[i].addMouseListener(myMouseEvent);
        }
    }

    public void setSegments(){
        this.segments = new JLabel[27];
        for(int i = 0 ; i < 27 ; i++){
            segments[i] = new JLabel();

            segments[i].setName("Nameof" + String.valueOf(i));
            segments[i].setLayout(null);
            segments[i].setForeground(Color.WHITE);
            segments[i].setFont(new Font("NanumSquare", Font.BOLD, 15));
            segments[i].setVisible(true);
            segments[i].setBounds(jLabelData[i][0], jLabelData[i][1], jLabelData[i][2], jLabelData[i][3]);
            segments[i].setFont(segments[0].getFont().deriveFont((float)jLabelData[i][4]));
            segments[i].setSize(jLabelData[i][5], jLabelData[i][6]);
            innerPanel.add(segments[i]);
        }
    }

    public void initTemplate(){
        getContentPane().setBackground(Color.WHITE);
        getContentPane().add(outerPanel);
        getContentPane().add(innerPanel);

        setOuterPanel();
        setInnerPanel();
        setButtons();
        setSegments();

        outerPanel.add(innerPanel);

        for(JButton btn: buttons)
            getContentPane().add(btn);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);
        setLayout(null);
        setVisible(true);

    }

    public DisplayManager() {
        super("Sleepy Clock");
        initTemplate();
    }

    public int getSelector() {
        return selector;
    }

    public void setSelector(int selector){
        this.selector = selector;
    }

    public void displayTime(String[] timeFormat){
        displays[0].display(timeFormat[0]);
        displays[1].display(timeFormat[1]);
        displays[2].display(timeFormat[2]);
        displays[3].display(timeFormat[7]);
        displays[4].display(timeFormat[8]);
        displays[5].display(timeFormat[3]);
        displays[6].display(timeFormat[4]);
        displays[7].display(timeFormat[5]);
    }

    public void display(Object[] test){
        //for test;
    }
}
