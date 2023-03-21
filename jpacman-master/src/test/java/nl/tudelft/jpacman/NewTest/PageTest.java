package nl.tudelft.jpacman.NewTest;

import static org.junit.Assert.assertEquals;

import nl.tudelft.jpacman.ui.PacManUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PageTest extends PacManUI {
    @BeforeEach

    @Test
    public void testHomePage() {
        PacManUI ui = new PacManUI();
        ui.Homepage();
        assertEquals("Homepage",ui.currentPage);
    }
    
//    @Test
//    public void testSettingPage() {
//        PacManUI ui = new PacManUI();
//        ui.settingpage();
//        assertEquals("Settingpage",ui.currentPage);
//    }
    
//    @Test
//    public void testChoosestagePage() {
//        PacManUI ui = new PacManUI();
//        ui.ChoosethemePage();
//        assertEquals("Choosestagepage",ui.currentPage);
//    }
    
    @Test
    public void testChoosethemePage() {
        PacManUI ui = new PacManUI();
        ui.ChoosethemePage();
        assertEquals("Choosethemepage",ui.currentPage);
    }

}
