package week10;

public class DuplicateKeysTest {
	public static void main(String[] args) {
		MyHashMap<Integer, String> test = new MyHashMap<>();
		
		test.put(2, "Hello");
		test.put(2, "world");
		
		System.out.println(test.getAll(2));
	}
}
