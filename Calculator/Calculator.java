import java.util.regex.Pattern;
import java.util.EmptyStackException;


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
		String[] tokens = s.split(" ");
		MyStack<Integer> evalStack = new MyStack<>();

		for (String token : tokens) {
			// Add number to stack
			if (isNumeric(token)) {
				evalStack.push(Integer.valueOf(token));
			}
			// Perform operation and push result to stack
			else {
				try {
					Integer arg2 = evalStack.pop();
					Integer arg1 = evalStack.pop();
					evalStack.push(operate(token, arg1, arg2));
				}
				// Throw exception if the stack doesn't have the right number
				// of arguments
				catch (EmptyStackException e) {
					throw new InvalidPostfixException();
				}
			}
		}

		// Throw Invalid expression if the stack doesn't have the right amount
		// of elements
		if (evalStack.size() != 1) {
			throw new InvalidPostfixException();
		}

		return evalStack.top();
	}

	private Integer operate(String operate, Integer arg1, Integer arg2)
			throws InvalidPostfixException {
		Integer result = switch (operate.charAt(0)) {
			case '+' -> arg1 + arg2;
			case '-' -> arg1 - arg2;
			case '*' -> arg1 * arg2;
			default -> throw new InvalidPostfixException();
		};

		return result;
	}

	private boolean isNumeric(String token) {
		Pattern number = Pattern.compile("-?\\d+");
		return number.matcher(token).matches();
	}

	public static void main(String[] args) {
		Calculator c = new Calculator();
		String expr = "4 6 + 9 * 5 7 * + 3 *";
		int result = 0;
		try {
			result = c.evaluatePostfix(expr);
			System.out.printf("%s = %d\n", expr, result);
		} catch (InvalidPostfixException e) {
			System.out.println("Invalid Expression");
		}

	}
}
