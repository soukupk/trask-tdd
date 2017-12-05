package cz.trask.tdd;

import java.util.Arrays;
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
	public void createUser(CreateUserRequest createUserRequest) throws UserEmailExistsException {
		if(userRepository.findByEmail(createUserRequest.getEmail()) != null) {
			throw new UserEmailExistsException();
		}
		
		User user = mapUserFromCreateUserRequest(createUserRequest);		
		UserSettings userSettings = createDefaultUserSettings(user);
		
		userRepository.save(user);
		userSettingsRepository.save(userSettings);
	}

	private UserSettings createDefaultUserSettings(User user) {
		UserSettings userSettings = new UserSettings();
		userSettings.setUser(user);
		userSettings.setFavoriteWebsite("https://google.com");
		return userSettings;
	}

	private User mapUserFromCreateUserRequest(CreateUserRequest createUserRequest) {
		User user = new User();
		user.setEmail(createUserRequest.getEmail());
		user.setFirstname(createUserRequest.getFirstname());
		user.setSurname(createUserRequest.getSurname());
		return user;
	}

	public List<User> getAll() {
		return Arrays.asList();
	}
	
}
