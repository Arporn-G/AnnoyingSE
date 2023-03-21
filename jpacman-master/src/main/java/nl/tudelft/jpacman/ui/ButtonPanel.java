package nl.tudelft.jpacman.ui;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

/**
 * A panel containing a button for every registered action.
 *
 * @author Jeroen Roosen 
 */
class ButtonPanel extends JPanel {

    /**
     * Default serialisation ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Create a new button panel with a button for every action.
     * @param buttons The map of caption - action for each button.
     * @param parent The parent frame, used to return window focus.
     */
    Font pixelMplusRegular, pixelMplusBold;
    private Image background;
    PacManUI pacManUI;

    private boolean over = false;

    public boolean isOver() {
        return this.over;
    }
    public void setOver(boolean over) {
        this.over = over;
    }

    ButtonPanel(final Map<String, Action> buttons, final JFrame parent) {
        super();
        assert buttons != null;
        assert parent != null;

//        Font sansSerifFont = new Font("MS Reference Sans Serif", Font.TRUETYPE_FONT, 24);
        setPreferredSize(new Dimension(600,100));
//        setBounds(300, 200, 600, 600);
        String path = "";
        if (pacManUI.getTheme_() == 2){
            path = "/dungeon";
        }else if(pacManUI.getTheme_() == 3){
            path = "/farm";
        }else if(pacManUI.getTheme_() == 4){
            path = "/pirate";
        }else {
            path = "";
        }

        background = new ImageIcon("./src/main/resources/sprite"+path+"/button.png").getImage();
//        over = false;
//        color = new Color(33, 239, 128);
//        colorOver = new Color(244, 131, 163);

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
        this.setBackground(Color.BLACK);

        for (final String caption : buttons.keySet()) {
            JButton button = new JButton(caption);
            button.addActionListener(e -> {
                buttons.get(caption).doAction();
                parent.requestFocusInWindow();
            });

//            button.setBackground(color);
            button.setFont(pixelMplusRegular);

            if (pacManUI.getTheme_() == 2){
                button.setBackground(new Color(92,92,86));
                button.setBorder(new LineBorder(new Color(52,52,46), 4));
                button.setForeground(new Color(240,204,3));

            }else if(pacManUI.getTheme_() == 3){
//                button.setBackground(new Color(179,215,67));
//                button.setBorder(new LineBorder(new Color(201,91,9), 4));
//                button.setForeground(new Color(251,165,36));
                button.setBackground(new Color(198,63,59));
                button.setBorder(new LineBorder(new Color(243,243,243), 4));
                button.setForeground(new Color(243,243,243));
            }else if(pacManUI.getTheme_() == 4){
                button.setBackground(new Color(255,186,113));
                button.setBorder(new LineBorder(new Color(167,85,0), 4));
                button.setForeground(new Color(167,85,0));
            }else {
                button.setBackground(new Color(132,136,244));
                button.setBorder(new LineBorder(new Color(24,242,123), 4));
                button.setForeground(Color.WHITE);
            }

            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent me) {
                    super.mouseEntered(me);
                    if (pacManUI.getTheme_() == 2){
                        button.setBackground(new Color(52,52,46));
                        button.setBorder(new LineBorder(new Color(92,92,86), 4));
                        button.setForeground(new Color(235,225,9));

                    }else if(pacManUI.getTheme_() == 3){
//                        button.setBackground(new Color(255,204,108));
//                        button.setBorder(new LineBorder(new Color(78,127,28), 4));
//                        button.setForeground(new Color(120,129,4));
                        button.setBackground(new Color(243,243,243));
                        button.setBorder(new LineBorder(new Color(198,63,59), 4));
                        button.setForeground(new Color(198,63,59));
                    }else if(pacManUI.getTheme_() == 4){
                        button.setBackground(new Color(167,85,0));
                        button.setBorder(new LineBorder(new Color(255,186,113), 4));
                        button.setForeground(new Color(255,186,113));
                    }else {
                        button.setBackground(new Color(24,242,123));
                        button.setBorder(new LineBorder(new Color(132,136,244), 4));
                        button.setForeground(Color.BLACK);
                    }
//                    button.setBackground(colorOver);
                    setOver(true);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    if (pacManUI.getTheme_() == 2){
                        button.setBackground(new Color(92,92,86));
                        button.setBorder(new LineBorder(new Color(52,52,46), 4));
                        button.setForeground(new Color(240,204,3));

                    }else if(pacManUI.getTheme_() == 3){
//                        button.setBackground(new Color(179,215,67));
//                        button.setBorder(new LineBorder(new Color(201,91,9), 4));
//                        button.setForeground(new Color(251,165,36));
                        button.setBackground(new Color(198,63,59));
                        button.setBorder(new LineBorder(new Color(243,243,243), 4));
                        button.setForeground(new Color(243,243,243));
                    }else if(pacManUI.getTheme_() == 4){
                        button.setBackground(new Color(255,186,113));
                        button.setBorder(new LineBorder(new Color(167,85,0), 4));
                        button.setForeground(new Color(167,85,0));
                    }else {
                        button.setBackground(new Color(132,136,244));
                        button.setBorder(new LineBorder(new Color(24,242,123), 4));
                        button.setForeground(Color.WHITE);
                    }
//                    button.setBackground(color);
                    setOver(false);
                }
            });

            add(button);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
}
