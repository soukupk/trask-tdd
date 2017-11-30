package cz.trask.tdd;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private UserRepository userRepository;
	
	private UserSettingsRepository userSettingsRepository;
	
	@Autowired	
	public UserService(UserRepository userRepository, UserSettingsRepository userSettingsRepository) {
		super();
		this.userRepository = userRepository;
		this.userSettingsRepository = userSettingsRepository;
	}
	
	@Transactional
	public void createUser(CreateUserRequest createUserRequest) {
		User user = new User();
		user.setEmail(createUserRequest.getEmail());
		user.setFirstname(createUserRequest.getFirstname());
		user.setSurname(createUserRequest.getSurname());
		
		UserSettings userSettings = new UserSettings();
		userSettings.setUser(user);
		userSettings.setFavoriteWebsite("https://google.com");
		
		userRepository.save(user);
		userSettingsRepository.save(userSettings);
	}

	public List<User> getAll() {
		return userRepository.findAll();
	}
	
}
