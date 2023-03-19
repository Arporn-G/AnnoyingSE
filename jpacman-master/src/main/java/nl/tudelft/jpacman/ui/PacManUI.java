package nl.tudelft.jpacman.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.game.GameFactory;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.sprite.PacManSprites;
import nl.tudelft.jpacman.ui.ScorePanel.ScoreFormatter;

/**
 * The default JPacMan UI frame. The PacManUI consists of the following
 * elements:
 *
 * <ul>
 * <li>A score panel at the top, displaying the score of the player(s).
 * <li>A board panel, displaying the current level, i.e. the board and all units
 * on it.
 * <li>A button panel, containing all buttons provided upon creation.
 * </ul>
 *
 * @author Jeroen Roosen
 *
 */
public class PacManUI extends JFrame {

    /**
     * Default serialisation UID.
     */
    private static final long serialVersionUID = 1L;

    private static int theme_num = 1;

    /**
     * The desired frame rate interval for the graphics in milliseconds, 40
     * being 25 fps.
     */
    private static final int FRAME_INTERVAL = 40;

    /**
     * The panel displaying the player scores.
     */
    private ScorePanel scorePanel;

    private BoardPanel boardPanel;

    private ButtonPanel buttonPanel;
    private Game game;
    private PacKeyListener keys;

    public String currentPage;
    /**
     * Creates a new UI for a JPacman game.
     *
     */

    public PacManUI(){};
    public PacManUI(final Game game, final Map<String, Action> buttons,
                    final Map<Integer, Action> keyMappings,
                    ScoreFormatter scoreFormatter) {
        super("JPacman");
        assert game != null;
        assert buttons != null;
        assert keyMappings != null;
        this.game = game;
        this.boardPanel = new BoardPanel(game);
        this.keys = new PacKeyListener(keyMappings);
        addKeyListener(keys);
        this.setResizable(false);
        this.buttonPanel = new ButtonPanel(buttons, this);

        this.scorePanel = new ScorePanel(game.getPlayers());
        if (scoreFormatter != null) {
            this.scorePanel.setScoreFormatter(scoreFormatter);
        }

        this.boardPanel = new BoardPanel(game);

        /*Container contentPanel = getContentPane();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);
        contentPanel.add(scorePanel, BorderLayout.NORTH);
        contentPanel.add(boardPanel, BorderLayout.CENTER);
        pack();*/
        JFrame jf = new JFrame();
        Homepage();

    }

    public void start() {
        setVisible(true);
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(this::nextFrame, 0, FRAME_INTERVAL, TimeUnit.MILLISECONDS);

    }

    public void restart() {
        setVisible(true);
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(this::nextFrame, 0, FRAME_INTERVAL, TimeUnit.MILLISECONDS);
        GamePage();
    }

    private void nextFrame() {
        boardPanel.repaint();
        scorePanel.refresh();
    }

