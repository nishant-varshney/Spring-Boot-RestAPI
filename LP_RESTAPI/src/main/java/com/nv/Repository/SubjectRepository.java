package com.nv.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nv.Model.SubjectInformation;

public interface SubjectRepository extends JpaRepository<SubjectInformation, Integer> {

}
