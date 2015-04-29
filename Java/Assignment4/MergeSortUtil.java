//package hw3;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class MergeSortUtil {

	public static <T> List<T> mergeSort(List<T> li, Comparator<T> myComparator){
		if(li.size()>1){
			int n = li.size()/2;
			List<T> li1 = new LinkedList<T>(li.subList(0, n));
			List<T> li2 = new LinkedList<T>(li.subList(n, li.size()));
			li1 = mergeSort(li1, myComparator);
			li2 = mergeSort(li2, myComparator);
			return merge(li1,li2, myComparator);
		}else{
			return li;
		}
	}
	
	public static <T> List<T> merge(List<T> li1, List<T> li2, Comparator<T> myComparator){
		List<T> res = new LinkedList<T>();
		int compareres;
		while(! (li1.isEmpty() || li2.isEmpty())){
			compareres = myComparator.compare(li1.get(0), li2.get(0));
			if(compareres<0){
				res.add(li1.remove(0));
			}else{
				res.add(li2.remove(0));
			}
		}
		while(!li1.isEmpty()){
			res.add(li1.remove(0));
		}
		while(!li2.isEmpty()){
			res.add(li2.remove(0));
		}
		return res;
	}
	
}
