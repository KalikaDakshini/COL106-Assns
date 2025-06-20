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
		String[] tokens = s.split(" ");
		MyStack<String> tokenStack = new MyStack<>();
		MyStack<String> opStack = new MyStack<>();

		// Shunting Yard Algorithm
		for (String token : tokens) {
			// Push numbers into one stack
			if (isNumeric(token)) {
				tokenStack.push(token);
			}
			// Push operands into a second stack
			else {
				// Push left paranthesis onto stack and continue
				if (token.equals("(")) {
					opStack.push(token);
					continue;
				}
				// Pop all the operands until left paren
				else if (token.equals(")")) {
					while (!opStack.top().equals("(")) {
						tokenStack.push(opStack.pop());
					}
					opStack.pop();
					continue;
				}

				// Check precedence and push operator
				while (!opStack.isEmpty()
						&& prec(token) < prec(opStack.top())) {
					tokenStack.push(opStack.pop());
				}
				opStack.push(token);
			}
		}

		// Push any remaining operands into the token Stack
		while (!opStack.isEmpty()) {
			tokenStack.push(opStack.pop());
		}

		// Build the Token stack into a postfix string seperated by spaces
		return makeString(tokenStack);
	}

	private int prec(String operand) {
		int result = switch (operand.charAt(0)) {
			case '-' -> 1;
			case '+' -> 2;
			case '*' -> 3;
			default -> 0;
		};
		return result;
	}

	// Conctenate elements of the stack with spaces
	private String makeString(StackInterface<String> s) {
		return String.join(" ", s);
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

	// Operate token on arguments
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

	// Check if a string is numeric
	private boolean isNumeric(String token) {
		Pattern number = Pattern.compile("-?\\d+");
		return number.matcher(token).matches();
	}

	public static void main(String[] args) {
		Calculator c = new Calculator();
		String expr = "3 * ( ( 4 + 6 ) * 9 + ( 5 * 7 ) )";
		try {
			String postfix = c.convertExpression(expr);
			System.out.println(postfix);
			int result = c.evaluatePostfix(postfix);
			System.out.printf("%s = %d\n", expr, result);
		} catch (Exception e) {
			System.out.println("Invalid Expression");
		}
	}
}
