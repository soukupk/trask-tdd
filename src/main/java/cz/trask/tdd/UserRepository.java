package cz.trask.tdd;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Long> {

	void save(User user);
	
	List<User> findAll();

	User findByEmail(String email);

	//(SELECT ue.name FROM UserEntry ue WHERE ue.user = u)
	//@Query("SELECT u.firstname AS firstName, u.surname AS surname, u.userEntries as userEntryExports FROM User u")
	@Query("SELECT u.firstname AS firstName, u.surname AS surname, u.userEntries as userEntryExports FROM User u")
	List<UserExport> findAllForExport();
	
}
