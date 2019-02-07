package com.nv.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import th.co.geniustree.springdata.jpa.repository.JpaSpecificationExecutorWithProjection;

import com.nv.Model.CCInformation;
import com.nv.Model.CourseInformation;
import com.nv.Model.TeacherInformation;
import com.nv.Repository.BeanInterfaces.TeacherbyCC;
import com.nv.VO.CoursebyIDOutput;



@Repository
public interface CourseRepository extends JpaRepository<CourseInformation, Integer>,JpaSpecificationExecutorWithProjection<CourseInformation>{
	
	/*
	
	  @Query("SELECT u FROM CourseInformation u join teacherID  WHERE u.id = :id ")
	    public CourseInformation getOne(@Param("id") Integer id);
*/
	final String  getFrequencyForADD= "select c.frequency from CourseInformation c where ((c.startDate >= :startDate or :startDate <= c.endDate) "
									+ "and (c.startDate <= :endDate or :endDate >= c.endDate ) and (c.startTime >= :startTime or :startTime <= c.endTime)"
									+ " and (c.startTime <= :endTime or :endTime >= c.endTime)) and c.isActive = 'true' and c.teacher = :teacher ";
	
	final String  getFrequencyForUPDATE= "select c.frequency from CourseInformation c where ((c.startDate >= :startDate or :startDate <= c.endDate) "
			+ "and (c.startDate <= :endDate or :endDate >= c.endDate ) and (c.startTime >= :startTime or :startTime <= c.endTime)"
			+ " and (c.startTime <= :endTime or :endTime >= c.endTime))  and c.isActive = 'true' and c.teacher = :teacher and c.id != :id";
	
	@Query(getFrequencyForADD)
    public String findFreeTeacherforAdd(@Param("startDate") Date startDate,@Param("endDate") Date endDate,@Param("startTime") String startTime,
    		@Param("endTime") String endTime,@Param("teacher") TeacherInformation teacher);
    
	
	@Query(getFrequencyForUPDATE)
    public String findFreeTeacherforupdate(@Param("startDate") Date startDate,@Param("endDate") Date endDate,@Param("startTime") String startTime,
    				@Param("endTime") String endTime,@Param("teacher") TeacherInformation teacher,@Param("id") Integer id);
    
	
	
	public CoursebyIDOutput  getByid(@Param("id") Integer id);
	
	final String selectData = "select course from CourseInformation course left join course.cc cc where course.id = :id";
	@Query(selectData)
    public CoursebyIDOutput findCourse(@Param("id") Integer id);
    
}



