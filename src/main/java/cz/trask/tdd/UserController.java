package cz.trask.tdd;

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
	UserRepository userRepository;
	
	@RequestMapping(path="/users", method = RequestMethod.POST)
	public CreateUserResponse createUser(@RequestBody @Valid CreateUserRequest createUserRequest, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return new CreateUserResponse(CreateUserResponseStatus.INVALID_INPUT);
		}
		
		User user = new User();
		user.setEmail(createUserRequest.getEmail());
		user.setFirstname(createUserRequest.getFirstname());
		user.setSurname(createUserRequest.getSurname());
		
		userRepository.save(user);
		return new CreateUserResponse(CreateUserResponseStatus.OK);
	}
	
}
