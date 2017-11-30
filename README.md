# trask-tdd

## Functional test skeleton

```
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserControllerIT {

    @LocalServerPort
    private int port;
	
    @Autowired
    private TestRestTemplate restTemplate;

	@Test
	public void testCreateUser_shouldReturnSuccessResponse_whenUserEmailIsValid() {
		// příprava prostředí
		String email = "ksoukup@trask.cz";
		String firstname = "Karel";
		String surname = "Soukup";
		
		ObjectNode createUserRequest = JsonNodeFactory.instance.objectNode();
		createUserRequest.put("email", email);
		createUserRequest.put("firstname", firstname);
		createUserRequest.put("surname", surname);
		
		// provolání testované části		
		ObjectNode createUserResponse = restTemplate.postForObject("http://localhost:" + port + "/users", createUserRequest, ObjectNode.class);
		
		// ověření
		ObjectNode expectedCreateUserResponse = JsonNodeFactory.instance.objectNode();
		expectedCreateUserResponse.put("status", "OK");		
		Assert.assertEquals(expectedCreateUserResponse, createUserResponse);		
	}
}
```
## Rest template skeleton

```
@RestController
public class UserController {

	@RequestMapping(path="/users", method = RequestMethod.POST)
	public CreateUserResponse sendEmail(@RequestBody @Valid CreateUserRequest createUserRequest, BindingResult bindingResult) {

	}
	
}
```

##First CreateUserRequest

```
public class CreateUserRequest {

	private String email;

	private String firstname;

	private String surname;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		return "CreateUserRequest [email=" + email + ", firstname=" + firstname + ", surname=" + surname + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreateUserRequest other = (CreateUserRequest) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}
	
}
```
