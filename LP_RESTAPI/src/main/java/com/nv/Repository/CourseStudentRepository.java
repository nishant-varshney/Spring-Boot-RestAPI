package com.nv.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nv.Model.CCInformation;
import com.nv.Model.CourseInformation;
import com.nv.Model.CourseStudentMapping;
import com.nv.Model.StudentInformation;
import com.nv.Repository.BeanInterfaces.TeacherbyCC;



@Repository
public interface CourseStudentRepository extends JpaRepository<CourseStudentMapping, Integer>{
	
	final String courseStuCount = "select count(c) from CourseStudentMapping c where c.course = :courseID and c.student = :studentID";
	

    @Query(courseStuCount)
    public Integer getCourseStuCount(@Param("courseID") CourseInformation courseID,@Param("studentID") StudentInformation studentID);
    

}
