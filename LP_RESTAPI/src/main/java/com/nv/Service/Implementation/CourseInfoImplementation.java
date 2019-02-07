package com.nv.Service.Implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nv.Model.CourseInformation;
import com.nv.Model.TeacherInformation;
import com.nv.Model.UserInformation;
import com.nv.Repository.CourseRepository;
import com.nv.Repository.UserInfoRepository;
import com.nv.Service.CourseInfoService;
import com.nv.Service.ScheduleClassService;
import com.nv.Specification.CourseSpecification;
import com.nv.Specification.TeacherSpecification;
import com.nv.Specification.Criteria.SearchCriteria;
import com.nv.Utility.Utility;
import com.nv.VO.CourseOutput;
import com.nv.VO.CoursebyIDOutput;
import com.nv.VO.ExploreCourseInput;
import com.nv.VO.ExploreCourseOutput;
import com.nv.VO.SearchCCInput;
import com.nv.VO.SearchCourseInput;
import com.nv.VO.SearchCourseOutput;


@Service
public class CourseInfoImplementation implements CourseInfoService {

	
	@Autowired
	CourseRepository courseRepo;
	
	@Autowired
	UserInfoRepository userRepo;
	
	@Autowired
	ScheduleClassService scService;
	
	@Override
	public List<CourseInformation> listCourse() {
		// TODO Auto-generated method stub
		return courseRepo.findAll();
	}

	@Override
	public CoursebyIDOutput findOne(Integer id) {
		// TODO Auto-generated method stub
		CoursebyIDOutput course= null;
		try{
			if(courseRepo.existsById(id)){
			 course = courseRepo.findCourse(id);
			}
			System.out.println("course:"+course);
			
		}catch(Exception e){
			System.out.println("error while getting course::"+id+":::"+e.toString());
		//	e.printStackTrace();
		}
		return course;
	}

	@Override
	public String addCourse(CourseInformation courseInfo) {
		// TODO Auto-generated method stub
		List<String> key =  new ArrayList<String>();
		List<String> value =  new ArrayList<String>();
		Boolean isTeacherAvaliable = true;
		String dbFrequency="";
		Boolean add = false;
		
		try{
			int teacherCount = userRepo.getCountByIDandRole(courseInfo.getTeacher().getTeacherID(), "Teacher");
			int ownerCount = userRepo.getCountByIDandRole(courseInfo.getCreatedByID(), courseInfo.getCreatedByRole());
			if(teacherCount == 0){
					key.add("Status");value.add("Fail");
					key.add("Message");value.add("No teacher with given ID");
			}else if(ownerCount == 0){
				key.add("Status");value.add("Fail");
				key.add("Message");value.add("Role Not found with given ID");
			}
			else{
					if(courseInfo.getId() != null){
						dbFrequency =  courseRepo.findFreeTeacherforupdate(Utility.getSqlDate(courseInfo.getStartDate()), Utility.getSqlDate(courseInfo.getEndDate()), courseInfo.getStartTime(), 
								courseInfo.getEndTime(),courseInfo.getTeacher(),courseInfo.getId()); 
					}else{
						dbFrequency = courseRepo.findFreeTeacherforAdd(Utility.getSqlDate(courseInfo.getStartDate()), Utility.getSqlDate(courseInfo.getEndDate()), courseInfo.getStartTime(), 
								courseInfo.getEndTime(),courseInfo.getTeacher());
						add=true;
					}
					System.out.println("dbfre in course::"+dbFrequency);
					if(dbFrequency != null){
						isTeacherAvaliable = Utility.isFrequencyAvaliable(dbFrequency, courseInfo.getFrequency());
					}
					if(isTeacherAvaliable){
						courseInfo.setIsActive("true");
						courseRepo.save(courseInfo);
						if(add){
							System.out.println("Going to add SC");
							if(scService.addSC(courseInfo)){
								System.out.println("sc added Sucessfull");
								key.add("Status");value.add("Success");
								key.add("Message");value.add("Course Operation Successfull");
							}else{
								System.out.println("sc added Unsucessfull");
								key.add("Status");value.add("Warning");
								key.add("Message");value.add("Course Operation Successfull But Schedule Class Saving Failed!!");
							}
						}else{
							System.out.println("Going to update SC");
							courseInfo.setStartDate(Utility.getStartDate(courseInfo.getStartDate()));
							if(scService.updateSC(courseInfo)){
								key.add("Status");value.add("Success");
								key.add("Message");value.add("Course Operation Successfull");
							}
							else{
								key.add("Status");value.add("Warning");
								key.add("Message");value.add("Course Operation Successfull But Schedule Class update Failed!!");
							}
						}
					}else{
						key.add("Status");value.add("Warning");
						key.add("Message");value.add("Teacher is busy with this schedule");
					}
			}
			
		}catch(Exception e){
			key.add("Status");value.add("Error");
			key.add("Message");value.add("Course Operation failed");
			e.printStackTrace();
		}
		return Utility.getResponce(key, value);
	}

