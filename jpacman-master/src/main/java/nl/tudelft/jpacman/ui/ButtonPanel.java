package nl.tudelft.jpacman.ui;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
    ButtonPanel(final Map<String, Action> buttons, final JFrame parent) {
        super();
        assert buttons != null;
        assert parent != null;

//        Font sansSerifFont = new Font("MS Reference Sans Serif", Font.TRUETYPE_FONT, 24);
//        setBackground(Color.BLACK);

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
        setBackground(Color.BLACK);


        for (final String caption : buttons.keySet()) {
            JButton button = new JButton(caption);
            button.addActionListener(e -> {
                buttons.get(caption).doAction();
                parent.requestFocusInWindow();
            });

            button.setBackground(Color.WHITE);
            button.setFont(pixelMplusRegular);

            add(button);
        }
    }
}
