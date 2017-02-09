
public class Minus implements Expression{

	private Expression left;
	private Expression right;
	
	public Minus(Expression left, Expression right){
		this.left = left;
		this.right = right;
	}
	
	@Override
	public int interpret(){
		int result = left.interpret() - right.interpret();
		return result;
	}
}
