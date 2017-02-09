
public class Division implements Expression {

	private Expression left;
	private Expression right;
	
	public Division(Expression left, Expression right){
		this.left = left;
		this.right = right;
	}
	
	@Override
	public int interpret() {
		int result = left.interpret()/right.interpret();
		return result;
	}

}
