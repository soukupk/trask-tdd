package cz.trask.tdd;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class UserSettings {

	@Id
    @GeneratedValue
    private Long id;
	
	@OneToOne
	private User user;
	
	private String favoriteWebsite;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFavoriteWebsite() {
		return favoriteWebsite;
	}

	public void setFavoriteWebsite(String favoriteWebsite) {
		this.favoriteWebsite = favoriteWebsite;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((favoriteWebsite == null) ? 0 : favoriteWebsite.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		UserSettings other = (UserSettings) obj;
		if (favoriteWebsite == null) {
			if (other.favoriteWebsite != null)
				return false;
		} else if (!favoriteWebsite.equals(other.favoriteWebsite))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserSettings [id=" + id + ", user=" + user + ", favoriteWebsite=" + favoriteWebsite + "]";
	}

	
	
}
