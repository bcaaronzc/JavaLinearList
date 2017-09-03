
public class Student {
	int studentNumber;
	String name;
	boolean isMale;
	int age;
	
	Student(int initStudentNumber, String initName, boolean initIsMale, int initAge){
		studentNumber = initStudentNumber;
		name = initName;
		isMale = initIsMale;
		age = initAge;
	}
	
	void changeStudentNumber(int newNumber){
		studentNumber = newNumber;
	}
	
	void changeName(String newName){
		name = newName;
	}
	
	void changeIsMale(boolean newIsMale){
		isMale = newIsMale;
	}
	
	void changeAge(int newAge){
		age = newAge;
	}
	
	int getStudentNumber(){
		return studentNumber;
	}
	
	String getName(){
		return name;
	}
	
	boolean getSex(){
		return isMale;
	}
	
	int getAge(){
		return age;
	}
}
