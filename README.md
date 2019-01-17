# ordered-map-utils
Provide utils to work on consecutive elements in an ordered map

Let's imagine you have a LinkedHashMap of temperature records.

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