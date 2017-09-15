import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by pmatos9 on 24/05/17.
 */
public class MainTest {
    @Test
    public void portIsOpen() throws Exception {
        boolean result = true;
        result = Main.portIsOpen("localhost",8080,20);
        assertTrue(result);
        result = Main.portIsOpen("localhost",9300,20);
        assertTrue(result);
        result = Main.portIsOpen("localhost",5000,20);
        assertTrue(result);
        result = Main.portIsOpen("localhost",5601,20);
        assertTrue(result);
        result = Main.portIsOpen("localhost",5432,20);
        assertTrue(result);
        result = Main.portIsOpen("localhost",5672,20);
        assertTrue(result);
        result = Main.portIsOpen("localhost",12215,20);
        assertTrue(result);
        result = Main.portIsOpen("localhost",12214,20);
        assertTrue(result);
    }



}