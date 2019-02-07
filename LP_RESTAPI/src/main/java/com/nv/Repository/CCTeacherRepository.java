package com.nv.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nv.Model.CCInformation;
import com.nv.Model.CCTeacherInformation;
import com.nv.Model.TeacherInformation;
import com.nv.Repository.BeanInterfaces.TeacherbyCC;


@Repository
public interface CCTeacherRepository extends JpaRepository<CCTeacherInformation, Integer> {
	
	  
    @Query("SELECT count(ct) FROM CCTeacherInformation ct WHERE ct.teacher = :teacher_id and ct.cc = :cc_id")
    public int getCountByTeacherAndCCId(@Param("teacher_id") TeacherInformation teacher_id,@Param("cc_id") CCInformation cc_id);
    
    
    
    @Query("SELECT ct FROM CCTeacherInformation ct WHERE ct.requestByRole = 'CC' and ct.teacher = :teacher_id and ct.requestStatus='false'")
    public List<CCTeacherInformation> getTeacherPendingCase(@Param("teacher_id") TeacherInformation teacher_id);

    
    
    @Query("SELECT ct FROM CCTeacherInformation ct WHERE ct.requestByRole = 'Teacher' and ct.cc = :cc_id and ct.requestStatus='false'")
    public List<CCTeacherInformation> getCCPendingCase(@Param("cc_id") CCInformation cc_id);
    
    
    @Query("SELECT NEW CCTeacherInformation(ct.teacher,ct.requestByRole,ct.requestedDate,ct.confirmationDate) FROM CCTeacherInformation ct WHERE ct.cc = :cc_id and ct.requestStatus='Approve'")
    public List<TeacherbyCC> getTeacherListByCC(@Param("cc_id") CCInformation cc_id);
    
    
    
}



