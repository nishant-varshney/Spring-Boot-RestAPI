package com.nv.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nv.Model.ClassInformation;


@Repository
public interface ClassRepository extends JpaRepository<ClassInformation, Integer>{

}
