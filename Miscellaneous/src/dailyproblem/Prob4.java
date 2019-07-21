package dailyproblem;

import java.util.Stack;

public class Prob4 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String brackets = "([])[]({})";
		System.out.println(isBalanced(brackets));
	}
	
	static boolean isBalanced(String brackets) {
		Stack<Character> stack = new Stack<Character>();
		for (char curr : brackets.toCharArray()) {
			char inverse = inverse(curr);
			if (!stack.isEmpty() && stack.peek().equals(inverse)) {
				stack.pop();
			} else {
				stack.add(curr);
			}
		}
		return stack.isEmpty();
	}
	
	static Character inverse(char c) {
		switch (c) {
		case ']':
			return '[';
		case '}':
			return '{';
		case ')':
			return '(';
		default:
			return ' ';
		}
	}
}
