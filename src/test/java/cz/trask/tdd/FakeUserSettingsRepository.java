package cz.trask.tdd;

import cz.trask.tdd.helpers.FakeRepository;

public class FakeUserSettingsRepository extends FakeRepository<UserSettings> implements UserSettingsRepository {

	@Override
	protected Long getId(UserSettings entity) {
		return entity.getId();
	}

	@Override
	protected void setId(UserSettings entity, Long id) {
		entity.setId(id);		
	}

}
