import static org.junit.Assert.*;
import org.junit.Test;

import java.util.logging.FileHandler;

public class FlikTest {
    @Test
    public void testFilk() {
        int i = 5;
        int a = 7;
        int b = 7;
        while (i > -5) {
            i--;
            a--;
            b--;
            assertTrue(Flik.isSameNumber(a, b));
        }
        assertTrue(Flik.isSameNumber(128, 128));
    }

    @Test
    public void test128 (){
        int i = 128;
        for (int j = 120; j < 130; j++) {
            if (j!=128) {
                assertTrue(!Flik.isSameNumber(i, j));
            } else {
                assertTrue(Flik.isSameNumber(i, j));
            }
        }
    }
}
