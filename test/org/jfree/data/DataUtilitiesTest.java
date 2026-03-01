package org.jfree.data;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.security.InvalidParameterException;

import org.junit.jupiter.api.Test;


class DataUtilitiesTest {

    private static final double DELTA = 0.000000001d;

    // =========================================================================
    // Helper: build a 3-key mock {0:5, 1:9, 2:2} matching the Javadoc example
    // =========================================================================
    private KeyedValues buildJavadocMock() {
        KeyedValues data = mock(KeyedValues.class);
        when(data.getItemCount()).thenReturn(3);
        when(data.getKey(0)).thenReturn(0);
        when(data.getKey(1)).thenReturn(1);
        when(data.getKey(2)).thenReturn(2);
        when(data.getValue(0)).thenReturn(5.0);
        when(data.getValue(1)).thenReturn(9.0);
        when(data.getValue(2)).thenReturn(2.0);
        return data;
    }
    
  /*/____________________

    calculateColumnTotal
    _____________________*/

    @Test
    void testCalculateColumnTotal_basic_column0() {
    DefaultKeyedValues2D table = new DefaultKeyedValues2D();
    table.addValue(4.0, "R1", "C1");
    table.addValue(6.0, "R2", "C1");
    table.addValue(10.0, "R3", "C1");
    double result = DataUtilities.calculateColumnTotal(table, 0);
    assertEquals(20.0, result, 1e-12);
    }
    @Test
    void testCalculateColumnTotal_basic_column1() {
    DefaultKeyedValues2D table = new DefaultKeyedValues2D();
    table.addValue(3.0, "R1", "C1");
    table.addValue(8.0, "R1", "C2");
    table.addValue(5.0, "R2", "C1");
    table.addValue(7.0, "R2", "C2");
    table.addValue(2.0, "R3", "C1");
    table.addValue(9.0, "R3", "C2");
    double result = DataUtilities.calculateColumnTotal(table, 1);
    assertEquals(24.0, result, 1e-12);
    }
    @Test
    void testCalculateColumnTotal_ignoresNullValues() {
    DefaultKeyedValues2D table = new DefaultKeyedValues2D();
    table.addValue(5.0, "R1", "C1");
    table.addValue(null, "R2", "C1");
    table.addValue(15.0, "R3", "C1");
    double result = DataUtilities.calculateColumnTotal(table, 0);
    assertEquals(20.0, result, 1e-12);
    }
    @Test
    void testCalculateColumnTotal_singleRow() {
    DefaultKeyedValues2D table = new DefaultKeyedValues2D();
    table.addValue(12.0, "R1", "C1");
    double result = DataUtilities.calculateColumnTotal(table, 0);
    assertEquals(12.0, result, 1e-12);
    }
    @Test
    void testCalculateColumnTotal_zeroValues() {
    DefaultKeyedValues2D table = new DefaultKeyedValues2D();
    table.addValue(0.0, "R1", "C1");
    table.addValue(0.0, "R2", "C1");
    double result = DataUtilities.calculateColumnTotal(table, 0);
    assertEquals(0.0, result, 1e-12);
    }
    @Test
    void testCalculateColumnTotal_invalidColumnIndex_throws() {
    DefaultKeyedValues2D table = new DefaultKeyedValues2D();
    table.addValue(5.0, "R1", "C1");
    assertThrows(IndexOutOfBoundsException.class,
    () -> DataUtilities.calculateColumnTotal(table, 5));
    }
    @Test
    void testCalculateColumnTotal_nullData_throws() {
    assertThrows(IllegalArgumentException.class,
    () -> DataUtilities.calculateColumnTotal((Values2D) null, 0));
    }


 // -------------------------
     // calculateRowTotal
     // -------------------------

     @Test
     void testCalculateRowTotal_basic() {
         DefaultKeyedValues2D table = new DefaultKeyedValues2D();
         table.addValue(1.0, "R1", "C1");
         table.addValue(2.0, "R1", "C2");
         table.addValue(3.0, "R1", "C3");

         double result = DataUtilities.calculateRowTotal(table, 0);
         assertEquals(6.0, result, 1e-12);
     }

     @Test
     void testCalculateRowTotal_ignoresNullValues() {
         DefaultKeyedValues2D table = new DefaultKeyedValues2D();
         table.addValue(1.0, "R1", "C1");
         table.addValue(null, "R1", "C2");
         table.addValue(3.0, "R1", "C3");

         double result = DataUtilities.calculateRowTotal(table, 0);
         assertEquals(4.0, result, 1e-12);
     }

