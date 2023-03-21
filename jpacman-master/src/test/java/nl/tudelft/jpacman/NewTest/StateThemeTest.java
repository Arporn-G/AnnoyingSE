package nl.tudelft.jpacman.NewTest;

import static org.junit.Assert.assertEquals;

import nl.tudelft.jpacman.ui.PacManUI;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
public class StateThemeTest {
    @Test
    @DisplayName("StateThemeTest_dungeon")
    public void StateThemeTest_dungeon(){
        PacManUI ui = new PacManUI();
        ui.setTheme_(2);
        ui.ChoosethemePage();
        assertEquals("dungeon/state", ui.Statepage());

    }
    @Test
    @DisplayName("StateThemeTest_farm")
    public void StateThemeTest_farm(){
        PacManUI ui = new PacManUI();
        ui.setTheme_(3);
        ui.ChoosethemePage();
        assertEquals("farm/state", ui.Statepage());

    }
    @Test
    @DisplayName("StateThemeTest_pirate")
    public void StateThemeTest_pirate(){
        PacManUI ui = new PacManUI();
        ui.setTheme_(4);
        ui.ChoosethemePage();
        assertEquals("pirate/state", ui.Statepage());

    }
    @Test
    @DisplayName("StateThemeTest_default")
    public void StateThemeTest_default(){
        PacManUI ui = new PacManUI();
        ui.setTheme_(5);
        ui.ChoosethemePage();
        assertEquals("5", ui.Statepage());

    }
}
