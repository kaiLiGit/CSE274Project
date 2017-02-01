import java.util.ArrayList; 

 /**
 * Project: Infix -> Postfix conversion
 * @author TODO: Kai Li 
 *
 */

public class PostfixConverter {

	/**
	 * Convert an infix expression to a postfix expression.  Assumes the expression uses
	 * only integers, parentheses, and the operator set {+, -, *, /, ^}.
  	 * @param formula   The infix expession.
	 * @return          The postfix expression.
	 * @exception       Throw Illegal Argument exception if the parenthesis
	 *                  don't match up correctly.
	 */
	public static String infix2postfix(String formula) {
		//final result string
		String r = "";
		//stack only takes on operators and parentheses
		CharStack stack = new CharStack(20);
		Tokenizer tokens = new Tokenizer(formula);
		//using arraylist to store each token
		ArrayList<Tokenizer> s = new ArrayList<Tokenizer>();
		/////parentheses balance checking/////////////
		CharStack parenStack = new CharStack(20);
		for (int i = 0; i < formula.length(); i++) {
			char t = formula.charAt(i);
			if (t == '(') {
				parenStack.push(t);
			}else if (t == ')') {
				if (parenStack.isEmpty())
					throw new IllegalArgumentException(); 
				if (parenStack.pop()!='(') {
					throw new IllegalArgumentException();
				}
			}
			if ((!parenStack.isEmpty()) && i == formula.length()-1) {
				throw new IllegalArgumentException();
			}
		}
		
		////////populate arraylist with tokens////////////////////
		while (true) {
			String t = tokens.next();
			if (t == "") {
				break; 
			}
			s.add(new Tokenizer(t));
		}
		
		for (Tokenizer t : s) {
			String token = t.next(); 
			if (Tokenizer.isNumber(token)) {
				r+= token +" "; 
				//popped the remain item in stack
				//after all numeric value is processed
				if (s.get(s.size()-1)==t) {
					while (!stack.isEmpty()) {
						r+= stack.pop() + " "; 
					}
				}
			}else if (token.equals("(")){
				stack.push(token.charAt(0));
			}else if (token.equals(")")){
				while (true) {
					String popStr = stack.pop() +""; 
					if (popStr.equals("(")||stack.isEmpty()) {
						//if stack only has one item left
						//and token( ')' ) is last closing parenthesis
						//and top value of stack is not '('
						if (stack.size()==1 && 
								token.charAt(0)==formula.charAt(formula.length()-1)
								&& !String.valueOf(stack.peek()).equals("(")) {
							r+= stack.pop()+" ";
							break;
						}
						break; 
					}
					r+= popStr +" ";
				}
			}else{//processing all operators
				while (true) {
					if (!stack.isEmpty()) {
						//operator from stack
						char optrFromStack = stack.peek(); 
						//checking operator validation
						if (Tokenizer.isOperator(optrFromStack)) {
							//comparing Priority of each operator
							if (Tokenizer.operatorPrioity(optrFromStack)>=
							Tokenizer.operatorPrioity(token.charAt(0))) {
								//special case: dealing with right-associative operators
								if (token.equals("^")&&token.equals(String.valueOf(optrFromStack))) {
									//when top value is '^' keep pushing 
									//(token,'^') into the stack
									stack.push(token.charAt(0));
									break;
								}
								r += stack.pop() + " ";
								if (stack.isEmpty()) { 
									//push token onto stack 
									//when stack is empty
									stack.push(token.charAt(0));
									break;
								}
							}else{
								//push token into stack when 
								//token has greater priority 
								stack.push(token.charAt(0));
								break;
							}
						}else{
							//when top value of stack is (opening parenthesis)
							//push token onto stack
							stack.push(token.charAt(0));
							break;
						}
					}else{
						//when stack is empty, push token on stack
						stack.push(token.charAt(0));
						break;
					}
				}
			}
		}
		return r;
	
	}
	
	/**
	 * Given postfix expression, calculate the value.
	 * @param s      The postfix expression.
	 * @return       double: the calculated value.
	 */
	public static double evaluatePostfix(String s) {
		//Use DoubleStack to store numeric values
		DoubleStack valueStack = new DoubleStack(20);
		Tokenizer tokens = new Tokenizer(s);
		//using arraylist to wrap each token value
		ArrayList<Tokenizer> tokenList = new ArrayList<Tokenizer>();
		int numOfOperator = 0;
		int numOfOperand = 0; 
		////////////Equation Validation////////////////////
		while (true) {
			String t = tokens.next();
			if (t == "") {
				break; 
			}
			if (Tokenizer.isNumber(t)) {
				numOfOperand ++;
			}
			if (Tokenizer.isOperator(t)) {
				numOfOperator ++;
			}
			if (t.equals("(")||t.equals(")")) {
				throw new IllegalArgumentException("Parentheses not Allowed");
			}
			tokenList.add(new Tokenizer(t));
		} 
		if ((numOfOperand-1)!= numOfOperator) {
			throw new IllegalArgumentException("unbalanced operands and operators");
		}
		////////////////////////////////////////////////
		double RHS = 0.0;
		double LHS = 0.0;
		for (Tokenizer token : tokenList) {
			String tokenVal = token.next(); 
			//if tokenVal is number, push into the stack
			if (Tokenizer.isNumber(tokenVal)){
				valueStack.push(Double.parseDouble(tokenVal));}
			//evaluate popped values when tokenVal is operator
			if (Tokenizer.isOperator(tokenVal)) {
				RHS = valueStack.pop();
				LHS = valueStack.pop();
				switch (tokenVal) {
				case "+":
					valueStack.push(LHS + RHS);
					break;
				case "-":
					valueStack.push(LHS - RHS);
					break;
				case "/":
					if (!Double.isInfinite(LHS/RHS)){
						valueStack.push(LHS / RHS);
					}else{
						throw new ArithmeticException("Attempted to Divide by zero");
					}
					break;
				case "*":
					valueStack.push(LHS * RHS);
					break;
				default:
					valueStack.push(Math.pow(LHS, RHS));
					break;
				}
			}
		}
		return valueStack.pop(); 
	}
}