     @Test
     void testCalculateRowTotal_invalidRowIndex_throws() {
         DefaultKeyedValues2D table = new DefaultKeyedValues2D();
         table.addValue(1.0, "R1", "C1");

         assertThrows(IndexOutOfBoundsException.class,
                 () -> DataUtilities.calculateRowTotal(table, 3));
     }

     @Test
     void testCalculateRowTotal_nullData_throws() {
         assertThrows(IllegalArgumentException.class,
                 () -> DataUtilities.calculateRowTotal((Values2D) null, 0));
     }


    

    /**
     * TC5.1a — Input {0:5, 1:9, 2:2}: key 0 should be 5/16 = 0.3125
     */
    @Test
    void testTC5_1a_JavadocExample_Key0() {
        KeyedValues result = DataUtilities.getCumulativePercentages(buildJavadocMock());
        assertEquals(0.3125, result.getValue(0).doubleValue(), DELTA,
                "Key 0: cumulative % should be 5/16 = 0.3125");
    }

    /**
     * TC5.1b — Input {0:5, 1:9, 2:2}: key 1 should be (5+9)/16 = 0.875
     */
    @Test
    void testTC5_1b_JavadocExample_Key1() {
        KeyedValues result = DataUtilities.getCumulativePercentages(buildJavadocMock());
        assertEquals(0.875, result.getValue(1).doubleValue(), DELTA,
                "Key 1: cumulative % should be (5+9)/16 = 0.875");
    }

    /**
     * TC5.1c — Input {0:5, 1:9, 2:2}: key 2 (last) should be 1.0
     */
    @Test
    void testTC5_1c_JavadocExample_Key2_LastIsOne() {
        KeyedValues result = DataUtilities.getCumulativePercentages(buildJavadocMock());
        assertEquals(1.0, result.getValue(2).doubleValue(), DELTA,
                "Key 2: last cumulative % must be 1.0");
    }

 
    /**
     * TC5.2 — ECP: single entry {0:7} → output must be 1.0 (7/7)
     */
    @Test
    void testTC5_2_SingleKey_AlwaysOneHundredPercent() {
        KeyedValues data = mock(KeyedValues.class);
        when(data.getItemCount()).thenReturn(1);
        when(data.getKey(0)).thenReturn(0);
        when(data.getValue(0)).thenReturn(7.0);

        KeyedValues result = DataUtilities.getCumulativePercentages(data);

        assertEquals(1.0, result.getValue(0).doubleValue(), DELTA,
                "Single key: cumulative % must be 1.0");
    }


    /**
     * TC5.3a — {0:10, 1:10}: key 0 should be 10/20 = 0.5
     */
    @Test
    void testTC5_3a_TwoKeys_EqualValues_Key0() {
        KeyedValues data = mock(KeyedValues.class);
        when(data.getItemCount()).thenReturn(2);
        when(data.getKey(0)).thenReturn(0);
        when(data.getKey(1)).thenReturn(1);
        when(data.getValue(0)).thenReturn(10.0);
        when(data.getValue(1)).thenReturn(10.0);

        KeyedValues result = DataUtilities.getCumulativePercentages(data);

        assertEquals(0.5, result.getValue(0).doubleValue(), DELTA,
                "Key 0: 10/20 = 0.5");
    }

    /**
     * TC5.3b — {0:10, 1:10}: key 1 (last) should be 1.0
     */
    @Test
    void testTC5_3b_TwoKeys_EqualValues_Key1() {
        KeyedValues data = mock(KeyedValues.class);
        when(data.getItemCount()).thenReturn(2);
        when(data.getKey(0)).thenReturn(0);
        when(data.getKey(1)).thenReturn(1);
        when(data.getValue(0)).thenReturn(10.0);
        when(data.getValue(1)).thenReturn(10.0);

        KeyedValues result = DataUtilities.getCumulativePercentages(data);

        assertEquals(1.0, result.getValue(1).doubleValue(), DELTA,
                "Key 1: last key must be 1.0");
    }

 
    /**
     * TC5.4a — {0:1, 1:3, 2:6}: key 0 should be 1/10 = 0.1
     */
    @Test
    void testTC5_4a_FirstKeySmallestPercentage_Key0() {
        KeyedValues data = mock(KeyedValues.class);
        when(data.getItemCount()).thenReturn(3);
        when(data.getKey(0)).thenReturn(0);
        when(data.getKey(1)).thenReturn(1);
        when(data.getKey(2)).thenReturn(2);
        when(data.getValue(0)).thenReturn(1.0);
        when(data.getValue(1)).thenReturn(3.0);
        when(data.getValue(2)).thenReturn(6.0);

        KeyedValues result = DataUtilities.getCumulativePercentages(data);

        assertEquals(0.1, result.getValue(0).doubleValue(), DELTA,
                "Key 0: 1/10 = 0.1");
    }

