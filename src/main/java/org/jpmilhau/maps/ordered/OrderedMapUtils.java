package org.jpmilhau.maps.ordered;

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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import org.paumard.streams.StreamsUtils;

public class OrderedMapUtils {
	
	private OrderedMapUtils() {
		throw new IllegalStateException("Utility class");
	}
	
	public static <K, V, R> LinkedHashMap<K, Operation<K, R>> apply(LinkedHashMap<K, V> map, BiFunction<V, V, R> biFunction) {
		Objects.requireNonNull(map);
		Objects.requireNonNull(biFunction);
		checkNotSingleEntry(map);
		
		return applyFunctionToConsecutiveElements(map, biFunction);
	}
		
	public static <K, V, R extends Comparable<? super R>> List<Operation<K, R>> filterAllMax(LinkedHashMap<K, V> map, BiFunction<V, V, R> biFunction) {
		Objects.requireNonNull(map);
		Objects.requireNonNull(biFunction);
		checkNotSingleEntry(map);
		
		return filterMaxResults(applyFunctionToConsecutiveElements(map, biFunction));
	}
	
	public static <K, V, R extends Comparable<? super R>> List<Operation<K, R>> filterAllMin(LinkedHashMap<K, V> map, BiFunction<V, V, R> biFunction) {
		Objects.requireNonNull(map);
		Objects.requireNonNull(biFunction);
		checkNotSingleEntry(map);
		
		return filterMinResults(applyFunctionToConsecutiveElements(map, biFunction));
	}
	
	public static <K , V, R extends Number> double average(LinkedHashMap<K, V> map, BiFunction<V, V, R> biFunction) {
		Objects.requireNonNull(map);
		Objects.requireNonNull(biFunction);
		checkNotSingleEntry(map);

		return averageResults(applyFunctionToConsecutiveElements(map, biFunction));
	}
	
	public static <K, V, R> LinkedHashMap<K, Operation<K, Boolean>> test(LinkedHashMap<K, V> map, BiPredicate<V, V> biPredicate) {
		Objects.requireNonNull(map);
		Objects.requireNonNull(biPredicate);
		checkNotSingleEntry(map);

		return applyPredicateToConsecutiveElements(map, biPredicate);
	}
	
	public static <K, V, R> List<KeyPair<K>> filterTests(LinkedHashMap<K, V> map, BiPredicate<V, V> biPredicate, boolean filter) {
		Objects.requireNonNull(map);
		Objects.requireNonNull(biPredicate);
		checkNotSingleEntry(map);

		return filterTests(applyPredicateToConsecutiveElements(map, biPredicate), filter);
	}

	public static <K, V, R> List<List<KeyPair<K>>> filterGroupedConsecutiveTests(LinkedHashMap<K, V> map, BiPredicate<V, V> biPredicate, boolean filter) {
		Objects.requireNonNull(map);
		Objects.requireNonNull(biPredicate);
		checkNotSingleEntry(map);

		return findConsecutiveTests(map, biPredicate, filter);
	}
	
	public static <K, V, R> List<List<KeyPair<K>>> filterGroupedConsecutiveTests(LinkedHashMap<K, V> map, BiPredicate<V, V> biPredicate, boolean filter, int minGroupSize, int maxGroupSize) {
		Objects.requireNonNull(map);
		Objects.requireNonNull(biPredicate);
		checkNotSingleEntry(map);

		return findConsecutiveTests(map, biPredicate, filter, minGroupSize, maxGroupSize);
	}
	
	public static <K, V> List<List<KeyPair<K>>> filterAllMaxGroupedConsecutiveTests(LinkedHashMap<K, V> map, BiPredicate<V, V> biPredicate, boolean filter) {
		Objects.requireNonNull(map);
		Objects.requireNonNull(biPredicate);
		checkNotSingleEntry(map);
		
		return findLongestLists(findConsecutiveTests(map, biPredicate, filter));
	}
	
	public static <K, V> List<List<KeyPair<K>>> filterAllMinGroupedConsecutiveTests(LinkedHashMap<K, V> map, BiPredicate<V, V> biPredicate, boolean filter) {
		Objects.requireNonNull(map);
		Objects.requireNonNull(biPredicate);
		checkNotSingleEntry(map);
		
		return findShortestLists(findConsecutiveTests(map, biPredicate, filter));
	}
	
	public static <K, V> double averageGroupedConsecutiveTests(LinkedHashMap<K, V> map, BiPredicate<V, V> biPredicate, boolean filter) {
		Objects.requireNonNull(map);
		Objects.requireNonNull(biPredicate);
		checkNotSingleEntry(map);
		
		return averageListSize(findConsecutiveTests(map, biPredicate, filter));
	}	
	
	public static <K extends Comparable<? super K>, V> LinkedHashMap<K, V> sortByKey(Map<K, V> map) {
		return map.entrySet().stream() // use stream
					.sorted(Map.Entry.<K, V>comparingByKey()) // sort by key
					.collect( // build result map
							Collectors.toMap(
								Map.Entry::getKey, 
								Map.Entry::getValue, 
								(e1, e2) -> e1, LinkedHashMap::new
							)
					);
	}
	
