package com.nv.Service;

import java.util.List;

import com.nv.Model.CCInformation;
import com.nv.Model.SearchCC;
import com.nv.Model.SubjectInformation;
import com.nv.VO.ClassOutput;
import com.nv.VO.SearchCCInput;
import com.nv.VO.SubjectOutput;



public interface CCService {
	
	List<CCInformation> listCC();
	String findOne(Integer id);
	String addCC(CCInformation CCInfo);
	String deleteCC(Integer id);
	
	String findCCforaffilliation(SearchCCInput seachCCInput);
	
	List<SubjectOutput> findSubject(Integer ccID);
	List<ClassOutput> findClass(Integer ccID);


}
