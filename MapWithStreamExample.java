package com.aa.cp.report.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.aa.cp.base.util.Pair;

public class MapWithStreamExample {

	public static void main(String[] args) {
		List<Pair<String, String>> pairs = new ArrayList<Pair<String,String>>();		
		pairs.add(new Pair<String, String>("DFWATL","120.00"));
		pairs.add(new Pair<String, String>("DFWATL","125.00"));
		pairs.add(new Pair<String, String>("DFWATL","130.00"));
		pairs.add(new Pair<String, String>("DFWATL","140.12"));
		pairs.add(new Pair<String, String>("DFWATLPF","140.00"));
		pairs.add(new Pair<String, String>("DFWATLPF","140.45"));
		
		/*
		 * Map<String, List<String>> map = new HashMap<String, List<String>>();
		 * 
		 * for (Pair<String, String> pair: pairs) { String first = pair.getFirst();
		 * String second = pair.getSecond();
		 * 
		 * if (map.get(first) == null) { List<String> amounts = new ArrayList<String>();
		 * amounts.add(second); map.put(first, amounts); } else { List<String> amounts =
		 * map.get(first); amounts.add(second); map.put(first, amounts); } }
		 */
		
		 Map<String, List<String>> groupByInConditionsMap =
					pairs.stream().collect(Collectors.groupingBy(Pair::getFirst,
							Collectors.mapping(Pair::getSecond, Collectors.toList())));		
		
		
		Map<String, List<String>> minMaxAmountMap = groupByInConditionsMap.entrySet().stream().collect(Collectors.toMap(
                entry -> entry.getKey(), 
                entry -> List.of(Collections.min(entry.getValue()),Collections.max(entry.getValue())))
            );
		System.out.println(minMaxAmountMap);
		
		Map<String, Pair<String, String>> fareAmountMap1 = groupByInConditionsMap.entrySet().stream().collect(Collectors.toMap(
                entry -> entry.getKey(), 
                entry -> new Pair<String, String>(Collections.min(entry.getValue()),Collections.max(entry.getValue())))
            );
		fareAmountMap1.entrySet().stream().forEach(e-> System.out.println(e.getKey() + "->" + e.getValue().getFirst() +" "+e.getValue().getSecond()));
	}

}
