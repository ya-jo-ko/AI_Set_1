import java.util.Scanner;  // Import the Scanner class
import java.util.List;
import java.util.*;

/*
 * Main function for csp problem of cryptarithmetic puzzles.
 * Program asks for domain number p, and solves puzzle in Zp
 */
public class Console {
	public static void main(String args[]) {
		Scanner myObj = new Scanner(System.in);  // Create a Scanner object
		int [] domain = readDomain(myObj);
		Scanner myObj2 = new Scanner(System.in);  // Create a Scanner object
		String [] stringArray = readFromUser(myObj2);
		myObj.close();
		myObj2.close();
		String firstLine = stringArray[0];
		String secondLine = stringArray[1];
		String thirdLine = stringArray[2];		
		List<Vars> varsToAssign = createVars(stringArray);
		List<Vars> assignedVars = new LinkedList<Vars>();
		Vars[] line1 = getLine(varsToAssign, 1, firstLine.length(), 0);
		Vars[] line2 = getLine(varsToAssign, 2, secondLine.length(), firstLine.length());
		Vars[] line3 = getLine(varsToAssign, 3, thirdLine.length(), secondLine.length());
		Vars[][] rows = getRows(varsToAssign, firstLine.length(), secondLine.length());
		varsToAssign = removeDuplicateVars(varsToAssign);

		BackTracking backTracking = new BackTracking(line1, line2, line3, rows, varsToAssign, assignedVars, domain);
		Constraints csp = new Constraints(0, 0);
		boolean check = backTracking.backTrackingSearch(csp);
		if(check) {
			backTracking.printAssignment(assignedVars);
		}
		else {
			System.out.println("No solution found for CSP...");
		}
	}
	// Useful functions
	public static String[] readFromUser(Scanner myObj) {
		String[] stringArray = new String[3];
		System.out.println("Enter first line: ");
		String firstLine = myObj.nextLine();  // Read user input
		System.out.println("Enter second line: ");
		String secondLine = myObj.nextLine();  // Read user input
		System.out.println("Enter third line: ");
		String thirdLine = myObj.nextLine();  // Read user input
		System.out.println("\nCryptarithmetic given: " );  // Output user input
		System.out.println(firstLine);
		System.out.println(secondLine);
		System.out.println(thirdLine);
		stringArray[0] = firstLine;
		stringArray[1] = secondLine;
		stringArray[2] = thirdLine;
		return stringArray;
	}

	public static int[] readDomain(Scanner myObj) {
		System.out.println("Enter domain: ");
		int domain = myObj.nextInt();  // Read user input
		int[] d = new int[domain];
		for(int i=0;i<domain;i++) {
			d[i]=i;
		}
		return d;
	}
	
	public static List<Vars> createVars(String[] stringArray) {
		List<Vars> varsToAssign = new LinkedList<Vars>();
		List<Integer> temp = new LinkedList<Integer>();
		for(int j=0; j<3; j++) {
			for(int i=0; i<stringArray[j].length(); i++) {
				Vars var = new Vars(temp);
				var.character = stringArray[j].substring(i, i+1);
				if(stringArray[j].length()==2) {
					var.rows.add(0, i+1);
				}
				else if(stringArray[j].length()==1){
					var.rows.add(0, i+2);
				}
				else {
					var.rows.add(0, i);					
				}
				varsToAssign.add(var);
			}
		}
		return varsToAssign;
	}

	public static Vars[] getLine(List<Vars> varsToAssign, int lineNumber, int lineSize, int prevLsize) {
		Vars[] line = new Vars[lineSize];
		for(int i=0;i<lineSize;i++) {
			line[i] = varsToAssign.get(i+(lineNumber-1)*prevLsize);
		}
		return line;
	}

	public static Vars[][] getRows(List<Vars> varsToAssign, int lineSize1, int lineSize2){
		Vars[][] rows = new Vars[3][3];
		List<Integer> temp = new LinkedList<Integer>();
		if(lineSize1<3) {
			if(lineSize2<3) {
				Vars var1 = new Vars(temp);
				var1.assignment=0;
				var1.character=null;
				rows[0][0]=var1;
				rows[0][1]=var1;
				rows[0][2]=varsToAssign.get(lineSize1+lineSize2);
				for(int i=1;i<3;i++) {
					rows[i][0]=varsToAssign.get(i-1);
					rows[i][1]=varsToAssign.get(lineSize1+i-1);
					rows[i][2]=varsToAssign.get(lineSize1+lineSize2+i);
				}
			}
		}
		else {
			for (int i=0;i<3;i++) {
				for (int j=0;j<3;j++) {
					rows[i][j]=varsToAssign.get(lineSize1*j+i);
				}
			}			
		}
		return rows;
	}
	
	public static boolean found(String c, List<Vars> varsToAssign)
	{
		boolean flag=false;
		for(int i=0;i<varsToAssign.size();i++)
		{
			if(varsToAssign.get(i).character.equals(c))
				flag=true;
		}
		if(flag)
			return true;
		else
			return false;	
	}
	
	public static List<Vars> removeDuplicateVars(List<Vars> varsToAssign){
		int i=0;
		while(i<varsToAssign.size()) {
			Vars var = varsToAssign.get(i);
			varsToAssign.remove(var);
			if(!found(var.character,varsToAssign)) {
				varsToAssign.add(i, var);
				i++;
			}
		}
		//change order
		varsToAssign.add(varsToAssign.get(0));
		varsToAssign.remove(0);
		varsToAssign.add(varsToAssign.get(0));
		varsToAssign.remove(0);
		return varsToAssign;
	}
}
