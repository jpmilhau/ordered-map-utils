# OrderedMapUtils
Build status: [![build_status](https://travis-ci.com/jpmilhau/ordered-map-utils.svg?branch=master)](https://travis-ci.com/jpmilhau/ordered-map-utils)
Provide utils to work on consecutive elements in an ordered map.

Let's imagine you have a LinkedHashMap of temperature records.
* key is the day of the year
* value is the average temperature of the day
Data come from the [meteorological station in Trappes (France)](http://www.meteociel.fr/climatologie/villes.php?code=7145&mois=1&annee=2016)

```java
LinkedHashMap<Date, Float> temperatures = new LinkedHashMap<Date, Float>();  
SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
try {  
  temperatures.put(formatter.parse("01/01/2016"), 8.9f);  
  temperatures.put(formatter.parse("02/01/2016"), 10f);  
  ...  
  temperatures.put(formatter.parse("31/12/2016"), -3.2f);  
} catch (ParseException e) {  
  e.printStackTrace();  
}  
```

With the class OrderedMapUtils and two provided classes,
* KeyPair<K> that handles a key and its successive in the map
* Operation<K,R> that handles a key, its successive in the map and the result of an operation applied to the two values in the map
you can easily work on **consecutive elements** to:

## Apply an operation
Difference of temperature from one day to the next day:

```java
OrderedMapUtils.apply(temperatures, (t1, t2) -> (t2 - t1)).values().stream()  
  .forEachOrdered(  
       e -> System.out.println("From " + e.getKey() + " To " + e.getNextKey() + " : " + e.getResult())  
  );  
```

From Fri Jan 01 2016 To Sat Jan 02 2016 : 1.1\
From Sat Jan 02 2016 To Sun Jan 03 2016 : -2.3\
...\
From Fri Dec 30 2016 To Sat Dec 31 2016 : -1.3

## Filter all maximum of an operation
Maximum day-to-day thermal amplitude:
 
```java
OrderedMapUtils.filterAllMax(temperatures, (t1, t2) -> Math.abs(t2 - t1)).stream()  
  .forEachOrdered(  
       e -> System.out.println("From " + e.getKey() + " To " + e.getNextKey() + " : " + e.getResult())  
  );  
```

From Sat Apr 02 2016 To Sun Apr 03 2016 : 10.0

## Filter all minimum of an operation
Minimum day-to-day thermal amplitude:

```java
OrderedMapUtils.filterAllMin(temperatures, (t1, t2) -> Math.abs(t2 - t1)).stream()  
  .forEachOrdered(  
       e -> System.out.println("From " + e.getKey() + " To " + e.getNextKey() + " : " + e.getResult())  
  );  
```

From Sun Mar 27 2016 To Mon Mar 28 2016 : 0.0\
From Wed Jun 29 2016 To Thu Jun 30 2016 : 0.0\
From Wed Aug 24 2016 To Thu Aug 25 2016 : 0.0\
From Fri Sep 02 2016 To Sat Sep 03 2016 : 0.0\
From Wed Nov 30 2016 To Thu Dec 01 2016 : 0.0\
From Tue Dec 06 2016 To Wed Dec 07 2016 : 0.0

## Find the average of an operation
Average day-to-day thermal amplitude:

```java
System.out.println("Average day-to-day thermal amplitude: " //
+ OrderedMapUtils.average(temperatures, (t1, t2) -> Math.abs(t2 - t1)));  
```

Average day-to-day thermal amplitude: 2.1

## Test the result of an operation
Check if day-to-day thermal amplitude is greater than 8°:

```java
OrderedMapUtils.test(temperatures, (t1, t2) -> Math.abs(t2 - t1) >= 8f).values().stream()  
  .forEachOrdered(  
       e -> System.out.println("From " + e.getKey() + " to " + e.getNextKey() + " : " + e.getResult())  
  );
```

From Fri Jan 01 2016 to Sat Jan 02 CET 2016 : false\
From Sat Jan 02 2016 to Sun Jan 03 2016 : false\
...\
From Sat Apr 02 2016 to Sun Apr 03 2016 : true\
...

## Filter by a test
List periods where day-to-day thermal amplitude is greater than 8°:

```java
OrderedMapUtils.filterTests(temperatures, (t1, t2) -> Math.abs(t2 - t1) >= 8f, true).stream()  
  .forEachOrdered(  
       e -> System.out.println("From " + e.getKey() + " to " + e.getNextKey())  
  );
```

From Sat Apr 02 2016 to Sun Apr 03 2016\
From Fri May 13 2016 to Sat May 14 2016\
From Sat May 21 2016 to Sun May 22 2016

## Filter by a grouped of consecutive successful tests
List periods of exactly 5 consecutive days of temperature increase:

```java
OrderedMapUtils.filterGroupedConsecutiveTests(temperatures, (t1, t2) -> t2 - t1 > 0f, true, 5, 5).stream()  
  .forEachOrdered(  
       e -> System.out.println("From " + e.get(0).getKey() + " to " + e.get(e.size() - 1).getNextKey())  
  );
```

From Mon Sep 19 2016 to Sat Sep 24 2016

## Filter all maximum of a grouped of consecutive successful tests
List the longest periods of consecutive days of temperature increase:

```java
OrderedMapUtils.filterAllMaxGroupedConsecutiveTests(temperatures, (t1, t2) -> t2 - t1 > 0f, true).stream()  
  .forEachOrdered(  
       e -> System.out.println("From " + e.get(0).getKey() + " to " + e.get(e.size() - 1).getNextKey())  
  );  
```

From Wed Jul 13 2016 to Tue Jul 19 2016\
From Tue Dec 20 2016 to Mon Dec 26 2016

## Find the average a grouped of consecutive successful tests
Average duration of consecutive days of temperature increase:

```java
System.out.println("Average duration of consecutive days of temperature increase: " //
+ OrderedMapUtils.averageGroupedConsecutiveTests(temperatures, (t1, t2) -> t2 - t1 > 0f, true));  
``` 

Average duration of consecutive days of temperature increase: 1.8
