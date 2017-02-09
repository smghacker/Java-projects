import java.util.Stack;
import java.lang.Integer;
public class Evaluator implements Expression {

	private Expression syntaxTree;
	
	public Evaluator(String expression){
		Stack<Expression> expressionStack = new Stack<Expression>();
		
		String[] subexpressions = expression.split(" ");
		int endOfExpression = subexpressions.length - 1;
		int beginOfExpression = 0;
		for(int i = endOfExpression; i >= beginOfExpression; --i){
			String currentSubexpression = subexpressions[i];
			if(currentSubexpression.compareTo("+") == 0){
				Expression newExpression = new Plus(expressionStack.pop(), expressionStack.pop());
				expressionStack.push(newExpression);
				
			}else if(currentSubexpression.compareTo("-") == 0){
				
				Expression newExpression = new Minus(expressionStack.pop(), expressionStack.pop());
				expressionStack.push(newExpression);	
				
			}else if(currentSubexpression.compareTo("*") == 0){
				
				Expression newExpression = new Multiplication(expressionStack.pop(), expressionStack.pop());
				expressionStack.push(newExpression);
				
			}else if(currentSubexpression.compareTo("/") == 0){
				
				Expression newExpression = new Division(expressionStack.pop(), expressionStack.pop());
				expressionStack.push(newExpression);
				
			}else{
				
				int number = Integer.parseInt(currentSubexpression);
				Expression newExpression = new Number(number);
				expressionStack.push(newExpression);
				
			}
		}
		
		this.syntaxTree =expressionStack.pop();
	}
	
	@Override
	public int interpret() {
		return this.syntaxTree.interpret();
	}

}
