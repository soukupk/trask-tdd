package cz.trask.tdd;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@Spy
	UserRepository userRepository = new FakeUserRepository();
	
	@Spy
	UserSettingsRepository userSettingsRepository = new FakeUserSettingsRepository();
	
	@InjectMocks
	UserService userService;
		
	@Captor
	private ArgumentCaptor<UserSettings> saveUserSettingsArgumentCaptor;

	@Test
	public void testCreateUser_shouldSaveUserSettings_withGoogleFavoriteWebsite() throws UserEmailExistsException {
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
		
		Mockito.verify(userSettingsRepository).save(saveUserSettingsArgumentCaptor.capture());
		
		UserSettings actualUserSettings = saveUserSettingsArgumentCaptor.getAllValues().get(0);
		assertUserSettings(expectedUserSettings, actualUserSettings);
	}

	private void assertUserSettings(UserSettings expectedUserSettings, UserSettings actualUserSettings) {
		User expectedUser = expectedUserSettings.getUser();
		User actualUser = actualUserSettings.getUser();
		assertUser(expectedUser, actualUser);
		Assert.assertEquals(expectedUserSettings.getFavoriteWebsite(), actualUserSettings.getFavoriteWebsite());
	}

	private void assertUser(User expectedUser, User actualUser) {
		Assert.assertEquals(expectedUser.getEmail(), actualUser.getEmail());
		Assert.assertEquals(expectedUser.getFirstname(), actualUser.getFirstname());
		Assert.assertEquals(expectedUser.getSurname(), actualUser.getSurname());
	}
	
	@Test(expected = UserEmailExistsException.class)
	public void testCreateUser_shouldThrowUserEmailExistsException_whenUserAlreadyExists() throws UserEmailExistsException {
		CreateUserRequest createFirstUserRequest = new CreateUserRequest();
		createFirstUserRequest.setEmail("karel.soukup@trask.cz");
		createFirstUserRequest.setFirstname("Karel");
		createFirstUserRequest.setSurname("Soukup");
		
		CreateUserRequest createSecondUserRequestWithSameEmail = new CreateUserRequest();
		createSecondUserRequestWithSameEmail.setEmail("karel.soukup@trask.cz");
		createSecondUserRequestWithSameEmail.setFirstname("Karel");
		createSecondUserRequestWithSameEmail.setSurname("Ota");
		
		userService.createUser(createFirstUserRequest);		
		userService.createUser(createSecondUserRequestWithSameEmail);
	}
	
}