    public  void Homepage(){
        currentPage = "Homepage";
        setTitle("JPacman");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        JButton settingpage = new JButton();
        JButton exit = new JButton();
        JButton PlayGame = new JButton();

        //    private PacManSprites setSprites;
        String themeHome;
        if(theme_num == 2) {
            themeHome = "dungeon/home"; /*dun*/
            settingpage.setBounds(184, 320, 228, 78);
            exit.setBounds(184, 402, 228, 78);
            PlayGame.setBounds(184, 232, 228, 78);
        }
        else if(theme_num ==3) {
            themeHome = "farm/home"; /*fram*/
            settingpage.setBounds(214, 320, 180, 70);
            exit.setBounds(214, 394, 180, 70);
            PlayGame.setBounds(214, 252, 180, 70);
        }
        else if(theme_num ==4) {
            themeHome = "pirate/home"; /*pirate*/
            settingpage.setBounds(110, 329, 150, 60);
            exit.setBounds(280, 329, 150, 60);
            PlayGame.setBounds(120, 228, 230, 75);
        }
        else {
            themeHome = "1"; /*default*/
            settingpage.setBounds(249, 329, 100, 45);
            exit.setBounds(249, 405, 100, 45);
            PlayGame.setBounds(190, 268, 210, 30);
        }

        ImageIcon imp = new ImageIcon("./src/main/resources/sprite/"+ themeHome +".png");
        JLabel j = new JLabel(imp);
        setContentPane(j);
        revalidate();

        // Just for refresh :) Not optional!

        settingpage.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                j.setIcon(null);
                ChoosethemePage();

            }
        });

        settingpage.setOpaque(false);
        settingpage.setContentAreaFilled(false);
        settingpage.setBorderPainted(false);
        add(settingpage);

        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

            }
        });

        exit.setOpaque(false);
        exit.setContentAreaFilled(false);
        exit.setBorderPainted(false);
        add(exit);

        PlayGame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Statepage();
                remove(settingpage);
                remove(exit);
                remove(PlayGame);
            }
        });
        PlayGame.setOpaque(false);
        PlayGame.setContentAreaFilled(false);
        PlayGame.setBorderPainted(false);
        add(PlayGame);
        setSize(600,600);
        this.setLocationRelativeTo(null);

    }

    public void Statepage() {
        currentPage = "Statepage";
        setTitle("JPacman");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        JButton stateone = new JButton();
        JButton statetwo = new JButton();
        JButton statethree = new JButton();
        JButton statefour = new JButton();
        JButton statefive = new JButton();
        JButton home = new JButton();

        String stateHome;
        if (theme_num == 2) {
            stateHome = "dungeon/stage"; /*dun*/
            stateone.setBounds(368, 540, 100, 40);
            statetwo.setBounds(35, 375, 100, 40);
            statethree.setBounds(466, 375, 100, 40);
            statefour.setBounds(35, 215, 100, 40);
            statefive.setBounds(466, 215, 100, 40);
            home.setBounds(10, 450, 125, 40);
        } else if (theme_num == 3) {
            stateHome = "farm/stage"; /*fram*/
            stateone.setBounds(23, 355, 100, 120);
            statetwo.setBounds(160, 350, 100, 120);
            statethree.setBounds(240, 470, 100, 120);
            statefour.setBounds(289, 267, 100, 120);
            statefive.setBounds(435, 265, 100, 120);
            home.setBounds(10, 500, 153, 53);
        } else if (theme_num == 4) {
            stateHome = "pirate/state"; /*pirate*/
            stateone.setBounds(75, 40, 155, 163);
            statetwo.setBounds(414, 28, 144, 188);
            statethree.setBounds(245, 190, 155, 163);
            statefour.setBounds(30, 300, 155, 163);
            statefive.setBounds(423, 320, 149, 266);
            home.setBounds(240, 500, 113, 58);
        } else {
            stateHome = "5"; /*default*/
            stateone.setBounds(100, 20, 150, 150);
            statetwo.setBounds(340, 40, 150, 150);
            statethree.setBounds(155, 200, 150, 150);
            statefour.setBounds(120, 390, 150, 150);
            statefive.setBounds(390, 300, 150, 150);
            home.setBounds(409, 520, 113, 58);
        }

        ImageIcon imp = new ImageIcon("./src/main/resources/sprite/" + stateHome + ".png");
        JLabel j = new JLabel(imp);
        setContentPane(j);
        revalidate();

        stateone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTheme_(theme_num);
                dispose();
                new Launcher().launch(1);
            }
        });
        stateone.setOpaque(false);
        stateone.setContentAreaFilled(false);
        stateone.setBorderPainted(false);
        add(stateone);

        statetwo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTheme_(theme_num);;
                dispose();
                new Launcher().launch(2);
            }
        });
        statetwo.setOpaque(false);
        statetwo.setContentAreaFilled(false);
        statetwo.setBorderPainted(false);
        add(statetwo);
        statethree.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setTheme_(theme_num);
                dispose();
                new Launcher().launch(3);
            }
        });
        statethree.setOpaque(false);
        statethree.setContentAreaFilled(false);
        statethree.setBorderPainted(false);
        add(statethree);

        statefour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTheme_(theme_num);
                dispose();
                new Launcher().launch(4);
            }
        });
        statefour.setOpaque(false);
        statefour.setContentAreaFilled(false);
        statefour.setBorderPainted(false);
        add(statefour);

        statefive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTheme_(theme_num);
                dispose();
                new Launcher().launch(5);
            }
        });
        statefive.setOpaque(false);
        statefive.setContentAreaFilled(false);
        statefive.setBorderPainted(false);
        add(statefive);
        home.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Homepage();
            }
        });
        home.setOpaque(false);
        home.setContentAreaFilled(false);
        home.setBorderPainted(false);
        add(home);
    }



    /*
    public void Settingpage(){
        currentPage = "Settingpage";
        setTitle("JPacman");
        ImageIcon imp = new ImageIcon("./src/main/resources/sprite/4.png");
        JLabel j1 = new JLabel(imp);
        setContentPane(j1);
        // Just for refresh :) Not optional!
    /*
        JButton stateBtn = new JButton();
        stateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==stateBtn) {
                    j1.setIcon(null);
                    ChoosestagePage();
                }
            }
        });
        stateBtn.setBounds(150,160,280,50);
        stateBtn.setOpaque(false);
        stateBtn.setContentAreaFilled(false);
        stateBtn.setBorderPainted(false);
        this.add(stateBtn);
        JButton themeBtn = new JButton();
        themeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==themeBtn) {
                    j1.setIcon(null);
                    ChoosethemePage();
                }
            }
        });
        themeBtn.setBounds(150,260,280,50);
        themeBtn.setOpaque(false);
        themeBtn.setContentAreaFilled(false);
        themeBtn.setBorderPainted(false);
        this.add(themeBtn);
        JButton backBtn = new JButton();
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Homepage();
            }
        });
        backBtn.setBounds(220,360,140,60);
        backBtn.setOpaque(false);
        backBtn.setContentAreaFilled(false);
        backBtn.setBorderPainted(false);
        this.add(backBtn);
        // set frame properties
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setVisible(true);
    }*/

    public void GamePage() {
        /*getContentPane().removeAll();
        validate();
        setTitle("JPacman");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setSize(400,400);
        setBackground(Color.WHITE);
        JButton backtohome1 = new JButton("Back");
        backtohome1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Homepage();
            }
        });
        backtohome1.setBounds(253,250,80,30);
        add(backtohome1);
        JLabel label2 = new JLabel("Game Page");
        label2.setBounds(153,150,100,30);
        add(label2);*/
        currentPage = "Gamepage";
        /*
        setTitle("JPacman");
        ImageIcon img5 = new ImageIcon("../resources/sprite/game_page_north.png");
        JLabel j4 = new JLabel(img5);
        setContentPane(j4);
        setSize(600, 50);
        setVisible(true);
        */

        /*
        JFrame frame = new JFrame();
        frame.setSize(800,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.add(this.boardPanel, BorderLayout.CENTER);
        frame.add(this.scorePanel, BorderLayout.NORTH);
        frame.add(this.buttonPanel, BorderLayout.SOUTH);
        frame.setTitle("JPACMAN");
        frame.setVisible(true);
         */


        Container contentPanel = getContentPane();
        contentPanel.setLayout(new BorderLayout());
//        contentPanel.setSize(800, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon imp = new ImageIcon("./src/main/resources/sprite/farm/home.png");
        JLabel j = new JLabel(imp);
//        this.setLocationRelativeTo(null);
//        this.setResizable(false);
        contentPanel.add(this.buttonPanel, BorderLayout.SOUTH);
        contentPanel.add(this.scorePanel, BorderLayout.NORTH);
        contentPanel.add(this.boardPanel, BorderLayout.CENTER);

        pack();
    }
    /*
    public void ChoosestagePage(){
        currentPage = "Choosestagepage";
        setTitle("JPacman");
        ImageIcon imp3 = new ImageIcon("./src/main/resources/sprite/4.png");
        JLabel j3 = new JLabel(imp3);
        setContentPane(j3);
        setSize(600,600);
        JButton backtosetting = new JButton();
        backtosetting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Homepage();
            }
        });
        backtosetting.setBounds(237,444,110,50);
        backtosetting.setOpaque(false);
        backtosetting.setContentAreaFilled(false);
        backtosetting.setBorderPainted(false);
        add(backtosetting);
    }*/
    public void ChoosethemePage(){
        currentPage = "Choosethemepage";
        setTitle("JPacman");
        ImageIcon imp4 = new ImageIcon("./src/main/resources/sprite/4.png");
        JLabel j4 = new JLabel(imp4);
        setContentPane(j4);
        setSize(600,600);
        JButton theme1 = new JButton();
        JButton theme2 = new JButton();
        JButton theme3 = new JButton();
        JButton theme4 = new JButton();

        theme1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setTheme_(1);
                Homepage();

            }
        });
        theme1.setBounds(100,90,150,150);
        theme1.setOpaque(false);
        theme1.setContentAreaFilled(false);
        theme1.setBorderPainted(false);
        add(theme1);
        theme2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setTheme_(2);
                Homepage();

            }
        });
        theme2.setBounds(340,90,150,150);
        theme2.setOpaque(false);
        theme2.setContentAreaFilled(false);
        theme2.setBorderPainted(false);
        add(theme2);
        theme3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setTheme_(3);
                dispose();
                new Launcher().launch(true);
            }
        });
        theme3.setBounds(100,260,150,150);
        theme3.setOpaque(false);
        theme3.setContentAreaFilled(false);
        theme3.setBorderPainted(false);
        add(theme3);
        theme4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setTheme_(4);
                Homepage();

            }
        });
        theme4.setBounds(340,260,150,150);
        theme4.setOpaque(false);
        theme4.setContentAreaFilled(false);
        theme4.setBorderPainted(false);
        add(theme4);

        JButton backtosetting = new JButton();
        backtosetting.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Homepage();
            }
        });
        backtosetting.setBounds(237,444,110,50);
        backtosetting.setOpaque(false);
        backtosetting.setContentAreaFilled(false);
        backtosetting.setBorderPainted(false);
        add(backtosetting);
    }
    public static int getTheme_() {
        return theme_num;
    }

    public static void setTheme_(int theme_num) {
        PacManUI.theme_num = theme_num;
    }




}
