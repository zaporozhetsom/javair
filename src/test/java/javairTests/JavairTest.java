package javairTests;

import BusinessLogic.Entities.Flight;
import BusinessLogic.Entities.Javair;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by zom on 19.09.2017.
 */
public class JavairTest {
    @Test
    public void canCreateJAvair() {
        Javair javair = new Javair();
    }

    @Test
    public void getFlights() {
        Javair javair = new Javair();
//        Assert.assertEquals(Flight.class, javair.flights.get(0).getClass());
    }
}