	@Override
	public String deleteCourse(Integer id) {
		List<String> key =  new ArrayList<String>();
		List<String> value =  new ArrayList<String>();
		try{
			if(!courseRepo.existsById(id)){
				key.add("Status");value.add("Fail");
				key.add("Message");value.add("No Course is registered with given ID");
			}else{
				courseRepo.deleteById(id);
				key.add("Status");value.add("Success");
				key.add("Message");value.add("Course Deleted Successfully");
			}
		}catch(Exception e){
			key.add("Status");value.add("Error");
			key.add("Message");value.add("Course Operation failed");
			e.printStackTrace();
		}
		return Utility.getResponce(key, value);
	}

	@Override
	public String updateCourse(CourseInformation courseInfo) {
		// TODO Auto-generated method stub
		List<String> key =  new ArrayList<String>();
		List<String> value =  new ArrayList<String>();
		if(!courseRepo.existsById(courseInfo.getId())){
			key.add("Status");value.add("Fail");
			key.add("Message");value.add("No Course is registered with given ID");
			return Utility.getResponce(key, value);
		}else{
			CourseInformation courseDB = courseRepo.getOne(courseInfo.getId());
			courseInfo.setIsActive(courseDB.getIsActive());
			return addCourse(courseInfo);
		}
			
	}

	@Override
	public String updateCourseStatus(Integer id, String active) {
		// TODO Auto-generated method stub
		List<String> key =  new ArrayList<String>();
		List<String> value =  new ArrayList<String>();
		try{
			if(!courseRepo.existsById(id)){
				key.add("Status");value.add("Fail");
				key.add("Message");value.add("No Course is registered with given ID");
			}else{
				CourseInformation courseDB = courseRepo.getOne(id);
				courseDB.setIsActive(active);
				courseRepo.save(courseDB);
				key.add("Status");value.add("Success");
				key.add("Message");value.add("Course Operation Successfull");
			}
		}catch(Exception e){
			key.add("Status");value.add("Error");
			key.add("Message");value.add("Course Operation failed");
			e.printStackTrace();
		}
		return Utility.getResponce(key, value);
	}
	
	

