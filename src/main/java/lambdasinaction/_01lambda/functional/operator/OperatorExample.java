package lambdasinaction._01lambda.functional.operator;

import java.util.function.IntBinaryOperator;

public class OperatorExample {
	private static int[] scores = { 92, 95, 87 };

	public static int maxOrMin(IntBinaryOperator operator) {
		int result = scores[0];
		for (int score : scores) {
			result = operator.applyAsInt(result, score);
		}
		return result; }

	public static void main(String[] args) {
		// 최대값 얻기
		int maxValue = maxOrMin((n1, n2) -> {
			if (n1 >= n2) return n1;
			else return n2;
		});

		System.out.println("maxValue = " + maxValue);

		// 최소값 얻기
		int minValue = maxOrMin((n1, n2) -> {
			if (n1 <= n2) return n1;
			else return n2;
		});

		minValue = maxOrMin((n1, n2) -> Math.min(n1, n2));

		minValue = maxOrMin(Math::min);

		System.out.println("minValue = " + minValue);

	}
}
