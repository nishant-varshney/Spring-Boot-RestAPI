package com.nv.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nv.Model.CCInformation;
import com.nv.Model.CCSubjectInformation;
import com.nv.Model.SubjectInformation;
import com.nv.VO.SubjectOutput;


@Repository
public interface CCSubjectRepository extends JpaRepository<CCSubjectInformation, Integer> {
	
	/*final String deleteby ="select c.id from CCSubjectInformation c where c.subject = :subject and c.cc = :cc";
	
	@Query(getCCSubCount)
	public Integer getCCSubCount(@Param("subject") SubjectInformation subject,@Param("cc") CCInformation cc);*/
	
	public Integer deleteBycc(@Param("cc") CCInformation cc);
	
	
	public List<SubjectOutput> getBycc(@Param("cc") CCInformation cc);
}
