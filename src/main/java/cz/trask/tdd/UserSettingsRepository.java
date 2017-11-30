package cz.trask.tdd;

import org.springframework.data.repository.Repository;

public interface UserSettingsRepository extends Repository<UserSettings, Long> {

	void save(UserSettings userSettings);
	
}
