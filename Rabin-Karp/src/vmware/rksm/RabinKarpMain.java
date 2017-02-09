package vmware.rksm;

public class RabinKarpMain {
	public static void main(String[] args){
		RabinKarpStringMatching rksm = new RabinKarpStringMatching("kola");
		System.out.println(rksm.checkIfCertainSubstringExists("ola"));
		System.out.println(rksm.checkIfCertainSubstringExists("kola"));
		System.out.println(rksm.checkIfCertainSubstringExists("k"));
		System.out.println(rksm.checkIfCertainSubstringExists("olaadsa"));
		System.out.println(rksm.checkIfCertainSubstringExists("aaaaa"));
		
		System.out.println(rksm.getPositionOfCertainSubstringExists("ola"));
	}
}
