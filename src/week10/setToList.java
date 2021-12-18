package week10;

import java.util.*;

public class setToList {
	public static <E> ArrayList<E> setToList(Set<E> s) {
		ArrayList<E> list = new ArrayList<E>();
		
		for (E o : s) {
			list.add(o);
		}
		
		return list;
	}
	
	public static void main (String[] args) {
		HashSet<Integer> set = new HashSet<>();
		set.add(1);
		set.add(2);
		set.add(3);
		set.add(4);
		set.add(500000000);
		
		System.out.println(setToList(set));
	}
}
