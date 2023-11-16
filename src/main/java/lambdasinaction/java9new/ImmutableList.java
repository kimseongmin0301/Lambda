package lambdasinaction.java9new;

import java.util.List;

public class ImmutableList {
	public static void main(String[] args) {
		List<String> list = List.of("one","two");
		System.out.println(list);
		//java.lang.UnsupportedOperationException 발생함
		//list.add("three");
	}
}
