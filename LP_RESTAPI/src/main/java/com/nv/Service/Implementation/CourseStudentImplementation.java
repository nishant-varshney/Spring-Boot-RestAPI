package com.nv.Service.Implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nv.Model.CourseInformation;
import com.nv.Model.CourseStudentMapping;
import com.nv.Model.StudentInformation;
import com.nv.Repository.CourseRepository;
import com.nv.Repository.CourseStudentRepository;
import com.nv.Repository.StudentRepository;
import com.nv.Service.CourseStudentService;
import com.nv.Utility.Utility;


@Service
public class CourseStudentImplementation implements CourseStudentService {

	
	@Autowired
	CourseStudentRepository courseStuRepo;
	
	@Autowired
	StudentRepository studentRepo;
	
	@Autowired
	CourseRepository courseRepo;
	
	@Override
	public List<CourseStudentMapping> listCourseStudent() {
		// TODO Auto-generated method stub
		return courseStuRepo.findAll();
	}

	@Override
	public String addCourseStudentMapping(List<CourseStudentMapping> courseStudent) {
		// TODO Auto-generated method stub
		List<String> key = new ArrayList<String>();
		List<String> val = new ArrayList<String>();
		System.out.println("method called");
		List<CourseStudentMapping> courseStudentTosave = new ArrayList<CourseStudentMapping>(); 
		List<StudentInformation> invalidStudent = new ArrayList<StudentInformation>();
		List<CourseInformation> invalidCourse =  new ArrayList<CourseInformation>();
		try{
			for(CourseStudentMapping mapping : courseStudent){
					if(mapping.getStudent() != null && !studentRepo.existsById(mapping.getStudent().getId())){
						invalidStudent.add(mapping.getStudent());
					}else if(mapping.getCourse()!= null && !courseRepo.existsById(mapping.getCourse().getId())){
						invalidCourse.add(mapping.getCourse());
					}else{
					    if(courseStuRepo.getCourseStuCount(mapping.getCourse(), mapping.getStudent()) == 0 ){
					    	courseStudentTosave.add(mapping);
					    }
					}
			}
			if(courseStudentTosave.size() > 0){
				courseStuRepo.saveAll(courseStudentTosave);
				CourseInformation dbCourse = courseRepo.getOne(courseStudentTosave.get(0).getCourse().getId());
				dbCourse.setStudentCount(courseStudentTosave.size());
				courseRepo.save(dbCourse);
				key.add("Status");val.add("Success");
				key.add("Message");val.add("Student Enrollement Successfully completed");
				String student="";
				String course="";
				for(StudentInformation invalidstu : invalidStudent){
					student = student+","+invalidstu.getId();
				}
				for(CourseInformation invalidcou : invalidCourse){
					course = course+","+invalidcou.getId();
				}
				key.add("Invalid Student ID");val.add(student.replaceFirst(",", ""));
				key.add("Invalid Course ID");val.add(course.replaceFirst(",", ""));
			}else{
				key.add("Status");val.add("Warning");
				key.add("Message");val.add("All the students are already enrolled");
			}
			
		}catch(Exception e){
			key.clear();val.clear();
			key.add("Status");val.add("Error");
			key.add("Message");val.add("Error at server side");
			e.printStackTrace();
		}
		return Utility.getResponce(key, val);
	}

	@Override
	public String deleteCourseStudentMapping(List<CourseStudentMapping> courseStudent) {
		// TODO Auto-generated method stub
		List<String> key = new ArrayList<String>();
		List<String> val = new ArrayList<String>();
		List<CourseStudentMapping> courseStudentToRemove = new ArrayList<CourseStudentMapping>(); 
		try{
			for(CourseStudentMapping mapping : courseStudent){
				 if(courseStuRepo.getCourseStuCount(mapping.getCourse(), mapping.getStudent()) >= 0 ){
					 courseStudentToRemove.add(mapping);
				  }
			}
				 courseStuRepo.deleteAll(courseStudentToRemove);
				 key.add("Status");val.add("Success");
				 key.add("Message");val.add("Student Successfully removed from course");
				 CourseInformation dbCourse = courseRepo.getOne(courseStudentToRemove.get(0).getCourse().getId());
				 dbCourse.setStudentCount(courseStudentToRemove.size());
				 courseRepo.save(dbCourse);
		}catch(Exception e){
			key.clear();val.clear();
			key.add("Status");val.add("Error");
			key.add("Message");val.add("Error at server side");
			e.printStackTrace();
		}
		return Utility.getResponce(key, val);
	}

}
