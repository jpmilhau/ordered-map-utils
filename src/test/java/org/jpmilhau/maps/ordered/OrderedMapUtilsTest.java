package org.jpmilhau.maps.ordered;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

public class OrderedMapUtilsTest {
	
	private final static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	@Test
	public void applyTest() throws ParseException {
		LinkedHashMap<Date, Float> temperatures = TestData.getInstance().getTemperatures();
		LinkedHashMap<Date, Operation<Date, Float>> results = OrderedMapUtils.apply(temperatures, (t1, t2) -> (t2 - t1));
		assertEquals(365, results.size());
		
		assertEquals(formatter.parse("01/01/2016"), results.get(formatter.parse("01/01/2016")).getKey());
		assertEquals(formatter.parse("02/01/2016"), results.get(formatter.parse("01/01/2016")).getNextKey());
		assertFloatEquality(Float.valueOf(1.1f), results.get(formatter.parse("01/01/2016")).getResult());
		
		assertEquals(formatter.parse("12/05/2016"), results.get(formatter.parse("12/05/2016")).getKey());
		assertEquals(formatter.parse("13/05/2016"), results.get(formatter.parse("12/05/2016")).getNextKey());
		assertFloatEquality(Float.valueOf(3.3f), results.get(formatter.parse("12/05/2016")).getResult());
		
		assertEquals(formatter.parse("30/12/2016"), results.get(formatter.parse("30/12/2016")).getKey());
		assertEquals(formatter.parse("31/12/2016"), results.get(formatter.parse("30/12/2016")).getNextKey());
		assertFloatEquality(Float.valueOf(-1.3f), results.get(formatter.parse("30/12/2016")).getResult());
		
		assertNull(results.get(formatter.parse("31/12/2016")));
	}
	
	@Test
	public void filterAllMaxTest() throws ParseException {
		LinkedHashMap<Date, Float> temperatures = TestData.getInstance().getTemperatures();
		List<Operation<Date, Float>> results = OrderedMapUtils.filterAllMax(temperatures, (t1, t2) -> Math.abs(t2 - t1));
		assertEquals(1, results.size());
		
		assertEquals(formatter.parse("02/04/2016"), results.get(0).getKey());
		assertEquals(formatter.parse("03/04/2016"), results.get(0).getNextKey());
		assertFloatEquality(Float.valueOf(10f), results.get(0).getResult());
	}
	
	@Test
	public void filterAllMinTest() throws ParseException {
		LinkedHashMap<Date, Float> temperatures = TestData.getInstance().getTemperatures();
		List<Operation<Date, Float>> results = OrderedMapUtils.filterAllMin(temperatures, (t1, t2) -> Math.abs(t2 - t1));
		assertEquals(6, results.size());
		
		assertEquals(formatter.parse("27/03/2016"), results.get(0).getKey());
		assertEquals(formatter.parse("28/03/2016"), results.get(0).getNextKey());
		assertFloatEquality(Float.valueOf(0f), results.get(0).getResult());
		
		assertEquals(formatter.parse("29/06/2016"), results.get(1).getKey());
		assertEquals(formatter.parse("30/06/2016"), results.get(1).getNextKey());
		assertFloatEquality(Float.valueOf(0f), results.get(1).getResult());
		
		assertEquals(formatter.parse("24/08/2016"), results.get(2).getKey());
		assertEquals(formatter.parse("25/08/2016"), results.get(2).getNextKey());
		assertFloatEquality(Float.valueOf(0f), results.get(2).getResult());
		
		assertEquals(formatter.parse("02/09/2016"), results.get(3).getKey());
		assertEquals(formatter.parse("03/09/2016"), results.get(3).getNextKey());
		assertFloatEquality(Float.valueOf(0f), results.get(3).getResult());
		
		assertEquals(formatter.parse("30/11/2016"), results.get(4).getKey());
		assertEquals(formatter.parse("01/12/2016"), results.get(4).getNextKey());
		assertFloatEquality(Float.valueOf(0f), results.get(4).getResult());
		
		assertEquals(formatter.parse("06/12/2016"), results.get(5).getKey());
		assertEquals(formatter.parse("07/12/2016"), results.get(5).getNextKey());
		assertFloatEquality(Float.valueOf(0f), results.get(5).getResult());
	}
	
	@Test
	public void averageTest() throws ParseException {
		LinkedHashMap<Date, Float> temperatures = TestData.getInstance().getTemperatures();
		double result = OrderedMapUtils.average(temperatures, (t1, t2) -> Math.abs(t2 - t1));
		assertDoubleEquality(Double.valueOf(2.187f), result);
	}
	
