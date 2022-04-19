package ru.kata.spring.boot_security.demo.model;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table (name = "roles")
@Getter
@RequiredArgsConstructor
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String rolename;

    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> user;

    @Override
    public String getAuthority() {
        return null;
    }

    public Role(String rolename) {
        this.rolename = rolename;
    }
}
