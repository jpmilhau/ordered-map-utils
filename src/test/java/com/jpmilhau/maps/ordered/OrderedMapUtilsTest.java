package com.jpmilhau.maps.ordered;

/*-
 * #%L
 * OrderedMapUtils
 * %%
 * Copyright (C) 2016 - 2019 Jean-Pierre Milhau
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import org.junit.Test;

import com.jpmilhau.maps.ordered.KeyPair;
import com.jpmilhau.maps.ordered.Operation;
import com.jpmilhau.maps.ordered.OrderedMapUtils;

import static org.junit.Assert.*;

/**
 * 
 * Test of OrderedMapUtils
 * 
 * @author Jean-Pierre Milhau
 *
 */
public class OrderedMapUtilsTest {
	
	private final static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	@Test
	public void applyTest() throws ParseException {
		LinkedHashMap<Date, Float> temperatures1 = TestData.getInstance().getTemperaturesAsLinkedHashMap();
		LinkedHashMap<Date, Operation<Date, Float>> results1 = OrderedMapUtils.apply(temperatures1, (t1, t2) -> (t2 - t1));
		checkApplyResults(results1);
		
		SortedMap<Date, Float> temperatures2 = TestData.getInstance().getTemperaturesAsSortedMap();
		SortedMap<Date, Operation<Date, Float>> results2 = OrderedMapUtils.apply(temperatures2, (t1, t2) -> (t2 - t1));
		checkApplyResults(results2);
	}
	
	@Test
	public void filterAllMaxTest() throws ParseException {
		LinkedHashMap<Date, Float> temperatures1 = TestData.getInstance().getTemperaturesAsLinkedHashMap();
		List<Operation<Date, Float>> results1 = OrderedMapUtils.filterAllMax(temperatures1, (t1, t2) -> Math.abs(t2 - t1));
		checkFilterAllMaxResults(results1);
		
		SortedMap<Date, Float> temperatures2 = TestData.getInstance().getTemperaturesAsSortedMap();
		List<Operation<Date, Float>> results2 = OrderedMapUtils.filterAllMax(temperatures2, (t1, t2) -> Math.abs(t2 - t1));
		checkFilterAllMaxResults(results2);
	}
	
	@Test
	public void filterAllMinTest() throws ParseException {
		LinkedHashMap<Date, Float> temperatures1 = TestData.getInstance().getTemperaturesAsLinkedHashMap();
		List<Operation<Date, Float>> results1 = OrderedMapUtils.filterAllMin(temperatures1, (t1, t2) -> Math.abs(t2 - t1));
		checkFilterAllMinResults(results1);
		
		SortedMap<Date, Float> temperatures2 = TestData.getInstance().getTemperaturesAsSortedMap();
		List<Operation<Date, Float>> results2 = OrderedMapUtils.filterAllMin(temperatures2, (t1, t2) -> Math.abs(t2 - t1));
		checkFilterAllMinResults(results2);
	}
	
	@Test
	public void averageTest() throws ParseException {
		LinkedHashMap<Date, Float> temperatures1 = TestData.getInstance().getTemperaturesAsLinkedHashMap();
		double result1 = OrderedMapUtils.average(temperatures1, (t1, t2) -> Math.abs(t2 - t1));
		checkAverageResult(result1);
		
		SortedMap<Date, Float> temperatures2 = TestData.getInstance().getTemperaturesAsSortedMap();
		double result2 = OrderedMapUtils.average(temperatures2, (t1, t2) -> Math.abs(t2 - t1));
		checkAverageResult(result2);
	}
	
	@Test
	public void testTest() throws ParseException {
		LinkedHashMap<Date, Float> temperatures1 = TestData.getInstance().getTemperaturesAsLinkedHashMap();
		LinkedHashMap<Date, Operation<Date, Boolean>> results1 = OrderedMapUtils.test(temperatures1, (t1, t2) -> Math.abs(t2 - t1) >= 8f);
		checkTestResults(results1);
		
		SortedMap<Date, Float> temperatures2 = TestData.getInstance().getTemperaturesAsSortedMap();
		SortedMap<Date, Operation<Date, Boolean>> results2 = OrderedMapUtils.test(temperatures2, (t1, t2) -> Math.abs(t2 - t1) >= 8f);
		checkTestResults(results2);
	}
	
