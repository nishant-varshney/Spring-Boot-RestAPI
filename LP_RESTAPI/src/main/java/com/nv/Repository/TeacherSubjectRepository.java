package com.nv.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nv.Model.CCInformation;
import com.nv.Model.TeacherInformation;
import com.nv.Model.TeacherSubjectInformation;
import com.nv.VO.SubjectOutput;



@Repository
public interface TeacherSubjectRepository extends JpaRepository<TeacherSubjectInformation,Integer>{
	
	public Integer deleteByteacher(@Param("teacher") TeacherInformation teacher);
	
	public List<SubjectOutput> getByteacher(@Param("teacher") TeacherInformation teacher);

}
