package week10;

public class HashString {
	public static int hashCodeForString(String s) {
		return hashCodeForString(s, s.length() - 1);
	}
	
	public static int hashCodeForString(String s, int i) {
		if (i == 1) {
			return ((int)s.charAt(0) * 31 + (int)s.charAt(i));
		} else {
			return (hashCodeForString(s, i - 1) * 31 + (int)s.charAt(i));
		}
	}
	
	public static void main(String[] args) {
		System.out.println(hashCodeForString("Fodor"));
		System.out.println(hashCodeForString("Frodo"));
	}
}
