package com.nv.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nv.Model.CCInformation;
import com.nv.Model.CCTeacherInformation;
import com.nv.Model.SearchCC;
import com.nv.Repository.BeanInterfaces.CCForAffilliation;



@Repository
public interface CCInfoRepository extends JpaRepository<CCInformation, Integer>,JpaSpecificationExecutor<CCInformation> {
	
	final String CCSEARCHQUERY="select NEW com.nv.Model.SearchCC(cc.id,cc.name,cc.email,user.phoneNo,cc.address1,cc.address2,cc.pincode,cc.city,cc.state) from  CCInformation cc  left outer join UserInformation user on user.id=cc.id"
			+ " where cc.name like %:#{#searchCC.name}% OR cc.email like %:#{#searchCC.email}% OR cc.pincode like %:#{#searchCC.pincode}% OR cc.city like %:#{#searchCC.city}% OR"
			+ " cc.state like %:#{#searchCC.state}% OR user.phoneNo like %:#{#searchCC.phoneNo}%";

	
	
	  @Query(CCSEARCHQUERY)
	    public List<CCForAffilliation> findCCforAffilliation(@Param("searchCC") SearchCC searchCC);
	    
}
