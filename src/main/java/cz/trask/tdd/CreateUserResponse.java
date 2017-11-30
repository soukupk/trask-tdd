package cz.trask.tdd;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateUserResponse {

	@JsonProperty("status")
	private CreateUserResponseStatus createUserResponseStatus;
	
	public CreateUserResponse() {
		super();
	}

	public CreateUserResponse(CreateUserResponseStatus createUserResponseStatus) {
		this.createUserResponseStatus = createUserResponseStatus;
	}

	public CreateUserResponseStatus getCreateUserResponseStatus() {
		return createUserResponseStatus;
	}

	public void setCreateUserResponseStatus(CreateUserResponseStatus createUserResponseStatus) {
		this.createUserResponseStatus = createUserResponseStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createUserResponseStatus == null) ? 0 : createUserResponseStatus.hashCode());
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
		CreateUserResponse other = (CreateUserResponse) obj;
		if (createUserResponseStatus != other.createUserResponseStatus)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CreateUserResponse [createUserResponseStatus=" + createUserResponseStatus + "]";
	}
	
}
