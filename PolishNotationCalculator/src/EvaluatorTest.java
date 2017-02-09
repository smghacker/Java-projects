
public class EvaluatorTest {
	public static void main(String args[]){
		String expression = "- * / 15 - 7 + 1 1 3 + 2 + 1 1";
		Evaluator calculator = new Evaluator(expression);
		System.out.print(calculator.interpret());
	}
}
