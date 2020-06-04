package view;

import view.handler.MyMouseEvent;
import view.template.RoundJPanel;

import javax.swing.*;
import java.awt.*;

public class DisplayManager extends JFrame{

    public void timeKeepingYear(){                                   //0 : Time Keeping 연

    }
    public void timeKeepingMonth(){}                                 //1 : Time Keeping 월
    public void timeKeepingDay(){}                                   //2 : Time Keeping 일
    public void timeKeepingWeekDay(){}                               //3 : Time Keeping 요일
    public void timeKeepingAlarmAMFM(){}                             //4 : Time Keeping/Alarm AM/PM (시간제) (변경불가능)
    public void timeKeepingTimerStopWatchAlarmHour(){}               //5 : Time Keeping/Timer/Stopwatch/Alarm 시
    public void timeKeepingTimerStopWatchAlarmMinute(){}             //6 : Time Keeping/Timer/Stopwatch/Alarm 분
    public void timeKeepingTimerStopWatchSecond(){}                  //7 : Time Keeping/Timer/Stopwatch 초
    public void timerStopWatchCountingState(){}                      //8 : Timer/Stopwatch counting (시계 우상단)
    public void timerStopWatchAlarmSleepingActivationState(){}       //9 : Timer/Stopwatch/Alarm/Sleeping Time activation (시계 우하단)
    public void stopWatchLap(){}                                     //10: Stop watch의 Lap
    public void stopWatchMilliSecond(){}                             //11: Stop watch의 millisecond
    public void alarmSunday(){}                                      //12: Alarm 일
    public void alarmMonday(){}                                      //13: Alarm 월
    public void alarmTuesday(){}                                     //14: Alarm 화
    public void alarmWednesday(){}                                   //15: Alarm 수
    public void alarmThursday(){}                                    //16: Alarm 목
    public void alarmFriday(){}                                      //17: Alarm 금
    public void alarmSaturday(){}                                    //18: Alarm 토
    public void alarmNumber(){}                                      //19: Alarm 번호 (시계 좌하단)
    public void showAlarming(){}                                     //20: Alarm이 표시될 때 전부 (하나로 통합하고, 이걸 한번에 보여줌
    public void strings1(){}                                         //21: global time의 내도시 / 추천 수면시간 / 목표 기상시각
    public void strings2(){}                                         //22: global time의 내도시 시간 (시간제 포함) / 추천 수면시간1 / 목표 기상시각
    public void cityorsleepingwakeuptimedata1(){}                    //23: global time의 남의도시 / 추천 수면시각2 / 최대 수면시간
    public void cityorsleepingwakeuptimedata2(){}                    //24: global time의 남의도시 시간 (시간제 포함) / 추천 수면시간2 / 목표 기상시
    public void cheeringMessageShowAll(){}                           //25: Cheering message 전체
    public void chstomizeOwnClock(){}                                //26: function list에서 customize own clock
    public void icon1(){}                                            //27: 아이콘1 (제일 좌측)
    public void icon2(){}                                            //28: 아이콘2
    public void icon3(){}                                            //29: 아이콘3
    public void icon4(){}                                            //30: 아이콘4
    public void icon5(){}                                            //31: 아이콘5
    public void icon6(){}                                            //32: 아이콘6 (제일 우측)
    interface DisplayAction{ void display(); }
    private DisplayAction[] displays = new DisplayAction[]{
            new DisplayAction() { public void display() { timeKeepingYear();                            } },
            new DisplayAction() { public void display() { timeKeepingMonth();                           } },
            new DisplayAction() { public void display() { timeKeepingDay();                             } },
            new DisplayAction() { public void display() { timeKeepingWeekDay();                         } },
            new DisplayAction() { public void display() { timeKeepingAlarmAMFM();                       } },
            new DisplayAction() { public void display() { timeKeepingTimerStopWatchAlarmHour();         } },
            new DisplayAction() { public void display() { timeKeepingTimerStopWatchAlarmMinute();       } },
            new DisplayAction() { public void display() { timeKeepingTimerStopWatchSecond();            } },
            new DisplayAction() { public void display() { timerStopWatchCountingState();                } },
            new DisplayAction() { public void display() { timerStopWatchAlarmSleepingActivationState(); } },
            new DisplayAction() { public void display() { stopWatchLap();                               } },
            new DisplayAction() { public void display() { stopWatchMilliSecond();                       } },
            new DisplayAction() { public void display() { alarmSunday();                                } },
            new DisplayAction() { public void display() { alarmMonday();                                } },
            new DisplayAction() { public void display() { alarmTuesday();                               } },
            new DisplayAction() { public void display() { alarmWednesday();                             } },
            new DisplayAction() { public void display() { alarmThursday();                              } },
            new DisplayAction() { public void display() { alarmFriday();                                } },
            new DisplayAction() { public void display() { alarmSaturday();                              } },
            new DisplayAction() { public void display() { alarmNumber();                                } },
            new DisplayAction() { public void display() { showAlarming();                               } },
            new DisplayAction() { public void display() { strings1();                                   } },
            new DisplayAction() { public void display() { strings2();                                   } },
            new DisplayAction() { public void display() { cityorsleepingwakeuptimedata1();              } },
            new DisplayAction() { public void display() { cityorsleepingwakeuptimedata2();              } },
            new DisplayAction() { public void display() { cheeringMessageShowAll();                     } },
            new DisplayAction() { public void display() { chstomizeOwnClock();                          } },
            new DisplayAction() { public void display() { icon1();                                      } },
            new DisplayAction() { public void display() { icon2();                                      } },
            new DisplayAction() { public void display() { icon3();                                      } },
            new DisplayAction() { public void display() { icon4();                                      } },
            new DisplayAction() { public void display() { icon5();                                      } },
            new DisplayAction() { public void display() { icon6();                                      } },
    };




