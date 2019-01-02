package ru.kolesnikov.votingsystem.model;

import org.hibernate.annotations.BatchSize;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

@SuppressWarnings("JpaQlInspection")
@Entity
@Table(name = "users")
public class User extends AbstractBaseEntity {

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    @Size(min = 3, max = 50)
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 5, max = 100)
    private String password;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    private Set<Role> roles;

    public User() {
    }

    public User(User u) {
        this(u.getId(), u.getEmail(), u.getPassword(), u.getRoles());
    }

    public User(Long id, String email, String password, Role role, Role... roles) {
        this(id, email, password, EnumSet.of(role, roles));
    }

    public User(Long id, String email, String password, Collection<Role> roles) {
        super(id);
        this.email = email;
        this.password = password;
        setRoles(roles);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String name) {
        this.email = name;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? Collections.emptySet() : EnumSet.copyOf(roles);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email +
                ", roles=" + roles +
                '}';
    }
}
