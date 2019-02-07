package com.nv.Controller;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.nv.Configuration.AccountCredentials;
import com.nv.Model.CCInformation;
import com.nv.Model.CCTeacherInformation;
import com.nv.Model.ClassInformation;
import com.nv.Model.CourseInformation;
import com.nv.Model.CourseStudentMapping;
import com.nv.Model.ScheduleClassInformation;
import com.nv.Model.SearchCC;
import com.nv.Model.SearchTeacher;
import com.nv.Model.StudentInformation;
import com.nv.Model.SubjectInformation;
import com.nv.Model.TeacherInformation;
import com.nv.Model.TeacherSubjectInformation;
import com.nv.Model.UserInformation;
import com.nv.Repository.CourseStudentRepository;
import com.nv.Service.CCService;
import com.nv.Service.CCTeacherInfoService;
import com.nv.Service.ClassService;
import com.nv.Service.CourseInfoService;
import com.nv.Service.CourseStudentService;
import com.nv.Service.ScheduleClassService;
import com.nv.Service.StudentService;
import com.nv.Service.SubjectService;
import com.nv.Service.TeacherInfoService;
import com.nv.Service.UserInfoService;
import com.nv.Service.Implementation.ClassInfoImplementation;
import com.nv.Service.Implementation.ScheduleClassInfoImplementation;
import com.nv.Utility.Utility;
import com.nv.VO.ClassOutput;
import com.nv.VO.CoursebyIDOutput;
import com.nv.VO.ExploreCourseInput;
import com.nv.VO.SearchCCInput;
import com.nv.VO.SearchCourseInput;
import com.nv.VO.SearchTeacherInput;
import com.nv.VO.SubjectOutput;


@RestController
public class MainController {
	
	
	@Autowired
	UserInfoService userInfoService;
	
	@Autowired
	TeacherInfoService teacherService;
	
	@Autowired 
	CCService ccService;
	
	@Autowired
	CourseInfoService courseService;
	
	@Autowired
	StudentService studentService;
	
	
	@Autowired
	CCTeacherInfoService ccTeacherService;
	
	@Autowired
	CourseStudentService courseStudentService;
	
	@Autowired
	ClassService classService;
	
	@Autowired
	SubjectService subjectService;
	
	
	@Autowired
	ScheduleClassService SCService;
	
														/*  SIGNUP APIs	*/