    /**
     * TC5.4b — {0:1, 1:3, 2:6}: key 1 should be (1+3)/10 = 0.4
     */
    @Test
    void testTC5_4b_FirstKeySmallestPercentage_Key1() {
        KeyedValues data = mock(KeyedValues.class);
        when(data.getItemCount()).thenReturn(3);
        when(data.getKey(0)).thenReturn(0);
        when(data.getKey(1)).thenReturn(1);
        when(data.getKey(2)).thenReturn(2);
        when(data.getValue(0)).thenReturn(1.0);
        when(data.getValue(1)).thenReturn(3.0);
        when(data.getValue(2)).thenReturn(6.0);

        KeyedValues result = DataUtilities.getCumulativePercentages(data);

        assertEquals(0.4, result.getValue(1).doubleValue(), DELTA,
                "Key 1: (1+3)/10 = 0.4");
    }

    /**
     * TC5.4c — {0:1, 1:3, 2:6}: key 2 (last) should be 1.0
     */
    @Test
    void testTC5_4c_FirstKeySmallestPercentage_Key2() {
        KeyedValues data = mock(KeyedValues.class);
        when(data.getItemCount()).thenReturn(3);
        when(data.getKey(0)).thenReturn(0);
        when(data.getKey(1)).thenReturn(1);
        when(data.getKey(2)).thenReturn(2);
        when(data.getValue(0)).thenReturn(1.0);
        when(data.getValue(1)).thenReturn(3.0);
        when(data.getValue(2)).thenReturn(6.0);

        KeyedValues result = DataUtilities.getCumulativePercentages(data);

        assertEquals(1.0, result.getValue(2).doubleValue(), DELTA,
                "Key 2: last must be 1.0");
    }

    /**
     * TC5.5 — {0:100, 1:200, 2:300}: last key must be 1.0
     */
    @Test
    void testTC5_5_LastKeyAlwaysOnePointZero() {
        KeyedValues data = mock(KeyedValues.class);
        when(data.getItemCount()).thenReturn(3);
        when(data.getKey(0)).thenReturn(0);
        when(data.getKey(1)).thenReturn(1);
        when(data.getKey(2)).thenReturn(2);
        when(data.getValue(0)).thenReturn(100.0);
        when(data.getValue(1)).thenReturn(200.0);
        when(data.getValue(2)).thenReturn(300.0);

        KeyedValues result = DataUtilities.getCumulativePercentages(data);

        assertEquals(1.0, result.getValue(2).doubleValue(), DELTA,
                "Last key cumulative % must always be 1.0");
    }

 
    /**
     * TC5.6a — {0:2.5, 1:2.5}: key 0 should be 2.5/5.0 = 0.5
     */
    @Test
    void testTC5_6a_DecimalValues_Key0() {
        KeyedValues data = mock(KeyedValues.class);
        when(data.getItemCount()).thenReturn(2);
        when(data.getKey(0)).thenReturn(0);
        when(data.getKey(1)).thenReturn(1);
        when(data.getValue(0)).thenReturn(2.5);
        when(data.getValue(1)).thenReturn(2.5);

        KeyedValues result = DataUtilities.getCumulativePercentages(data);

        assertEquals(0.5, result.getValue(0).doubleValue(), DELTA,
                "Key 0: 2.5/5.0 = 0.5");
    }

    /**
     * TC5.6b — {0:2.5, 1:2.5}: key 1 should be 1.0
     */
    @Test
    void testTC5_6b_DecimalValues_Key1() {
        KeyedValues data = mock(KeyedValues.class);
        when(data.getItemCount()).thenReturn(2);
        when(data.getKey(0)).thenReturn(0);
        when(data.getKey(1)).thenReturn(1);
        when(data.getValue(0)).thenReturn(2.5);
        when(data.getValue(1)).thenReturn(2.5);

        KeyedValues result = DataUtilities.getCumulativePercentages(data);

        assertEquals(1.0, result.getValue(1).doubleValue(), DELTA,
                "Key 1: 5.0/5.0 = 1.0");
    }

  
    /**
     * TC5.7a — {0:3, 1:5, 2:2}: key 0 value < key 1 value
     */
    @Test
    void testTC5_7a_CumulativeIncreasing_v0_LessThan_v1() {
        KeyedValues data = mock(KeyedValues.class);
        when(data.getItemCount()).thenReturn(3);
        when(data.getKey(0)).thenReturn(0);
        when(data.getKey(1)).thenReturn(1);
        when(data.getKey(2)).thenReturn(2);
        when(data.getValue(0)).thenReturn(3.0);
        when(data.getValue(1)).thenReturn(5.0);
        when(data.getValue(2)).thenReturn(2.0);

        KeyedValues result = DataUtilities.getCumulativePercentages(data);

        assertTrue(result.getValue(0).doubleValue() < result.getValue(1).doubleValue(),
                "Cumulative %: value at key 0 must be less than key 1");
    }

