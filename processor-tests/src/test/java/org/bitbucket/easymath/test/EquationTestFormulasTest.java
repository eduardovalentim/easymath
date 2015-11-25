package org.bitbucket.easymath.test;

import java.math.BigDecimal;

import org.junit.Test;

public class EquationTestFormulasTest {

    static {
        System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");
    }
    
    @Test
    public final void testSimple() {
        EquationTestFormulas f = new EquationTestFormulas();
        f.simple(BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE);
    }

    @Test
    public final void testQuadric() {
        EquationTestFormulas f = new EquationTestFormulas();
        f.quadric(BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE);
    }

    @Test
    public final void testCubic() {
        EquationTestFormulas f = new EquationTestFormulas();
        f.cubic(BigDecimal.ONE, BigDecimal.ONE);
    }

    @Test
    public final void testQuartic() {
        EquationTestFormulas f = new EquationTestFormulas();
        f.quartic(BigDecimal.ONE, BigDecimal.ONE);
    }

}
