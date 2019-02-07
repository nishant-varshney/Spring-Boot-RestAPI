package com.nv.Specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.nv.Model.CourseInformation;
import com.nv.Specification.Criteria.SearchCriteria;
import com.nv.VO.SearchCourseOutput;


public class CourseSpecification implements Specification<CourseInformation>{

	private SearchCriteria criteria;
	
	@Override
	public Predicate toPredicate(Root<CourseInformation> root,
			CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		// TODO Auto-generated method stub
		Expression<String> expression;
		Predicate predicate = null;
	/*	
		if(criteria.getKey().equalsIgnoreCase("subject")){
			 expression = root.join(criteria.getKey()).get("subjectId");
		}else if((criteria.getKey().equalsIgnoreCase("classID"))){
			// expression = root.join(criteria.getKey()).get("classID");
			expression=root.get(criteria.getKey());
		}else if((criteria.getKey().equalsIgnoreCase("teacher"))){
			 expression = root.join("teacher").get("teacherID");
		}else if((criteria.getKey().equalsIgnoreCase("cc"))){
			 expression = root.join("cc").get("id");
		}else*/ if((criteria.getKey().equalsIgnoreCase("student"))){
			 expression = root.join("student").get("student");
			 predicate =  expression.in(criteria.getValue());
		}else if((criteria.getKey().equalsIgnoreCase("exploreteacher"))){
			 expression = root.join("teacher").get("pincode");
			 predicate =  expression.in(criteria.getValue());
		}else if((criteria.getKey().equalsIgnoreCase("explorecc"))){
			 expression = root.join("cc").get("pincode");
			 predicate =  expression.in(criteria.getValue());
		}else if((criteria.getKey().equalsIgnoreCase("frequency"))){
			predicate = criteriaBuilder.like(
	                root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
			
		}
		else{
			expression = root.get(criteria.getKey());
			 predicate =  expression.in(criteria.getValue());
		}
		
		
		query.distinct(true);
		
		  
		
		return predicate;
	}
	
	


	public SearchCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(SearchCriteria criteria) {
		this.criteria = criteria;
	}

	public CourseSpecification(SearchCriteria criteria) {
		super();
		this.criteria = criteria;
	}



	public CourseSpecification() {
		super();
	}

	
	
	
	

}
