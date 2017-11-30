package cz.trask.tdd;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private UserRepository userRepository;
	
	@Autowired	
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	@Transactional
	public void createUser(CreateUserRequest createUserRequest) {
		User user = new User();
		user.setEmail(createUserRequest.getEmail());
		user.setFirstname(createUserRequest.getFirstname());
		user.setSurname(createUserRequest.getSurname());
		
		userRepository.save(user);
	}

	public List<User> getAll() {
		return userRepository.findAll();
	}
	
}