	@Override
	public String searchCourse(SearchCourseInput searchInput) {
		String searchResult = "";
		
		try{
		ObjectMapper ob= new ObjectMapper();
		
		Boolean isSearch = true;
		
			
		CourseSpecification userSpec=null;
		CourseSpecification roleSpec=null;
		CourseSpecification startDateSpec=null;
		CourseSpecification endDateSpec=null;
		CourseSpecification classSpec=null;
		CourseSpecification teacherSpec=null;
		CourseSpecification subjectSpec=null;
		
		
		userSpec = new CourseSpecification(new SearchCriteria(searchInput.getRole(), ":", searchInput.getUserID()));
		
		
		
		if(!Utility.isNull(searchInput.getStartDate())){
			startDateSpec = new CourseSpecification(new SearchCriteria("startDate",":",Utility.getSqlDate(searchInput.getStartDate())));
			isSearch=true;
		}
		if(!Utility.isNull(searchInput.getEndDate())){
			endDateSpec = new CourseSpecification(new SearchCriteria("endDate",":",Utility.getSqlDate(searchInput.getEndDate())));
			isSearch=true;
		}
		if(!Utility.isNull(searchInput.getClassID())){
			classSpec = new CourseSpecification(new SearchCriteria("classID",":",searchInput.getClassID()));
			isSearch=true;
		}
		if(!Utility.isNull(searchInput.getTeacher())){
			teacherSpec = new CourseSpecification(new SearchCriteria("teacher",":",searchInput.getTeacher()));
			isSearch=true;
		}
		if(!Utility.isNull(searchInput.getSubject())){
			subjectSpec = new CourseSpecification(new SearchCriteria("subject",":",searchInput.getSubject()));
			isSearch=true;
		}

		if(isSearch){
			Page<CourseOutput>  listreturn = courseRepo.findAll(Specification.where(userSpec).and(startDateSpec).and(endDateSpec)
																	.and(classSpec).and(teacherSpec).and(subjectSpec), CourseOutput.class,new PageRequest(0, 100));
			 searchResult =  ob.writeValueAsString(listreturn.getContent());
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return searchResult;
	}

	@Override
	public String exploreCourse(ExploreCourseInput searchInput) {
		// TODO Auto-generated method stub
		String searchResult = "";
		
		try{
		ObjectMapper ob= new ObjectMapper();
		
		Boolean isSearch = true;
		
			
		CourseSpecification startDateSpec=null;
		CourseSpecification endDateSpec=null;
		CourseSpecification classSpec=null;
		CourseSpecification subjectSpec=null;
		CourseSpecification startTimeSpec = null;
		CourseSpecification endTimeSpec = null;
		CourseSpecification pincodeSpec1 = null;
		CourseSpecification pincodeSpec2 = null;
		CourseSpecification freSpec = null;
		
		
		if(!Utility.isNull(searchInput.getStartDate())){
			startDateSpec = new CourseSpecification(new SearchCriteria("startDate",":",Utility.getSqlDate1(searchInput.getStartDate())));
			isSearch=true;
		}
		if(!Utility.isNull(searchInput.getEndDate())){
			endDateSpec = new CourseSpecification(new SearchCriteria("endDate",":",Utility.getSqlDate1(searchInput.getEndDate())));
			isSearch=true;
		}
		if(!Utility.isNull(searchInput.getStartTime())){
			startTimeSpec = new CourseSpecification(new SearchCriteria("startTime",":",searchInput.getStartTime()));
			isSearch=true;
		}
		if(!Utility.isNull(searchInput.getEndTime())){
			endTimeSpec = new CourseSpecification(new SearchCriteria("endTime",":",searchInput.getEndTime()));
			isSearch=true;
		}
		if(!Utility.isNull(searchInput.getClassID())){
			classSpec = new CourseSpecification(new SearchCriteria("classID",":",searchInput.getClassID()));
			isSearch=true;
		}
		if(!Utility.isNull(searchInput.getPincode())){
			pincodeSpec1 = new CourseSpecification(new SearchCriteria("exploreteacher",":",searchInput.getPincode()));
			pincodeSpec2 = new CourseSpecification(new SearchCriteria("explorecc",":",searchInput.getPincode()));
			isSearch=true;
		}
		if(!Utility.isNull(searchInput.getSubject())){
			subjectSpec = new CourseSpecification(new SearchCriteria("subject",":",searchInput.getSubject()));
			isSearch=true;
		}

		if(!Utility.isNull(searchInput.getDay())){
			freSpec = new CourseSpecification(new SearchCriteria("frequency",":",searchInput.getDay()));
			isSearch=true;
		}

		if(isSearch){
			Page<ExploreCourseOutput>  listreturn = courseRepo.findAll(Specification.where(classSpec).and(subjectSpec).and(freSpec)
																	.and(startDateSpec).and(endDateSpec).and(startTimeSpec).and(endTimeSpec).and(pincodeSpec1).or(pincodeSpec2), 
																	ExploreCourseOutput.class,new PageRequest(0, 100));
			 searchResult =  ob.writeValueAsString(listreturn.getContent());
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return searchResult;
	}


}
