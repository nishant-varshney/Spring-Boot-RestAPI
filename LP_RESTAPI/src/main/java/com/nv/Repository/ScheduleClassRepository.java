package com.nv.Repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import th.co.geniustree.springdata.jpa.repository.JpaSpecificationExecutorWithProjection;

import com.nv.Model.CourseInformation;
import com.nv.Model.ScheduleClassInformation;
import com.nv.Model.TeacherInformation;


@Repository
public interface ScheduleClassRepository extends JpaRepository<ScheduleClassInformation, Integer>,JpaSpecificationExecutorWithProjection<ScheduleClassInformation> {

	
	final String  getFreeSC= "select count(sc) from ScheduleClassInformation sc where sc_date = :#{#scInfo.date} and start_time = :#{#scInfo.startTime} "
										+ "and end_time = :#{#scInfo.endTime} "
									  + "and course in (select id from CourseInformation where  "
									  + "teacher  in (select teacher from CourseInformation where id  = :#{#scInfo.course.id} ))";

		@Query(getFreeSC)
		public int getFreeSC(@Param("scInfo") ScheduleClassInformation scInfo );

		public List<ScheduleClassInformation> findByCourse(@Param("course") CourseInformation course);
	
		final String  removePastSC= "delete from ScheduleClassInformation sc where date <= :date and course = :course ";
		
		final String  removeFutureSC= "delete from ScheduleClassInformation sc where date >= :date and course = :course ";
		
		
		@Transactional
		@Modifying
		@Query(removePastSC)
		public int removePastSC(@Param("date") Date date, @Param("course") CourseInformation course);
		
		@Transactional
		@Modifying
		@Query(removeFutureSC)
		public int removeFutureSC(@Param("date") Date date, @Param("course") CourseInformation course);
	
				 
	
}
