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
        setTitle("JPacman");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        ImageIcon imp = new ImageIcon("./src/main/resources/sprite/1.png");
        JLabel j = new JLabel(imp);
        setContentPane(j);
        revalidate();

        // Just for refresh :) Not optional!
        JButton settingpage = new JButton();
        settingpage.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                j.setIcon(null);
                Settingpage();

            }
        });
        settingpage.setBounds(249,329,100,45);
        settingpage.setOpaque(false);
        settingpage.setContentAreaFilled(false);
        settingpage.setBorderPainted(false);
        add(settingpage);

        JButton exit = new JButton();
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

            }
        });

        exit.setBounds(249,405,100,45);
        exit.setOpaque(false);
        exit.setContentAreaFilled(false);
        exit.setBorderPainted(false);
        add(exit);

        JButton PlayGame = new JButton();
        PlayGame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                GamePage();
                remove(settingpage);
                remove(exit);
                remove(PlayGame);
            }
        });
        PlayGame.setBounds(190,268,210,30);
        PlayGame.setOpaque(false);
        PlayGame.setContentAreaFilled(false);
        PlayGame.setBorderPainted(false);
        add(PlayGame);
        setSize(600,600);

    }

    public void Settingpage(){
        setTitle("JPacman");
        ImageIcon imp = new ImageIcon("./src/main/resources/sprite/stbg.png");
        JLabel j1 = new JLabel(imp);
        setContentPane(j1);

        // Just for refresh :) Not optional!

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


    }

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

        Container contentPanel = getContentPane();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(this.buttonPanel, BorderLayout.SOUTH);
        contentPanel.add(this.scorePanel, BorderLayout.NORTH);
        contentPanel.add(this.boardPanel, BorderLayout.CENTER);
        pack();
    }
    public void ChoosestagePage(){

        setTitle("JPacman");
        ImageIcon imp3 = new ImageIcon("./src/main/resources/sprite/3.png");
        JLabel j3 = new JLabel(imp3);
        setContentPane(j3);
        setSize(600,600);

        JButton backtosetting = new JButton();
        backtosetting.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Settingpage();

            }
        });
        backtosetting.setBounds(237,444,110,50);
        backtosetting.setOpaque(false);
        backtosetting.setContentAreaFilled(false);
        backtosetting.setBorderPainted(false);
        add(backtosetting);
    }
    public void ChoosethemePage(){

        setTitle("JPacman");
        ImageIcon imp4 = new ImageIcon("./src/main/resources/sprite/4.png");
        JLabel j4 = new JLabel(imp4);
        setContentPane(j4);
        setSize(600,600);

        JButton backtosetting = new JButton();
        backtosetting.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Settingpage();
            }
        });
        backtosetting.setBounds(237,444,110,50);
        backtosetting.setOpaque(false);
        backtosetting.setContentAreaFilled(false);
        backtosetting.setBorderPainted(false);
        add(backtosetting);
    }




}
