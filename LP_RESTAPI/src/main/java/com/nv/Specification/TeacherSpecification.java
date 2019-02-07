package com.nv.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.nv.Model.TeacherInformation;
import com.nv.Specification.Criteria.SearchCriteria;



public class TeacherSpecification implements Specification<TeacherInformation> {

	private SearchCriteria criteria;

	@Override
	public Predicate toPredicate(Root<TeacherInformation> root,
			CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		// TODO Auto-generated method stub
		
		Expression<String> expression =null;
		Predicate predicate =null;
		
		if(criteria.getKey().equalsIgnoreCase("subject")){
			 expression = root.join(criteria.getKey()).get("subject");
			 predicate =  expression.in(criteria.getValue());
		}else if((criteria.getKey().equalsIgnoreCase("classID"))){
			 expression = root.join(criteria.getKey()).get("classID");
			 predicate =  expression.in(criteria.getValue());
		}else if((criteria.getKey().equalsIgnoreCase("phoneNo"))){
			 expression = root.join("user").get("phoneNo");
			 predicate =  expression.in(criteria.getValue());
		}else if((criteria.getKey().equalsIgnoreCase("name"))){
			predicate = criteriaBuilder.like(
	                root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
		}
		else{
			expression = root.get(criteria.getKey());
		}
		
		
		query.distinct(true);
		/*
		return criteriaBuilder.like(
                root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");*/
		//System.out.println("predict is::"+predicate.toString());
		return predicate;
	}

	public TeacherSpecification(SearchCriteria criteria) {
		super();
		this.criteria = criteria;
	}

	
	
}
