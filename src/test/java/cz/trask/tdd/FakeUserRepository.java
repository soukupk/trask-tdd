package cz.trask.tdd;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.IncorrectResultSizeDataAccessException;

import cz.trask.tdd.helpers.FakeRepository;

public class FakeUserRepository extends FakeRepository<User> implements UserRepository {

	@Override
	protected Long getId(User entity) {
		return entity.getId();
	}

	@Override
	protected void setId(User entity, Long id) {
		entity.setId(id);		
	}

	@Override
	public User findByEmail(String email) {
		List<User> result = entities.values().stream()
		.filter(user -> user.getEmail().equals(email))
		.collect(Collectors.toList());
		
		if(result.size() == 0) {
			return null;
		} else if(result.size() > 1) {
			throw new IncorrectResultSizeDataAccessException(1);
		}
		
		return result.get(0);
	}

	@Override
	public List<UserExport> findAllForExport() {
		return null;
	}

}
