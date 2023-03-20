package nl.tudelft.jpacman.ui;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.swing.*;

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

    private boolean over;
    private Color color, colorOver, colorClick, borderColor;
    private int radius = 0;

    public boolean isOver() {
        return over;
    }
    public void setOver(boolean over) {
        this.over = over;
    }

    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColorOver() {
        return colorOver;
    }
    public void setColorOver(Color colorOver) {
        this.colorOver = colorOver;
    }

    public Color getColorClick() {
        return colorClick;
    }
    public void setColorClick(Color colorClick) {
        this.colorClick = colorClick;
    }

    public Color getBorderColor() {
        return borderColor;
    }
    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public int getRadius() {
        return radius;
    }
    public void setRadius(int radius) {
        this.radius = radius;
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
        over = false;
        color = new Color(33, 239, 128);
        colorOver = new Color(244, 131, 163);

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
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent me) {
//                    super.mouseEntered(me);
//                    button.setBackground(colorOver);
                    over = true;
                }

                @Override
                public void mouseExited(MouseEvent e) {
//                    super.mouseExited(e);
//                    button.setBackground(color);
                    over = false;
                }
            });

            add(button);
        }
    }
    public void mouseEntered(MouseEvent evt) {

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
}
