package nl.tudelft.jpacman.game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Level.LevelObserver;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.points.PointCalculator;
import nl.tudelft.jpacman.ui.PacManUI;
import nl.tudelft.jpacman.ui.PacManUiBuilder;
import nl.tudelft.jpacman.ui.ScorePanel;

import javax.swing.*;

/**
 * A basic implementation of a Pac-Man game.
 *
 * @author Jeroen Roosen
 */
public abstract class Game implements LevelObserver {
    PacManUI theme_num;
    /**
     * <code>true</code> if the game is in progress.
     */
    private boolean inProgress;

    /**
     * Object that locks the start and stop methods.
     */
    private final Object progressLock = new Object();

    /**
     * The algorithm used to calculate the points that
     * they player gets whenever some action happens.
     */
    private PointCalculator pointCalculator;

    /**
     * Creates a new game.
     *
     * @param pointCalculator
     *             The way to calculate points upon collisions.
     */
    protected Game(PointCalculator pointCalculator) {
        this.pointCalculator = pointCalculator;
        inProgress = false;
    }

    /**
     * Starts or resumes the game.
     */
    public void start() {
        synchronized (progressLock) {
            if (isInProgress()) {
                return;
            }
            if (getLevel().isAnyPlayerAlive() && getLevel().remainingPellets() > 0) {
                inProgress = true;
                getLevel().addObserver(this);
                getLevel().start();
            }
        }
    }

    /**
     * Pauses the game.
     */
    public void stop() {
        synchronized (progressLock) {
            if (!isInProgress()) {
                return;
            }
            inProgress = false;
            getLevel().stop();
        }
    }

    /**
     * @return <code>true</code> iff the game is started and in progress.
     */
    public boolean isInProgress() {
        return inProgress;
    }

    /**
     * @return An immutable list of the participants of this game.
     */
    public abstract List<Player> getPlayers();

    /**
     * @return The level currently being played.
     */
    public abstract Level getLevel();

    /**
     * Moves the specified player one square in the given direction.
     *
     * @param player
     *            The player to move.
     * @param direction
     *            The direction to move in.
     */
    public void move(Player player, Direction direction) {
        if (isInProgress()) {
            // execute player move.
            getLevel().move(player, direction);
            pointCalculator.pacmanMoved(player, direction);
        }
    }

    @Override
    public void levelWon() {
        stop();
    }

    @Override
    public void levelLost() {
        PacManUI p = new PacManUI();
        stop();
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        frame.setBounds(0, 0, 300, 300);
//        JLabel label = new JLabel("End");
        String score = ScorePanel.DEFAULT_SCORE_FORMATTER.format(getPlayers().get(0));
//        JLabel label2 = new JLabel( "Your Final " + score + " !");
        JButton retryButton =new JButton();
        JButton homeButton = new JButton();
        //JButton NextStateButton = new JButton("NextState");
        retryButton.setBounds(80, 80, 135, 65);
        retryButton.setOpaque(false);
        retryButton.setContentAreaFilled(false);
        retryButton.setBorderPainted(false);

        //NextStateButton.setBounds(100, 200, 60, 60);
        //NextStateButton.setOpaque(true);
        //NextStateButton.setContentAreaFilled(true);
        //NextStateButton.setBorderPainted(true);

        homeButton.setBounds(80, 160, 135, 65);
        homeButton.setOpaque(false);
        homeButton.setContentAreaFilled(false);
        homeButton.setBorderPainted(false);
        String path = "";
        if (theme_num.getTheme_() == 2){
            path = "/dungeon";
        }
        else if (theme_num.getTheme_() == 3) {
            path = "/farm";
        }
        else if (theme_num.getTheme_() == 4) {
            path = "/pirate";
        }
        else {
            path = "";
        }
        ImageIcon imp = new ImageIcon("./src/main/resources/sprite"+ path +"/end.png");
        JLabel j = new JLabel(imp);
        frame.setContentPane(j);
        frame.add(retryButton);
        frame.add(homeButton);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        frame.setSize(300, 300);
        frame.setVisible(true);
        retryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                Launcher.dispose();
                new Launcher().launch(false);

            }
        });

        //NextStateButton.addActionListener(new ActionListener() {
        //    public void actionPerformed(ActionEvent e) {
        //        frame.setVisible(false);
        //        Launcher.dispose();
        //        new Launcher().launch(true);



        //    }
       // });
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                Launcher.dispose();
                new Launcher().launch(true);



            }
        });
         // Display the frame as a pop-up window

    }

}
