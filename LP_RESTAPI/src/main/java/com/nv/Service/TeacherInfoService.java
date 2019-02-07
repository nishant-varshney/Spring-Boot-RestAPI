package com.nv.Service;

import java.util.List;

import com.nv.Model.SearchCC;
import com.nv.Model.SearchTeacher;
import com.nv.Model.TeacherInformation;
import com.nv.VO.ClassOutput;
import com.nv.VO.SearchTeacherInput;
import com.nv.VO.SubjectOutput;



public interface TeacherInfoService {
	
	List<TeacherInformation> listTeacher();
	String findOne(Integer id);
	String addTeacher(TeacherInformation teacherInfo);
	String deleteUser(Integer id);
	
	String findTeacherforaffilliation(SearchTeacherInput seachTeacherInput);
	
	List<SubjectOutput> findSubject(Integer teacherID);
	
	List<ClassOutput> findClass(Integer teacherID);
}