    /**
     * TC5.7b — {0:3, 1:5, 2:2}: key 1 value < key 2 value
     */
    @Test
    void testTC5_7b_CumulativeIncreasing_v1_LessThan_v2() {
        KeyedValues data = mock(KeyedValues.class);
        when(data.getItemCount()).thenReturn(3);
        when(data.getKey(0)).thenReturn(0);
        when(data.getKey(1)).thenReturn(1);
        when(data.getKey(2)).thenReturn(2);
        when(data.getValue(0)).thenReturn(3.0);
        when(data.getValue(1)).thenReturn(5.0);
        when(data.getValue(2)).thenReturn(2.0);

        KeyedValues result = DataUtilities.getCumulativePercentages(data);

        assertTrue(result.getValue(1).doubleValue() < result.getValue(2).doubleValue(),
                "Cumulative %: value at key 1 must be less than key 2");
    }

  
    /**
     * TC5.8 — null input: per Javadoc → throws InvalidParameterException
     */
    @Test
    void testTC5_8_NullData_ThrowsException() {
        assertThrows(Exception.class,
                () -> DataUtilities.getCumulativePercentages(null),
                "Passing null should throw an exception");
    }

  
    /**
     * TC5.9 — {0:5, 1:9, 2:2}: result must have exactly 3 items
     */
    @Test
    void testTC5_9_ResultItemCountMatchesInput() {
        KeyedValues result = DataUtilities.getCumulativePercentages(buildJavadocMock());
        assertEquals(3, result.getItemCount(),
                "Output must have the same number of items as input");
    }

 

    /**
     * TC5.10a — {0:5, 1:9, 2:2}: key 0 value must be in [0.0, 1.0]
     */
    @Test
    void testTC5_10a_ValueInRange_Key0() {
        KeyedValues result = DataUtilities.getCumulativePercentages(buildJavadocMock());
        double val = result.getValue(0).doubleValue();
        assertTrue(val >= 0.0 && val <= 1.0,
                "Key 0 must be in [0.0, 1.0], was: " + val);
    }

    /**
     * TC5.10b — {0:5, 1:9, 2:2}: key 1 value must be in [0.0, 1.0]
     */
    @Test
    void testTC5_10b_ValueInRange_Key1() {
        KeyedValues result = DataUtilities.getCumulativePercentages(buildJavadocMock());
        double val = result.getValue(1).doubleValue();
        assertTrue(val >= 0.0 && val <= 1.0,
                "Key 1 must be in [0.0, 1.0], was: " + val);
    }

    /**
     * TC5.10c — {0:5, 1:9, 2:2}: key 2 value must be in [0.0, 1.0]
     */
    @Test
    void testTC5_10c_ValueInRange_Key2() {
        KeyedValues result = DataUtilities.getCumulativePercentages(buildJavadocMock());
        double val = result.getValue(2).doubleValue();
        assertTrue(val >= 0.0 && val <= 1.0,
                "Key 2 must be in [0.0, 1.0], was: " + val);
    }



    /**
     * TC5.11a — {0:null, 1:8, 2:2}: key 0 is null → treated as 0, skipped.
     * Total = 0 + 8 + 2 = 10
     * Expected: {0:0.0, 1:0.8, 2:1.0}
     */
    @Test
    void testTC5_11a_NullValue_FirstKey_IsSkipped() {
        KeyedValues data = mock(KeyedValues.class);
        when(data.getItemCount()).thenReturn(3);
        when(data.getKey(0)).thenReturn(0);
        when(data.getKey(1)).thenReturn(1);
        when(data.getKey(2)).thenReturn(2);
        when(data.getValue(0)).thenReturn(null);   // null value
        when(data.getValue(1)).thenReturn(8.0);
        when(data.getValue(2)).thenReturn(2.0);

        KeyedValues result = DataUtilities.getCumulativePercentages(data);

        // key 1: 8/10 = 0.8
        assertEquals(0.8, result.getValue(1).doubleValue(), DELTA,
                "Key 1: 8/10 = 0.8 (null key 0 skipped)");
    }

