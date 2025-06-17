class InvalidPostfixException extends Exception {
}


class InvalidExprException extends Exception {
}


public class Calculator {

	/**
	 * Convert a string from standard notation to postfix notation
	 */
	public String convertExpression(String s) throws InvalidExprException {
		return s;
	}

	/**
	 * Evaluate a postfix expression
	 */
	public int evaluatePostfix(String s) throws InvalidPostfixException {
		return 0;
	}

	public static void main(String[] args) {
		Calculator c = new Calculator();
		String expr = "4 6 + 9 * 5 7 * + 3 *";
		int result = 0;
		try {
			result = c.evaluatePostfix(expr);
			System.out.printf("%s = %d\n", expr, result);
		} catch (Exception e) {
			System.out.println("Invalid Expression");
		}

	}
}
