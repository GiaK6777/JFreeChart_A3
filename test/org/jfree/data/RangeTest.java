package org.jfree.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RangeTest {

	private Range exampleRange;
	private static final double DELTA = 0.000000001d;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}
	
	@BeforeEach
	void setUp() throws Exception {
		
	}
	
	/* Test getLowerBound() */
	
	@Test
	void testTC1_1_LB() {
		exampleRange = new Range(-50, 50);
		assertEquals(-50.0, exampleRange.getLowerBound(), DELTA);
	}
	
	@Test 
	void testTC1_2_UB() {
		exampleRange = new Range(-1.0, 1.0);
		assertEquals(1.0, exampleRange.getUpperBound(), DELTA);
	}
	
	@Test
	void testTC1_3_LEN() {
		exampleRange = new Range(0, 100);
		assertEquals(100.0, exampleRange.getLength(), DELTA);
	}

	@Test
	void testTC1_4_CV() {
		exampleRange = new Range(-10.0, 10.0);
		assertEquals(0.0, exampleRange.getCentralValue(), DELTA);
	}
	
	@Test
	void testTC1_5_LEN() {
		exampleRange = new Range(5.5, 9.5);
		assertEquals(4.0, exampleRange.getLength(), DELTA);
	}

	
	/* Test getUpperBound() */

	@Test
	void testTC2_1_UB() {
		exampleRange = new Range(75.0, 80.0); 
		assertEquals(80.0, exampleRange.getUpperBound(), DELTA);
	}
	
	@Test
	void testTC2_2_CV() {
		exampleRange = new Range(-80.0, -20.0);
		assertEquals(-50.0, exampleRange.getCentralValue(), DELTA);
	}
	
	@Test
	void testTC2_3_LB() {
		exampleRange = new Range(10.5, 20.5);
		assertEquals(10.5, exampleRange.getLowerBound(), DELTA);
	}
	
	@Test
	void testTC2_4_LEN() {
		exampleRange = new Range(42.0, 84.0);
		assertEquals(42.0, exampleRange.getLength(), DELTA);
	}
	
	@Test
	void testTC2_5_CV() {
		exampleRange = new Range(30.0, 90.0);
		assertEquals(60.0, exampleRange.getCentralValue(), DELTA);
	}
	
	/* Test getLength() */
	
	@Test
	void testTC3_1_CV() {
		exampleRange = new Range(24.0, 56.0);
		assertEquals(40.0, exampleRange.getCentralValue(), DELTA);
	}
	
	@Test
	void testTC3_2_LEN() {
		exampleRange = new Range(4.8, 9.1);
		assertEquals(4.3, exampleRange.getLength(), DELTA);
	}
	
	@Test
	void testTC3_3_LB() {
		exampleRange = new Range(6.8, 9.4);
		assertEquals(6.8, exampleRange.getLowerBound(), DELTA);
	}
	
	@Test
	void testTC3_4_UB() {
		exampleRange = new Range(8.2, 14.7);
		assertEquals(14.7, exampleRange.getUpperBound(), DELTA);
	}
	
	@Test
	void testTC3_5_CV() {
		exampleRange = new Range(7.0, 9.3);
		assertEquals(8.15, exampleRange.getCentralValue(), DELTA);
	}
	
	/* Test getCentralValue() */
	
	@Test
	void testTC4_1_LEN() {
		exampleRange = new Range(2.4, 9.8);
		assertEquals(7.4, exampleRange.getLength(), DELTA);
	}
	
	@Test
	void testTC4_2_UB() {
		exampleRange = new Range(1.0, 9.9);
		assertEquals(9.9, exampleRange.getUpperBound(), DELTA);
	}
	
	/* Test contains() */

	@Test
	void testTC5_1_ContainsBelow() {
		exampleRange = new Range(10.0, 20.0);
		assertFalse(exampleRange.contains(5.0));
	}
	
	@Test
	void testTC5_2_ContainsJustBelowLower() {
		exampleRange = new Range(10.0, 20.0);
		assertFalse(exampleRange.contains(9.9));
	}
	
	@Test
	void testTC5_3_ContainsAtLower() {
		exampleRange = new Range(10.0, 20.0);
		assertTrue(exampleRange.contains(10.0));
	}
	
	@Test
	void testTC5_4_ContainsInside() {
		exampleRange = new Range(10.0, 20.0);
		assertTrue(exampleRange.contains(15.0));
	}
	
	@Test
	void testTC5_5_ContainsAtUpper() {
		exampleRange = new Range(10.0, 20.0);
		assertTrue(exampleRange.contains(20.0));
	}
	
	@Test
	void testTC5_6_ContainsJustAboveUpper() {
		exampleRange = new Range(10.0, 20.0);
		assertFalse(exampleRange.contains(20.1));
	}
	
	@Test
	void testTC5_7_ContainsFarAbove() {
		exampleRange = new Range(10.0, 20.0);
		assertFalse(exampleRange.contains(30.0));
	}
	
	/* Test intersects() */
	
	@Test
	void testTC6_1_IntersectsBelow() {
		exampleRange = new Range(10.0, 20.0);
		assertFalse(exampleRange.intersects(5.0, 9.0));
	}
	
	@Test
	void testTC6_2_IntersectsTouchLeft() {
		exampleRange = new Range(10.0, 20.0);
		assertTrue(exampleRange.intersects(5.0, 10.0));
	}
	
	@Test
	void testTC6_3_IntersectsOverlapLeft() {
		exampleRange = new Range(10.0, 20.0);
		assertTrue(exampleRange.intersects(5.0, 15.0));
	}
	
	@Test
	void testTC6_4_IntersectsInside() {
		exampleRange = new Range(10.0, 20.0);
		assertTrue(exampleRange.intersects(12.0, 18.0));
	}
	
	@Test
	void testTC6_5_IntersectsOverlapRight() {
		exampleRange = new Range(10.0, 20.0);
		assertTrue(exampleRange.intersects(15.0, 25.0));
	}
	
	@Test
	void testTC6_6_IntersectsTouchRight() {
		exampleRange = new Range(10.0, 20.0);
		assertTrue(exampleRange.intersects(20.0, 25.0));
	}
	
	@Test
	void testTC6_7_IntersectsAbove() {
		exampleRange = new Range(10.0, 20.0);
		assertFalse(exampleRange.intersects(21.0, 25.0));
	}
	
	@Test
	void testTC6_8_IntersectsEnvelops() {
		exampleRange = new Range(10.0, 20.0);
		assertTrue(exampleRange.intersects(5.0, 25.0));
	}
	
	/* Test constrain() */

	@Test
	void testTC7_1_ConstrainBelow() {
		exampleRange = new Range(10.0, 20.0);
		assertEquals(10.0, exampleRange.constrain(9.0), DELTA);
	}
	
	@Test 
	void testTC7_2_ConstrainAtLower() { 
		exampleRange = new Range(10.0, 20.0);
		assertEquals(10.0, exampleRange.constrain(10.0), DELTA ); 
	}
	
	@Test 
	void testTC7_3_ConstrainInside() { 
		exampleRange = new Range(10.0, 20.0);
		assertEquals(15.0, exampleRange.constrain(15.0), DELTA); 
	}
	
	@Test 
	void testTC7_4_ConstrainAtUpper() { 
		exampleRange = new Range(10.0, 20.0);
		assertEquals(20.0, exampleRange.constrain(20.0), DELTA); 
	}
	
	@Test 
	void testTC7_5_ConstrainAbove() { 
		exampleRange = new Range(10.0, 20.0);
		assertEquals(20.0, exampleRange.constrain(21.0), DELTA); 
	}
	
	@Test 
	void testTC7_6_ConstrainFarBelow() { 
		exampleRange = new Range(10.0, 20.0);
		assertEquals(10.0, exampleRange.constrain(-100.0), DELTA); 
	}
	
	@Test 
	void testTC7_7_ConstrainFarAbove() { 
		exampleRange = new Range(10.0, 20.0);
		assertEquals(20.0, exampleRange.constrain(500.0), DELTA); 
	}
	
	/* Test combine() */
	
	@Test
	void testTC8_1_ValidRangeConstruction() { 
	    Range range1 = new Range(10.0,20.0); 
	    Range range2 = new Range(15.0,25.0);
	    
	    assertTrue(range1.getLowerBound() <= range1.getUpperBound()); 
	    assertTrue(range2.getLowerBound() <= range2.getUpperBound()); 
	}
	
	@Test 
	void testTC8_2_CombineNonOverlapping() { 
		assertThrows(IllegalArgumentException.class, () -> Range.combine(new Range(10.0,20.0), new Range(25.0,30.0)));
	}
	
	@Test 
	void testTC8_3_CombineFirstNull() { 
		Range range = Range.combine(null, new Range(10.0,20.0));
		assertEquals(10.0, range.getLowerBound(), DELTA);
		assertEquals(20.0, range.getUpperBound(), DELTA);
	}
	
	@Test
	void testTC8_4_CombineSecondNull() {
		Range range = Range.combine(new Range(10.0,20.0), null);
		assertEquals(10.0, range.getLowerBound(), DELTA);
		assertEquals(20.0, range.getUpperBound(), DELTA);
	}
	
	@Test
	void testTC8_5_CombineBothNull() {
		assertNull(Range.combine(null,null));
	}
	
	/* Test expandToInclude() */
	
	@Test
	void testTC9_1_ExpandBelowLower() {
		Range range = Range.expandToInclude(new Range(10.0,20.0),5.0);
		assertEquals(5.0,range.getLowerBound(),DELTA);
		assertEquals(20.0,range.getUpperBound(),DELTA);
	}
	
	@Test
	void testTC9_2_ExpandAtLower() {
		Range range = Range.expandToInclude(new Range(10.0,20.0),10.0); 
		assertEquals(10.0,range.getLowerBound(),DELTA);
		assertEquals(20.0,range.getUpperBound(),DELTA); 
	}
	
	@Test
	void testTC9_3_ExpandInside() {
		Range range = Range.expandToInclude(new Range(10.0,20.0),15.0);
		assertEquals(10.0,range.getLowerBound(),DELTA);
		assertEquals(20.0,range.getUpperBound(),DELTA); 
	}
	
	@Test 
	void testTC9_4_ExpandAtUpper() {
		Range range = Range.expandToInclude(new Range(10.0,20.0),20.0);
		assertEquals(10.0,range.getLowerBound(),DELTA);
		assertEquals(20.0,range.getUpperBound(),DELTA);
	}
	
	@Test 
	void testTC9_5_ExpandAboveUpper() { 
		Range range = Range.expandToInclude(new Range(10.0,20.0),25.0); 
		assertEquals(10.0,range.getLowerBound(),DELTA); 
		assertEquals(25.0,range.getUpperBound(),DELTA);
	}
	
	@Test 
	void testTC9_6_ExpandNullRange() { 
		Range range = Range.expandToInclude(null,12.0); 
		assertEquals(12.0,range.getLowerBound(),DELTA); 
		assertEquals(12.0,range.getUpperBound(),DELTA);
	}
	
	/* Test expand() */

	@Test 
	void testTC10_1_ExpandZeroMargins() { 
		Range range = Range.expand(new Range(10.0,20.0),0.0,0.0); 
		assertEquals(10.0,range.getLowerBound(),DELTA); 
		assertEquals(20.0,range.getUpperBound(),DELTA);
	}

	@Test 
	void testTC10_2_ExpandPositiveMargins() { 
		Range range = Range.expand(new Range(10.0,20.0),0.1,0.1);
		assertEquals(9.0,range.getLowerBound(),DELTA); 
		assertEquals(21.0,range.getUpperBound(),DELTA);
	}

	@Test 
	void testTC10_3_ExpandNegativeMargins() { 
		assertThrows(IllegalArgumentException.class, () -> Range.expand(new Range(10.0,20.0),-0.1,-1.1));
	}

	@Test 
	void testTC10_4_ExpandNullRange() { 
		assertThrows(IllegalArgumentException.class, () -> Range.expand(null,0.1,0.1)); 
	}
	
	/* Test shift(Range base, double delta) */

	@Test
	void testTC11_1_ShiftPositive() {
		Range range = Range.shift(new Range(10.0,20.0),5.0);
		assertEquals(15.0,range.getLowerBound(),DELTA); 
		assertEquals(25.0,range.getUpperBound(),DELTA);
	}

	@Test 
	void testTC11_2_ShiftNegative() {
		Range range = Range.shift(new Range(10.0,20.0),-5.0); 
		assertEquals(5.0,range.getLowerBound(),DELTA); 
		assertEquals(15.0,range.getUpperBound(),DELTA); 
	}
	
	@Test 
	void testTC11_3_ShiftZero() { 
		Range range = Range.shift(new Range(10.0,20.0),0.0); 
		assertEquals(10.0,range.getLowerBound(),DELTA); 
		assertEquals(20.0,range.getUpperBound(),DELTA);
	}
	
	/* Test shift(Range base, double delta, boolean allowZeroCrossing) */

	@Test
	void testTC12_1_ShiftPositiveDelta() {
	    exampleRange = new Range(1, 5);
	    Range shifted = Range.shift(exampleRange, 2, true);

	    assertEquals(3.0, shifted.getLowerBound());
	    assertEquals(7.0, shifted.getUpperBound());
	}
	
	@Test
	void testTC12_2_ShiftNegativeDelta() {
	    exampleRange = new Range(5, 10);
	    Range shifted = Range.shift(exampleRange, -2, true);

	    assertEquals(3.0, shifted.getLowerBound());
	    assertEquals(8.0, shifted.getUpperBound());
	}
	
	@Test
	void testTC12_3_ShiftZeroDelta() {
	    exampleRange = new Range(2, 6);
	    Range shifted = Range.shift(exampleRange, 0, true);

	    assertEquals(2.0, shifted.getLowerBound());
	    assertEquals(6.0, shifted.getUpperBound());
	}
	
	@Test
	void testTC12_4_ShiftCrossZeroAllowed() {
	    exampleRange = new Range(-5, -1);
	    Range shifted = Range.shift(exampleRange, 6, true);

	    assertEquals(1.0, shifted.getLowerBound());
	    assertEquals(5.0, shifted.getUpperBound());
	}
	
	@Test
	void testTC12_5_ShiftCrossZeroNotAllowed() {
	    exampleRange = new Range(-5, -1);
	    Range shifted = Range.shift(exampleRange, 6, false);

	    assertEquals(0.0, shifted.getLowerBound());
	    assertEquals(5.0, shifted.getUpperBound());
	}

	/* Test equals() */
	
	@Test
	void testTC13_1_EqualsSameObject() {
	    exampleRange = new Range(1, 5);
	    assertTrue(exampleRange.equals(exampleRange));
	}
	
	@Test
	void testTC13_2_EqualsEquivalentRange() {
	    exampleRange = new Range(1, 5);
	    Range exampleRange2 = new Range(1, 5);

	    assertTrue(exampleRange.equals(exampleRange2));
	}
	
	@Test
	void testTC13_3_EqualsDifferentLower() {
	    exampleRange = new Range(1, 5);
	    Range exampleRange2 = new Range(2, 5);

	    assertFalse(exampleRange.equals(exampleRange2));
	}
	
	@Test
	void testTC13_4_EqualsDifferentUpper() {
	    exampleRange = new Range(1, 5);
	    Range exampleRange2 = new Range(1, 6);

	    assertFalse(exampleRange.equals(exampleRange2));
	}
	
	@Test
	void testTC13_5_EqualsNull() {
	    exampleRange = new Range(1, 5);
	    assertFalse(exampleRange.equals(null));
	}
	
	
	/* Test toString() */
	
	@Test
	void testTC14_1_ToStringWithPositiveVals() {
		exampleRange = new Range(5, 10);
		assertEquals("Range[5.0,10.0]", exampleRange.toString());
	
	}
	
	@Test
	void testTC14_2_ToStringWithNegativeVals() {
	    exampleRange = new Range(-10, -5);
	    assertEquals("Range[-10.0,-5.0]", exampleRange.toString());
	}
	
	@Test
	void testTC14_3_ToStringWithMixedVals() {
	    exampleRange = new Range(-3, 9);
	    assertEquals("Range[-3.0,9.0]", exampleRange.toString());
	}
	
	@Test
	void testTC14_4_ToStringWithDecimals() {
	    exampleRange = new Range(1.5, 2.75);
	    assertEquals("Range[1.5,2.75]", exampleRange.toString());
	}
	
	@Test
	void testTC14_5_ToStringWithZero() {
	    exampleRange = new Range(0, 5);
	    assertEquals("Range[0.0,5.0]", exampleRange.toString());
	}
	
	@Test
	void testTC14_6_ToStringWithDoubleZeroVals() {
	    exampleRange = new Range(0, 0);
	    assertEquals("Range[0.0,0.0]", exampleRange.toString());
	}
	
	@Test
	void testTC14_7_ToStringWithSameNonZeroVals() {
	    Range exampleRange = new Range(6, 6);
	    assertEquals("Range[6.0,6.0]", exampleRange.toString());
	}
		
	@AfterEach
	void tearDown() throws Exception {
		
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
	}

}