	@Test
	public void testFilterTests() throws ParseException {
		LinkedHashMap<Date, Float> temperatures1 = TestData.getInstance().getTemperaturesAsLinkedHashMap();
		List<KeyPair<Date>> results1 = OrderedMapUtils.filterTests(temperatures1, (t1, t2) -> Math.abs(t2 - t1) >= 8f, true);
		checkFilterTestsResults(results1);
		
		LinkedHashMap<Date, Float> temperatures2 = TestData.getInstance().getTemperaturesAsLinkedHashMap();
		List<KeyPair<Date>> results2 = OrderedMapUtils.filterTests(temperatures2, (t1, t2) -> Math.abs(t2 - t1) >= 8f, true);
		checkFilterTestsResults(results2);
	}
	
	@Test
	public void testFilterGroupedConsecutiveTests() throws ParseException {
		LinkedHashMap<Date, Float> temperatures1 = TestData.getInstance().getTemperaturesAsLinkedHashMap();
		List<List<KeyPair<Date>>> results1 = OrderedMapUtils.filterGroupedConsecutiveTests(temperatures1, (t1, t2) -> t2 - t1 > 0f, true);
		checkFilterGroupedConsecutiveTestsResults1(results1);
		
		results1 = OrderedMapUtils.filterGroupedConsecutiveTests(temperatures1, (t1, t2) -> t2 - t1 > 0f, true, 5, 5);
		checkFilterGroupedConsecutiveTestsResults2(results1);
		
		SortedMap<Date, Float> temperatures2 = TestData.getInstance().getTemperaturesAsSortedMap();
		List<List<KeyPair<Date>>> results2 = OrderedMapUtils.filterGroupedConsecutiveTests(temperatures2, (t1, t2) -> t2 - t1 > 0f, true);
		checkFilterGroupedConsecutiveTestsResults1(results2);
		
		results2 = OrderedMapUtils.filterGroupedConsecutiveTests(temperatures2, (t1, t2) -> t2 - t1 > 0f, true, 5, 5);
		checkFilterGroupedConsecutiveTestsResults2(results2);
	}
	
	@Test
	public void testFilterAllMaxGroupedConsecutiveTests() throws ParseException {
		LinkedHashMap<Date, Float> temperatures1 = TestData.getInstance().getTemperaturesAsLinkedHashMap();
		List<List<KeyPair<Date>>> results1 = OrderedMapUtils.filterAllMaxGroupedConsecutiveTests(temperatures1, (t1, t2) -> t2 - t1 > 0f, true);
		checkFilterAllMaxGroupedConsecutiveTestsResults(results1);
		
		SortedMap<Date, Float> temperatures2 = TestData.getInstance().getTemperaturesAsSortedMap();
		List<List<KeyPair<Date>>> results2 = OrderedMapUtils.filterAllMaxGroupedConsecutiveTests(temperatures2, (t1, t2) -> t2 - t1 > 0f, true);
		checkFilterAllMaxGroupedConsecutiveTestsResults(results2);
	}
	
	@Test
	public void testFilterAllMinGroupedConsecutiveTests() throws ParseException {
		LinkedHashMap<Date, Float> temperatures1 = TestData.getInstance().getTemperaturesAsLinkedHashMap();
		List<List<KeyPair<Date>>> results1 = OrderedMapUtils.filterAllMinGroupedConsecutiveTests(temperatures1, (t1, t2) -> t2 - t1 > 0f, true);
		checkFilterAllMinGroupedConsecutiveTestsResults(results1);
		
		SortedMap<Date, Float> temperatures2 = TestData.getInstance().getTemperaturesAsSortedMap();
		List<List<KeyPair<Date>>> results2 = OrderedMapUtils.filterAllMinGroupedConsecutiveTests(temperatures2, (t1, t2) -> t2 - t1 > 0f, true);
		checkFilterAllMinGroupedConsecutiveTestsResults(results2);
	}
	