	@PostMapping(value="/newuser/register",consumes= MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String signup(@RequestBody UserInformation userInfo) {
		System.out.println("calles");
		return userInfoService.addUser(userInfo);
	}	
	
	@PostMapping(value="/newuser/validate",consumes= MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String  validateOTP(@RequestBody UserInformation userInfo) {
		System.out.println("validateOTP");
		if(userInfo.getPassword() != null)
			userInfo.setPassword(new BCryptPasswordEncoder().encode(userInfo.getPassword()));
		return userInfoService.validateOTP(userInfo);
	}	
	
													/* HAND-SHAKING Process APIs  */
	
	
	@GetMapping(value="affilliation/find-cc",produces=MediaType.APPLICATION_JSON_VALUE)
	public  @ResponseBody String findCC(@RequestParam(value="name",required=false) String name,@RequestParam(value="email",required=false) String email,
										@RequestParam(value="pincode",required=false) String pincode,@RequestParam(value="city",required=false) String city,
										@RequestParam(value="state",required=false) String state,@RequestParam(value="phoneno",required=false) String phoneno){
		SearchCCInput searCCInput = new SearchCCInput(name, email, pincode, city, state, phoneno);
		return ccService.findCCforaffilliation(searCCInput);
	}	
	
	
	@GetMapping(value="affilliation/find-teacher",produces=MediaType.APPLICATION_JSON_VALUE)
	public  @ResponseBody String findTeacher(@RequestParam(value="name",required=false) String name,@RequestParam(value="classID",required=false) List<ClassInformation> classID,
											@RequestParam(value="subject",required=false) List<SubjectInformation> subject,@RequestParam(value="pincode",required=false) List<String> pincode,
										@RequestParam(value="city",required=false) List<String> city,@RequestParam(value="state",required=false) List<String> state,
										@RequestParam(value="qualification",required=false) List<String> qualification,@RequestParam(value="experience",required=false) List<String> experience,
										@RequestParam(value="email",required=false) List<String> email,@RequestParam(value="phoneNo",required=false) List<String> phoneNo){
		//SearchTeacher searchTeacher = new SearchTeacher(name, subject, classID, pincode, city, state, qualification, experience, email, phoneNo);
	try{
		SearchTeacherInput searchTeacherInput = new SearchTeacherInput(name, subject, classID, pincode, city, state, qualification, experience, email, phoneNo);
		return teacherService.findTeacherforaffilliation(searchTeacherInput);
	}catch(Exception e){
		e.printStackTrace();
		return "error";
	}
	}	
	
	
	@PostMapping(value="affilliation/request",consumes= MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String reqTeacherforCC(@RequestBody CCTeacherInformation ccTeacherInfo) {
		System.out.println(ccTeacherInfo.getTeacher().getTeacherID());
		//ccTeacherInfo.setRequestByRole("CC");
		return ccTeacherService.addCCTeacher(ccTeacherInfo);
	}	
	
	
	@GetMapping(value="affilliation/request/{Role}/{ID}",produces=MediaType.APPLICATION_JSON_VALUE)
	public  @ResponseBody String getPendingReq(@PathVariable("Role") String role,@PathVariable("ID") Integer id) {
		String a="";
		try {
			a= ccTeacherService.getPendingReq(role, id);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}	
	
	@PutMapping(value="affilliation/request",consumes= MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public  @ResponseBody String takeAction(@RequestBody CCTeacherInformation ccTeacherInfo) {
		return ccTeacherService.updateReq(ccTeacherInfo);
	}	
	
	
														/* USER DETAIL APIs*/

	
	@GetMapping(value="user/managed-user/{role}/{ID}",produces=MediaType.APPLICATION_JSON_VALUE)
	public  @ResponseBody String getUserDetail(@PathVariable("role") String role,@PathVariable("ID") Integer id) {
		if(role.equalsIgnoreCase("cc")){
			return ccService.findOne(id);
		}else if(role.equalsIgnoreCase("teacher")){
			return teacherService.findOne(id);
		}else if(role.equalsIgnoreCase("student")){
			return studentService.findOne(id);
		}else{
			return Utility.getResponce("Message", "Invalid Role");
		}
	}	
	
	
						
														/* ROLE-> TEACHER APIs */
	
	@PutMapping(value="teacher/managed-teacher/{teacherID}",consumes= MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String updateTeacher(@RequestBody TeacherInformation teacherInfo,@PathVariable("teacherID") Integer Teacherid) {
		System.out.println("call updateteacher"+Teacherid);
		teacherInfo.setTeacherID(Teacherid);
		return teacherService.addTeacher(teacherInfo);
	}	
	
	
	
	@GetMapping(value="teacher/managed-teacher/{CCID}",produces=MediaType.APPLICATION_JSON_VALUE)
	public  @ResponseBody String getTeacherList(@PathVariable("CCID") Integer CCID) {
		return ccTeacherService.getTeacherListByCC(CCID);
	}	
	
	
														/* ROLE -> CC APIs */
	
	@PutMapping(value="cc/managed-cc/{CCID}",consumes= MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String updateCC(@RequestBody CCInformation ccInfo,@PathVariable("CCID") Integer CCID) {
		//System.out.println("call updateCC"+ccInfo.getSubject().get(0).getSubject().getSubjectId());
		ccInfo.setId(CCID);
		return ccService.addCC(ccInfo);
	}	
	
													/* ROLE -> STUDENT APIs */
	

	@PutMapping(value="student/managed-student/{studentID}",consumes= MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String updateStudent(@RequestBody StudentInformation studentInfo,@PathVariable("studentID") Integer studentID) {
		//System.out.println("call updateCC"+CCID);
		studentInfo.setId(studentID);
		return studentService.addStudent(studentInfo);
	}	
	
													/* Course APIs  */
	
	@PostMapping(value="course/managed-courses",consumes= MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String addCourse(@RequestBody CourseInformation courseInfo) {
		System.out.println(courseInfo.getTeacher().getTeacherID());
		return courseService.addCourse(courseInfo);
	}	
	
	
	@PutMapping(value="course/managed-courses/{courseId}",consumes= MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String updateCourse(@PathVariable("courseId") Integer courseId,@RequestBody CourseInformation courseInfo) {
		System.out.println(courseInfo.getTeacher().getTeacherID());
		courseInfo.setId(courseId);
		return courseService.updateCourse(courseInfo);
	}	
	
	@PutMapping(value="course/managed-courses/course-active/{courseId}/{courseActive}",consumes= MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String updateCourseStatus(@PathVariable("courseId") Integer courseId,@PathVariable("courseActive") String courseActive) {
		return courseService.updateCourseStatus(courseId, courseActive);
	}	
	
	
	@GetMapping(value="course/managed-courses/{CourseID}",produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CoursebyIDOutput getCourseDetail(@PathVariable("CourseID") Integer CourseID) {
		CoursebyIDOutput c= null;
		try{
			c=courseService.findOne(CourseID);
		//	System.out.println("json:"+json);
		}catch(Exception e){
			System.out.println("error:"+e.toString());
			e.printStackTrace();
		}
		return c;
	}	
	
	
	@DeleteMapping(value="course/managed-courses/{CourseID}",produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String deleteCourse(@PathVariable("CourseID") Integer CourseID) {
		return courseService.deleteCourse(CourseID);
	}	
	
	
	
	@GetMapping(value="course/by/{Role}/{ID}",produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String searchCourse(@PathVariable("Role") String Role,@PathVariable("ID") Integer ID,
														@RequestParam(value="StartDate",required=false) String startDate,@RequestParam(value="EndDate",required=false) String endDate,
														@RequestParam(value="ClassID",required=false) List<ClassInformation> classID,@RequestParam(value="TeacherID",required=false) List<TeacherInformation> teacherID,
														@RequestParam(value="SubjectID",required=false) List<SubjectInformation> subjectID) {
		
		System.out.println("date received::"+startDate);
		SearchCourseInput searchInput = new SearchCourseInput(ID, Role, startDate, endDate, classID, subjectID, teacherID);
		return courseService.searchCourse(searchInput);
		
	}	
	
	
	@GetMapping(value="exploreCourse",produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String exploreCourse(@RequestParam(value="classID",required=false) List<ClassInformation> classID, @RequestParam(value="subjectID",required=false) List<SubjectInformation> subject,
											 @RequestParam(value="Day",required=false) String day,@RequestParam(value="StartDate",required=false) String startDate,
											 @RequestParam(value="EndDate",required=false) String endDate,@RequestParam(value="startTime",required=false) String startTime,
											 @RequestParam(value="endTime",required=false) String endTime,@RequestParam(value="pincode",required=false) String pincode) {
		
		System.out.println("date received::"+startDate);
		ExploreCourseInput searchInput = new ExploreCourseInput(pincode, startDate, endDate, startTime, endTime, classID, subject, day);
		return courseService.exploreCourse(searchInput);
		
	}	
	
												/* Student Enrollement */
	@PostMapping(value="enroll/course-student",consumes= MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String addCourseStudent(@RequestBody List<CourseStudentMapping> courseStudentMapping) {
		System.out.println("method caaled");
		return courseStudentService.addCourseStudentMapping(courseStudentMapping);
	}	
	
	@DeleteMapping(value="enroll/course-student/",consumes= MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String deleteCourseStudent(@RequestBody List<CourseStudentMapping> courseStudentMapping) {
		return courseStudentService.deleteCourseStudentMapping(courseStudentMapping);
	}	
	
	
													/* SUBJECT APIs */
	
	@GetMapping(value="subject/by/{Role}/{ID}",produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<SubjectOutput> getSubjectByRole(@PathVariable("Role") String role,@PathVariable("ID") Integer ID) {
		List<SubjectOutput> subjects = null;
		if(role.equalsIgnoreCase("cc"))
			subjects = ccService.findSubject(ID);
		else if(role.equalsIgnoreCase("teacher"))
			subjects = teacherService.findSubject(ID);
		//System.out.println("subject::"+subjects.size());
		return subjects;
	}	
	
	
	
	@GetMapping(value="master/subject",produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<SubjectInformation> getSubjectMaster() {
			return subjectService.findAll();
	}	
	
	
	@GetMapping(value="subject/find/{ID}",produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody SubjectInformation getSubjectByID(@PathVariable("ID") Integer ID) {
			return subjectService.getOne(ID);
	}	
	
	

	
													/* Class APIs */

		@GetMapping(value="class/by/{Role}/{ID}",produces=MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody List<ClassOutput> getClassByRole(@PathVariable("Role") String role,@PathVariable("ID") Integer ID) {
				List<ClassOutput> classes = null;
				if(role.equalsIgnoreCase("cc"))
					classes = ccService.findClass(ID);
				else if(role.equalsIgnoreCase("teacher"))
					classes = teacherService.findClass(ID);
				//System.out.println("subject::"+subjects.size());
				return classes;
		}	
		
		
		@GetMapping(value="master/class",produces=MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody List<ClassInformation> getClassMaster() {
				return classService.findAll();
		}	
		
		
		@GetMapping(value="class/find/{ID}",produces=MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody ClassInformation getClassByID(@PathVariable("ID") Integer ID) {
				return classService.getOne(ID);
		}	

	
									/* SCHEDULE CLASS API */
		
		
		@GetMapping(value="sc-class/find/{ID}/{role}/{startDate}/{endDate}",produces=MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody String getSC(@PathVariable("ID") Integer ID,@PathVariable("role") String role,@PathVariable("startDate") String startDate,@PathVariable("endDate") String endDate) {
				return SCService.listSC(ID,role,startDate,endDate);
		}	
		
		@PutMapping(value="sc-class/{ID}",produces=MediaType.APPLICATION_JSON_VALUE,consumes= MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody String updateSC(@RequestBody ScheduleClassInformation scInfo,@PathVariable("ID") Integer ID) {
				scInfo.setId(ID);
				return SCService.updateSC(scInfo);
		}	
		
		@PostMapping(value="sc-class/managed-sc",produces=MediaType.APPLICATION_JSON_VALUE,consumes= MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody String addSC(@RequestBody ScheduleClassInformation scInfo) {
				return SCService.addSc(scInfo);
		}	
	
	
	@GetMapping(value="/",produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String Test(){
		return Utility.getResponce("Status", "Your Project is running fine");
	}

}
