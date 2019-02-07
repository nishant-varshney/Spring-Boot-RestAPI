package com.nv.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nv.Model.StudentInformation;


@Repository
public interface StudentRepository extends JpaRepository<StudentInformation, Integer> {

}
