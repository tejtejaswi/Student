package com.teju.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.teju.model.Student;

@Component
public class StudentDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private final String Insert_Student = "INSERT INTO student(id, first_Name, last_Name, email, salary) VALUES (?, ?, ?, ?, ?)";
	private final String Update_Student = "UPDATE mytestdb.student SET first_Name = ?, last_Name = ?, email = ?, salary = ? WHERE id = ?";
	private final String Delete_Student = "DELETE FROM mytestdb.student WHERE id = ? ";
	private final String Get_By_Id = "select * from student where id = ?";
	private final String GetAll = "select * from student";
	
	public boolean create(Student student1){
	int noOfStudents = jdbcTemplate.update(Insert_Student,new Object[] { 
			student1.getId(),student1.getFirst_Name(),student1.getLast_Name(),student1.getEmail(),student1.getSalary() 
				});
	if (noOfStudents > 0)
			
	return true;	
	else
	return false;
				
		
	}
	
	public boolean update(Student student1){
		int noOfStudents = jdbcTemplate.update(Update_Student,new Object[] {
				student1.getFirst_Name(),student1.getLast_Name(),student1.getEmail(),student1.getSalary(),student1.getId()
				
		});
		
		if (noOfStudents > 0)
			return true;
		else
			return false;
	}
	
	public boolean delete(Integer id){
		int noOfRecordsDeleted = jdbcTemplate.update(Delete_Student,new Object[]{id});
		if (noOfRecordsDeleted > 0 )
			return true;
		else 
			return false;
	}
public Student get(Integer id){
	System.out.println("id entered");
	Map<String,Object> row = jdbcTemplate.queryForMap(Get_By_Id,new Object[]{id});
	System.out.println("after query");
	Student tempstudent = buildStudent(row);
	System.out.println("returning");
	return tempstudent;
}
	public Student buildStudent(Map<String,Object>row){
		System.out.println("start of build");
		Student tempstudent = new Student();
		tempstudent.setId((Integer) row.get("id"));
		tempstudent.setFirst_Name((String) row.get("first_Name"));
		tempstudent.setLast_Name((String ) row.get("last_Name"));
		tempstudent.setEmail((String) row.get("email"));
		tempstudent.setSalary((Double) row.get("salary"));
		return tempstudent;
		
	}
	public List<Student> getAll(){
		List<Student> studentList = new ArrayList<Student>();
		List<Map<String, Object>> rowList = jdbcTemplate.queryForList(GetAll) ;
		for(Map<String,Object>row : rowList){
			studentList.add(buildStudent(row));
		
		}
		
	return studentList;	
	}
	
	
}
