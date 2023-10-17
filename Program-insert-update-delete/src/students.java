

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;

public class students {
	
    //database
	cn_db_mysql cdm = new cn_db_mysql();
	
    private String student_id;
    private String first_name;
    private String last_name;
    private String date_of_birth;
    private String gender;
    private String email;
    private String phone;
	private String major;
    private double gpa;
    
    public students() {
    	
    }
    
    // Constructor with only basic information (firstName, lastName, dateOfBirth, gender)
    public students(String firstName, String lastName, String dateOfBirth, String gender) {
        this.first_name = firstName;
        this.last_name = lastName;
        this.date_of_birth = dateOfBirth;
        this.gender = gender;
    }

    // Constructor with all the information
    public students(String student_id, String firstName, String lastName, String dateOfBirth,
                    String gender, String email, String phone, String major, double gpa) {
        this.student_id = student_id;
        this.first_name = firstName;
        this.last_name = lastName;
        this.date_of_birth = dateOfBirth;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.major = major;
        this.gpa = gpa;
    }
    
    //getter setter
    public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public double getGpa() {
		return gpa;
	}
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	
	public String generateLastRunNumber(int lastNumberOnDatabase) {
	    LocalDate currentDate = LocalDate.now();
	    DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yy");
	    String formattedYear = currentDate.format(yearFormatter);
	    int endYear = Integer.parseInt(formattedYear) + 43;

	    // Calculate the last run number
	    int lastRunNumber = lastNumberOnDatabase + 1;

	    // Format the last run number as a 4-digit string with leading zeros
	    String formattedLastRunNumber = String.format("%04d", lastRunNumber);

	    // Combine the end years, "410", and the formatted last run number
	    String lastRunNumberString = endYear + "0410" + formattedLastRunNumber;

	    return lastRunNumberString;
	}
	
    public String select_last_id(students s) {
    	try (Connection conn = cdm.getConnection()) {
	        String sql = "SELECT * FROM students ORDER BY StudentID DESC LIMIT 1;";
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        ResultSet resultSet = stmt.executeQuery();
	        
	        // Check if a result was found
	        if (resultSet.next()) {
	        	s.setStudent_id(resultSet.getString("StudentID"));	
	        }  else {
		        s.setStudent_id("0");
	        }
	        return "select data success " + s.student_id;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return "select data not success" ;
	    }	
    }
	
	// Select specific data by student ID
	public String selectData(students s) {
	    try (Connection conn = cdm.getConnection()) {
	        String sql = "SELECT * FROM students WHERE StudentID = ?";
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        stmt.setString(1, s.getStudent_id());
	        ResultSet resultSet = stmt.executeQuery();
	        
	        // Check if a result was found
	        if (resultSet.next()) {
	            s.setFirst_name( resultSet.getString("FirstName"));
	            s.setLast_name(resultSet.getString("LastName"));
	            s.setDate_of_birth(resultSet.getString("DateOfBirth"));
	            s.setGender(resultSet.getString("Gender"));
	            s.setEmail(resultSet.getString("Email")); // You need to add this column to your table
	            s.setPhone(resultSet.getString("Phone")); // You need to add this column to your table
	            s.setMajor(resultSet.getString("Major")); // You need to add this column to your table
	            s.setGpa(resultSet.getDouble("GPA")); // You need to add this column to your table
	        }
	        if (s.getFirst_name() != null) {
	        	return s.getStudent_id() + " " + s.getFirst_name() + " " + s.getLast_name() + " " + s.getGender();	
	        } else {
	        	return "input again!! ";
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return "select data not success";
	    }
	    
	    
	}

	
	//insert
    public String insertData(students s) {
		System.out.println(select_last_id(s));
		
	    String input = s.getStudent_id();
	    int lastNumberOnDatabase = 0; // Change this to the last number on your database

	    if (input.length() >= 4) {
	        String lastFourCharacters = input.substring(input.length() - 4);
	        lastNumberOnDatabase = Integer.parseInt(lastFourCharacters);
	        System.out.println("Last 4 characters: " + Integer.parseInt(lastFourCharacters));
	    } else {
	        System.out.println("The input string is too short.");
	        lastNumberOnDatabase = 0; // Set to a default value when the input is too short
	    }
    	
	    String lastRunNumber = generateLastRunNumber(lastNumberOnDatabase);
	    s.setStudent_id(lastRunNumber);
	    System.out.println("Last Run Number: " + lastRunNumber);
	    
        try (Connection conn = cdm.getConnection()) {
            String sql = "INSERT INTO students (StudentID,FirstName, LastName, DateOfBirth, Gender) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, s.student_id); 
            stmt.setString(2, s.first_name);
            stmt.setString(3, s.last_name);
            stmt.setString(4, s.date_of_birth);
            stmt.setString(5, s.gender);
            stmt.executeUpdate();
            return "Data inserted successfully.";
        } catch (SQLException e) {
        	e.printStackTrace();
            return e.getMessage();
        }
    }
    
    //update
    public String updateData(students s) {
        try (Connection conn = cdm.getConnection()) {
            String sql = "UPDATE students SET FirstName = ?, LastName = ? , DateOfBirth = ? , Gender = ? WHERE StudentID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, s.first_name);
            stmt.setString(2, s.last_name);
            stmt.setString(3, s.date_of_birth);
            stmt.setString(4, s.gender);
            stmt.setString(5, s.student_id);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                return "Data updated successfully.";
            } else {
                return "No data found with the given student ID.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
    
    //delete
    public String deleteData(students s) {
        try (Connection conn = cdm.getConnection()) {
            String sql = "DELETE FROM students WHERE StudentID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, s.getStudent_id());
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                return "Data deleted successfully.";
            } else {
                return "No data found with the given student ID.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
    

  }
