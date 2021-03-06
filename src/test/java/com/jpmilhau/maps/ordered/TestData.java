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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.SortedMap;

/**
 * 
 * Test data
 * 
 * @author Jean-Pierre Milhau
 *
 */
public class TestData {
	
	private static final TestData instance = new TestData();
	
	private HashMap<Date, Float> temperatures;
	
	private LinkedHashMap<Date, Float> temperaturesAsLinkedHashMap;
	
	private SortedMap<Date, Float> temperaturesAsSortedMap;
	
	public static TestData getInstance() {
		return instance;
	}
	
	public LinkedHashMap<Date, Float> getTemperaturesAsLinkedHashMap() {
		if (temperaturesAsLinkedHashMap == null) {
			temperaturesAsLinkedHashMap = OrderedMapUtils.toLinkedHashMap(temperatures);
		}
		return temperaturesAsLinkedHashMap;
	}
	
	public SortedMap<Date, Float> getTemperaturesAsSortedMap() {
		if (temperaturesAsSortedMap == null) {
			temperaturesAsSortedMap = OrderedMapUtils.toTreeMap(temperatures);
		}
		return temperaturesAsSortedMap;
	}
	
	private TestData() {
		initTemperatures();
	}
	
	private void initTemperatures() {
		temperatures = new HashMap<Date, Float>();
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			// January
			temperatures.put(formatter.parse("01/01/2016"), 8.9f);
			temperatures.put(formatter.parse("02/01/2016"), 10f);
			temperatures.put(formatter.parse("03/01/2016"), 7.7f);
			temperatures.put(formatter.parse("04/01/2016"), 9.3f);
			temperatures.put(formatter.parse("05/01/2016"), 8.4f);
			temperatures.put(formatter.parse("06/01/2016"), 9f);
			temperatures.put(formatter.parse("07/01/2016"), 11.1f);
			temperatures.put(formatter.parse("08/01/2016"), 7.1f);
			temperatures.put(formatter.parse("09/01/2016"), 10.6f);
			temperatures.put(formatter.parse("10/01/2016"), 7.6f);
			temperatures.put(formatter.parse("11/01/2016"), 8.3f);
			temperatures.put(formatter.parse("12/01/2016"), 6.8f);
			temperatures.put(formatter.parse("13/01/2016"), 6.4f);
			temperatures.put(formatter.parse("14/01/2016"), 7.4f);
			temperatures.put(formatter.parse("15/01/2016"), 4.7f);
			temperatures.put(formatter.parse("16/01/2016"), 4.4f);
			temperatures.put(formatter.parse("17/01/2016"), 2.5f);
			temperatures.put(formatter.parse("18/01/2016"), -0.1f);
			temperatures.put(formatter.parse("19/01/2016"), -0.2f);
			temperatures.put(formatter.parse("20/01/2016"), 1.2f);
			temperatures.put(formatter.parse("21/01/2016"), 3.4f);
			temperatures.put(formatter.parse("22/01/2016"), 7.6f);
			temperatures.put(formatter.parse("23/01/2016"), 6.6f);
			temperatures.put(formatter.parse("24/01/2016"), 11f);
			temperatures.put(formatter.parse("25/01/2016"), 12.4f);
			temperatures.put(formatter.parse("26/01/2016"), 11.3f);
			temperatures.put(formatter.parse("27/01/2016"), 11.6f);
			temperatures.put(formatter.parse("28/01/2016"), 8.4f);
			temperatures.put(formatter.parse("29/01/2016"), 8.3f);
			temperatures.put(formatter.parse("30/01/2016"), 9.2f);
			temperatures.put(formatter.parse("31/01/2016"), 13.3f);
			// February
			temperatures.put(formatter.parse("01/02/2016"), 12.1f);
			temperatures.put(formatter.parse("02/02/2016"), 11.2f);
			temperatures.put(formatter.parse("03/02/2016"), 7.8f);
			temperatures.put(formatter.parse("04/02/2016"), 9.9f);
			temperatures.put(formatter.parse("05/02/2016"), 9.7f);
			temperatures.put(formatter.parse("06/02/2016"), 13f);
			temperatures.put(formatter.parse("07/02/2016"), 11.4f);
			temperatures.put(formatter.parse("08/02/2016"), 10.9f);
			temperatures.put(formatter.parse("09/02/2016"), 11.7f);
			temperatures.put(formatter.parse("10/02/2016"), 7.3f);
			temperatures.put(formatter.parse("11/02/2016"), 7.8f);
			temperatures.put(formatter.parse("12/02/2016"), 4f);
			temperatures.put(formatter.parse("13/02/2016"), 8.9f);
			temperatures.put(formatter.parse("14/02/2016"), 7.6f);
			temperatures.put(formatter.parse("15/02/2016"), 6.6f);
			temperatures.put(formatter.parse("16/02/2016"), 5.6f);
			temperatures.put(formatter.parse("17/02/2016"), 3.4f);
			temperatures.put(formatter.parse("18/02/2016"), 3.9f);
			temperatures.put(formatter.parse("19/02/2016"), 8.7f);
			temperatures.put(formatter.parse("20/02/2016"), 11.9f);
			temperatures.put(formatter.parse("21/02/2016"), 11.7f);
			temperatures.put(formatter.parse("22/02/2016"), 11.3f);
			temperatures.put(formatter.parse("23/02/2016"), 8.9f);
			temperatures.put(formatter.parse("24/02/2016"), 6.1f);
			temperatures.put(formatter.parse("25/02/2016"), 6.6f);
			temperatures.put(formatter.parse("26/02/2016"), 5.8f);
			temperatures.put(formatter.parse("27/02/2016"), 7.1f);
			temperatures.put(formatter.parse("28/02/2016"), 6.3f);
			temperatures.put(formatter.parse("29/02/2016"), 7.1f);
			// March
			temperatures.put(formatter.parse("01/03/2016"), 9.5f);
			temperatures.put(formatter.parse("02/03/2016"), 10f);
			temperatures.put(formatter.parse("03/03/2016"), 8.7f);
			temperatures.put(formatter.parse("04/03/2016"), 7.8f);
			temperatures.put(formatter.parse("05/03/2016"), 7.3f);
			temperatures.put(formatter.parse("06/03/2016"), 8f);
			temperatures.put(formatter.parse("07/03/2016"), 5.8f);
			temperatures.put(formatter.parse("08/03/2016"), 8.7f);
			temperatures.put(formatter.parse("09/03/2016"), 9.8f);
			temperatures.put(formatter.parse("10/03/2016"), 7.4f);
			temperatures.put(formatter.parse("11/03/2016"), 10.5f);
			temperatures.put(formatter.parse("12/03/2016"), 11f);
			temperatures.put(formatter.parse("13/03/2016"), 10.3f);
			temperatures.put(formatter.parse("14/03/2016"), 7.6f);
			temperatures.put(formatter.parse("15/03/2016"), 11.8f);
			temperatures.put(formatter.parse("16/03/2016"), 7.2f);
			temperatures.put(formatter.parse("17/03/2016"), 12.3f);
			temperatures.put(formatter.parse("18/03/2016"), 10f);
			temperatures.put(formatter.parse("19/03/2016"), 6.3f);
			temperatures.put(formatter.parse("20/03/2016"), 6f);
			temperatures.put(formatter.parse("21/03/2016"), 9.7f);
			temperatures.put(formatter.parse("22/03/2016"), 11.9f);
			temperatures.put(formatter.parse("23/03/2016"), 8.9f);
			temperatures.put(formatter.parse("24/03/2016"), 10f);
			temperatures.put(formatter.parse("25/03/2016"), 13.5f);
			temperatures.put(formatter.parse("26/03/2016"), 14f);
			temperatures.put(formatter.parse("27/03/2016"), 12.9f);
			temperatures.put(formatter.parse("28/03/2016"), 12.9f);
			temperatures.put(formatter.parse("29/03/2016"), 13.4f);
			temperatures.put(formatter.parse("30/03/2016"), 8.9f);
			temperatures.put(formatter.parse("31/03/2016"), 6.4f);
			// April
			temperatures.put(formatter.parse("01/04/2016"), 11.5f);
			temperatures.put(formatter.parse("02/04/2016"), 10f);
			temperatures.put(formatter.parse("03/04/2016"), 20f);
			temperatures.put(formatter.parse("04/04/2016"), 16f);
			temperatures.put(formatter.parse("05/04/2016"), 14.5f);
			temperatures.put(formatter.parse("06/04/2016"), 13.3f);
			temperatures.put(formatter.parse("07/04/2016"), 11.2f);
			temperatures.put(formatter.parse("08/04/2016"), 12.3f);
			temperatures.put(formatter.parse("09/04/2016"), 11.6f);
			temperatures.put(formatter.parse("10/04/2016"), 17f);
			temperatures.put(formatter.parse("11/04/2016"), 14.8f);
			temperatures.put(formatter.parse("12/04/2016"), 16.4f);
			temperatures.put(formatter.parse("13/04/2016"), 17f);
			temperatures.put(formatter.parse("14/04/2016"), 15.4f);
			temperatures.put(formatter.parse("15/04/2016"), 15.7f);
			temperatures.put(formatter.parse("16/04/2016"), 14.3f);
			temperatures.put(formatter.parse("17/04/2016"), 9.5f);
			temperatures.put(formatter.parse("18/04/2016"), 13.4f);
			temperatures.put(formatter.parse("19/04/2016"), 15.2f);
			temperatures.put(formatter.parse("20/04/2016"), 18.6f);
			temperatures.put(formatter.parse("21/04/2016"), 20.1f);
			temperatures.put(formatter.parse("22/04/2016"), 15.2f);
			temperatures.put(formatter.parse("23/04/2016"), 10.1f);
			temperatures.put(formatter.parse("24/04/2016"), 8.5f);
			temperatures.put(formatter.parse("25/04/2016"), 10.6f);
			temperatures.put(formatter.parse("26/04/2016"), 8.6f);
			temperatures.put(formatter.parse("27/04/2016"), 10f);
			temperatures.put(formatter.parse("28/04/2016"), 11.7f);
			temperatures.put(formatter.parse("29/04/2016"), 13.6f);
			temperatures.put(formatter.parse("30/04/2016"), 10.2f);
			// May
			temperatures.put(formatter.parse("01/05/2016"), 14.8f);
			temperatures.put(formatter.parse("02/05/2016"), 17.4f);
			temperatures.put(formatter.parse("03/05/2016"), 15.5f);
			temperatures.put(formatter.parse("04/05/2016"), 17.5f);
			temperatures.put(formatter.parse("05/05/2016"), 19.7f);
			temperatures.put(formatter.parse("06/05/2016"), 24f);
			temperatures.put(formatter.parse("07/05/2016"), 23f);
			temperatures.put(formatter.parse("08/05/2016"), 24.5f);
			temperatures.put(formatter.parse("09/05/2016"), 19.3f);
			temperatures.put(formatter.parse("10/05/2016"), 17.4f);
			temperatures.put(formatter.parse("11/05/2016"), 16.7f);
			temperatures.put(formatter.parse("12/05/2016"), 18.3f);
			temperatures.put(formatter.parse("13/05/2016"), 21.6f);
			temperatures.put(formatter.parse("14/05/2016"), 13f);
			temperatures.put(formatter.parse("15/05/2016"), 15.3f);
			temperatures.put(formatter.parse("16/05/2016"), 16.6f);
			temperatures.put(formatter.parse("17/05/2016"), 17.3f);
			temperatures.put(formatter.parse("18/05/2016"), 15.2f);
			temperatures.put(formatter.parse("19/05/2016"), 16.4f);
			temperatures.put(formatter.parse("20/05/2016"), 20.4f);
			temperatures.put(formatter.parse("21/05/2016"), 23.6f);
			temperatures.put(formatter.parse("22/05/2016"), 15.6f);
			temperatures.put(formatter.parse("23/05/2016"), 15.7f);
			temperatures.put(formatter.parse("24/05/2016"), 14.2f);
			temperatures.put(formatter.parse("25/05/2016"), 19.7f);
			temperatures.put(formatter.parse("26/05/2016"), 20.7f);
			temperatures.put(formatter.parse("27/05/2016"), 23.6f);
			temperatures.put(formatter.parse("28/05/2016"), 21.7f);
			temperatures.put(formatter.parse("29/05/2016"), 16.8f);
			temperatures.put(formatter.parse("30/05/2016"), 12.8f);
			temperatures.put(formatter.parse("31/05/2016"), 13.1f);
			// June
			temperatures.put(formatter.parse("01/06/2016"), 15.7f);
			temperatures.put(formatter.parse("02/06/2016"), 12.6f);
			temperatures.put(formatter.parse("03/06/2016"), 12.4f);
			temperatures.put(formatter.parse("04/06/2016"), 16.8f);
			temperatures.put(formatter.parse("05/06/2016"), 18.3f);
			temperatures.put(formatter.parse("06/06/2016"), 23.4f);
			temperatures.put(formatter.parse("07/06/2016"), 25.2f);
			temperatures.put(formatter.parse("08/06/2016"), 22.3f);
			temperatures.put(formatter.parse("09/06/2016"), 23.2f);
			temperatures.put(formatter.parse("10/06/2016"), 23.1f);
			temperatures.put(formatter.parse("11/06/2016"), 20.1f);
			temperatures.put(formatter.parse("12/06/2016"), 17.9f);
			temperatures.put(formatter.parse("13/06/2016"), 17.2f);
			temperatures.put(formatter.parse("14/06/2016"), 18.8f);
			temperatures.put(formatter.parse("15/06/2016"), 19.7f);
			temperatures.put(formatter.parse("16/06/2016"), 18.3f);
			temperatures.put(formatter.parse("17/06/2016"), 19.8f);
			temperatures.put(formatter.parse("18/06/2016"), 18.5f);
			temperatures.put(formatter.parse("19/06/2016"), 21.5f);
			temperatures.put(formatter.parse("20/06/2016"), 16.8f);
			temperatures.put(formatter.parse("21/06/2016"), 22.2f);
			temperatures.put(formatter.parse("22/06/2016"), 26.7f);
			temperatures.put(formatter.parse("23/06/2016"), 30.4f);
			temperatures.put(formatter.parse("24/06/2016"), 23.9f);
			temperatures.put(formatter.parse("25/06/2016"), 19.8f);
			temperatures.put(formatter.parse("26/06/2016"), 21.1f);
			temperatures.put(formatter.parse("27/06/2016"), 21.7f);
			temperatures.put(formatter.parse("28/06/2016"), 21.3f);
			temperatures.put(formatter.parse("29/06/2016"), 22.6f);
			temperatures.put(formatter.parse("30/06/2016"), 22.6f);
			// July
			temperatures.put(formatter.parse("01/07/2016"), 20.9f);
			temperatures.put(formatter.parse("02/07/2016"), 19.3f);
			temperatures.put(formatter.parse("03/07/2016"), 16.8f);
			temperatures.put(formatter.parse("04/07/2016"), 20.9f);
			temperatures.put(formatter.parse("05/07/2016"), 21.4f);
			temperatures.put(formatter.parse("06/07/2016"), 23f);
			temperatures.put(formatter.parse("07/07/2016"), 27.6f);
			temperatures.put(formatter.parse("08/07/2016"), 27.3f);
			temperatures.put(formatter.parse("09/07/2016"), 27.2f);
			temperatures.put(formatter.parse("10/07/2016"), 29.3f);
			temperatures.put(formatter.parse("11/07/2016"), 22.1f);
			temperatures.put(formatter.parse("12/07/2016"), 21.3f);
			temperatures.put(formatter.parse("13/07/2016"), 19.1f);
			temperatures.put(formatter.parse("14/07/2016"), 20.2f);
			temperatures.put(formatter.parse("15/07/2016"), 23.2f);
			temperatures.put(formatter.parse("16/07/2016"), 26.3f);
			temperatures.put(formatter.parse("17/07/2016"), 28.1f);
			temperatures.put(formatter.parse("18/07/2016"), 31f);
			temperatures.put(formatter.parse("19/07/2016"), 34.2f);
			temperatures.put(formatter.parse("20/07/2016"), 27.9f);
			temperatures.put(formatter.parse("21/07/2016"), 24.3f);
			temperatures.put(formatter.parse("22/07/2016"), 25.9f);
			temperatures.put(formatter.parse("23/07/2016"), 26.6f);
			temperatures.put(formatter.parse("24/07/2016"), 26.5f);
			temperatures.put(formatter.parse("25/07/2016"), 24.7f);
			temperatures.put(formatter.parse("26/07/2016"), 22.7f);
			temperatures.put(formatter.parse("27/07/2016"), 24.6f);
			temperatures.put(formatter.parse("28/07/2016"), 23.4f);
			temperatures.put(formatter.parse("29/07/2016"), 24.9f);
			temperatures.put(formatter.parse("30/07/2016"), 21.7f);
			temperatures.put(formatter.parse("31/07/2016"), 24f);
			// August
			temperatures.put(formatter.parse("01/08/2016"), 22.7f);
			temperatures.put(formatter.parse("02/08/2016"), 22.3f);
			temperatures.put(formatter.parse("03/08/2016"), 24.3f);
			temperatures.put(formatter.parse("04/08/2016"), 21.2f);
			temperatures.put(formatter.parse("05/08/2016"), 21.6f);
			temperatures.put(formatter.parse("06/08/2016"), 24.4f);
			temperatures.put(formatter.parse("07/08/2016"), 28.7f);
			temperatures.put(formatter.parse("08/08/2016"), 21.5f);
			temperatures.put(formatter.parse("09/08/2016"), 21.4f);
			temperatures.put(formatter.parse("10/08/2016"), 17.9f);
			temperatures.put(formatter.parse("11/08/2016"), 22.9f);
			temperatures.put(formatter.parse("12/08/2016"), 26.6f);
			temperatures.put(formatter.parse("13/08/2016"), 27.5f);
			temperatures.put(formatter.parse("14/08/2016"), 26.4f);
			temperatures.put(formatter.parse("15/08/2016"), 26.7f);
			temperatures.put(formatter.parse("16/08/2016"), 29.2f);
			temperatures.put(formatter.parse("17/08/2016"), 29.6f);
			temperatures.put(formatter.parse("18/08/2016"), 22.9f);
			temperatures.put(formatter.parse("19/08/2016"), 22f);
			temperatures.put(formatter.parse("20/08/2016"), 23.1f);
			temperatures.put(formatter.parse("21/08/2016"), 21.4f);
			temperatures.put(formatter.parse("22/08/2016"), 27.4f);
			temperatures.put(formatter.parse("23/08/2016"), 31.4f);
			temperatures.put(formatter.parse("24/08/2016"), 35.2f);
			temperatures.put(formatter.parse("25/08/2016"), 35.2f);
			temperatures.put(formatter.parse("26/08/2016"), 32.5f);
			temperatures.put(formatter.parse("27/08/2016"), 32.9f);
			temperatures.put(formatter.parse("28/08/2016"), 26.3f);
			temperatures.put(formatter.parse("29/08/2016"), 23f);
			temperatures.put(formatter.parse("30/08/2016"), 25.2f);
			temperatures.put(formatter.parse("31/08/2016"), 27.4f);
			// September
			temperatures.put(formatter.parse("01/09/2016"), 26.2f);
			temperatures.put(formatter.parse("02/09/2016"), 27.6f);
			temperatures.put(formatter.parse("03/09/2016"), 27.6f);
			temperatures.put(formatter.parse("04/09/2016"), 21.2f);
			temperatures.put(formatter.parse("05/09/2016"), 21.1f);
			temperatures.put(formatter.parse("06/09/2016"), 22.2f);
			temperatures.put(formatter.parse("07/09/2016"), 27.5f);
			temperatures.put(formatter.parse("08/09/2016"), 24.1f);
			temperatures.put(formatter.parse("09/09/2016"), 25.2f);
			temperatures.put(formatter.parse("10/09/2016"), 25.5f);
			temperatures.put(formatter.parse("11/09/2016"), 24.7f);
			temperatures.put(formatter.parse("12/09/2016"), 30.5f);
			temperatures.put(formatter.parse("13/09/2016"), 32f);
			temperatures.put(formatter.parse("14/09/2016"), 26.6f);
			temperatures.put(formatter.parse("15/09/2016"), 19.5f);
			temperatures.put(formatter.parse("16/09/2016"), 19.9f);
			temperatures.put(formatter.parse("17/09/2016"), 16.9f);
			temperatures.put(formatter.parse("18/09/2016"), 20.4f);
			temperatures.put(formatter.parse("19/09/2016"), 18.6f);
			temperatures.put(formatter.parse("20/09/2016"), 19.7f);
			temperatures.put(formatter.parse("21/09/2016"), 21.5f);
			temperatures.put(formatter.parse("22/09/2016"), 22.3f);
			temperatures.put(formatter.parse("23/09/2016"), 23f);
			temperatures.put(formatter.parse("24/09/2016"), 23.7f);
			temperatures.put(formatter.parse("25/09/2016"), 19.7f);
			temperatures.put(formatter.parse("26/09/2016"), 19.8f);
			temperatures.put(formatter.parse("27/09/2016"), 22.5f);
			temperatures.put(formatter.parse("28/09/2016"), 23.1f);
			temperatures.put(formatter.parse("29/09/2016"), 20.9f);
			temperatures.put(formatter.parse("30/09/2016"), 16.8f);
			// October
			temperatures.put(formatter.parse("01/10/2016"), 17.8f);
			temperatures.put(formatter.parse("02/10/2016"), 17.9f);
			temperatures.put(formatter.parse("03/10/2016"), 17.7f);
			temperatures.put(formatter.parse("04/10/2016"), 19f);
			temperatures.put(formatter.parse("05/10/2016"), 16.8f);
			temperatures.put(formatter.parse("06/10/2016"), 14.5f);
			temperatures.put(formatter.parse("07/10/2016"), 14.1f);
			temperatures.put(formatter.parse("08/10/2016"), 16.2f);
			temperatures.put(formatter.parse("09/10/2016"), 15.2f);
			temperatures.put(formatter.parse("10/10/2016"), 14f);
			temperatures.put(formatter.parse("11/10/2016"), 12.3f);
			temperatures.put(formatter.parse("12/10/2016"), 13.4f);
			temperatures.put(formatter.parse("13/10/2016"), 10.2f);
			temperatures.put(formatter.parse("14/10/2016"), 12.8f);
			temperatures.put(formatter.parse("15/10/2016"), 17.1f);
			temperatures.put(formatter.parse("16/10/2016"), 20.3f);
			temperatures.put(formatter.parse("17/10/2016"), 17.4f);
			temperatures.put(formatter.parse("18/10/2016"), 15.1f);
			temperatures.put(formatter.parse("19/10/2016"), 12.7f);
			temperatures.put(formatter.parse("20/10/2016"), 13.4f);
			temperatures.put(formatter.parse("21/10/2016"), 13.5f);
			temperatures.put(formatter.parse("22/10/2016"), 13.8f);
			temperatures.put(formatter.parse("23/10/2016"), 11.7f);
			temperatures.put(formatter.parse("24/10/2016"), 13.7f);
			temperatures.put(formatter.parse("25/10/2016"), 14.2f);
			temperatures.put(formatter.parse("26/10/2016"), 12.2f);
			temperatures.put(formatter.parse("27/10/2016"), 15.3f);
			temperatures.put(formatter.parse("28/10/2016"), 13.3f);
			temperatures.put(formatter.parse("29/10/2016"), 12.8f);
			temperatures.put(formatter.parse("30/10/2016"), 16.4f);
			temperatures.put(formatter.parse("31/10/2016"), 17.3f);
			// November
			temperatures.put(formatter.parse("01/11/2016"), 15f);
			temperatures.put(formatter.parse("02/11/2016"), 11.8f);
			temperatures.put(formatter.parse("03/11/2016"), 11.6f);
			temperatures.put(formatter.parse("04/11/2016"), 7.7f);
			temperatures.put(formatter.parse("05/11/2016"), 10.8f);
			temperatures.put(formatter.parse("06/11/2016"), 8.8f);
			temperatures.put(formatter.parse("07/11/2016"), 5.7f);
			temperatures.put(formatter.parse("08/11/2016"), 6.9f);
			temperatures.put(formatter.parse("09/11/2016"), 9.5f);
			temperatures.put(formatter.parse("10/11/2016"), 10.1f);
			temperatures.put(formatter.parse("11/11/2016"), 9.3f);
			temperatures.put(formatter.parse("12/11/2016"), 6.9f);
			temperatures.put(formatter.parse("13/11/2016"), 9.9f);
			temperatures.put(formatter.parse("14/11/2016"), 8.7f);
			temperatures.put(formatter.parse("15/11/2016"), 12.4f);
			temperatures.put(formatter.parse("16/11/2016"), 11.8f);
			temperatures.put(formatter.parse("17/11/2016"), 12.8f);
			temperatures.put(formatter.parse("18/11/2016"), 10.2f);
			temperatures.put(formatter.parse("19/11/2016"), 10.5f);
			temperatures.put(formatter.parse("20/11/2016"), 12.7f);
			temperatures.put(formatter.parse("21/11/2016"), 15.2f);
			temperatures.put(formatter.parse("22/11/2016"), 11.4f);
			temperatures.put(formatter.parse("23/11/2016"), 13.1f);
			temperatures.put(formatter.parse("24/11/2016"), 12.2f);
			temperatures.put(formatter.parse("25/11/2016"), 8.7f);
			temperatures.put(formatter.parse("26/11/2016"), 9f);
			temperatures.put(formatter.parse("27/11/2016"), 8.2f);
			temperatures.put(formatter.parse("28/11/2016"), 6.2f);
			temperatures.put(formatter.parse("29/11/2016"), 3.9f);
			temperatures.put(formatter.parse("30/11/2016"), 6.8f);
			// December
			temperatures.put(formatter.parse("01/12/2016"), 6.8f);
			temperatures.put(formatter.parse("02/12/2016"), 6.1f);
			temperatures.put(formatter.parse("03/12/2016"), 6.6f);
			temperatures.put(formatter.parse("04/12/2016"), 5f);
			temperatures.put(formatter.parse("05/12/2016"), 10.6f);
			temperatures.put(formatter.parse("06/12/2016"), 11.9f);
			temperatures.put(formatter.parse("07/12/2016"), 11.9f);
			temperatures.put(formatter.parse("08/12/2016"), 11.3f);
			temperatures.put(formatter.parse("09/12/2016"), 10.5f);
			temperatures.put(formatter.parse("10/12/2016"), 8.5f);
			temperatures.put(formatter.parse("11/12/2016"), 8.9f);
			temperatures.put(formatter.parse("12/12/2016"), 5.3f);
			temperatures.put(formatter.parse("13/12/2016"), 8.8f);
			temperatures.put(formatter.parse("14/12/2016"), 9.8f);
			temperatures.put(formatter.parse("15/12/2016"), 8.9f);
			temperatures.put(formatter.parse("16/12/2016"), 10.8f);
			temperatures.put(formatter.parse("17/12/2016"), 8.9f);
			temperatures.put(formatter.parse("18/12/2016"), 3.5f);
			temperatures.put(formatter.parse("19/12/2016"), 5.5f);
			temperatures.put(formatter.parse("20/12/2016"), 4.6f);
			temperatures.put(formatter.parse("21/12/2016"), 6.8f);
			temperatures.put(formatter.parse("22/12/2016"), 8f);
			temperatures.put(formatter.parse("23/12/2016"), 8.1f);
			temperatures.put(formatter.parse("24/12/2016"), 9.1f);
			temperatures.put(formatter.parse("25/12/2016"), 10.2f);
			temperatures.put(formatter.parse("26/12/2016"), 11f);
			temperatures.put(formatter.parse("27/12/2016"), 7.3f);
			temperatures.put(formatter.parse("28/12/2016"), 8.2f);
			temperatures.put(formatter.parse("29/12/2016"), 3.4f);
			temperatures.put(formatter.parse("30/12/2016"), -1.9f);
			temperatures.put(formatter.parse("31/12/2016"), -3.2f);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
