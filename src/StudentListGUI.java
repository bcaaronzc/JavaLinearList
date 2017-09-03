import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class StudentListGUI extends JFrame implements ActionListener{
	JButton addButton, searchButton, insertButton, deleteButton, refreshButton;
	JTextArea dataArea;
	StudentList studentList;
	
	StudentListGUI(){
		this.setTitle("Student List");
		this.setBounds(200, 200, 800, 400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		addOperationPanel();
		addInfoPanel();
		studentList = new StudentList(50);
		this.setVisible(true);
	}
	
	void addOperationPanel(){
		JPanel operationPanel = new JPanel();
		operationPanel.setBorder(BorderFactory.createTitledBorder("Operations"));
		addButton = new JButton(" Add  ");
		insertButton = new JButton("Insert");
		searchButton = new JButton("Search");
		deleteButton = new JButton("Delete");
		refreshButton = new JButton("Refresh");
		
		addButton.addActionListener(this);
		insertButton.addActionListener(this);
		searchButton.addActionListener(this);
		deleteButton.addActionListener(this);
		refreshButton.addActionListener(this);
		
		operationPanel.add(addButton);
		operationPanel.add(insertButton);
		operationPanel.add(deleteButton);
		operationPanel.add(searchButton);
		operationPanel.add(refreshButton);
		
		this.add(operationPanel, BorderLayout.NORTH);
	}
	
	void addInfoPanel(){
		dataArea = new JTextArea("Student Number\tName\t\tSex\t\tAge\t\t\n");
		JScrollPane infoScrollPane = new JScrollPane(dataArea);
		infoScrollPane.setBorder(BorderFactory.createTitledBorder("Info"));
		this.add(infoScrollPane);
	}
	
	void refreshInfoPanel(){
		dataArea.setText("Student Number\tName\t\tSex\t\tAge\t\t");
		for (int i = 0; i < studentList.dataSize; i++){
			int studentNumber = studentList.data[i].studentNumber;
			int age = studentList.data[i].age;
			String name = studentList.data[i].name;
			String sex = "";
			if (studentList.data[i].isMale == true){
				sex = "Male";
			}
			else {
				sex = "Female";
			}
			dataArea.append("\n" + studentNumber + "\t\t" + name + "\t\t" + sex + "\t\t" + age + "\t\t");
			dataArea.paintImmediately(dataArea.getBounds());
		}
	}

	public void actionPerformed(ActionEvent e){
		if (e.getActionCommand() == " Add  "){
			AddDialog addDialog = new AddDialog(studentList);
		}
		if (e.getActionCommand() == "Insert"){
		}
		if (e.getActionCommand() == "Search"){
		}
		if (e.getActionCommand() == "Delete"){
		}
		if (e.getActionCommand() == "Refresh"){
			System.out.println("Refresh data size = " + studentList.dataSize);
			refreshInfoPanel();
			studentList.printAllData();
		}
	}
	
	public static void main(String[] args) {
		StudentListGUI studentListGUI = new StudentListGUI();
	}
}

class AddDialog extends JDialog implements ActionListener{
	JLabel studentNumberLabel, nameLabel, sexLabel, ageLabel;
	JTextField studentNumberArea, nameArea, ageArea;
	JButton comfirmButton, cancelButton;
	JRadioButton maleButton, femaleButton;
	ButtonGroup sexButtons;
	StudentList studentList;
	
	public AddDialog(StudentList initStudentList){
		this.setTitle("Add A New Student");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setBounds(530, 379, 400, 220);
		studentList = initStudentList;
		
		addOperationPanel();
		addTextPanel();
		
		this.setVisible(true);
	}
	
	void addOperationPanel(){
		JPanel operationPanel = new JPanel();
		comfirmButton = new JButton("Comfirm");
		cancelButton = new JButton("Cancel");
		comfirmButton.addActionListener(this);
		cancelButton.addActionListener(this);
		operationPanel.add(comfirmButton);
		operationPanel.add(cancelButton);
		this.add(operationPanel, BorderLayout.SOUTH);
	}
	
	void addTextPanel(){
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new GridLayout(4, 2));
		studentNumberLabel = new JLabel("Student Number: ");
		nameLabel = new JLabel("Name: ");
		ageLabel = new JLabel("Age: ");
		sexLabel = new JLabel("Sex: ");
		studentNumberArea = new JTextField(15);
		nameArea = new JTextField(15);
		ageArea = new JTextField(15);
		
		maleButton = new JRadioButton("Male");
		femaleButton = new JRadioButton("Female");
		sexButtons = new ButtonGroup();
		sexButtons.add(maleButton);
		sexButtons.add(femaleButton);
		JPanel sexButtonsPanel = new JPanel();
		sexButtonsPanel.add(maleButton);
		sexButtonsPanel.add(femaleButton);
		
		JPanel studentNumberLabelPanel = new JPanel();
		JPanel studentNumberAreaPanel = new JPanel();
		JPanel nameLabelPanel = new JPanel();
		JPanel nameAreaPanel = new JPanel();
		JPanel ageLabelPanel = new JPanel();
		JPanel ageAreaPanel = new JPanel();
		JPanel sexLabelPanel = new JPanel();
		studentNumberLabelPanel.add(studentNumberLabel);
		studentNumberAreaPanel.add(studentNumberArea);
		nameLabelPanel.add(nameLabel);
		nameAreaPanel.add(nameArea);
		ageLabelPanel.add(ageLabel);
		ageAreaPanel.add(ageArea);
		sexLabelPanel.add(sexLabel);
		
		textPanel.add(studentNumberLabelPanel);
		textPanel.add(studentNumberAreaPanel);
		textPanel.add(nameLabelPanel);
		textPanel.add(nameAreaPanel);
		textPanel.add(ageLabelPanel);
		textPanel.add(ageAreaPanel);
		textPanel.add(sexLabelPanel);
		textPanel.add(sexButtonsPanel);
		
		this.add(textPanel, BorderLayout.CENTER);
	}
	
	boolean saveData(){
		if (studentNumberArea.getText() == ""){
			JOptionPane.showMessageDialog(null, "Please enter the student number."
	                , "Error", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if (nameArea.getText() == ""){
			JOptionPane.showMessageDialog(null, "Please enter the student's name."
	                , "Error", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if (ageArea.getText() == ""){
			JOptionPane.showMessageDialog(null, "Please enter the student's age."
	                , "Error", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if (!maleButton.isSelected() && !femaleButton.isSelected()){
			JOptionPane.showMessageDialog(null, "Please select the student's sex."
	                , "Error", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if (!isNumeric(studentNumberArea.getText())){
			JOptionPane.showMessageDialog(null, "Student Number is wrong."
	                , "Error", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if (!isNumeric(ageArea.getText())){
			JOptionPane.showMessageDialog(null, "The student's age is wrong."
	                , "Error", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if (isNumeric(nameArea.getText())){
			JOptionPane.showMessageDialog(null, "The student's name is wrong."
	                , "Error", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		
		int studentNumber = Integer.parseInt(studentNumberArea.getText());
		String name = nameArea.getText();
		int age = Integer.parseInt(ageArea.getText());
		boolean isMale = true;
		if (femaleButton.isSelected()){
			isMale = false;
		}
		
		studentList.addItem(studentNumber, name, isMale, age);
		return true;
	}
	
	boolean isNumeric(String testString){
		for (int i = 0; i < testString.length(); i++){
			if (!Character.isDigit(testString.charAt(i))){
				return false;
			}
		}
		return true;
	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getActionCommand() == "Comfirm"){
			if (saveData()){
				this.dispose();
			}
		}
		if (e.getActionCommand() == "Cancel"){
			this.dispose();
		}
	}
}
