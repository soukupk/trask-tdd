package cz.trask.tdd;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(path="/users", method = RequestMethod.POST)
	public CreateUserResponse createUser(@RequestBody @Valid CreateUserRequest createUserRequest, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return new CreateUserResponse(CreateUserResponseStatus.INVALID_INPUT);
		}
		
		userService.createUser(createUserRequest);
		
		return new CreateUserResponse(CreateUserResponseStatus.OK);
	}
	
	@RequestMapping(path="/users")
	public List<User> getUsers() {
		return userService.getAll();
	}
	
}
