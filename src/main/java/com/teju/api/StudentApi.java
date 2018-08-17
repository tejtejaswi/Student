package com.teju.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teju.dao.StudentDao;
import com.teju.model.Student;



@RestController

public class StudentApi {
	Logger logger = LoggerFactory.getLogger(StudentApi.class);

	@Autowired
	StudentDao studentdao;
	
	@RequestMapping(path = "/student", method = RequestMethod.POST)
	public String create(@RequestBody Student student1) {
		logger.info("create method called for id:" + student1.getId());
		 
		if (student1.getId()== null || student1.getId() == 0)
			throw new RuntimeException ("Id must be passed");
		
		boolean created = studentdao.create(student1);
		if (created)
			return "Record created successfully";
		else return "Record couldn't be created";
		
	} 
	
	@RequestMapping(path = "/student",method = RequestMethod.PUT)
	public String change(@RequestBody Student student1) {
	logger.info("Update method calledfor ID:" + student1.getId());
	
	if (student1.getId() == null || student1.getId() == 0)
		throw new RuntimeException ("Id must be passed");
	
	boolean Updated = studentdao.update( student1);
	if (Updated)
		return "Record Updated successfully";
	else 
		return "Record Couldn't be Updated";
	
	}
	
	@RequestMapping(path = "/student",method = RequestMethod.DELETE)
	public String delete(@RequestParam Integer id){
		logger.info("Delete Method called for Id:" + id );
		if (id == null || id == 0)
			throw new RuntimeException ("ID must be passed");
		boolean deleted = studentdao.delete(id);
		if (deleted )
			return "Record deleted Successfully";
		else
			return "Record couldnt be deleted";
	}
	@RequestMapping (path = "/student", method = RequestMethod.GET)
	public Student get(@RequestParam Integer id){
		logger.info("Get Method called for ID:" +id);
		
		return studentdao.get(id);
		
	}
	@RequestMapping (path = "/student/getAll", method = RequestMethod.GET)
	public List<Student> getAll(){
		logger.info("GetAll Method called");
		return studentdao.getAll();
	}
	}

