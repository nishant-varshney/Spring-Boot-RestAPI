package com.nv.Service.Implementation;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nv.Model.CourseInformation;
import com.nv.Model.ScheduleClassInformation;
import com.nv.Repository.CourseRepository;
import com.nv.Repository.ScheduleClassRepository;
import com.nv.Service.ScheduleClassService;
import com.nv.Specification.CourseSpecification;
import com.nv.Specification.Criteria.ScheduleClassSpecification;
import com.nv.Specification.Criteria.SearchCriteria;
import com.nv.Utility.Utility;
import com.nv.VO.CourseOutput;
import com.nv.VO.SCOutput;


@Service
public class ScheduleClassInfoImplementation implements ScheduleClassService{
	
	@Autowired
	ScheduleClassRepository scRepo;
	
	@Autowired
	CourseRepository courseRepo;

	@Override
	public String listSC(Integer Id,String role,String startDate,String endDate) {
		String searchResult = "";
		try{
			ScheduleClassSpecification idSpec = null;
			ScheduleClassSpecification dateSpec = null;
			ObjectMapper ob= new ObjectMapper();
			
			if(!Utility.isNull(Id)){
				idSpec = new ScheduleClassSpecification(new SearchCriteria(role,":",Id));
				dateSpec= new ScheduleClassSpecification(new SearchCriteria(startDate,"DateComp",endDate));
			}
			
			Page<SCOutput>  listreturn = scRepo.findAll(Specification.where(idSpec).and(dateSpec), SCOutput.class,new PageRequest(0, 100));
			searchResult =  ob.writeValueAsString(listreturn.getContent());
		}catch(Exception e){
			e.printStackTrace();
		}
		return searchResult;
	}

	@Override
	public Boolean addSC(CourseInformation courseInfo) {
		// TODO Auto-generated method stub
		String[] freArray;
		List<ScheduleClassInformation> scList = new ArrayList<ScheduleClassInformation>();
		List<LocalDate> date = null;
		List<String> key = null;
		List<String> val = null;
		try{
			if(courseInfo.getFrequency().contains(",")){
				System.out.println("multiple frequency days");
				freArray = courseInfo.getFrequency().split(",");
				for (String frequency : freArray) {
					 date = Utility.getDates(courseInfo.getStartDate(),courseInfo.getEndDate(),frequency);
					 System.out.println("no of dates for "+frequency+":::"+date.size());
					 for(LocalDate d : date){
							scList.add(new ScheduleClassInformation(Utility.convertToUtilDate(d), courseInfo.getStartTime(), courseInfo.getEndTime(),courseInfo));
						}
				}
			}else{
				System.out.println("single frequency days");
				 date = Utility.getDates(courseInfo.getStartDate(),courseInfo.getEndDate(),courseInfo.getFrequency());
				 for(LocalDate d : date){
						scList.add(new ScheduleClassInformation(Utility.convertToUtilDate(d), courseInfo.getStartTime(), courseInfo.getEndTime(),courseInfo));
					}
			}
			System.out.println("will save SC"+scList.size());
			scRepo.saveAll(scList);
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public String updateSC(ScheduleClassInformation scInfo) {
		// TODO Auto-generated method stub
		System.out.println("called");
		List<String> key = new ArrayList<String>();
		List<String> val = new ArrayList<String>();
		try{
			if(scRepo.existsById(scInfo.getId())){
				ScheduleClassInformation scDb = scRepo.getOne(scInfo.getId());
				System.out.println("one111");
				scInfo.setCourse(scDb.getCourse());
				
				if(scRepo.getFreeSC(scInfo) == 0){
					System.out.println("going to save");
					scRepo.save(scInfo);
					key.add("Status");val.add("Success");
					key.add("Message");val.add("SC Information updated");
				}else{
					key.add("Status");val.add("Warning");
					key.add("Message");val.add("Teacher is not free on given schedule date/time");
				}
			}else{
					key.add("Status");val.add("Fail");
					key.add("Message");val.add("Schedule Class not found");
				}
			}catch (Exception e) {
				// TODO: handle exception
				key.add("Status");val.add("Error");
				key.add("Message");val.add("Error at server end");
				e.printStackTrace();
			}
		return Utility.getJsonResponce(key, val);
	}

	@Override
	public String addSc(ScheduleClassInformation scInfo) {
		// TODO Auto-generated method stub
		List<String> key = new ArrayList<String>();
		List<String> val = new ArrayList<String>();
		try{
			scRepo.save(scInfo);
			key.add("Status");val.add("Success");
			key.add("Message");val.add("SC Information Added");
		}catch(Exception e){
			key.add("Status");val.add("Error");
			key.add("Message");val.add("Error at server End");
			e.printStackTrace();
		}
		return Utility.getResponce(key, val);
	}

	@Override
	public Boolean updateSC(CourseInformation courseInfo) {
		// TODO Auto-generated method stub
		String[] freArray;
		List<ScheduleClassInformation> scList = new ArrayList<ScheduleClassInformation>();
		List<LocalDate> date = null;
		List<String> key = null;
		List<String> val = null;
		try{
			if(courseInfo.getStartDate().equals(Utility.getTodayDate()) || courseInfo.getStartDate().after(Utility.getTodayDate())){
					scRepo.removeFutureSC(courseInfo.getStartDate(), courseInfo);
			}else{
				 	scRepo.removePastSC(courseInfo.getStartDate(), courseInfo);
			}
			
			if(courseInfo.getFrequency().contains(",")){
				freArray = courseInfo.getFrequency().split(",");
				for (String frequency : freArray) {
					 date = Utility.getDates(courseInfo.getStartDate(),courseInfo.getEndDate(),frequency);
					 for(LocalDate d : date){
							scList.add(new ScheduleClassInformation(Utility.convertToUtilDate(d), courseInfo.getStartTime(), courseInfo.getEndTime(),courseInfo));
						}
				}
			}else{
				 date = Utility.getDates(courseInfo.getStartDate(),courseInfo.getEndDate(),courseInfo.getFrequency());
				 for(LocalDate d : date){
						scList.add(new ScheduleClassInformation(Utility.convertToUtilDate(d), courseInfo.getStartTime(), courseInfo.getEndTime(),courseInfo));
					}
			}
			scRepo.saveAll(scList);
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

}