	@Test
	public void testTest() throws ParseException {
		LinkedHashMap<Date, Float> temperatures = TestData.getInstance().getTemperatures();
		LinkedHashMap<Date, Operation<Date, Boolean>> results = OrderedMapUtils.test(temperatures, (t1, t2) -> Math.abs(t2 - t1) >= 8f);
		assertEquals(365, results.size());
		
		assertEquals(formatter.parse("01/01/2016"), results.get(formatter.parse("01/01/2016")).getKey());
		assertEquals(formatter.parse("02/01/2016"), results.get(formatter.parse("01/01/2016")).getNextKey());
		assertFalse(results.get(formatter.parse("01/01/2016")).getResult());
		
		assertEquals(formatter.parse("02/04/2016"), results.get(formatter.parse("02/04/2016")).getKey());
		assertEquals(formatter.parse("03/04/2016"), results.get(formatter.parse("02/04/2016")).getNextKey());
		assertTrue(results.get(formatter.parse("02/04/2016")).getResult());
		
		assertEquals(formatter.parse("30/12/2016"), results.get(formatter.parse("30/12/2016")).getKey());
		assertEquals(formatter.parse("31/12/2016"), results.get(formatter.parse("30/12/2016")).getNextKey());
		assertFalse(results.get(formatter.parse("30/12/2016")).getResult());
		
		assertNull(results.get(formatter.parse("31/12/2016")));
	}
	
	@Test
	public void testFilterTests() throws ParseException {
		LinkedHashMap<Date, Float> temperatures = TestData.getInstance().getTemperatures();
		List<KeyPair<Date>> results = OrderedMapUtils.filterTests(temperatures, (t1, t2) -> Math.abs(t2 - t1) >= 8f, true);
		assertEquals(3, results.size());
		
		assertEquals(formatter.parse("02/04/2016"), results.get(0).getKey());
		assertEquals(formatter.parse("03/04/2016"), results.get(0).getNextKey());
		
		assertEquals(formatter.parse("13/05/2016"), results.get(1).getKey());
		assertEquals(formatter.parse("14/05/2016"), results.get(1).getNextKey());
		
		assertEquals(formatter.parse("21/05/2016"), results.get(2).getKey());
		assertEquals(formatter.parse("22/05/2016"), results.get(2).getNextKey());
	}
	
	@Test
	public void testFilterGroupedConsecutiveTests() throws ParseException {
		LinkedHashMap<Date, Float> temperatures = TestData.getInstance().getTemperatures();
		List<List<KeyPair<Date>>> results = OrderedMapUtils.filterGroupedConsecutiveTests(temperatures, (t1, t2) -> t2 - t1 > 0f, true);
		assertEquals(102, results.size());
		
		results = OrderedMapUtils.filterGroupedConsecutiveTests(temperatures, (t1, t2) -> t2 - t1 > 0f, true, 5, 5);
		assertEquals(1, results.size());
		
		assertEquals(formatter.parse("19/09/2016"), results.get(0).get(0).getKey());
		assertEquals(formatter.parse("24/09/2016"), results.get(0).get(4).getNextKey());
	}
	
	@Test
	public void testFilterAllMaxGroupedConsecutiveTests() throws ParseException {
		LinkedHashMap<Date, Float> temperatures = TestData.getInstance().getTemperatures();
		List<List<KeyPair<Date>>> results = OrderedMapUtils.filterAllMaxGroupedConsecutiveTests(temperatures, (t1, t2) -> t2 - t1 > 0f, true);
		assertEquals(2, results.size());
		
		assertEquals(6, results.get(0).size());
		assertEquals(formatter.parse("13/07/2016"), results.get(0).get(0).getKey());
		assertEquals(formatter.parse("19/07/2016"), results.get(0).get(5).getNextKey());
		
		assertEquals(6, results.get(1).size());
		assertEquals(formatter.parse("20/12/2016"), results.get(1).get(0).getKey());
		assertEquals(formatter.parse("26/12/2016"), results.get(1).get(5).getNextKey());
	}
	
	@Test
	public void testFilterAllMinGroupedConsecutiveTests() throws ParseException {
		LinkedHashMap<Date, Float> temperatures = TestData.getInstance().getTemperatures();
		List<List<KeyPair<Date>>> results = OrderedMapUtils.filterAllMinGroupedConsecutiveTests(temperatures, (t1, t2) -> t2 - t1 > 0f, true);
		assertEquals(55, results.size());
	}
	
	@Test
	public void averageGroupedConsecutiveTests() throws ParseException {
		LinkedHashMap<Date, Float> temperatures = TestData.getInstance().getTemperatures();
		double result = OrderedMapUtils.averageGroupedConsecutiveTests(temperatures, (t1, t2) -> t2 - t1 > 0f, true);
		assertDoubleEquality(Double.valueOf(1.814f), result);
	}

	private static void assertFloatEquality(Float f1, Float f2) {
		if (Math.abs(f1 - f2) >= 0.1f) {
			fail(MessageFormat.format("Expected : {0}, Got: {1}", f1, f2));
		}
	}
	
	private static void assertDoubleEquality(Double d1, Double d2) {
		if (Math.abs(d1 - d2) >= 0.001f) {
			fail(MessageFormat.format("Expected : {0}, Got: {1}", d1, d2));
		}
	}
}
