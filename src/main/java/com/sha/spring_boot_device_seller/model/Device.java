package com.sha.spring_boot_device_seller.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data//getter setter
@Entity//tells JPA this is an entity
@Table(name="devices")//will have db table relation
public class Device {

    @Id//pk definition
    @GeneratedValue(strategy = GenerationType.IDENTITY)//creates auto incremental column
    private Long id;

    @Column(name = "name",nullable = false,length = 100)
    private String name;

    @Column(name = "description",nullable = false,length = 1000)
    private String description;

    @Column(name = "price",nullable = false)
    private BigDecimal price;

    @Column(name = "create_time",nullable = false)
    private LocalDateTime createTime;

    //device type
    @Enumerated(EnumType.STRING)//if we dont mention the type of enum it will take defaul as endocoding
    @Column(name = "device_type",nullable = false)
    private DeviceType deviceType;

    //@OneToMany(fetch = FetchType.LAZY,mappedBy = "device")
    //private Set<Purchase> purchaseList;

}
