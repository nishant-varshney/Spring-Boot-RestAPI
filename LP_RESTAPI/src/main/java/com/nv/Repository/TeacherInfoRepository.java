package com.nv.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;





import com.nv.Model.SearchTeacher;
import com.nv.Model.TeacherInformation;
import com.nv.Repository.BeanInterfaces.TeacherForAffiliation;
import com.nv.VO.SearchTeacherInput;


@Repository
public interface TeacherInfoRepository 
				extends JpaRepository<TeacherInformation, Integer>,JpaSpecificationExecutor<TeacherInformation> {
/*	
	final String TEACHERSEARCHQUERY="select NEW com.nv.Model.SearchTeacher(t.teacherID,t.name,t.email,user.phoneNo,t.address1,t.address2,t.pincode,t.city,"
					+ "t.state,t.qualifications,t.experience,datediff(curdate(),t.creationDate) as lpExpeience,t.description,t.subject,t.classID"
					+ ") from  TeacherInformation t  left outer join UserInformation user on user.id=t.teacherID left outer join TeacherClassInformation tc on t.teacherID=tc.teacher left outer join TeacherSubjectInformation ts on t.teacherID=ts.teacher"
					+ " where t.name like %:#{#searchTeacher.name}% AND tc.classID in :#{#searchTeacher.classID} OR ts.subject in :#{#searchTeacher.subject} OR t.pincode like %:#{#searchTeacher.pincode}% OR"
					+ " t.city like %:#{#searchTeacher.city}% OR t.state like %:#{#searchTeacher.state}%  OR t.qualifications like %:#{#searchTeacher.qualifications}%  OR t.experience like %:#{#searchTeacher.experience}% "
					+ "OR t.email like %:#{#searchTeacher.email}% OR user.phoneNo like %:#{#searchTeacher.phoneNo}%";

	*/
	final String TEACHERSEARCHQUERY="select NEW com.nv.Model.SearchTeacher(t.teacherID,t.name,t.email,user.phoneNo,t.address1,t.address2,t.pincode,t.city,"
			+ "t.state,t.qualifications,t.experience,datediff(curdate(),t.creationDate) as lpExpeience,t.description,GROUP_CONCAT(ts.subject) as subject ,tc.classID as classID"
			+ ") from  TeacherInformation t  left outer join UserInformation user on user.id=t.teacherID left outer join TeacherClassInformation tc on t.teacherID=tc.teacher left outer join TeacherSubjectInformation ts on t.teacherID=ts.teacher"
			+ " where t.name in :#{#searchTeacherInput.name} OR tc.classID in :#{#searchTeacherInput.classID} OR ts.subject in :#{#searchTeacherInput.subject} OR t.pincode  in :#{#searchTeacherInput.pincode} OR"
			+ " t.city in :#{#searchTeacherInput.city} OR t.state in :#{#searchTeacherInput.state}  OR t.qualifications in :#{#searchTeacherInput.qualification}  OR t.experience in :#{#searchTeacherInput.experience} "
			+ "OR t.email in :#{#searchTeacherInput.email} OR user.phoneNo in :#{#searchTeacherInput.phoneNo} group by ts.subject ";

	
	 @Query(TEACHERSEARCHQUERY)
	    public List<TeacherForAffiliation> findTeacherforAffilliation(@Param("searchTeacherInput") SearchTeacherInput searchTeacherInput);
	    
	 
	 
	 
}
