package com.nv.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nv.Model.UserInformation;


@Repository
public interface UserInfoRepository extends JpaRepository<UserInformation, Integer> {
	
	UserInformation findOneByPhoneNo(String phoneNo);
	
	
	    @Query("SELECT u FROM UserInformation u WHERE u.phoneNo = :phoneNO and u.role = :role")
	    public UserInformation findByRoleAndPhone(@Param("phoneNO") String phoneNO,@Param("role") String role);
	    
	    
	    @Query("SELECT u FROM UserInformation u WHERE u.phoneNo = :phoneNO and u.role = :role and u.userActive= 'True'")
	    public UserInformation findActiveUser(@Param("phoneNO") String phoneNO,@Param("role") String role);

	    
	    @Query("SELECT count(u) FROM UserInformation u WHERE u.id = :id and u.role = :role")
	    public int getCountByIDandRole(@Param("id") Integer id,@Param("role") String role);
}
