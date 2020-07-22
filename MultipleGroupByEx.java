package com.test;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.maxBy;

import java.util.List;
import java.util.Map;
import java.util.Optional;



public class MultipleGroupByEx {

	static class AddonFareClass {
		private int keyid;
		private int subid;
		private String tariff;
		private String cararier;
		private String fareClass;
		private String type;
		private int owrt;
		private int action;		
		
		public AddonFareClass(int keyid, int subid, String tariff, String cararier, String fareClass, String type,
				int owrt, int action) {
			super();
			this.keyid = keyid;
			this.subid = subid;
			this.tariff = tariff;
			this.cararier = cararier;
			this.fareClass = fareClass;
			this.type = type;
			this.owrt = owrt;
			this.action = action;
		}
		public int getKeyid() {
			return keyid;
		}
		public void setKeyid(int keyid) {
			this.keyid = keyid;
		}
		public int getSubid() {
			return subid;
		}
		public void setSubid(int subid) {
			this.subid = subid;
		}
		public String getTariff() {
			return tariff;
		}
		public void setTariff(String tariff) {
			this.tariff = tariff;
		}
		public String getCararier() {
			return cararier;
		}
		public void setCararier(String cararier) {
			this.cararier = cararier;
		}
		public String getFareClass() {
			return fareClass;
		}
		public void setFareClass(String fareClass) {
			this.fareClass = fareClass;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public int getOwrt() {
			return owrt;
		}
		public void setOwrt(int owrt) {
			this.owrt = owrt;
		}
		public int getAction() {
			return action;
		}
		public void setAction(int action) {
			this.action = action;
		}
		@Override
		public String toString() {
			return "AddonFareClass [keyid=" + keyid + ", subid=" + subid + ", tariff=" + tariff + ", cararier="
					+ cararier + ", fareClass=" + fareClass + ", type=" + type + ", owrt=" + owrt + ", action=" + action
					+ "]";
		}

		
	}

	public static void main(String[] args) {
		List<AddonFareClass> people = List.of(
				new AddonFareClass(1, 1, "001", "AA", "N*****", "1", 1, 3), 
				new AddonFareClass(2, 1, "001", "DL", "******", "1", 1, 3),
				new AddonFareClass(1, 2, "001", "AA", "N*****", "1", 1, 3),
				new AddonFareClass(2, 2, "005", "DL", "******", "1", 1, 3),
				new AddonFareClass(1, 3, "001", "AA", "N*****", "1", 1, 3)
				);

		/*
		 * Function<Person, List<Object>> compositeKey = personRecord ->
		 * Arrays.<Object>asList(personRecord.getAge(), personRecord.getSalary());
		 * 
		 * Map<List<Object>, List<Person>> peopleByAge; peopleByAge = people
		 * .collect(groupingBy(compositeKey, mapping((Person p) -> p, toList())));
		 */

		/*
		 * Function<Person, List<Object>> compositeKey = personRecord ->
		 * Arrays.<Object>asList(personRecord.getAge(), personRecord.getSalary());
		 * 
		 * Map<List<Object>, List<Person>> peopleByAge; peopleByAge =
		 * people.stream().collect(groupingBy(compositeKey, mapping((Person p) -> p,
		 * toList()))); System.out.println(peopleByAge);
		 */

		Map<String, List<AddonFareClass>> peopleBySomeKey = people.stream().collect(groupingBy(p -> getGroupingByKey(p)));
		
		 for (Map.Entry<String, List<AddonFareClass>> entry : peopleBySomeKey.entrySet()) {
		        System.out.println(entry.getKey() + ":" + entry.getValue());
		        List<AddonFareClass> persons = entry.getValue();
		        
		        Optional<AddonFareClass> maxSubIdPerson = persons.stream().collect(maxBy(comparing(AddonFareClass::getSubid)));
		        System.out.println(maxSubIdPerson.get().getSubid());
		    }
		
		//System.out.println(peopleBySomeKey);
	}

	// write getGroupingByKey() function
	private static String getGroupingByKey(AddonFareClass p) {
		return p.getTariff() + "/" + p.getCararier() + "/" + p.getFareClass() + "/" + p.getType() + "/" + p.getOwrt();
	}
}