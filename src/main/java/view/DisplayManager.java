package view;

import view.handler.MyMouseEvent;
import view.template.RoundJPanel;

import javax.swing.*;
import java.awt.*;

public class DisplayManager extends JFrame{

    static final String[] cheeringMessages = {
            "<div style='height:80px; text-align:center; display:block;'>이제 잘 시간이에요<br>오늘도 수고 많았어요^^b</div>",
            "<div style='height:80px; text-align:center; display:block;'>혹시 야근인가요? ㅠㅠbr>퇴근까지 힘내 보아요!</div>",
            "<div style='height:80px; text-align:center; display:block;'>공부도 일단 꿀잠 자고<br>이어서 하는 건 어때요?</div>",
            "<div style='height:80px; text-align:center; display:block;'>이제 잘 시간입니다!<br>개운한 내일을 위한 수면!</div>",
            "<div style='height:80px; text-align:center; display:block;'>못 자더라도 좌절 마세요<br>더 나은 내일을 위한 과정입니다!</div>",
            "<div style='height:80px; text-align:center; display:block;'>꿈꿀 시간인가요,<br>꿈을 이루는 시간인가요?</div>",
            "<div style='height:80px; text-align:center; display:block;'>잠을 자도, 잠을 못 자도,<br>지금은 미래를 위한 시간입니다!</div>",
            "<div style='height:80px; text-align:center; display:block;'>당신의 빛나는<br>내일을 응원합니다!</div>",
            "<div style='height:80px; text-align:center; display:block;'>질 좋은 잠도<br>미래를 위한 투자입니다!</div>",
            "<div style='height:80px; text-align:center; display:block;'>잠 잘 시간이에요!<br>오늘 하루는 어땠나요?</div>",
            "<div style='height:80px; text-align:center; display:block;'>좋은 하루 보내셨나요?<br>언제나 당신을 응원합니다!</div>",
            "<div style='height:80px; text-align:center; display:block;'>잘 시간이에요<br>휴식은 당신의 연료가 됩니다!</div>",
            "<div style='height:80px; text-align:center; display:block;'>잘 시간입니다!<br>당신의 온전한 재충전을 바랍니다</div>",
            "<div style='height:80px; text-align:center; display:block;'>잠에 들 시간이군요<br>건강한 내일을 위해서!</div>",
            "<div style='height:80px; text-align:center; display:block;'>힘든 하루였나요...?<br>잘 될 거에요. 힘내세요!</div>",
            "<div style='height:80px; text-align:center; display:block;'>좋은 하루의 끝은<br>좋은 내일의 시작입니다</div>",
            "<div style='height:80px; text-align:center; display:block;'>누구나 수면은 필수!<br>규칙적인 수면을 놓치지 마세요</div>",
            "<div style='height:80px; text-align:center; display:block;'>행복한 잠에 빠져,<br>개운하게 내일을 시작해요!</div>",
            "<div style='height:80px; text-align:center; display:block;'>언제나 당신의 미래와<br>당신의 수면을 응원합니다!</div>",
            "<div style='height:80px; text-align:center; display:block;'>오늘도 수고 많았어요!<br>오늘의 마무리를 할 시간입니다!</div>"
    };


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
        if(value.equals("false")) segments[5].setText(null);
        else{segments[5].setText(value);}
    }
    private void timeKeepingTimerStopWatchAlarmMinute(String value){ //6 : Time Keeping/Timer/Stopwatch/Alarm 분

        if(value.equals("false")) segments[6].setText(null);
        else{segments[6].setText(value);}
    }
    private void timeKeepingTimerStopWatchSecond(String value){      //7 : Time Keeping/Timer/Stopwatch 초
        if(value.equals("false")) segments[7].setText(null);
        else{segments[7].setText(value);}
    }
    private void timerStopWatchCountingState(String value){          //8 : Timer/Stopwatch counting (시계 우상단)
        //System.out.println("THIs is : " + value);
        if(value.equals("false")) segments[8].setText(null);
        else if(value.equals("OFF") || value.equals("0")) segments[8].setIcon(activatedIcons[1]);
        else segments[8].setIcon(activatedIcons[0]);
    }
    private void timerStopWatchAlarmSleepingActivationState(String value){   //9 : Timer/Stopwatch/Alarm/Sleeping Time activation (시계 우하단)
        if(value.equals("false")) segments[9].setText(null);
        else if(value.equals("1") || value.equals("2")) segments[9].setText("ON");
        else{segments[9].setText(value);}
    }
    private void stopWatchLap(String value){                         //10: Stop watch의 Lap
        segments[10].setText(value);
    }
    private void stopWatchMilliSecond(String value){                 //11: Stop watch의 millisecond
        segments[11].setText(value);
    }
    private void alarmSunday(String value){                          //12: Alarm 일
        if(value.equals("false")) segments[12].setText(null);
        else segments[12].setText(value);
    }
    private void alarmMonday(String value){                          //13: Alarm 월
        if(value.equals("false")) segments[13].setText(null);
        else segments[13].setText(value);
    }
    private void alarmTuesday(String value){                         //14: Alarm 화
        if(value.equals("false")) segments[14].setText(null);
        else segments[14].setText(value);
    }
    private void alarmWednesday(String value){                       //15: Alarm 수
        if(value.equals("false")) segments[15].setText(null);
        else segments[15].setText(value);
    }
    private void alarmThursday(String value){                        //16: Alarm 목
        if(value.equals("false")) segments[16].setText(null);
        else segments[16].setText(value);
    }
    private void alarmFriday(String value){                          //17: Alarm 금
        if(value.equals("false")) segments[17].setText(null);
        else segments[17].setText(value);
    }
    private void alarmSaturday(String value){                        //18: Alarm 토
        if(value.equals("false")) segments[18].setText(null);
        else segments[18].setText(value);
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
    private void sleepingGlobalTimeMyText(String value){                             //21: global time의 내도시 / 추천 수면시간 / 목표 기상시각
        // String innerText = "<html>뉴욕<br>마이애미<br>토론토<br></html>";
        segments[21].setText(value);
    }
    private void sleepingGlobalTimeMyHour(String value){                             //22: global time의 내도시 시간 (시간제 포함) / 추천 수면시간1 / 목표 기상시각
        // String innerText = "<html><span style='font-size: 20px;'>AM $nbsp</span><span style='font-size:30px;'>03:01</span></html>";
        segments[22].setText(value);
    }
    private void sleepingGlobalTimeOtherText(String value){        //before : 23: global time의 남의도시 / 추천 수면시각2 / 최대 수면시간    after : 그냥 내도시시각 (Hour)
        //String innerText = "<html>뉴욕<br>마이애미<br>토론토<br></html>";
        segments[23].setText(value);
    }
    private void sleepingGlobalTimeOtherHour(String value){        //before : 24: global time의 남의도시 시간 (시간제 포함) / 추천 수면시간2 / 목표 기상시     after : 그냥 남의도시시각 (Hour)
        //String innerText = "<html><span style='font-size: 20px;'>AM $nbsp</span><span style='font-size:30px;'>03:01</span></html>";
        segments[24].setText(value);

    }
    private void cheeringMessageShowAll(String value){               //25: Cheering message 전체
        String temp = cheeringMessages[(int)(Math.random() * cheeringMessages.length)];
        String value1 = value.substring(0,10); // year month day dayOfWeek
        String value2 = value.substring(11, 13); // timeFormat
        String value3 = value.substring(14); // hour minute
        String innerText = "<html><div style='border:1px solid red width:80 height:80' >" +
                temp +
                "<div style='width:180px; height:30px; display:block; font-size:22px; text-align:center;'>" + value1 + "</div>" +
                "<div style='width:180px; height:50px; display:block; font-size:27px; text-align:center;'><b style='font-size:22px;'>" + value2 + "</b>" + value3 + "</div>" +
                "</div></html>";
        segments[25].setText(innerText);
    }
    private void customizeOwnClock(String value){                    //26: function list에서 customize own clock

        String[] texts = value.split(" ");

        String innerText = "<html><div style='text-align:center;'>" + texts[0] + "<br>" + texts[1] + " " + texts[2] + " " + texts[3] + "</div></html>";
        segments[26].setText(innerText);
    }

    private void myTimeMinute(String value){
        segments[27].setText(value);
    }

    private void otherTimeMinute(String value){
        segments[28].setText(value);
    }

    private void myTimeTimeFormat(String value) {
        segments[29].setText(value);
    }

    private void otherTimeTimeFormat(String value){
        segments[30].setText(value);
    }

    interface DisplayAction{ void display(String value); }
    private final DisplayAction[] displays = new DisplayAction[]{
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
            this::sleepingGlobalTimeMyText,
            this::sleepingGlobalTimeMyHour,
            this::sleepingGlobalTimeOtherText,
            this::sleepingGlobalTimeOtherHour,
            this::cheeringMessageShowAll,
            this::customizeOwnClock,
            this::myTimeMinute,
            this::otherTimeMinute,
            this::myTimeTimeFormat,
            this::otherTimeTimeFormat,
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

    // Color
    Color outerPanelColor = new java.awt.Color(220, 220, 220);
    Color innerPanelColor = new java.awt.Color(0, 0, 0);
    // Color innerPanelColor = new java.awt.Color(255, 255, 255);


    // Icons

    String[] iconNames = { "timekeeping_active.png", "timer_active.png", "stop_watch_active.png", "alarm_active.png",
            "global_time_active.png", "sleeping_time_active.png" };
    ImageIcon[] icons;
    ImageIcon[] activatedIcons;
    JLabel[] labelIcons;

    //Event Listener
    MyMouseEvent myMouseEvent = new MyMouseEvent("TEST");

    // Segments
    JLabel[] segments;
    JLabel selector_label;


    private ImageIcon[] getResizedActivatedIcon(){


        activatedIcons = new ImageIcon[2];
        java.net.URL url1 = getClass().getClassLoader().getResource("start_icon.png");
        java.net.URL url2 = getClass().getClassLoader().getResource("pause_icon.png");
        ImageIcon icon1ImageIcon = new ImageIcon(url1);
        ImageIcon icon2ImageIcon = new ImageIcon(url2);
        Image originIcon1 = icon1ImageIcon.getImage();
        Image originIcon2 = icon2ImageIcon.getImage();
        Image icon1Image = originIcon1.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        Image icon2Image = originIcon2.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        activatedIcons[0] = new ImageIcon(icon1Image);
        activatedIcons[1] = new ImageIcon(icon2Image);

        return activatedIcons;
    }


    private ImageIcon[] getResizedIcon(){

        icons = new ImageIcon[6];
        for(int i = 0 ; i < 6 ; i++){
            java.net.URL url = getClass().getClassLoader().getResource(iconNames[i]);
            ImageIcon originIcon = new ImageIcon(url);
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
            //0 :15, 1: 43, 2: 71, 3:
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

        activatedIcons = getResizedActivatedIcon();
        icons = getResizedIcon();
        labelIcons = setIcons(icons);

        for(JLabel icon: labelIcons)
            innerPanel.add(icon);

        innerPanel.setVisible(true);
    }

    // setBound(x,y,width, height), font(bold(1), size), setSize(width, height), label
    private final int[][] jLabelData = new int[][]{
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

            {20, 40, 110, 110, 15, 110, 110},   //21
            {150, 40, 100, 150, 30, 100, 100},  //22(시1)
            {20, 140, 100, 150, 15, 100, 100},  //23
            {150, 140, 100, 150, 30, 100, 100},  //24(시2)

            {20, 0, 200, 300, 20, 200, 300},    //25
            {20, 0, 250, 300, 30, 400, 200},    //26

            {200, 40, 100, 150, 30, 100, 100},  //27(분1)
            {200, 140, 100, 150, 30, 100, 100}, //28(분2)
            {100, 40, 100, 150, 25, 100, 100},  //29(시간제1)
            {100, 140, 100, 150, 25, 100, 100}, //30(시간제2)

    }; // 27 + (6) + 4 == 31개


    private final int[][] selectorPosition = new int[][]{
            {65, -33, 50, 50, 25, 50, 10},       //0
            {105, -33, 50, 50, 25, 50, 10},      //1
            {145, -33, 50, 50, 25, 50, 10},      //2
            {180, 20, 50, 50, 25, 50, 50},      //3
            {20, 130, 50, 50, 25, 50, 50},      //4
            {90, 65, 100, 150, 50, 100, 150},   //5
            {160, 65, 150, 150, 50, 150, 150},  //6
            {215, 80, 40, 40, 30, 40, 40},      //7
            {205, 30, 40, 40, 30, 40, 40},      //8
            {205, 240, 50, 50, 25, 50, 50},     //9
            {93, 95, 180, 30, 22, 180, 30},     //10
            {205, 170, 50, 50, 20, 50, 50},     //11
            {32, -31, 50, 50, 23, 50, 50},       //12
            {62, -31, 50, 50, 23, 50, 50},       //13
            {92, -31, 50, 50, 23, 50, 50},       //14
            {122, -31, 50, 50, 23, 50, 50},      //15
            {152, -31, 50, 50, 23, 50, 50},      //16
            {182, -31, 50, 50, 23, 50, 50},      //17
            {212, -31, 50, 50, 23, 50, 50},      //18
            {215, 70, 50, 50, 23, 50, 50},      //19
            {20, 0, 200, 300, 20, 200, 300},    //20
            {5, 38, 110, 110, 15, 110, 110},   //21
            {160, 10, 100, 150, 30, 100, 100},  //22(시1)
            {5, 134, 100, 150, 15, 100, 100},  //23
            {160, 110, 100, 150, 30, 100, 100},  //24(시2)

            {20, 0, 200, 300, 20, 200, 300},    //25
            {20, 0, 250, 300, 30, 400, 200},    //26

            {210, 10, 100, 150, 30, 100, 100},  //27(분1)
            {210, 110, 100, 150, 30, 100, 100}, //28(분2)
            {100, 40, 100, 150, 25, 100, 100},  //29(시간제1)
            {100, 140, 100, 150, 25, 100, 100},  //30(시간제2)

            {22, 180, 50, 50, 23, 50, 50},  //FunctionList icon1  31
            {50, 180, 50, 50, 23, 50, 50},  //FunctionList icon2  32
            {78, 180, 50, 50, 23, 50, 50},  //FunctionList icon3  33
            {106, 180, 50, 50, 23, 50, 50},  //FunctionList icon4  34
            {134, 180, 50, 50, 23, 50, 50},  //FunctionList icon5  35
            {162, 180, 50, 50, 23, 50, 50},  //FunctionList icon6  36
    }; // 37개

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
        this.segments = new JLabel[31];
        for(int i = 0 ; i < 31 ; i++){
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
        segments[8].setForeground(Color.BLUE);
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

        selector_label.setText(".");
        selector_label.setBounds(selectorPosition[selector][0], selectorPosition[selector][1], 80, 80);
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
    public void setTimer(String[] timeFormat) {
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
        //System.out.println(timeFormat[8]);
        displays[9].display("1"); // actiavte
        //string 앞의 0까지 앞에서 해 줬다고 가정합니다
        String temp = String.format("%s:%s:%s:%s", timeFormat[4], timeFormat[5], timeFormat[6], timeFormat[7]);
        displays[10].display(temp); // Lap
    }
    public void displayAlarm(Object[] timeFormat) {
        /*
        0 : 시
        1 : 분
        2 : AM/PM
        3 : 요일 boolean 배열
        4 : activate
        5 : alarm number
         */
        displays[5].display((String)timeFormat[0]); // 시
        displays[6].display((String)timeFormat[1]); // 분
        if(timeFormat[2].equals("  ")) {
            displays[4].display("  ");
        }
        else displays[4].display((String)timeFormat[2]); // AM/PM

        if(((boolean[])timeFormat[3])[0]) displays[12].display("일"); else displays[12].display("false");
        if(((boolean[])timeFormat[3])[1]) displays[13].display("월"); else displays[13].display("false");
        if(((boolean[])timeFormat[3])[2]) displays[14].display("화"); else displays[14].display("false");
        if(((boolean[])timeFormat[3])[3]) displays[15].display("수"); else displays[15].display("false");
        if(((boolean[])timeFormat[3])[4]) displays[16].display("목"); else displays[16].display("false");
        if(((boolean[])timeFormat[3])[5]) displays[17].display("금"); else displays[17].display("false");
        if(((boolean[])timeFormat[3])[6]) displays[18].display("토"); else displays[18].display("false");
        /*
        displays[12].display(timeFormat[3]); // 일
        displays[13].display(timeFormat[4]); // 월
        displays[14].display(timeFormat[5]); // 화
        displays[15].display(timeFormat[6]); // 수
        displays[16].display(timeFormat[7]); // 목
        displays[17].display(timeFormat[8]); // 금
        displays[18].display(timeFormat[9]); // 토
         */
        displays[9].display((String)timeFormat[4]); // activate
        displays[19].display(String.valueOf(Integer.parseInt((String)timeFormat[5]) + 1)); // alarm number
    }
    public void setAlarm(Object[] timeFormat) {
        /*
        0 : 시
        1 : 분
        2 : AM/PM
        3 : 요일 boolean 배열
        4 : activate
        5 : alarm number
         */
        displays[5].display((String)timeFormat[0]); // 시
        displays[6].display((String)timeFormat[1]); // 분
        displays[4].display((String)timeFormat[2]); // AM/PM

        if(((boolean[])timeFormat[3])[0]) displays[12].display("일"); else displays[12].display("false");
        if(((boolean[])timeFormat[3])[1]) displays[13].display("월"); else displays[13].display("false");
        if(((boolean[])timeFormat[3])[2]) displays[14].display("화"); else displays[14].display("false");
        if(((boolean[])timeFormat[3])[3]) displays[15].display("수"); else displays[15].display("false");
        if(((boolean[])timeFormat[3])[4]) displays[16].display("목"); else displays[16].display("false");
        if(((boolean[])timeFormat[3])[5]) displays[17].display("금"); else displays[17].display("false");
        if(((boolean[])timeFormat[3])[6]) displays[18].display("토"); else displays[18].display("false");
        /*
        displays[12].display(timeFormat[3]); // 일
        displays[13].display(timeFormat[4]); // 월
        displays[14].display(timeFormat[5]); // 화
        displays[15].display(timeFormat[6]); // 수
        displays[16].display(timeFormat[7]); // 목
        displays[17].display(timeFormat[8]); // 금
        displays[18].display(timeFormat[9]); // 토
         */
    }

    public void displayGlobalTime(String[] timeFormat) {
        /*
        0: myTimeZone의 시간제
        1: myTimeZone의 hour
        2: myTimeZone의 minute
        3: myTimeZone의 도시 1
        4: myTimeZone의 도시 2
        5: myTimeZone의 도시 3
        6: anotherTimeZone의 시간제
        7: anotherTimeZone의 hour
        8: anotherTimeZone의 minute
        9: anotherTimeZone의 도시 1
        10: anotherTimeZone의 도시 2
        11: anotherTimeZone의 도시 3
        */
        //System.out.println(Arrays.toString(timeFormat));

        displays[21].display(// 내 도시의 도시들을 묶어서 전달
                "<html>" + timeFormat[3] + "<br>" + timeFormat[4] + "<br>" + timeFormat[5] + "</html>");
        displays[29].display(timeFormat[0]);// 내 도시의 시간제
        displays[22].display(timeFormat[1]);
        displays[27].display(timeFormat[2]);

        displays[23].display(// 다른 도시의 도시들을 묶어서 전달
                "<html>" + timeFormat[9] + "<br>" + timeFormat[10] + "<br>" + timeFormat[11] + "</html>");
        displays[30].display(timeFormat[6]);
        displays[24].display(timeFormat[7]);
        displays[28].display(timeFormat[8]);
    }

    public void displaySleepingTime(String[] timeformat){

        //21: 추천 수면시간 / 목표 기상시각
        //22: 추천 수면시간1(시) / 목표 기상시각(시)
        //23: 추천 수면시각2 / 최대 수면시간
        //24: 추천 수면시간2(시) / 목표 기상시(시)
        //33: 추천 수면시간(분) / 목표 기상시각(분)
        //34: 추천 수면시각2(분) / 최대 수면시간(분)

        // 21 -> 22 -> 33
        // 23 -> 24 -> 34

        displays[21].display(timeformat[0]); // 추천 수면시각1
        displays[29].display(timeformat[1]); // 추천 수면시간1 시간제
        displays[22].display(timeformat[2]); // 추천 수면시각1 (시)
        displays[27].display(timeformat[3]); // 추천 수면시각1 (분)
        displays[23].display(timeformat[4]); // 추천 수면시각2
        displays[30].display(timeformat[5]); // 추천 수면시각2 시간제
        displays[24].display(timeformat[6]); // 추천 수면시간2 (시)
        displays[28].display(timeformat[7]); // 추천 수면시각2 (분)
        displays[9].display(timeformat[8]);

    }

    public void displayShowAlarming(String currentTime){
        //String 형식은 "20 05 03 일    06:30"
        //12시간제일때는 "20 05 03 일 AM 06:30"
        displays[20].display(currentTime);
    }

    public void displayCheeringMessage(String currentTime) {
        displays[25].display(currentTime);
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

    public void displayFunctionListEdit(String value){
        displays[26].display(value);   //customize your own clock 보여줌
    }

    public void changeIconPosition(boolean left){
        int trueSelector = this.selector - 31;
        Icon curIcon, targetIcon;

        if(left){
            //icon n n-1의 위치를 바꾸고
            //n = 0이면, 0과 5
            if(trueSelector == 0){
                curIcon = labelIcons[0].getIcon();
                targetIcon = labelIcons[5].getIcon();
                labelIcons[0].setIcon(targetIcon);
                labelIcons[5].setIcon(curIcon);
            }else{
                curIcon = labelIcons[trueSelector].getIcon();
                targetIcon = labelIcons[trueSelector - 1].getIcon();
                labelIcons[trueSelector].setIcon(targetIcon);
                labelIcons[trueSelector - 1].setIcon(curIcon);
            }
        }else{
            if(trueSelector == 5){
                curIcon = labelIcons[0].getIcon();
                targetIcon = labelIcons[5].getIcon();
                labelIcons[0].setIcon(targetIcon);
                labelIcons[5].setIcon(curIcon);
            }else{
                curIcon = labelIcons[trueSelector].getIcon();
                targetIcon = labelIcons[trueSelector + 1].getIcon();
                labelIcons[trueSelector].setIcon(targetIcon);
                labelIcons[trueSelector + 1].setIcon(curIcon);
            }
        }


    }

    /**
     *
     * private ImageIcon[] getResizedIcon(){
     *
     *         icons = new ImageIcon[6];
     *         for(int i = 0 ; i < 6 ; i++){
     *             String iconFile = String.format("assets/%s.png", iconNames[i]);
     *             ImageIcon originIcon = new ImageIcon(iconFile);
     *             Image originImg = originIcon.getImage();
     *             Image changedImg= originImg.getScaledInstance(30, 30, Image.SCALE_SMOOTH );
     *             icons[i] = new ImageIcon(changedImg);
     *         }
     *         return icons;
     *
     *     }
     *
     *     private JLabel[] setIcons(ImageIcon[] icons){
     *
     *         labelIcons = new JLabel[6];
     *
     *         for(int i = 0 ; i < 6 ; i++){
     *             labelIcons[i] = new JLabel(icons[i],SwingUtilities.CENTER);
     *             labelIcons[i].setLayout(null);
     *             labelIcons[i].setBounds(15+(i*28), 250, 100, 100);
     *             //0 :15, 1: 43, 2: 71, 3:
     *             labelIcons[i].setSize(30,30);
     *             labelIcons[i].setVisible(true);
     *         }
     *         return labelIcons;
     *     }
     *
     */



    public void cleanDisplay(){
        for(int i = 0; i < 31; i++){
            segments[i].setText(null);
        }
        segments[8].setIcon(null);
    }
}
