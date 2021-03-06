package cz.trask.tdd;

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

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql("classpath:deleteAll.sql")
public class UserControllerIT {

	@LocalServerPort
    private int port;
	
	@Autowired
    private TestRestTemplate restTemplate;
	
	@Test
	public void testCreateUser_shouldReturnsStatusOK_whenUsersEmailIsValid() {
		String email = "ksoukup@trask.cz";
		String firstname = "Karel";
		String surname = "Soukup";
		
		ObjectNode createUserRequest = JsonNodeFactory.instance.objectNode();
		createUserRequest.put("email", email);
		createUserRequest.put("firstname", firstname);
		createUserRequest.put("surname", surname);
		
		ObjectNode createUserResponse = restTemplate.postForObject("http://localhost:" + port + "/users", createUserRequest, ObjectNode.class);
		
		ObjectNode expectedCreateUserResponse = JsonNodeFactory.instance.objectNode();
		expectedCreateUserResponse.put("status", "OK");
		
		Assert.assertEquals(expectedCreateUserResponse, createUserResponse);
	}
	
	@Test
	public void testCreateUser_shouldReturnsStatusInvalidInputResponse_whenUserEmailIsInvalid() {
		String email = "@trask.cz";
		String firstname = "Karel";
		String surname = "Soukup";
		
		ObjectNode createUserRequest = JsonNodeFactory.instance.objectNode();
		createUserRequest.put("email", email);
		createUserRequest.put("firstname", firstname);
		createUserRequest.put("surname", surname);
		
		ObjectNode createUserResponse = restTemplate.postForObject("http://localhost:" + port + "/users", createUserRequest, ObjectNode.class);
		
		ObjectNode expectedCreateUserResponse = JsonNodeFactory.instance.objectNode();
		expectedCreateUserResponse.put("status", "INVALID_INPUT");		
		Assert.assertEquals(expectedCreateUserResponse, createUserResponse);		
	}
	
	@Test
	public void testGetUsers_shouldReturnUser_whenUserCreated() {
		String email = "ksoukup@trask.cz";
		String firstname = "Karel";
		String surname = "Soukup";
		
		ObjectNode createUserRequest = JsonNodeFactory.instance.objectNode();
		createUserRequest.put("email", email);
		createUserRequest.put("firstname", firstname);
		createUserRequest.put("surname", surname);
		
		restTemplate.postForObject("http://localhost:" + port + "/users", createUserRequest, ObjectNode.class);

		ArrayNode getUsersResponse = restTemplate.getForObject("http://localhost:" + port + "/users", ArrayNode.class);

		ArrayNode expectedGetUsersResponse = JsonNodeFactory.instance.arrayNode();
		ObjectNode expectedUser = JsonNodeFactory.instance.objectNode();
		expectedUser.put("email", email);
		expectedUser.put("firstname", firstname);
		expectedUser.put("surname", surname);
		expectedGetUsersResponse.add(expectedUser);	
		Assert.assertEquals(expectedGetUsersResponse, getUsersResponse);		
	}
	
}
