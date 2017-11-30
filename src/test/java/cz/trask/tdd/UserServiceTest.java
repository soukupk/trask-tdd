package cz.trask.tdd;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@Mock
	UserSettingsRepository userSettingsRepository;
	
	@InjectMocks
	UserService userService;
	
	@Test
	public void testCreateUser_shouldSaveUserSettings_withDefaultFavoriteWebsite() {
		CreateUserRequest createUserRequest = new CreateUserRequest();
		createUserRequest.setEmail("karel.soukup@trask.cz");
		createUserRequest.setFirstname("Karel");
		createUserRequest.setSurname("Soukup");
		
		userService.createUser(createUserRequest);
		
		User expectedUser = new User();
		expectedUser.setEmail("karel.soukup@trask.cz");
		expectedUser.setFirstname("Karel");
		expectedUser.setSurname("Soukup");
		
		UserSettings expectedUserSettings = new UserSettings();
		expectedUserSettings.setUser(expectedUser);
		expectedUserSettings.setFavoriteWebsite("https://google.com");
		
		Mockito.verify(userSettingsRepository).save(expectedUserSettings);
	}
	
}
