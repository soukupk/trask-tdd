package cz.trask.tdd;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

	@RequestMapping(path="/users", method = RequestMethod.POST)
	public CreateUserResponse sendEmail(@RequestBody CreateUserRequest createUserRequest) {
		return new CreateUserResponse(CreateUserResponseStatus.OK);
	}
	
}
