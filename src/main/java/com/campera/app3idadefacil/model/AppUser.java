package com.campera.app3idadefacil.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@NoArgsConstructor
@Table(name = "app_user")
public class AppUser implements UserDetails {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true) @Getter @Setter
    private Long id;
    @Column(name = "first_name", nullable = false) @Getter @Setter
    private String firstName;
    @Column(name = "last_name", nullable = false) @Getter @Setter
    private String lastName;
    @Column(name = "email", nullable = false) @Getter @Setter
    private String email;
    @Column(name = "hashed_password", nullable = false) @Setter
    private String hashedPassword;
    @ElementCollection(fetch = FetchType.EAGER) @CollectionTable(name = "user_authority_mapping" )
    @Enumerated(EnumType.STRING)  @Getter
    private Set<Authority> authorityList = EnumSet.noneOf(Authority.class);

    public AppUser(String firstName, String lastName, String email, String hashedPassword){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.hashedPassword = hashedPassword;
    }

    public boolean addAuthority(Authority authority){
        return this.authorityList.add(authority);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        AppUser other = (AppUser) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorityList;
    }
    @Override
    public String getPassword() {
        return this.hashedPassword;
    }
    @Override
    public String getUsername() {
        return this.email;
    }
    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true;	}
    @Override
    public boolean isEnabled() { return true; }

}
