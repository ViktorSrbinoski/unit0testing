import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

public class TimePeriodTest {

    private TimePeriod periodA;
    private Calendar calendar;
    private static final long ONE_DAY_MILLIS = 24L*60L*60L*1000L;

    @Before
    public void setUp() {
        calendar = Calendar.getInstance();
        calendar.set(2019, Calendar.APRIL, 1);
        periodA = new TimePeriod(new Date(calendar.getTimeInMillis()), new Date(calendar.getTimeInMillis()+(30L* ONE_DAY_MILLIS)));
    }

    @Test
    public void periodAContainsPeriodB(){
        TimePeriod periodB = new TimePeriod(new Date(calendar.getTimeInMillis()+ ONE_DAY_MILLIS), new Date(calendar.getTimeInMillis()+ 2L*ONE_DAY_MILLIS));
        Assert.assertTrue(periodA.overlapsWith(periodB));
    }

    @Test
    public void periodBContainsPeriodA(){
        TimePeriod periodB = new TimePeriod(new Date(calendar.getTimeInMillis()- ONE_DAY_MILLIS), new Date(calendar.getTimeInMillis()+ 31*ONE_DAY_MILLIS));
        Assert.assertTrue(periodA.overlapsWith(periodB));
    }

    @Test
    public void periodBIntersectsPeriodA(){
        TimePeriod periodB = new TimePeriod(new Date(calendar.getTimeInMillis()- ONE_DAY_MILLIS), new Date(calendar.getTimeInMillis()+ ONE_DAY_MILLIS));
        Assert.assertTrue(periodA.overlapsWith(periodB));
    }

    @Test
    public void periodAIntersectsPeriodB(){
        TimePeriod periodB = new TimePeriod(new Date(calendar.getTimeInMillis()+ ONE_DAY_MILLIS), new Date(calendar.getTimeInMillis()+ 31L*ONE_DAY_MILLIS));
        Assert.assertTrue(periodA.overlapsWith(periodB));
    }

    @Test
    public void periodAEqualsPeriodB(){
        TimePeriod periodB = new TimePeriod(new Date(calendar.getTimeInMillis()), new Date(calendar.getTimeInMillis()+ 30L*ONE_DAY_MILLIS));
        Assert.assertTrue(periodA.overlapsWith(periodB));
    }

    @Test
    public void periodAEndEqualsPeriodBStart(){
        TimePeriod periodB = new TimePeriod(new Date(calendar.getTimeInMillis()+ 30L*ONE_DAY_MILLIS), new Date(calendar.getTimeInMillis()+31L*ONE_DAY_MILLIS));
        Assert.assertTrue(periodA.overlapsWith(periodB));
    }

    @Test
    public void periodADoesNotOverlapPeriodB(){
        TimePeriod periodB = new TimePeriod(new Date(calendar.getTimeInMillis()+ 31L*ONE_DAY_MILLIS), new Date(calendar.getTimeInMillis()+ 32L*ONE_DAY_MILLIS));
        Assert.assertFalse(periodA.overlapsWith(periodB));
    }
}