package com.nv.Specification.Criteria;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.nv.Model.ScheduleClassInformation;
import com.nv.Utility.Utility;

public class ScheduleClassSpecification implements Specification<ScheduleClassInformation> {

	private SearchCriteria criteria;
	
	@Override
	public Predicate toPredicate(Root<ScheduleClassInformation> root,
			CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		Expression<String> expression;
		Predicate predicate =null;
		
		if((criteria.getKey().equalsIgnoreCase("student"))){
			 expression = root.join("course").join("student").get("student");
			 predicate = criteriaBuilder.equal(expression, criteria.getValue());
		}else if(criteria.getOperation().equalsIgnoreCase("DateComp")){
			predicate = criteriaBuilder.between(root.<Date>get("date"),Utility.getSqlDate1(criteria.getKey()),Utility.getSqlDate1((String)criteria.getValue()));
		}else if((criteria.getKey().equalsIgnoreCase("cc"))){
			 expression = root.join("course").join("cc").get("id");
			 predicate = criteriaBuilder.equal(expression, criteria.getValue());
		}else if((criteria.getKey().equalsIgnoreCase("teacher"))){
			 expression = root.join("course").join("teacher").get("teacherID");
			 predicate = criteriaBuilder.equal(expression, criteria.getValue());
		}else{
			predicate=null;
		}
		
		return predicate;
	}

	public SearchCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(SearchCriteria criteria) {
		this.criteria = criteria;
	}

	public ScheduleClassSpecification(SearchCriteria criteria) {
		super();
		this.criteria = criteria;
	}
	
	

}
