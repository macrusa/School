//package hw3;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class EquationSolver {
	
	private List<SolutionBean> left;
	private List<SolutionBean> right;
	
	public EquationSolver(){
		left = new LinkedList<SolutionBean>();
		right = new LinkedList<SolutionBean>();
	}
	
	public static Comparator<SolutionBean> SolutionBeanComparator = new Comparator<SolutionBean>(){
		public int compare(SolutionBean s1, SolutionBean s2){
			if(s1.getRes()>s2.getRes())return 1;
			if(s1.getRes()<s2.getRes())return -1;
			return 0;
		}
	};
	
	 
	
	public void generateSolutions(int max){
		long num1,num2,num3,resLeft,resRight;
		//0<i<=j<=k<=max; i^5=num1,j^5=num2,k^5=num3; i->A,j->B,k->C; i->D,j->E,k->F
		for(int i=1;i<=max;i++){
			for(int j=i;j<=max;j++){
				for(int k=j;k<=max;k++){
					num1=(long) Math.pow(i,5);
					num2=(long) Math.pow(j,5);
					num3=(long) Math.pow(k,5);
					// A^5 +B^5 +C^5 
					resLeft=num1+num2+num3;
					// F^5-E^5-D^5
					resRight=num3-num2-num1;
					left.add(new SolutionBean(i,j,k,resLeft));
					if(resRight>=0)right.add(new SolutionBean(i,j,k,resRight));
				}
			}
		}
	}
	
	public static void main(String[] args){
		
		
		//int max = 100;
		
		// You can change the size of max (N) in Run->Run Configuration->Arguments->Program Arguments
		int max = Integer.parseInt(args[0]);
		
		boolean hasres = false;
		EquationSolver eg = new EquationSolver();
		eg.generateSolutions(max);
		//System.out.println("generate done, item count: "+eg.left.size()+":"+eg.right.size());
		List<SolutionBean> left = MergeSortUtil.mergeSort(eg.left, SolutionBeanComparator);
		List<SolutionBean> right = MergeSortUtil.mergeSort(eg.right, SolutionBeanComparator);
		//System.out.println("sort done, item count: "+left.size()+":"+right.size());
		while(!(left.isEmpty() || right.isEmpty())){
			if(left.get(0).getRes()<right.get(0).getRes()){
				left.remove(0);
			}else
			if(left.get(0).getRes()>right.get(0).getRes()){
				right.remove(0);
			}else
			if(left.get(0).getRes()==right.get(0).getRes()){
				if(left.get(0).getNum3()<right.get(0).getNum1()){
					System.out.print(left.get(0).getNum1()+" "+left.get(0).getNum2()+" "+left.get(0).getNum3()+" ");
					System.out.print(right.get(0).getNum1()+" "+right.get(0).getNum2()+" "+right.get(0).getNum3());
					System.out.println();
				}
				hasres = true;
				left.remove(0);
				right.remove(0);
			}
		}
		if(!hasres)System.out.println("No solutioin");
	}
		

}
