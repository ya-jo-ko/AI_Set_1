import java.util.List;

/*
 * Class for backtracking algorithm
 */

public class BackTracking {
	public Vars[] line1;
	public Vars[] line2;
	public Vars[] line3;
	public Vars[][] rows;
	public List<Vars> varsToAssign;
	public List<Vars> assignedVars;
	public int[] domain; // [0,1,2,....,p-1]

	//Constructor
	public BackTracking(Vars[] line1, Vars[] line2, Vars[] line3, Vars[][] rows, List<Vars> varsToAssign, List<Vars> assignedVars, int[] domain) {
		this.assignedVars = assignedVars;
		this.domain = domain;
		this.line1 = line1;
		this.line2 = line2;
		this.line3  =  line3;
		this.rows = rows;
		this.varsToAssign = varsToAssign;
	}

	//Backtracking Search
	public boolean backTrackingSearch(Constraints csp) {
		return recursiveBackTracking(varsToAssign,assignedVars,csp,domain);
	}

	//Backtracking Recursion
	public boolean recursiveBackTracking(List<Vars> varsToAssign, List<Vars> assignedVars,Constraints csp, int[] domain) {
		if(varsToAssign.isEmpty()) {
			return true;
		}
		//pop varsToAssign
		Vars var = varsToAssign.get(0);
		varsToAssign.remove(0);
		//for each value in order-domain values
		for(int i=0;i<domain.length;i++) {
			//assign value to variable
			var.assignment=i;
			//check constraint 1, AllDiff
			boolean check0 = csp.checkIfAllDiff(assignedVars, var);
			//add variable to assigned, remove later if it's no good
			assignedVars.add(0,var);
			//set x1 x2
			for(int j=0;j<var.rows.size();j++) {
				csp.setX1X2(assignedVars, var.rows.get(j), rows[var.rows.get(j)], domain);
			}
			//check constraint 2, that sum is correct
			boolean check1 = true;
			for(int j=0;j<var.rows.size();j++) {
				check1 = csp.checkSum(varsToAssign, var, var.rows.get(j), rows[var.rows.get(j)], domain) && check1;
			}
			//check constraint 3, that no number starts with 0
			boolean check2 = csp.checkZeroAtStart(line1,var, var.assignment) && csp.checkZeroAtStart(line2,var, var.assignment) && csp.checkZeroAtStart(line3,var, var.assignment);
			if(check0 && check1 && check2) {
				boolean result = recursiveBackTracking(varsToAssign, assignedVars, csp, domain);
				if(result==true) {
					return result;
				}
			}
			//reset x1 x2, value was not good
			assignedVars.remove(0);
			for(int j=0;j<var.rows.size();j++) {
				csp.setX1X2zero();
			}
		}
		//add in list again, before backtracking
		varsToAssign.add(0, var);
		return false;
	}
	
	public void printAssignment(List<Vars> assignedVars) {
		System.out.println("\n%%%%%%%%%%%%%%%%%%%%%%%% Assignment is complete! %%%%%%%%%%%%%%%%%%%%%%%%");
		System.out.println("Results:");
		for(int i=0; i<assignedVars.size();i++) {
			System.out.println(assignedVars.get(i).character+" = "+assignedVars.get(i).assignment);
		}
	}
}
