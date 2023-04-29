import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.border.BevelBorder;
import java.util.Random;

import javax.swing.Timer;

public class Game extends JFrame {
    private int cnt = 0;
    public String name4;
    private String name3="";
    private int score = 0;

    JLabel timerLabel,timerlabel2,scoreLabel;
    boolean isTimerStart = false;

    public int[][] assignImages(int len) {
        int n = len * len / 2;
        int[][] imgs = new int[len][len];
        Random rnd = new Random();
        int[] arr = new int[25];
        for (int i = 1; i <= 24; i++) {
            arr[i] = i;
        }
        int ind = 0;
        for (int i = 0; i < n; i++) {
            while (true) {
                arr[ind] = 0;
                ind = rnd.nextInt(arr.length - 1);
                if (arr[ind] != 0)
                    break;
            }
            Pair pair1 = new Pair().getRandomPair(imgs);
            imgs[pair1.getX()][pair1.getY()] = ind;
            Pair pair2 = new Pair().getRandomPair(imgs);
            imgs[pair2.getX()][pair2.getY()] = ind;
        }
        return imgs;
    }
    public Game(int len , String name2) throws HeadlessException {
        this.name3=name2;
        name4=name3;
        ArrayList<ImageIcon> listOfImages = new ArrayList<ImageIcon>();
        for (int i = 0; i <= 24; i++) {
            listOfImages.add(new ImageIcon(i + ".jpg"));
        }
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        if (len == 4) {
            setTitle("EASY LEVEL");
        } else if (len == 6) {
            setTitle("HARD LEVEL");
        }
        ImageIcon timerIcon = null;
        java.net.URL imgURL1 = Game.class.getResource("timer.jpg");
        if (imgURL1 != null) {
                timerIcon = new ImageIcon(imgURL1);
                }

        timerLabel = new JLabel(timerIcon);
        timerLabel.setText("01:59");
        timerLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        timerLabel.setHorizontalAlignment(JLabel.CENTER);
        timerLabel.setBounds(70, 30, 100, 60);
        timerLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        timerLabel.setForeground(Color.WHITE);
        timerLabel.setOpaque(true);
        timerLabel.setBackground(Color.LIGHT_GRAY);
        add(timerLabel, BorderLayout.NORTH);

        ImageIcon arrowIcon = null;
        java.net.URL imgURL = Game.class.getResource("exit.jpg");
        if (imgURL != null) {
                arrowIcon = new ImageIcon(imgURL);
                }

        JButton exitButton = new JButton(arrowIcon);
        exitButton.addActionListener(e -> {
            dispose(); // Close the JFrame
            JOptionPane.showMessageDialog(this, "You chose to Quit, SCORE= "+score);
            try{

                FileWriter f=new FileWriter("account.txt",true);
                f.write("Score for user "+ name4 +"="+score); 
                f.close();
                dispose();
            }catch(Exception ae){}
        });
        exitButton.setPreferredSize(new Dimension(100, 40));
        exitButton.setBackground(Color.LIGHT_GRAY);
        exitButton.setFont(new Font("Serif", Font.BOLD, 25));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        scoreLabel = new JLabel("SCORE = " + score);
        scoreLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        scoreLabel.setBounds(0, 30, 100, 60);
        scoreLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        scoreLabel.setForeground(Color.BLACK);
        buttonPanel.add(scoreLabel);
        setResizable(true);
        setSize(600,600);
        setLocationRelativeTo(null);
        int[][] imgs = assignImages(len);
        JPanel panel = new JPanel(new GridLayout(len, len));
        JButton[][] buttons = new JButton[len][len];
        ArrayList<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setIcon(listOfImages.get(0));
                buttons[0][0].setBorder(BorderFactory.createTitledBorder("Click to reveal"));
                JButton btn = buttons[i][j];
                btn.setBorder(BorderFactory.createEtchedBorder(0));
                btn.setBackground(Color.cyan);
                int finalI = i;
                int finalJ = j;
                Pair pair = new Pair();
                btn.addActionListener(a -> {
                    if (!isTimerStart) {
                        int interval = 1000;
                        int duration = 120;
                        Timer timer = new Timer(interval, new TimerListener(duration, this));
                        timer.start();
                        isTimerStart = true;
                    }
                    btn.setIcon(listOfImages.get(imgs[finalI][finalJ]));
                    pair.setX(finalI);
                    pair.setY(finalJ);
                    pairs.add(pair);
                    cnt++;
                    if ((pairs.size() == 2) && (pairs.get(0).equals(pairs.get(1)))) {
                        cnt--;
                        
                        pairs.remove(1);
                    }
                    if (pairs.size() == 2) {
                        if ((imgs[pairs.get(0).getX()][pairs.get(0).getY()]) == (imgs[pairs.get(1).getX()][pairs.get(1)
                                .getY()])) {
                            buttons[pairs.get(0).getX()][pairs.get(0).getY()].setEnabled(false);
                            buttons[pairs.get(1).getX()][pairs.get(1).getY()].setEnabled(false);
                            pairs.remove(1);
                            pairs.remove(0);

                            score++;
                        scoreLabel.setText(" SCORE = " + String.valueOf(score));
                        } else {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            buttons[pairs.get(0).getX()][pairs.get(0).getY()].setIcon(listOfImages.get(0));
                            buttons[pairs.get(1).getX()][pairs.get(1).getY()].setIcon(listOfImages.get(0));
                            pairs.remove(1);
                            pairs.remove(0);
                            cnt -= 2;
                        }
                    }
                    if (cnt == (len * len)) {
                        this.dispose();
                        if(len==4)
                        {
                        JOptionPane.showMessageDialog(this, "Winner, SCORE=8");
                        }
                        else if(len==6)
                        {
                            JOptionPane.showMessageDialog(this, "Winner, SCORE=18");
                        }
                        JFrame alert = new JFrame("YOU WON!");
                        alert.setLocation(500, 400);
                        alert.setSize(300, 100);
                        JPanel alertPanel = new JPanel(new FlowLayout());
                        JButton newGame = new JButton("START NEW GAME");
                        newGame.addActionListener(actionEvent -> {
                            dispose();
                            new Window(name2);
                            alert.dispose();
                        });
                        
                        JButton close = new JButton("EXIT");
                        close.addActionListener(actionEvent -> {
                            dispose();
                            alert.dispose();
                        });
                        alertPanel.add(newGame);
                        alertPanel.add(close);
                        panel.setBackground(Color.BLACK);
                        alertPanel.setOpaque(false);
                        alertPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                        alertPanel.setBorder(BorderFactory.createEtchedBorder());
                        alertPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                        alertPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Winner"));
                        alert.add(alertPanel);
                        alert.setResizable(false);
                        alert.setVisible(true);
                    }
                });
                panel.add(buttons[i][j]);
            }
        }

        add(panel);
        setVisible(true);
    }


    // -------------------------------

    class TimerListener implements ActionListener {
        private int counter;
        private Game game;

        public TimerListener(int duration, Game game) {
            this.counter = duration;
            this.game = game;
        }

        public void actionPerformed(ActionEvent e) {
            if (counter > 0) {
                counter--;
                int minutes = counter / 60;
                int seconds = counter % 60;
                timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
            } else {

                Object[] options = { "EXIT", "PLAY AGAIN" };
                int choice = JOptionPane.showOptionDialog(game,
                        "GAME OVER! DO YOU WANT TO PLAY AGAIN?",
                        "GAME OVER",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[1]);

                if (choice == JOptionPane.YES_OPTION) {
                    game.dispose(); // close the JFrame
                } else if (choice == JOptionPane.NO_OPTION) {
                    game.setVisible(false); // hide the JFrame
                    game.dispose(); // dispose the JFrame
                    new Window(name3); // restart the application
                }
                ((Timer) e.getSource()).stop();
            }
        }
    }
}

