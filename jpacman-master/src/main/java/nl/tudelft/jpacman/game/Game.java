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
        JLabel label = new JLabel("End");
        String score = ScorePanel.DEFAULT_SCORE_FORMATTER.format(getPlayers().get(0));
        JLabel label2 = new JLabel( "Your Final " + score + " !");
        JButton okButton = new JButton("Restart");
        JButton cancelButton = new JButton("Home");


        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] {0, 0, 0};
        gridBagLayout.rowHeights = new int[] {0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[] {0.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[] {0.0, 0.0, 0.0, Double.MIN_VALUE};
        frame.getContentPane().setLayout(gridBagLayout);

        GridBagConstraints gbc_label1 = new GridBagConstraints();
        gbc_label1.insets = new Insets(5, 0, 0, 0);
        gbc_label1.gridx = 1;
        gbc_label1.gridy = 1;
        frame.getContentPane().add(label, gbc_label1);

        GridBagConstraints gbc_label2 = new GridBagConstraints();
        gbc_label2.insets = new Insets(0, 0, 0, 0);
        gbc_label2.gridx = 1;
        gbc_label2.gridy = 2;
        frame.getContentPane().add(label2, gbc_label2);

        GridBagConstraints gbc_btn1 = new GridBagConstraints();
        gbc_btn1.insets = new Insets(0, 10, 0, 0);
        gbc_btn1.gridx = 0;
        gbc_btn1.gridy = 3;
        frame.getContentPane().add(okButton, gbc_btn1);

        GridBagConstraints gbc_btn2 = new GridBagConstraints();
        gbc_btn2.insets = new Insets(0, 0, 0, 0);
        gbc_btn2.gridx = 2;
        gbc_btn2.gridy = 3;
        frame.getContentPane().add(cancelButton, gbc_btn2);


//        panel.add(label);
//        panel.add(label2);
//        panel.add(okButton);
//        panel.add(cancelButton);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        frame.setSize(300, 300);
        frame.setVisible(true);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                Launcher.dispose();
                new Launcher().launch(false);

            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                Launcher.dispose();
                new Launcher().launch(true);



            }
        });
         // Display the frame as a pop-up window

    }

}
