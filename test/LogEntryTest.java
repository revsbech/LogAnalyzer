
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class LogEntryTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public LogEntryTest( String testName ) {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite( LogEntryTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testLogEntry() {
        assertTrue( true );
    }

}