    int 선택자 = 0;

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
    String[] iconNames = { "timekeeping_inactive", "timer_inactive", "stop_watch_inactive", "alarm_inactive",
            "global_time_inactive", "sleeping_time_inactive" };
    ImageIcon[] icons;
    JLabel[] labelIcons;

    //Event Listener
    MyMouseEvent myMouseEvent = new MyMouseEvent("TEST");


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

        /*

        // now function
        // 날짜
        JLabel date = new JLabel("20/05/03 (SUN)");
        date.setLayout(null);
        date.setForeground(Color.WHITE);
        date.setBounds(50, 15, 150, 150);
        date.setFont(new Font("NanumSquare", Font.BOLD,22));
        date.setFont(date.getFont().deriveFont(22.f));
        date.setSize(200,50);
        date.setVisible(true);

        // AM
        JLabel clockTypeAM = new JLabel("AM");
        clockTypeAM.setLayout(null);
        clockTypeAM.setForeground(Color.WHITE);
        clockTypeAM.setBounds(30, 115, 150, 150);
        clockTypeAM.setFont(new Font("NanumSquare", Font.BOLD, 20));
        // date.setFont(date.getFont().deriveFont(20.f));
        clockTypeAM.setSize(100,50);
        clockTypeAM.setVisible(true);

        //PM
        JLabel clockTypePM = new JLabel("PM");
        clockTypePM.setLayout(null);
        clockTypePM.setForeground(Color.GRAY);
        clockTypePM.setBounds(30, 145, 150, 150);
        clockTypePM.setFont(new Font("NanumSquare", Font.BOLD, 20));
        // date.setFont(date.getFont().deriveFont(20.f));
        clockTypePM.setSize(100,50);
        clockTypePM.setVisible(true);

        // Time
        JLabel time = new JLabel("04:49");
        time.setLayout(null);
        time.setForeground(Color.WHITE);
        time.setBounds(70, 55, 200, 200);
        time.setFont(new Font("NanumSquare", Font.BOLD, 50));
        // date.setFont(date.getFont().deriveFont(20.f));
        time.setSize(200,200);
        time.setVisible(true);

        // Second
        JLabel second = new JLabel("16");
        second.setLayout(null);
        second.setForeground(Color.WHITE);
        second.setBounds(210, 145, 100, 50);
        second.setFont(new Font("NanumSquare", Font.BOLD, 20));
        // date.setFont(date.getFont().deriveFont(20.f));
        second.setSize(100,50);
        second.setVisible(true);

        innerPanel.add(date);
        innerPanel.add(clockTypeAM);
        innerPanel.add(clockTypePM);
        innerPanel.add(time);
        innerPanel.add(second);
         */

    }

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

    public void initTemplate(){
        getContentPane().setBackground(Color.WHITE);
        getContentPane().add(outerPanel);
        getContentPane().add(innerPanel);

        setOuterPanel();
        setInnerPanel();
        setButtons();

        outerPanel.add(innerPanel);

        for(JButton btn: buttons)
            getContentPane().add(btn);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);
        setLayout(null);
        setVisible(true);
    }

    // 실질적인 기능
    public void displayTimeKeeping(){

    }

    public DisplayManager() {
        super("Sleepy Clock");
        initTemplate();
    }

    public void display(String[] flags){
        //면에 표시하는 부분
    }

}
