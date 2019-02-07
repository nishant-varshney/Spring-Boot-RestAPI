package com.nv.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nv.Model.CCInformation;
import com.nv.Model.TeacherClassInformation;
import com.nv.Model.TeacherInformation;
import com.nv.VO.ClassOutput;


@Repository
public interface TeacherClassRepository extends JpaRepository<TeacherClassInformation, Integer>{
	public Integer deleteByteacher(@Param("teacher") TeacherInformation teacher);
	
	public List<ClassOutput> getByteacher(@Param("teacher") TeacherInformation teacher);
}
