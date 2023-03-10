package nl.tudelft.jpacman;

import nl.tudelft.jpacman.ui.PacManUI;

import static org.junit.Assert.assertEquals;

import javax.swing.JButton;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class PageTest extends PacManUI {
    @BeforeEach

    @Test
    public void testHomePage() {
        PacManUI ui = new PacManUI();
        ui.Homepage();
        assertEquals("Homepage",ui.currentPage);
    }
    
    @Test
    public void testSettingPage() {
        PacManUI ui = new PacManUI();
        ui.Settingpage();
        assertEquals("Settingpage",ui.currentPage);
    }
    
    @Test
    public void testChoosestagePage() {
        PacManUI ui = new PacManUI();
        ui.ChoosestagePage();
        assertEquals("Choosestagepage",ui.currentPage);
    }
    
    @Test
    public void testChoosethemePage() {
        PacManUI ui = new PacManUI();
        ui.ChoosethemePage();
        assertEquals("Choosethemepage",ui.currentPage);
    }

}
