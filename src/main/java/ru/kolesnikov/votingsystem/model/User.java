package ru.kolesnikov.votingsystem.model;

import org.hibernate.annotations.BatchSize;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
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

    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(min = 3, max = 25)
    private String name;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    private Set<Role> roles;

    public User() {
    }

    public User(User u) {
        this(u.getId(), u.getName(), u.getRoles());
    }

    public User(Long id, String name, Role role, Role... roles) {
        this(id, name, EnumSet.of(role, roles));
    }

    public User(Long id, String name, Collection<Role> roles) {
        super(id);
        this.name = name;
        setRoles(roles);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? Collections.emptySet() : EnumSet.copyOf(roles);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", role=" + roles +
                '}';
    }
}
