package org.aruizeac;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.runners.Parameterized.Parameter;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class DijkstraArithmeticInterpretatorTest {
    @Parameter()
    public String inOperation;

    @Parameter(1)
    public Double exp;

    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
            {
                "(1+((2+3)*(4*5)))",
                101.0
            },
            {
                "1*5",
                5.0
            },
            {
                "1*(5)",
                5.0
            },
            {
                "(1+(5*2))",
                11.0
            },
        };
        return Arrays.asList(data);
    }

    @Test
    public void testInterpreter() {
        Double out = DijkstraArithmeticInterpretator.execute(inOperation);
        assertEquals(exp, out);
    }
}