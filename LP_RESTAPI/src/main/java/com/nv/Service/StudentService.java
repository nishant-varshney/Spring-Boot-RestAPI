package com.nv.Service;

import java.util.List;

import com.nv.Model.StudentInformation;



public interface StudentService {
	
	List<StudentInformation> listStudent();
	String findOne(Integer id);
	String addStudent(StudentInformation studentInfo);
	String deleteStudent(Integer id);

}
