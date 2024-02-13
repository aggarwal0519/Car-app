import static org.junit.Assert.*;

import java.util.*;
import org.junit.Test;

public class MyCarTest {

    @Test
    public void testReadFile() {
        List<Car> cars = Main.readFile();
        assertEquals(9, cars.size());
    }

    @Test
    public void testValidateDateFalse(){
        //MyCar.validateDate("34/06/2012");
        assertEquals(false, Main.validateDate("34/06/2012"));
    }


    @Test
    public void testValidateDateTrue(){
        //MyCar.validateDate("34/06/2012");
        assertEquals(true, Main.validateDate("24/06/2012"));
    }
    @Test
    public void testValidateEmailTrue() {
        assertTrue(Main.validateEmail("example@gmail.com"));
    }
    @Test
    public void testValidateEmailFalse() {
        assertFalse(Main.validateEmail("examplegmail.com"));
    }
    @Test
    public void testValidateString(){
        assertEquals(true, Main.validateString("1"));
    }
    @Test
    public void testValidateStringEmpty(){
        assertEquals(false, Main.validateString(" "));
    }
}