	private static <K, V, R> LinkedHashMap<K, Operation<K, R>> applyFunctionToConsecutiveElements(Map<K, V> map, BiFunction<V, V, R> biFunction) {
		return StreamsUtils.roll(map.entrySet().stream(), 2) // Roll the map
							.map(e -> e.collect(Collectors.toList())).collect(Collectors.toList()) // Convert to List<List<Map.Entry<K, V>>> 
							.stream()
							.collect( // Apply function and build result map
								Collectors.toMap(
									e -> e.get(0).getKey(), 
									e -> new Operation<K, R>(e.get(0).getKey(), e.get(1).getKey(), biFunction.apply(e.get(0).getValue(), e.get(1).getValue())),
									(e1, e2) -> e1, LinkedHashMap::new
								)
							);
	}
	
	private static <K, V> LinkedHashMap<K, Operation<K, Boolean>> applyPredicateToConsecutiveElements(Map<K, V> map, BiPredicate<V, V> biPredicate) {
		return StreamsUtils.roll(map.entrySet().stream(), 2) // Roll the map
			.map(e -> e.collect(Collectors.toList())).collect(Collectors.toList()) // Convert to List<List<Map.Entry<K, V>>> 
			.stream()
			.collect( // Apply predicate and build a map<K, Boolean>
				Collectors.toMap(
					e -> e.get(0).getKey(), 
					e -> new Operation<K, Boolean>(e.get(0).getKey(), e.get(1).getKey(), biPredicate.test(e.get(0).getValue(), e.get(1).getValue())),
					(e1, e2) -> e1, LinkedHashMap::new
				)
			);
	}
	
	private static <K> List<KeyPair<K>> filterTests(LinkedHashMap<K, Operation<K, Boolean>> map, boolean filter) {
		return map.entrySet()
				.stream()
				.filter(e -> e.getValue().getResult().booleanValue() == filter)
				.map(e -> new KeyPair<K>(e.getKey(), e.getValue().getNextKey()))
				.collect(Collectors.toList());
	}
	
	private static <K, V> List<List<KeyPair<K>>> findConsecutiveTests(LinkedHashMap<K, V> map, BiPredicate<V, V> biPredicate, boolean filter) {
		LinkedHashMap<K, Operation<K, Boolean>> resultMap = applyPredicateToConsecutiveElements(map, biPredicate);
		return groupConsecutiveTests(resultMap, filter);
	}
	
	private static <K, V> List<List<KeyPair<K>>> findConsecutiveTests(LinkedHashMap<K, V> map, BiPredicate<V, V> biPredicate, boolean filter, int minGroupSize, int maxGroupSize) {
		return findConsecutiveTests(map, biPredicate, filter)
				.stream()
				.filter(e -> e.size() >= minGroupSize && e.size() <= maxGroupSize)
				.collect(Collectors.toList());
	}
		
	private static <K> List<List<KeyPair<K>>> groupConsecutiveTests(Map<K, Operation<K, Boolean>> map, boolean filter) {
		return  StreamsUtils.group(
							map.entrySet().stream(),
							e -> e.getValue().getResult().booleanValue() == filter,
							true, 
							e -> ! e.getValue().getResult(),
							false
						)
				.map (
					s -> s.map(e -> new KeyPair<K>(e.getKey(), e.getValue().getNextKey())).collect(Collectors.toList())
				)
				.collect(Collectors.toList());
	}
	
	private static <K> List<List<KeyPair<K>>> findLongestLists(List<List<KeyPair<K>>> list) {
		return StreamsUtils.filteringAllMax(list.stream(), (e1, e2) -> (e1.size() - e2.size()))
				.collect(Collectors.toList());
	}
	
	private static <K> List<List<KeyPair<K>>> findShortestLists(List<List<KeyPair<K>>> list) {
		int sizeShortestList = list.stream().min((e1, e2) -> (e1.size() - e2.size())).orElse(new ArrayList<>()).size();
		return list.stream()
				.filter(e -> e.size() == sizeShortestList)
				.collect(Collectors.toList());
	}
	
	private static <K> double averageListSize(List<List<KeyPair<K>>> list) {
		return list.stream()
					.map(e -> (Number) e.size())
					.mapToDouble(Number::doubleValue)
					.average()
					.orElse(0);
	}
	
	private static <K, R extends Comparable<? super R>> List<Operation<K, R>> filterMaxResults(Map<K, Operation<K, R>> map) {
		return StreamsUtils.filteringAllMax(
					map.entrySet().stream(), 
					(e1, e2) -> e1.getValue().getResult().compareTo(e2.getValue().getResult())
				)
				.map(e -> e.getValue())
				.collect(Collectors.toList());
	}
	
	private static <K, R extends Comparable<? super R>> List<Operation<K, R>> filterMinResults(Map<K, Operation<K, R>> map) {
		R minValue = map.entrySet()
						.stream()
						.map(e -> e.getValue().getResult())
						.min((e1, e2) -> e1.compareTo(e2))
						.orElse(null);
						
		if (minValue == null) {
			return new ArrayList<Operation<K, R>>();
		}
		
		return map.entrySet()
				.stream()
				.filter(e -> (e.getValue().getResult().compareTo(minValue) == 0))
				.map(e -> e.getValue())
				.collect(Collectors.toList());
	}
	
	private static <K, R extends Number> double averageResults(Map<K, Operation<K, R>> map) {
		return map.entrySet()
				.stream()
				.map(e -> (Number) e.getValue().getResult())
				.mapToDouble(Number::doubleValue)
				.average()
				.orElse(0);
	}
		
	private static void checkNotSingleEntry(@SuppressWarnings("rawtypes") Map map) {
		if (map.size() == 1)
			throw new IllegalArgumentException("Map cannot contain one single entry");
	}

}
