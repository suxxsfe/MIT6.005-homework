/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for the Expression abstract data type.
 */
public class ExpressionTest {

    // Testing strategy
    //   TODO
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    ConcreteNumberExpression numa = new ConcreteNumberExpression(1);
    ConcreteNumberExpression numb = new ConcreteNumberExpression(1.000);
    ConcreteNumberExpression numc = new ConcreteNumberExpression(1234);
    ConcreteNumberExpression numd = new ConcreteNumberExpression(1234.0000000001);
    ConcreteNumberExpression nume = new ConcreteNumberExpression(1234.00000001);
    ConcreteNumberExpression numf = new ConcreteNumberExpression(0);
    ConcreteNumberExpression numg = new ConcreteNumberExpression(1234.1234567);
    ConcreteNumberExpression numh = new ConcreteNumberExpression(1234.123);
        
    ConcreteVariableExpression vara = new ConcreteVariableExpression("abcd");
    ConcreteVariableExpression varb = new ConcreteVariableExpression("AbcD");
    
    @Test
    public void testConcreteNumberExpression(){
        assertEquals("expected String 1", "1", numa.toString());
        assertEquals("expected String 0", "0", numf.toString());
        assertEquals("expected String 1234.12346", "1234.12346", numg.toString());
        assertEquals("expected String 1234.123", "1234.123", numh.toString());
        assertEquals("expected String 1234", "1234", nume.toString());
        
        assertTrue("1 is equal to 1.000", numa.equals(numb));
        assertTrue("1234 is equal to 1234.0000000001", numc.equals(numd));
        assertFalse("1234 is not equal to 1234.00000001", numc.equals(nume));
        
        assertEquals("hash code of number 1234.123456 is 2592816", 123448062, numg.hashCode());
        assertEquals("hash code of number 0 is 0", 0, numf.hashCode());
        assertEquals("hash code of number 1234.0000000001 is 999991369", 999991369, numd.hashCode());
        assertEquals("hash code of number 1234.00000001 is 999991379", 999991379, nume.hashCode());
    }
    
    @Test
    public void testConcreteVariableExpression(){
        assertEquals("expected String abcd", "abcd", vara.toString());
        
        assertFalse("variable abcd is not equal to variable AbcD", vara.equals(varb));
        
        assertEquals("hash code of variable abcd is 232329171", 232329171, vara.hashCode());
    }
    
    @Test
    public void testConcreteAddExpression(){
        ConcreteAddExpression adda = new ConcreteAddExpression(numa, vara);
        ConcreteAddExpression addb = new ConcreteAddExpression(vara, numa);
        ConcreteAddExpression addc = new ConcreteAddExpression(adda, vara);
        ConcreteAddExpression addd = new ConcreteAddExpression(adda, vara);
        
        assertEquals("expected String ( 1 + abcd )", "( 1 + abcd )", adda.toString());
        assertEquals("expected String ( ( 1 + abcd ) + abcd )", "( ( 1 + abcd ) + abcd )", addc.toString());
        
        assertFalse("1 + abcd is not equal to abcd + 1", adda.equals(addb));
        assertTrue("the same expression ( ( 1 + abcd ) + abcd )", addc.equals(addd));
        
        assertEquals("hash code of expression 1+abcd is 132316443", 132316443, adda.hashCode());
    }
    
    @Test
    public void testConcreteMulExpression(){
        ConcreteMulExpression mula = new ConcreteMulExpression(numa, vara);
        ConcreteMulExpression mulb = new ConcreteMulExpression(vara, numa);
        ConcreteMulExpression mulc = new ConcreteMulExpression(mula, vara);
        ConcreteMulExpression muld = new ConcreteMulExpression(mula, vara);
        
        assertEquals("expected String 1 * abcd", "1 * abcd", mula.toString());
        assertEquals("expected String 1 * abcd * abcd", "1 * abcd * abcd", mulc.toString());
        
        assertFalse("1 * abcd is not equal to abcd * 1", mula.equals(mulb));
        assertTrue("the same expression 1 * abcd", mula.equals(new ConcreteMulExpression(numa, vara)));
        assertTrue("the same expression 1 * abcd * abcd", mulc.equals(muld));
        
        assertEquals("hash code of expression 1*abcd is 132316444", 132316444, mula.hashCode());
    }
}
