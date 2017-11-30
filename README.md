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
