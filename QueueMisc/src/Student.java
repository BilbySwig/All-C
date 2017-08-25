

public class Student implements Comparable{
	private String fname, lname;
	private int gradeLevel;
	private double gpa;
	
	public Student( String f, String l, int gl, double gp){
		fname = f;
		lname = l;
		gradeLevel = gl;
		gpa = gp;
	}
	
	public String toString(){
		return fname+" "+lname+"\t"+gradeLevel+"th grade\t"+gpa+" gpa";
	}

	@Override
	public int compareTo(Object arg0) {
		Student other = (Student)arg0;
		if( this.gradeLevel>other.gradeLevel) //i am younger
			return -5; // i am "less than" you
		else if( this.gradeLevel<other.gradeLevel)//i am older
			return 5; //i am "greater than" you
		else // i am equal to you
			return 0;
	}
	
}
