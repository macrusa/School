//package hw3;

// Generate the Tuple class stores the value of <A,B,C,Sum> and <D,E,F,Sum>

public class SolutionBean {
	private int num1;
	private int num2;
	private int num3;
	
	private long res;
	
	public SolutionBean(){
	}
	
	public SolutionBean(int num1,int num2, int num3, long res){
		this.num1 = num1;
		this.num2 = num2;
		this.num3 = num3;
		this.res = res;
	}
	
	public int getNum1() {
		return num1;
	}
	public void setNum1(int num1) {
		this.num1 = num1;
	}
	public int getNum2() {
		return num2;
	}
	public void setNum2(int num2) {
		this.num2 = num2;
	}
	public int getNum3() {
		return num3;
	}
	public void setNum3(int num3) {
		this.num3 = num3;
	}

	public long getRes() {
		return res;
	}

	public void setRes(long res) {
		this.res = res;
	}
	
	
}
