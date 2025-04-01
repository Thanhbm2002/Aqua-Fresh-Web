package com.quafresh.web.aquafreshweb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "User")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "fullname")
    private String fullname;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phone;
    @ManyToOne
    @JoinColumn(name = "id_address")
    private Address address;
    @Column(name = "role")
    private Boolean role;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Trả về quyền dựa trên giá trị của role
        return Collections.singletonList(
                new SimpleGrantedAuthority(role ? "ADMIN" : "USER")
        );
    }
}
