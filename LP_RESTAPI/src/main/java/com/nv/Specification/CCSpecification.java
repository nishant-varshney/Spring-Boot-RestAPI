package com.nv.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.nv.Model.CCInformation;
import com.nv.Specification.Criteria.SearchCriteria;

public class CCSpecification implements Specification<CCInformation> {

	
	
	private SearchCriteria criteria;

	@Override
	public Predicate toPredicate(Root<CCInformation> root,
			CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		// TODO Auto-generated method stub

		Expression<String> expression;
		Predicate predicate =null;
		if((criteria.getKey().equalsIgnoreCase("phoneNo"))){
			 expression = root.join("user").get("phoneNo");
			 predicate =  expression.in(criteria.getValue());
		}else if((criteria.getKey().equalsIgnoreCase("name"))){
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

	public CCSpecification(SearchCriteria criteria) {
		super();
		this.criteria = criteria;
	}
	
	

}
