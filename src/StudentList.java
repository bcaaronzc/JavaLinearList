import java.util.Scanner;

public class StudentList {
	Student[] data;
	int dataSize;
	int arraySize;
	
	// Constructor
	StudentList(int initArraySize){
		arraySize = initArraySize;
		data = new Student[arraySize];
		dataSize = 0;
	}
	
	// Expand array size
	void expandDataSize(){
		int expandMount = 10;
		arraySize = arraySize + expandMount;
		Student[] newData = new Student[arraySize];
		for (int i = 0; i < arraySize - expandMount; i++){
			newData[i] = data[i];
		}
		data = newData;
	}
	
	// Get item from the list
	Student getItem(int no){
		if (no > dataSize || data[no] == null){
			return null;
		}
		else {
			return data[no];
		}
	}
	
	// Get all data
	void getData(int no, int studentNumber, String name, String sex, int age){
		studentNumber = data[no].getStudentNumber();
		name = data[no].getName();
		if (data[no].getSex() == true){
			sex = "Male";
		}
		else {
			sex = "Female";
		}
		age = data[no].getAge();
	}
	
	// Add item
	void addItem(int initStudentNumber, String initName, boolean initIsMale, int initAge){
		if (dataSize >= arraySize){
			expandDataSize();
		}
		Student newStudent = new Student(initStudentNumber, initName, initIsMale, initAge);
		data[dataSize] = newStudent;
		dataSize = dataSize + 1;
		System.out.println("Student added.\n");
	}
	
	// Insert item
	void insertItem(int loc, int initStudentNumber, String initName, boolean initIsMale, int initAge){
		if (dataSize >= arraySize){
			expandDataSize();
		}
		for (int i = dataSize; i >= loc; i--){
			data[i + 1] = data[i];
		}
		Student newStudent = new Student(initStudentNumber, initName, initIsMale, initAge);
		data[loc] = newStudent;
		dataSize++;
	}
	
	// Locate an item in the list (Search item)
	int locate(int studentNumber){
		for (int i = 0; i <= dataSize; i++){
			if (data[i].getStudentNumber() == studentNumber){
				return i;
			}
		}
		return -1;
	}
	
	int locate(String name){
		for (int i = 0; i <= dataSize; i++){
			if (data[i].getName() == name){
				return i;
			}
		}
		return -1;
	}
	
	// Delete an item
	void delete(int loc){
		for (int i = loc; i < dataSize; i++){
			data[i] = data[i + 1];
		}
		dataSize--;
	}
	
	// Print all data
	void printAllData(){
		for (int i = 0; i < dataSize; i++){
			System.out.println("----- Shanghai JianPing Experimental Middle School Grade 9 Class 10 Student List -----");
			System.out.println("Student number:\t" + data[i].studentNumber);
			System.out.println("Name:\t\t" + data[i].getName());
			System.out.println("Sex:\t\t" + data[i].getSex());
			System.out.println("Age:\t\t" + data[i].getAge() + "\n");
		}
	}
	
	public static void main(String[] args) {
		StudentList studentList = new StudentList(10);
		Scanner input = new Scanner(System.in);
		int again = 1;
		do {
			System.out.print("Student number: ");
			int studentNumber = input.nextInt();
			System.out.print("Name: ");
			String name = input.next();
			System.out.print("Sex (m/f): ");
			String sex = input.next();
			boolean isMale;
			if (sex == "m"){
				isMale = true;
			}
			else {
				isMale = false;
			}
			System.out.print("Age: ");
			int age = input.nextInt();
			studentList.addItem(studentNumber, name, isMale, age);
			System.out.print("Student List Status: \n");
			System.out.println("\tData Size: " + studentList.dataSize);
			System.out.println("\tArray Size: " + studentList.arraySize);
			System.out.print("\nAdd another student? (1/0): ");
			again = input.nextInt();
		} while (again == 1);
		input.close();
		studentList.printAllData();
	}
}
