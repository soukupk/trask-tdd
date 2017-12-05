package cz.trask.tdd;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql("classpath:deleteAll.sql")
public class UserRepositoryIT {

	@Autowired
	UserRepository userRepository;
	
	@Test
	@Transactional
	public void testCreateUser_shouldReturnsStatusOK_whenUsersEmailIsValid() {
		User user = new User();
		user.setEmail("ksoukup@trask.cz");
		user.setFirstname("abc");
		user.setSurname("def");

		UserEntry userEntryOne = new UserEntry();
		userEntryOne.setName("entry 01");
		userEntryOne.setUser(user);

		UserEntry userEntryTwo = new UserEntry();
		userEntryTwo.setName("entry 02");
		userEntryTwo.setUser(user);
		user.setUserEntries(Arrays.asList(userEntryOne, userEntryTwo));

		userRepository.save(user);

		List<User> allUsers = userRepository.findAll();

		System.out.println("users " + allUsers.toString());


		List<UserExport> users = userRepository.findAllForExport();

		System.out.println("users " + users.toString());



	}
	
}