	@Test
	public void averageGroupedConsecutiveTests() throws ParseException {
		LinkedHashMap<Date, Float> temperatures1 = TestData.getInstance().getTemperaturesAsLinkedHashMap();
		double result1 = OrderedMapUtils.averageGroupedConsecutiveTests(temperatures1, (t1, t2) -> t2 - t1 > 0f, true);
		checkAverageGroupedConsecutiveTestsResult(result1);
		
		SortedMap<Date, Float> temperatures2 = TestData.getInstance().getTemperaturesAsSortedMap();
		double result2 = OrderedMapUtils.averageGroupedConsecutiveTests(temperatures2, (t1, t2) -> t2 - t1 > 0f, true);
		checkAverageGroupedConsecutiveTestsResult(result2);
	}
	
	private void checkApplyResults(Map<Date, Operation<Date, Float>> results) throws ParseException {
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

	private void checkFilterAllMaxResults(List<Operation<Date, Float>> results) throws ParseException {
		assertEquals(1, results.size());
		
		assertEquals(formatter.parse("02/04/2016"), results.get(0).getKey());
		assertEquals(formatter.parse("03/04/2016"), results.get(0).getNextKey());
		assertFloatEquality(Float.valueOf(10f), results.get(0).getResult());
	}

	private void checkFilterAllMinResults(List<Operation<Date, Float>> results) throws ParseException {
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

	private void checkAverageResult(double result) {
		assertDoubleEquality(Double.valueOf(2.187f), result);
	}

	private void checkTestResults(Map<Date, Operation<Date, Boolean>> results) throws ParseException {
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

	private void checkFilterTestsResults(List<KeyPair<Date>> results) throws ParseException {
		assertEquals(3, results.size());
		
		assertEquals(formatter.parse("02/04/2016"), results.get(0).getKey());
		assertEquals(formatter.parse("03/04/2016"), results.get(0).getNextKey());
		
		assertEquals(formatter.parse("13/05/2016"), results.get(1).getKey());
		assertEquals(formatter.parse("14/05/2016"), results.get(1).getNextKey());
		
		assertEquals(formatter.parse("21/05/2016"), results.get(2).getKey());
		assertEquals(formatter.parse("22/05/2016"), results.get(2).getNextKey());
	}

	private void checkFilterGroupedConsecutiveTestsResults1(List<List<KeyPair<Date>>> results) {
		assertEquals(102, results.size());
	}

	private void checkFilterGroupedConsecutiveTestsResults2(List<List<KeyPair<Date>>> results) throws ParseException {
		assertEquals(1, results.size());
		
		assertEquals(formatter.parse("19/09/2016"), results.get(0).get(0).getKey());
		assertEquals(formatter.parse("24/09/2016"), results.get(0).get(4).getNextKey());
	}

	private void checkFilterAllMaxGroupedConsecutiveTestsResults(List<List<KeyPair<Date>>> results) throws ParseException {
		assertEquals(2, results.size());
		
		assertEquals(6, results.get(0).size());
		assertEquals(formatter.parse("13/07/2016"), results.get(0).get(0).getKey());
		assertEquals(formatter.parse("19/07/2016"), results.get(0).get(5).getNextKey());
		
		assertEquals(6, results.get(1).size());
		assertEquals(formatter.parse("20/12/2016"), results.get(1).get(0).getKey());
		assertEquals(formatter.parse("26/12/2016"), results.get(1).get(5).getNextKey());
	}

	private void checkFilterAllMinGroupedConsecutiveTestsResults(List<List<KeyPair<Date>>> results) {
		assertEquals(55, results.size());
	}

	private void checkAverageGroupedConsecutiveTestsResult(double result) {
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
