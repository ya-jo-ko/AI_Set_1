import java.util.List;

/*
 * Class for constraint specification
 * Constraint 1: different characters must have different numbers
 * Constraint 2: sum must be valid
 * Constraint 3: no number can start with 0
 */

public class Constraints {

	public int X1;
	public int X2;

	//Constructor
	public Constraints(int X1, int X2) {
		this.X1 = X1;
		this.X2 = X2;
	}
	
	
	//Useful functions
	public boolean checkContain(List<Vars> assignedVars, Vars[] row, int index) {
		for(int i=0;i<assignedVars.size();i++) {
			if(assignedVars.get(i).character.equals(row[index].character)) {
				return true;
			}
		}
		return false;
	}

	public void setX1X2(List<Vars> assignedVars, int rowNum, Vars[] row, int[] domain) {
		if(rowNum==2) {
			if(checkContain(assignedVars, row, 0)) {
				if(checkContain(assignedVars, row, 1)) {
					this.X1=(row[0].assignment+row[1].assignment-(row[0].assignment+row[1].assignment)%domain.length)/domain.length;
				}
			}
		}
		else if(rowNum==1) {
			if(checkContain(assignedVars, row, 0)) {
				if(checkContain(assignedVars, row, 1)) {
					this.X2=(row[0].assignment+row[1].assignment-(row[0].assignment+row[1].assignment)%domain.length)/domain.length;
				}
			}
		}
	}
	
	public void setX1X2zero() {
		this.X1=0;
		this.X2=0;
	}

	public boolean checkIfAllDiff(List<Vars> assignedVars, Vars var) {
		for (int i=0;i<assignedVars.size();i++) {
			int value = assignedVars.get(i).assignment;
			if(value==var.assignment) {
				return false;
			}
		}
		return true;
	}

	public boolean checkSum(List<Vars> varsToAssign, Vars var, int rowNum, Vars[] row, int[] domain) {
		for(int i=0;i<row.length;i++) {
			for(int j=0;j<varsToAssign.size();j++) {
				if(varsToAssign.get(j).character.equals(row[i].character)) {
					return true;
				}
			}
			if(var.character.equals(row[i].character)) {
				row[i].assignment=var.assignment;
			}
		}
		if(rowNum==0) {
			if((row[0].assignment+row[1].assignment+X2)==row[2].assignment)
				return true;
		}
		else if(rowNum==1) {
			if((row[0].assignment+row[1].assignment + X1)==(row[2].assignment+X2*domain.length))
				return true;
		}
		else {
			if((row[0].assignment+row[1].assignment)==(row[2].assignment+X1*domain.length))
				return true;
		}
		return false;
	}

	public boolean checkZeroAtStart(Vars[] line, Vars var, int value) {
		if(var.character.equals(line[0].character)) {
			if(value==0) {
				return false;
			}
		}
		return true;
	}

}
