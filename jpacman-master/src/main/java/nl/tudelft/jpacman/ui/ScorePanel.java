package nl.tudelft.jpacman.ui;

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;
/**
 * add new import
 */
import java.io.IOException;
import java.io.InputStream;

import nl.tudelft.jpacman.level.Player;

/**
 * A panel consisting of a column for each player, with the numbered players on
 * top and their respective scores underneath.
 *
 * @author Jeroen Roosen
 *
 */
public class ScorePanel extends JPanel {
    /**
     * Default serialisation ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The map of players and the labels their scores are on.
     */
    private final Map<Player, JLabel> scoreLabels;

    /**
     * The default way in which the score is shown.
     */
    public static final ScoreFormatter DEFAULT_SCORE_FORMATTER =
        (Player player) -> String.format("Score: %3d", player.getScore());

    /**
     * The way to format the score information.
     */
    private ScoreFormatter scoreFormatter = DEFAULT_SCORE_FORMATTER;

    /**
     * Creates a new score panel with a column for each player.
     *
     * @param players
     *            The players to display the scores of.
     */
    Font pixelMplusRegular, pixelMplusBold;
    public ScorePanel(List<Player> players) {
        super();
        assert players != null;

        /**
         * edit rows:2 --> 4
         * add new
         * */
        int rows = 4, cols = players.size();
//        setLayout(new GridLayout(2, players.size()));
        setLayout(new GridLayout(rows, cols, 0, 0));
//        setBounds(300, 200, 600, 600);

        try {
            InputStream is = getClass().getResourceAsStream("/fonts/PixelMplus10-Regular.ttf");
            pixelMplusRegular = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(24F);
            is = getClass().getResourceAsStream("/fonts/PixelMplus10-Bold.ttf");
            pixelMplusBold = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(24F);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        }
//        pixelMplus = new Font("pixelMplus", Font.TRUETYPE_FONT, 24);

        setBackground(Color.BLACK);

        for (int i = 1; i <= cols; i++) {

            JLabel spaceLabel = new JLabel("");
            spaceLabel.setForeground(Color.WHITE);
            add(spaceLabel);
        }


        for (int i = 1; i <= players.size(); i++) {
//            add(new JLabel("Player " + i, JLabel.CENTER));
            JLabel playerTextLabel = new JLabel("Player " + i, JLabel.LEFT);
            playerTextLabel.setForeground(Color.WHITE);
            playerTextLabel.setFont(pixelMplusRegular);
            add(playerTextLabel);
        }

        scoreLabels = new LinkedHashMap<>();
        for (Player player : players) {
//            JLabel scoreLabel = new JLabel("0", JLabel.CENTER);
            JLabel scoreTextLabel = new JLabel("0", JLabel.LEFT);
            scoreTextLabel.setForeground(Color.WHITE);
            scoreTextLabel.setFont(pixelMplusRegular);
            scoreLabels.put(player, scoreTextLabel);
            add(scoreTextLabel);
        }

        for (int i = 1; i <= cols; i++) {

            JLabel spaceLabel = new JLabel("");
            spaceLabel.setForeground(Color.WHITE);
            add(spaceLabel);
        }
    }

    /**
     * Refreshes the scores of the players.
     */
    protected void refresh() {
        for (Map.Entry<Player, JLabel> entry : scoreLabels.entrySet()) {
            Player player = entry.getKey();
            String score = "";
            if (!player.isAlive()) {
                score = "You died. ";
            }
            score += scoreFormatter.format(player);
            entry.getValue().setText(score);
        }
    }

    /**
     * Provide means to format the score for a given player.
     */
    public interface ScoreFormatter {

        /**
         * Format the score of a given player.
         * @param player The player and its score
         * @return Formatted score.
         */
        String format(Player player);
    }

    /**
     * Let the score panel use a dedicated score formatter.
     * @param scoreFormatter Score formatter to be used.
     */
    public void setScoreFormatter(ScoreFormatter scoreFormatter) {
        assert scoreFormatter != null;
        this.scoreFormatter = scoreFormatter;
    }
}
