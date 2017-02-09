
public class Plus implements Expression{

	private Expression left;
	private Expression right;
	
	public Plus(Expression left, Expression right){
		this.left = left;
		this.right = right;
	}
	
	@Override
	public int interpret(){
		int result = left.interpret() + right.interpret();
		return result;
	}
}
