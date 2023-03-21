package nl.tudelft.jpacman.NewTest;

import static org.junit.Assert.assertEquals;

import nl.tudelft.jpacman.ui.PacManUI;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
public class HomePageTestTheme {
    @Test
    @DisplayName("HomePageTest_dungeon")
    public void HomePageTest_dungeon(){
        PacManUI ui = new PacManUI();
        ui.setTheme_(2);
        ui.ChoosethemePage();
        assertEquals("dungeon/home", ui.Homepage());

    }
    @Test
    @DisplayName("HomePageTest_farm")
    public void HomePageTest_farm(){
        PacManUI ui = new PacManUI();
        ui.setTheme_(3);
        ui.ChoosethemePage();
        assertEquals("farm/home", ui.Homepage());

    }
    @Test
    @DisplayName("HomePageTest_pirate")
    public void HomePageTest_pirate(){
        PacManUI ui = new PacManUI();
        ui.setTheme_(4);
        ui.ChoosethemePage();
        assertEquals("pirate/home", ui.Homepage());

    }
    @Test
    @DisplayName("HomePageTest_default")
    public void HomePageTest_default(){
        PacManUI ui = new PacManUI();
        ui.setTheme_(5);
        ui.ChoosethemePage();
        assertEquals("1", ui.Homepage());

    }
}