    /**
     * TC5.11b — {0:null, 1:8, 2:2}: last key must still be 1.0
     * even when some values are null.
     */
    @Test
    void testTC5_11b_NullValue_LastKeyStillOne() {
        KeyedValues data = mock(KeyedValues.class);
        when(data.getItemCount()).thenReturn(3);
        when(data.getKey(0)).thenReturn(0);
        when(data.getKey(1)).thenReturn(1);
        when(data.getKey(2)).thenReturn(2);
        when(data.getValue(0)).thenReturn(null);   // null value
        when(data.getValue(1)).thenReturn(8.0);
        when(data.getValue(2)).thenReturn(2.0);

        KeyedValues result = DataUtilities.getCumulativePercentages(data);

        // last key must always be 1.0
        assertEquals(1.0, result.getValue(2).doubleValue(), DELTA,
                "Last key must be 1.0 even when some values are null");
    }

    /**
     * TC5.11c — {0:5, 1:null, 2:5}: middle value is null → skipped.
     * Total = 5 + 0 + 5 = 10
     * Expected: {0:0.5, 1:0.5, 2:1.0}
     */
    @Test
    void testTC5_11c_NullValue_MiddleKey_IsSkipped() {
        KeyedValues data = mock(KeyedValues.class);
        when(data.getItemCount()).thenReturn(3);
        when(data.getKey(0)).thenReturn(0);
        when(data.getKey(1)).thenReturn(1);
        when(data.getKey(2)).thenReturn(2);
        when(data.getValue(0)).thenReturn(5.0);
        when(data.getValue(1)).thenReturn(null);   // null middle value
        when(data.getValue(2)).thenReturn(5.0);

        KeyedValues result = DataUtilities.getCumulativePercentages(data);

        // key 0: 5/10 = 0.5
        assertEquals(0.5, result.getValue(0).doubleValue(), DELTA,
                "Key 0: 5/10 = 0.5 (null middle key skipped)");
        // last key still 1.0
        assertEquals(1.0, result.getValue(2).doubleValue(), DELTA,
                "Last key must be 1.0 even with null middle value");
    }

    @Test
    void testCalculateRowTotal_validSum_whiteBox() {
        DefaultKeyedValues2D table = new DefaultKeyedValues2D();
        table.addValue(2.0, "R1", "C1");
        table.addValue(3.0, "R1", "C2");
        assertEquals(5.0, DataUtilities.calculateRowTotal(table, 0), 1e-12);
    }

    @Test
    void testCalculateColumnTotal_validSum_whiteBox() {
        DefaultKeyedValues2D table = new DefaultKeyedValues2D();
        table.addValue(4.0, "R1", "C1");
        table.addValue(6.0, "R2", "C1");
        assertEquals(10.0, DataUtilities.calculateColumnTotal(table, 0), 1e-12);
    }
    @Test
    void testCreateNumberArray_ValidInput() {
        double[] input = {1.0, 2.5, -3.4};
        Number[] result = DataUtilities.createNumberArray(input);

        assertEquals(3, result.length);
        assertEquals(1.0, result[0]);
        assertEquals(2.5, result[1]);
        assertEquals(-3.4, result[2]);
    }
    
    @Test
    void testCreateNumberArray_EmptyArray() {
        double[] input = {};
        Number[] result = DataUtilities.createNumberArray(input);

        assertEquals(0, result.length);
    }
    
    @Test
    void testCreateNumberArray_NullInput() {
        assertThrows(InvalidParameterException.class, () -> {
            DataUtilities.createNumberArray(null);
        });
    }
    @Test
    void testCreateNumberArray2D_ValidInput() {
        double[][] input = {
            {1.0, 2.0},
            {3.0, 4.0}
        };

        Number[][] result = DataUtilities.createNumberArray2D(input);

        assertEquals(2, result.length);
        assertEquals(2, result[0].length);
        assertEquals(1.0, result[0][0]);
        assertEquals(4.0, result[1][1]);
    }
    
    @Test
    void testCreateNumberArray2D_EmptyArray() {
        double[][] input = {};
        Number[][] result = DataUtilities.createNumberArray2D(input);

        assertEquals(0, result.length);
    }
    
    @Test
    void testCreateNumberArray2D_RowWithEmptyArray() {
        double[][] input = {
            {},
            {5.0}
        };

        Number[][] result = DataUtilities.createNumberArray2D(input);

        assertEquals(2, result.length);
        assertEquals(0, result[0].length);
        assertEquals(1, result[1].length);
        assertEquals(5.0, result[1][0]);
    }
    
    @Test
    void testCreateNumberArray2D_NullInput() {
        assertThrows(InvalidParameterException.class, () -> {
            DataUtilities.createNumberArray2D(null);
        });
    }



}


