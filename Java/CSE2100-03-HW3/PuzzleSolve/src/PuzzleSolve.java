import java.util.StringTokenizer;
/**
 * Skeleton for the PuzzleSolve assignment
 * 
 * Important: SLinkedList and Node classes must be already implemented
 * 
 * -- 	added method parseInputString()
 * -- 	added command line input parsing. 
 * 		If no input provided through command line, a default string is used.
 * -- 	added method getUniqueLetters() 
 * 
 * @version 9/6/12
 * @author Saad
 * */
public class PuzzleSolve {
	//
	// three parts of the input string s1 + s2 = s3
	//
	String inputLine = "";
	String[] inputParts = null;
	//
	SLinkedList uniqueLettersList = null;
	SLinkedList digitsList = null;
	//
	int numUniqueLetters = 0;
	//
	int numResults = 0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		try{
			String defaultInput = "abc + def = dac";
			//
			// take input from command line
			// #### Important ####
			// the command line argument must be enclosed with ""
			// for example, to run this program with a specific input,
			// write the following in command line:
			// > java PuzzleSolve "abc+def=ghi"
			//
			String inputLine = "";
			if( args.length > 0){
				inputLine = args[0];
			}
			else{
				System.out.println("No command line input found. " + 
						"Using \"" + defaultInput + "\" as default.");
				inputLine = defaultInput;
			}
			//
			//
			//
			PuzzleSolve ps = new PuzzleSolve(inputLine);
			ps.Solve();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}

	}
	public PuzzleSolve(String inputLine){
		try{
			//
			// make the input string lower case
			//
			this.inputLine = inputLine.toLowerCase();
			//
			// Generate the list of all unique letters in the input
			//
			this.uniqueLettersList = this.getListOfUniqueLetters();
			if( this.uniqueLettersList.size() == 0){
				throw new Exception("No letters in input.");
			}
			this.numUniqueLetters = (int) this.uniqueLettersList.size();
			//
			System.out.println( this.uniqueLettersList.toString());
			//
			// Split input string into three parts
			// this.s1, this.s2, and this.s3
			//
			this.inputParts = this.parsePuzzleInput();
			if( this.inputParts == null /*|| this.inputParts.length != 3*/){
				throw new Exception("Bad input");
			}
			//
			// Generate the list of all digits 0-9 
			//
			this.digitsList = this.getListOfAllDigits();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	/**
	 * Solves the puzzle by initiating the recursion(U,S,k). The initial parameters to 
	 * the recursion are
	 * -- U: list of all digits
	 * -- S: empty list
	 * -- k: number of unique letters
	 * 
	 * @uses RecursiveSolve()
	 * */
	public void Solve() throws Exception{
		if( this.numUniqueLetters > 10){
			//
			// no solutions possible
			//
			System.out.println("No results");
		}
		else{
			this.RecursiveSolve(this.digitsList, new SLinkedList(), this.numUniqueLetters);
			if(this.numResults == 0){
				System.out.println("No results");
			}
		}
	}
	/**
	 * Recursively generates all possible combinations of k digits 
	 * from the remaining digits in U and appends each combination to the current 
	 * partial mapping S. 
	 * 
	 * When k==0 (base condition), it means S is the complete mapping. This mapping is 
	 * tested to see whether it satisfies input puzzle. If it does, the mapping is printed.
	 * 
	 * When k > 0, a recursion-branch is generated for each digit in U. 
	 * -- 	Before each branch, the the first digit  from U is removed, and 
	 * 		is inserted at the first of S. The branch gets (k-1) as its third argument.
	 * --	After each branch, the first digit of S is removed, and is inserted 
	 * 		at the end of U.
	 * The above mechanism makes sure that at each level of recursion, 
	 * U always has the same elements and each element is used to spawn exactly 
	 * one branch.
	 * 
	 * @param U	SLinkedList		Singly-linked list of remaining available digits
	 * @param S SLinkedList		Singly-linked list of used-up digits
	 * @param k integer				Number of digits yet to add to S
	 * 
	 * @uses RecursiveSolve()
	 * @uses getNumberFromPuzzlePiece()
	 * 
	 * */
	private void RecursiveSolve(SLinkedList U, SLinkedList S, int k) throws Exception{
		String indent = "";
		for(int t = 0; t < this.numUniqueLetters - k; t++) indent += "\t";
		//
		// S is a sequence of digits, denoting the mapping
		// U is the sequence of remaining digits
		// k is the current depth. We shall stop at k=1
		//
		//
		// first, the base case for recursion
		//
		if( k == 0){			
//			System.out.println(indent + "[k=" + k + "] Testing " + this.uniqueLettersList.toString() + " = " + S.toString());
			//
			// find the numbers corresponding to the mapping S
			//
			int[] numbers = new int[this.inputParts.length];
			int partialSum = 0;
			for(int i = 0; i < numbers.length; i++){
				numbers[i] =  this.getNumberFromPuzzlePiece(this.inputParts[i], S);
				if( i < numbers.length - 1){
					partialSum += numbers[i];
				}
			}
			if( partialSum == numbers[numbers.length - 1]){
				//
				// Yay!! this configuration solves the puzzle
				//
				System.out.println(S.toString());
				this.numResults++;
			}
		}
		else{
			// We shall create branches for recursion
			// One branch for each remaining digit
			//
			// Let branch_d denote the branch 
			// corresponding to digit d from U
			//
			// branch_d should receive
			//		-- k_branch_d := k - 1
			// 		-- U_branch_d := U without digit d (all other digits remaining the same)
			//			Thus, size(U_branch_d) = size(U) - 1
			//		-- S_branch_d := S with digit d added to it  
			//
			for(int i = 0; i < U.size(); i++){
//				System.out.println("[k=" + k + "] Removing " + U.getHead().getElement().toString() + " from U = " + U.toString());				
				//
				// Remove the first digit node from U
				// remember that U now has one less digit than it originally had
				// U must be repaired before it can be used by other branches
				//
				// Add this digit node to S
				// Remember that S now has one more digit than it originally had
				// S must be repaired before it can be used by other branches
				//
				S.addFirst(U.removeFirst());
				//
				// Recursive call to branch
				//
				//System.out.println(indent + "[k=" + k + "] Going to branch with U = " + U.toString() + " and S = " + S.toString());				
				this.RecursiveSolve(U, S, k - 1);
//				System.out.println(indent + "[k=" + k + "] Back from branch. Now U = " + U.toString() + " and S = " + S.toString() );				
				//
				// after this branch is done, repair U and S so that they can be
				// used by subsequent branches
				//
				// Now repair S
				//
				U.addLast( S.removeFirst() );
//				System.out.println(indent + "[k=" + k + "] Repaired. Now U = " + U.toString() + " and S = " + S.toString() );				
			}
			// System.out.println(indent + "[k=" + k + "] Finished. Now U = " + U.toString() + " and S = " + S.toString() );
		}
	}
	private int getNumberFromPuzzlePiece(
			String puzzlePiece, 
			SLinkedList mappingList)  throws Exception{
		//
		//
		//
		//
		int numberForPuzzlePiece = 0;
		//
		// first, retrieve the mapping from letter to digit
		// each unique letter must have its corresponding digit
		//
		if(this.uniqueLettersList.size() != mappingList.size()){
			throw new Exception(
					"Number of unique letters (" + this.uniqueLettersList.size() + ")" + 
					" is not equal to the mapping size (" + mappingList.size() + ")" );
		}
		//
		// In the string "abc", 
		//		positional value of "c" is 1  
		//		positional value of "b" is 10  
		//		positional value of "a" is 100
		// 
		// Thus, the number corresponding to "abc"
		//		= 100 * digit(a) + 10 * digit(b) + 1 * digit(c) 
		// Find the relationship between the letter at position i and
		// its positional value (that is, the power of 10)
		//
		// For each letter in puzzlePiece,
		//		-- Let l be this letter
		// 		-- Find the node (uniqLetterNode) for this letter in uniqueLettersList
		// 		-- Find the digit corresponding to uniqLetterNode from mappingList
		//		-- add the numeric contribution of this letter to the 
		//			total value for this puzzle piece
		//
		//
		//
		for(int i = 0; i < puzzlePiece.length() ; i++){
			String letterInPuzzle = "" + puzzlePiece.charAt(i);
			//
			//
			Node uniqLetterNode = this.uniqueLettersList.getHead();
			Node mappingNode = mappingList.getHead();
			while(uniqLetterNode != null){
				//
				// see if the current node in unique letters list is 
				// equal to the current letter in puzzle
				//
				if(uniqLetterNode.getElement().equals(letterInPuzzle)){
					//
					// yes
					// what is the digit for this letter?
					//
					int digit = ((Integer) mappingNode.getElement()).intValue();
					int powerOfTen = puzzlePiece.length() - i - 1;
					//
					numberForPuzzlePiece += digit * Math.pow(10, powerOfTen);
					//
					// done with this letter
					//
					break;
				}
				else{
					//
					// no
					// Go to the next unique letter and its mapping
					//
					// Advance uniqLetterNode
					// Advance mappingNode
					//
					uniqLetterNode = uniqLetterNode.getNext();
					mappingNode = mappingNode.getNext();
				}
			} // for each unique letter
			
		} // for each letter in puzzlePiece
		//
		return numberForPuzzlePiece;
		
	}
	private SLinkedList getListOfAllDigits(){
		SLinkedList digits = new SLinkedList();
		//
		// create a node for each digit 0-9
		//
		for(int i = 0; i < 10; i++){
			Node digitNode = new Node(i);
			digits.addLast(digitNode);
		}
		return digits;
	}
	/**
	 * Identifies the unique letters in a string. Non-letter characters 
	 * are ignored. The characters in the input string are changed into lower case.
	 * 
	 * @return SLinkedList				A singly-linked list containing the unique characters.
	 * */
	public SLinkedList getListOfUniqueLetters(){
		SLinkedList uniqueLetters = new SLinkedList();
		if( this.inputLine != ""){
			//
			//
			boolean[] letters = new boolean[26]; // to keep track of used letters
			//
			// initialize with false
			//
			for(int i = 0; i < letters.length; i++){
				letters[i] = false; // denoting not present in the input
			}
			//
			//
			for(int i = 0; i < this.inputLine.length(); i++){
				char c = this.inputLine.charAt(i);
				//
				// consider only letter characters [a-z]
				//
				if( c >= 'a' && c <= 'z'){
					int letterIndex = c - 'a';
					if( letters[letterIndex] == false){
						letters[letterIndex] = true;
						//
						// add to the end of our list
						// so that the list becomes alphabetically sorted
						//
						Node node = new Node("" + c);
						uniqueLetters.addLast(node);
					}
				}
			}
		}
		//
		return uniqueLetters;
	}

	/**
	 * Splits the puzzle input string into three parts.
	 * 
	 * @return String[]			An array containing the three parts 
	 * 							of the input string. 
	 * 							[0] <= The first part (first operand)
	 * 							[1] <= The second part (second operand)
	 * 							[2] <= The third part (third operand)
	 * */
	public String[] parsePuzzleInput() throws Exception{
		String[] puzzleComponents = null;
		//
		// make sure the input is correctly formatted
		//
		int plusPos = this.inputLine.indexOf('+');
		int equalPos = this.inputLine.indexOf('=');
		if( equalPos < 0 || plusPos > equalPos){
			throw new Exception("Bad input");
		}
		//
		//
		StringTokenizer splitter = 
			new StringTokenizer(
					this.inputLine, // the string to split
					"+=" // the delimiters for splitting
		);
//		//
//		// there must be exactly three parts
//		//
//		if(splitter.countTokens() != 3){
//			throw new Exception("Bad input format: \"" + this.inputLine.trim() + "\"");
//		}
		//
		puzzleComponents = new String[splitter.countTokens()];
		int index = 0;
		while(splitter.hasMoreElements()){
			String part = splitter.nextToken().trim();
			puzzleComponents[index] = part;
			index++;
		}
		//
		//
		return puzzleComponents;
	}
}
