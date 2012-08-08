package com.tailf.jnc;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class YangUInt16Test {

    private short iv1;
    private long iv2;
    private String iv3;
    private YangUInt16 i1;
    private YangUInt16 i2;
    private YangUInt16 i3;

    @Before
    public void setUp() throws Exception {
        iv1 = 7;
        iv2 = 0xffff;
        iv3 = "13";
        i1 = new YangUInt16(iv1);
        i2 = new YangUInt16(iv2);
        i3 = new YangUInt16(iv3);
    }

    @Test
    public void testMin() throws YangException {
        try {
            i1.min(Short.MIN_VALUE);
            fail("Short.MIN_VALUE should be invalid");
        } catch (YangException e) {}
        try {
            i1.min(-1);
            fail("-1 should be invalid");
        } catch (YangException e) {}
        
        i1.min(0);  // Zero boundary value
        
        try {
            i1.min(Short.MAX_VALUE);
            fail("7 not smaller than Short.MAX_VALUE");
        } catch (YangException e) {}
        i2.min(Short.MAX_VALUE);  // 0xffff Smaller than Short.MAX_VALUE
        
        try {
            i1.min(0xffff);
            fail("7 not smaller than 0xffff");
        } catch (YangException e) {}
        i2.min(0xffff);  // 0xffff Smaller than 0xffff
        
        try {
            i2.min(0x10000);
            fail("0x100000 valid and/or 0xffff smaller than 0x10000");
        } catch (YangException e) {}
    }

    @Test
    public void testMax() throws YangException {
        try {
            i2.max(0x10000);
            fail("0x100000 should not be valid");
        } catch (YangException e) {}
        try {
            i2.max(-1);
            fail("-1 should not be valid");
        } catch (YangException e) {}
        try {
            i2.max(-0x8000);
            fail("-0x8000 should not be valid");
        } catch (YangException e) {}

        try {
            i2.max(0);
            fail("0xffff should be larger than 0");
        } catch (YangException e) {}
        i2.max(0xffff);  // 0xffff should not be smaller than 0xffff
    }

    @Test
    public void testParseString() {
        fail("Not yet implemented");
    }

    @Test
    public void testCheck() {
        fail("Not yet implemented");
    }

    @Test
    public void testFromString() {
        fail("Not yet implemented");
    }

    @Test
    public void testCanEqual() {
        assertTrue("Reflexive", i1.canEqual(i1));
        assertTrue("Symmetric", i1.canEqual(i2) && i2.canEqual(i1));
        assertTrue("Transitive", i1.canEqual(i2) && i2.canEqual(i3)
                && i1.canEqual(i3));
    }

    @Test
    public void testExact() {
        fail("Not yet implemented");
    }

    @Test
    public void testValid() {
        fail("Not yet implemented");
    }

    @Test
    public void testHashCode() {
        assertTrue(i1 + "not 7", i1.hashCode() == 7);
        assertTrue(i2 + "not 65535", i2.hashCode() == 0xffff);
        assertTrue(i3 + "not 13", i3.hashCode() == 13);
    }

    @Test
    public void testSetValueString() {
        fail("Not yet implemented");
    }

    @Test
    public void testSetValueT() {
        fail("Not yet implemented");
    }

    @Test
    public void testToString() {
        assertTrue(i1 + "not 7", i1.toString().equals("7"));
        assertTrue(i2 + "not 65535", i2.toString().equals("65535"));
        assertTrue(i3 + "not 13", i3.toString().equals("13"));
    }

    @Test
    public void testEqualsObject() throws YangException {
        YangUInt16 tmp1 = new YangUInt16(7);
        YangInt16 tmp2 = new YangInt16(7);
        java.io.Serializable tmp3 = new YangUInt16(7);

        assertTrue(tmp1.equals(tmp2));
        assertTrue("Symmetric", tmp1.equals(tmp2) == tmp2.equals(tmp1));
        assertTrue(tmp1.equals(tmp3));
        assertTrue("Symmetric", tmp1.equals(tmp3) == tmp3.equals(tmp1));
        assertTrue(tmp2.equals(tmp3));
        assertTrue("Symmetric", tmp2.equals(tmp3) == tmp3.equals(tmp2));
        
        assertTrue("Reflexive", i1.equals(i1));
        assertTrue("Symmetric", i1.equals(tmp1) == tmp1.equals(i1));
        assertTrue("Transitive", i1.equals(tmp1) && tmp1.equals(tmp3)
                == i1.equals(tmp3));
        
        assertFalse(i1.equals(7));
    }

}