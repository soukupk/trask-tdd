package cz.trask.tdd;

import java.util.List;

import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Long> {

	void save(User user);
	
	List<User> findAll();
	
	User findByEmail(String email);
	
}
