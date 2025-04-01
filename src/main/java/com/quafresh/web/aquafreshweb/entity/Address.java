package com.quafresh.web.aquafreshweb.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "address")
    private String address;

    @Column(name = "commune")
    private String commune;

    @Column(name = "district")
    private String district;

    @Column(name = "province")
    private String province;

    @Column(name = "status")
    private Boolean status;
}
