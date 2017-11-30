package cz.trask.tdd;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

	@RequestMapping(path="/users", method = RequestMethod.POST)
	public CreateUserResponse sendEmail(@RequestBody @Valid CreateUserRequest createUserRequest, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return new CreateUserResponse(CreateUserResponseStatus.INVALID_INPUT);
		}
		return new CreateUserResponse(CreateUserResponseStatus.OK);
	}
	
}
