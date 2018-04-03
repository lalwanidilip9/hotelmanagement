package online.hotelmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import online.hotelmanagement.entity.ApplicationUser;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long>{
	@Query("from ApplicationUser u where u.userId = :userId")
    ApplicationUser findByUserId(@Param("userId") Long userId);
	
	@Query("from ApplicationUser u where u.emailId = :emailId and u.userId <> :userId")
    ApplicationUser findIfEmailIdAlreadyExists(@Param("emailId") String emailId, @Param("userId") Long userId);
	
	@Query("from ApplicationUser u where u.mobileNumber = :mobileNumber and u.userId <> :userId")
    ApplicationUser findIfMobileAlreadyExists(@Param("mobileNumber") String mobileNumber, @Param("userId") Long userId);
	
	@Query("from ApplicationUser u where u.emailId = :userName and u.password = :password")
    ApplicationUser authenticateUser(@Param("userName") String userName, @Param("password") String password);
}
