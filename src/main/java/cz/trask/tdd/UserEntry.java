package cz.trask.tdd;

import javax.persistence.*;

@Entity
public class UserEntry {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserEntry{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
