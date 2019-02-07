package com.nv.Service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nv.Model.CCTeacherInformation;


public interface CCTeacherInfoService {
	
	List<CCTeacherInformation> listccTeacher();
	CCTeacherInformation findOne(Integer id);
	String addCCTeacher(CCTeacherInformation ccTeacherInfo);
	String deleteCCTeacher(Integer id);
	
	String getPendingReq(String role,Integer id) throws JsonProcessingException;
	String updateReq(CCTeacherInformation ccTeacherInfo);

	String getTeacherListByCC(Integer ccID);

}
