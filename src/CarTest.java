import static org.junit.Assert.*;
import java.io.*;
import java.util.*;
import org.junit.Test;

public class CarTest {
    @Test
    public void testCalculateTotalPrice(){
        Car car = new Car("C001","Toyota","Yaris","Sedan",2012,4,"Blue",50,15,10,10);
        assertTrue( car.calculateTotalPrice(7, false) == 430);
    }
}
