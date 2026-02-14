package com.reedmanit.retirementmaths.montecarlo;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AuHistoricalSeriesTest {

    @Test
    void constructor_loadsNonEmptySamples() {
        AuHistoricalSeries series = new AuHistoricalSeries();

        List<ReturnInflation> samples = series.samples();
        assertNotNull(samples, "samples() should not return null");
        assertFalse(samples.isEmpty(), "samples should not be empty (CSV should have data)");

        for (ReturnInflation ri : samples) {
            assertNotNull(ri, "each sample should be non-null");
            assertTrue(Double.isFinite(ri.nominalReturn()), "nominalReturn should be finite");
            assertTrue(Double.isFinite(ri.inflation()), "inflation should be finite");
        }
    }

    @Test
    void samples_areUnmodifiable() {
        AuHistoricalSeries series = new AuHistoricalSeries();
        List<ReturnInflation> samples = series.samples();

        assertThrows(UnsupportedOperationException.class,
                () -> samples.add(new ReturnInflation(0.01, 0.02)),
                "samples list should be unmodifiable");
    }

    @Test
    void loadFromCsv_missingResource_throwsIllegalStateException() throws Exception {
        Method m = AuHistoricalSeries.class.getDeclaredMethod("loadFromCsv", String.class);
        m.setAccessible(true);

        InvocationTargetException ex = assertThrows(InvocationTargetException.class,
                () -> m.invoke(null, "data/does_not_exist.csv"));

        Throwable cause = ex.getCause();
        assertNotNull(cause);
        assertInstanceOf(IllegalStateException.class, cause);
        assertTrue(cause.getMessage().contains("Failed to load AU historical CSV"),
                "Exception message should explain CSV load failure");
    }

}
