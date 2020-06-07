package view;

import view.handler.MyMouseEvent;
import view.template.RoundJPanel;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class DisplayManager extends JFrame{


    private void timeKeepingYear(String value){                      //0 : Time Keeping 연
        if(value.equals("false")) segments[0].setText(null);
        else{segments[0].setText(value);}
    }
    private void timeKeepingMonth(String value){                     //1 : Time Keeping 월
        if(value.equals("false")) segments[1].setText(null);
        else{segments[1].setText(value);}
    }
    private void timeKeepingDay(String value){                       //2 : Time Keeping 일
        if(value.equals("false")) segments[2].setText(null);
        else{segments[2].setText(value);}
    }
    private void timeKeepingWeekDay(String value){                   //3 : Time Keeping 요일
        if(value.equals("false")) segments[3].setText(null);
        else{segments[3].setText(value);}
        }
    private void timeKeepingAlarmAMFM(String value){                 //4 : Time Keeping/Alarm AM/PM (시간제) (변경불가능)

        if(value.equals("false")) segments[4].setText(null);
        else{segments[4].setText(value);}
    }
    private void timeKeepingTimerStopWatchAlarmHour(String value){   //5 : Time Keeping/Timer/Stopwatch/Alarm 시
        segments[5].setText(value);
    }
    private void timeKeepingTimerStopWatchAlarmMinute(String value){ //6 : Time Keeping/Timer/Stopwatch/Alarm 분
        segments[6].setText(value);
    }
    private void timeKeepingTimerStopWatchSecond(String value){      //7 : Time Keeping/Timer/Stopwatch 초
        segments[7].setText(value);
    }
    private void timerStopWatchCountingState(String value){          //8 : Timer/Stopwatch counting (시계 우상단)
        segments[8].setText(value);
    }
    private void timerStopWatchAlarmSleepingActivationState(String value){   //9 : Timer/Stopwatch/Alarm/Sleeping Time activation (시계 우하단)
        segments[9].setText(value);
    }
    private void stopWatchLap(String value){                         //10: Stop watch의 Lap
        segments[10].setText(value);
    }
    private void stopWatchMilliSecond(String value){                 //11: Stop watch의 millisecond
        segments[11].setText(value);
        }
    private void alarmSunday(String value){                          //12: Alarm 일
        segments[12].setText(value);
    }
    private void alarmMonday(String value){                          //13: Alarm 월
        segments[13].setText(value);
    }
    private void alarmTuesday(String value){                         //14: Alarm 화
        segments[14].setText(value);
    }
    private void alarmWednesday(String value){                       //15: Alarm 수
        segments[15].setText(value);
    }
    private void alarmThursday(String value){                        //16: Alarm 목
        segments[16].setText(value);
    }
    private void alarmFriday(String value){                          //17: Alarm 금
        segments[17].setText(value);
    }
    private void alarmSaturday(String value){                        //18: Alarm 토
        segments[18].setText(value);
    }
    private void alarmNumber(String value){                          //19: Alarm 번호 (시계 좌하단)
        segments[19].setText(value);
    }
    private void showAlarming(String value){                         //20: showAlarming
        /* String이          20 05 03 일 AM 06:30
        혹은(24시간제일경우)    20 05 03 일    17:30으로 표시된다 가정, 즉 길이가 일정하다고 가정한다
        19length, 0~10까지 자르고, 11~13시간제, 13~19이 현재시간
        -> HTML이라서, 시간제가 없으면 자동 정렬된다
        */
        if(value.equals("false")) segments[20].setText(null);
        else {
            String value1 = value.substring(0, 10);
            String value2 = value.substring(11, 13);
            String value3 = value.substring(13, 19);
            String innerText = "<html><div style='border:1px solid red width:80 height:80' >" +
                    "<div style='padding:0 50px 0 50px; display:block;'>" +
                    "<img src='file:assets/alarm_active.png' width=100 height=100></div>" +
                    "<div style='width:180px; height:30px; display:block; font-size:22px; text-align:center;'>"+ value1 +"</div>" +
                    "<div style='width:180px; height:50px; display:block; font-size:27px; text-align:center;'><b style='font-size:22px;'>"+value2+"</b>"+value3+"</div>" +
                    "</div></html>";
            segments[20].setText(innerText);
        }
    }
    private void strings1(String value){                             //21: global time의 내도시 / 추천 수면시간 / 목표 기상시각
        String innerText = "<html>뉴욕<br>마이애미<br>토론토<br></html>";
    }
    private void strings2(String value){                             //22: global time의 내도시 시간 (시간제 포함) / 추천 수면시간1 / 목표 기상시각
        String innerText = "<html><span style='font-size: 20px;'>AM $nbsp</span><span style='font-size:30px;'>03:01</span></html>";
    }
    private void cityorsleepingwakeuptimedata1(String value){        //23: global time의 남의도시 / 추천 수면시각2 / 최대 수면시간
        String innerText = "<html>뉴욕<br>마이애미<br>토론토<br></html>";
    }
    private void cityorsleepingwakeuptimedata2(String value){        //24: global time의 남의도시 시간 (시간제 포함) / 추천 수면시간2 / 목표 기상시
        String innerText = "<html><span style='font-size: 20px;'>AM $nbsp</span><span style='font-size:30px;'>03:01</span></html>";
    }
    private void cheeringMessageShowAll(String value){               //25: Cheering message 전체
        String innerText = "<html><div style='border:1px solid red width:80 height:80' >" +
                "<div style='height:80px; text-align:center; display:block;'>이제 잘 시간이에요<br>오늘도 수고 많았어요^^b</div>"+
                "<div style='width:180px; height:30px; display:block; font-size:22px; text-align:center;'>20 05 03 일</div>" +
                "<div style='width:180px; height:50px; display:block; font-size:27px; text-align:center;'><b style='font-size:22px;'>AM</b> 06:30</div>" +
                "</div></html>";
    }
    private void chstomizeOwnClock(String value){                    //26: function list에서 customize own clock
        String innerText = "<html><div style='text-align:center;'>Customize<br>Your Own Clock</div></html>";
    }
    private void icon1(String value){                                //27: 아이콘1 (제일 좌측)
        segments[27].setText(value);
    }
    private void icon2(String value){                                //28: 아이콘2
        segments[28].setText(value);
    }
    private void icon3(String value){                                //29: 아이콘3
        segments[29].setText(value);
    }
    private void icon4(String value){                                //30: 아이콘4
        segments[30].setText(value);
    }
    private void icon5(String value){                                //31: 아이콘5
        segments[31].setText(value);
    }
    private void icon6(String value){                                //32: 아이콘6 (제일 우측)
        segments[32].setText(value);
    }

    interface DisplayAction{ void display(String value); }
    private DisplayAction[] displays = new DisplayAction[]{
            this::timeKeepingYear,
            this::timeKeepingMonth,
            this::timeKeepingDay,
            this::timeKeepingWeekDay,
            this::timeKeepingAlarmAMFM,
            this::timeKeepingTimerStopWatchAlarmHour,
            this::timeKeepingTimerStopWatchAlarmMinute,
            this::timeKeepingTimerStopWatchSecond,
            this::timerStopWatchCountingState,
            this::timerStopWatchAlarmSleepingActivationState,
            this::stopWatchLap,
            this::stopWatchMilliSecond,
            this::alarmSunday,
            this::alarmMonday,
            this::alarmTuesday,
            this::alarmWednesday,
            this::alarmThursday,
            this::alarmFriday,
            this::alarmSaturday,
            this::alarmNumber,
            this::showAlarming,
            this::strings1,
            this::strings2,
            this::cityorsleepingwakeuptimedata1,
            this::cityorsleepingwakeuptimedata2,
            this::cheeringMessageShowAll,
            this::chstomizeOwnClock,
            this::icon1,
            this::icon2,
            this::icon3,
            this::icon4,
            this::icon5,
            this::icon6,
    };




    int selector = 0;

    // JButtons
    String[] btnNames = { "A", "B", "C", "D"};
    JButton[] buttons = new JButton[btnNames.length];
    int[] btnX = new int[]{60, 400, 60, 400};
    int[] btnY = new int[]{125, 125, 275, 275};

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
    JLabel selector_label;



    private ImageIcon[] getResizedIcon(){

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

    private JLabel[] setIcons(ImageIcon[] icons){

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

    private void setInnerPanel(){

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
    private int[][] jLabelData = new int[][]{
            {60, 20, 50, 50, 25, 50, 50},       //0
            {100, 20, 50, 50, 25, 50, 50},      //1
            {140, 20, 50, 50, 25, 50, 50},      //2
            {180, 20, 50, 50, 25, 50, 50},      //3
            {20, 130, 50, 50, 25, 50, 50},      //4
            {70, 80, 100, 150, 50, 100, 150},   //5
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

    private void setOuterPanel(){

        outerPanel.setLayout(null);
        outerPanel.setBounds(100,75,300,350);
        outerPanel.setOpaque(false);
        outerPanel.setBackground(outerPanelColor);

    }

    private void setButtons(){

        for(int i = 0 ; i < 4 ; i ++){
            buttons[i] = new JButton(btnNames[i]);
            buttons[i].setBounds(btnX[i], btnY[i], 40, 100);
            buttons[i].setBackground(outerPanelColor);
            buttons[i].setOpaque(true);
            buttons[i].setBorderPainted(true);
            buttons[i].addMouseListener(myMouseEvent);
        }
    }

    private void setSegments(){
        this.segments = new JLabel[27];
        for(int i = 0 ; i < 27 ; i++){
            segments[i] = new JLabel();
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

    private void setSelector_label(){
        selector_label = new JLabel();
        selector_label.setLayout(null);
        selector_label.setForeground(Color.WHITE);
        selector_label.setFont(new Font("NanumSquare", Font.BOLD, 15));
        selector_label.setVisible(true);
        selector_label.setBounds(jLabelData[selector][0], jLabelData[selector][1]+30, jLabelData[selector][2], jLabelData[selector][3]);
        selector_label.setFont(segments[0].getFont().deriveFont((float)jLabelData[selector][4]));
        selector_label.setSize(jLabelData[selector][5], jLabelData[selector][6]);
        innerPanel.add(selector_label);
    }

    private void initTemplate(){
        getContentPane().setBackground(Color.WHITE);
        getContentPane().add(outerPanel);
        getContentPane().add(innerPanel);

        setOuterPanel();
        setInnerPanel();
        setButtons();
        setSegments();
        setSelector_label();

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

    public void displaySelector(){

        int x = segments[selector].getX();
        int y = segments[selector].getY();

        selector_label.setText(".");
        selector_label.setBounds(x, y, 80, 80);
        selector_label.setFont(segments[0].getFont().deriveFont((float)50));
        selector_label.setSize(80, 80);
    }

    public void notDisplaySelector(){
        selector_label.setText(null);
    }

    //String[]을 받아 display 하는 메소드들
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
    public void displayTimer(String[] timeFormat) {
        /*
        0 : 시
        1 : 분
        2 : 초
        3 : counting
        4 : activate
         */
        displays[5].display(timeFormat[0]); // 시
        displays[6].display(timeFormat[1]); // 분
        displays[7].display(timeFormat[2]); // 초
        displays[8].display(timeFormat[3]); // counting
        displays[9].display(timeFormat[4]); // activate
    }
    public void displayStopWatch(String[] timeFormat) {
        //S : stopwatch, L : Lap
        /*
        0 : S시
        1 : S분
        2 : S초
        3 : Sms
        4 : L시
        5 : L분
        6 : L초
        7 : Lms
        8 : pause/start
        9 : activate
         */
        //activate에 대해 이야기 해 보아야 함(function list에 어떻게 표현해야 할 지)
        displays[5].display(timeFormat[0]); // S시
        displays[6].display(timeFormat[1]); // S분
        displays[7].display(timeFormat[2]); // S초
        displays[11].display(timeFormat[3]); // Sms
        displays[8].display(timeFormat[8]); //pause&start
        displays[9].display(timeFormat[9]); // actiavte
        //string 앞의 0까지 앞에서 해 줬다고 가정합니다
        String temp = String.format("%s:%s:%s:%s", timeFormat[4], timeFormat[5], timeFormat[6], timeFormat[7]);
        displays[10].display(temp); // Lap
    }
    public void displayAlarm(String[] timeFormat) {
        /*
        0 : 시
        1 : 분
        2 : AM/PM
        3 : 일
        4 : 월
        5 : 화
        6 : 수
        7 : 목
        8 : 금
        9 : 토
        10 : activate
        11 : alarm number
         */
        displays[5].display(timeFormat[0]); // 시
        displays[6].display(timeFormat[1]); // 분
        displays[4].display(timeFormat[2]); // AM/PM
        displays[12].display(timeFormat[3]); // 일
        displays[13].display(timeFormat[4]); // 월
        displays[14].display(timeFormat[5]); // 화
        displays[15].display(timeFormat[6]); // 수
        displays[16].display(timeFormat[7]); // 목
        displays[17].display(timeFormat[8]); // 금
        displays[18].display(timeFormat[9]); // 토
        displays[9].display(timeFormat[10]); // activate
        displays[19].display(timeFormat[11]); // alarm number
    }

    public String getValueFromCurrentSelector(){
        return segments[selector].getText();
    }

    public void display(Object[] test){
        //for test;
    }

    public void displayShowAlarming(String currentTime){
        //String 형식은 "20 05 03 일    06:30"
        //12시간제일때는 "20 05 03 일 AM 06:30"
        displays[20].display(currentTime);
    }

    public void notDisplayIcon(){
        for(int i = 0; i < 6; i ++){
            labelIcons[i].setVisible(false);
        }
    }

    public void displayIcon(){
        for(int i = 0; i < 6; i++){
            labelIcons[i].setVisible(true);
        }
    }
}
