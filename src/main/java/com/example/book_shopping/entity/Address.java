package com.example.book_shopping.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author lengo
 * created on 3/18/2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "addresses")
public class Address extends BaseEntity {
    @Column(nullable = false)
    private String addressDetail;
    @Column()
    private String description;
    @Column(nullable = false, columnDefinition = "BIT(1) default false")
    private boolean isMain;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    private User user;
}
