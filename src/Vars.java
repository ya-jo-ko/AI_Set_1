import java.util.List;
/*
 * Class for defining our variables - alpharithmetic characters
 */
public class Vars {
	public String character;
	public int assignment;
	public List<Integer> rows;
	
	public Vars(List<Integer> rows) {
		this.rows = rows;
	}
	public List<Integer> getRows() {
		return rows;
	}
	public void setRows(List<Integer> rows) {
		this.rows = rows;
	}
	public String getCharacter() {
		return character;
	}
	public void setCharacter(String character) {
		this.character = character;
	}
	public int getAssignment() {
		return assignment;
	}
	public void setAssignment(int assignment) {
		this.assignment = assignment;
	}
	
}
