import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.Stack;

public class TestStack {

	@Test
	public void testStack() {
		Stack<Integer> s = new Stack<>();
		MyStack<Integer> ms = new MyStack<>();

		assertEquals(ms.top(), s.peek());
	}
}
