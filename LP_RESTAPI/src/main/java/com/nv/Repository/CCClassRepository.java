package com.nv.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nv.Model.CCClassInformation;
import com.nv.Model.CCInformation;
import com.nv.VO.ClassOutput;



@Repository
public interface CCClassRepository extends JpaRepository<CCClassInformation, Integer>{

	
	public Integer deleteBycc(@Param("cc") CCInformation cc);
	
	
	public List<ClassOutput> getBycc(@Param("cc") CCInformation cc);
}
