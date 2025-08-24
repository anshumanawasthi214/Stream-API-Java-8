package com.java8.stream.api;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamApiInitializatioin {

	
	//1. Using collection object
	List<String> values=List.of("Value0","Value1","Value2","Value3","Value4","Value5");
	Stream<String> stream1=values.stream();//Stream object is created from collection object
	
	
	//2. Using Array of values
	String[] val= {"Value0","Value1","Value2","Value3","Value4","Value5"};
	Stream<String> stream2=Arrays.stream(val);
	
	//3. Using Stream methods
	Stream<String> stream3=Stream.of("Value0","Value1","Value2","Value3","Value4","Value5");
	
	//4. Using Generate method
	Stream<String> stream4=Stream.generate(()->"Value1");
	
	//5. Using builder method
	Stream.Builder<String> streamBuilder=Stream.builder();
	Stream<String> stream5=streamBuilder.add("value1").add("value2").add("value3").build();
	
	
	//6.Empty Stream using empty method
	Stream<String> stream6=	Stream.empty();
	
}
