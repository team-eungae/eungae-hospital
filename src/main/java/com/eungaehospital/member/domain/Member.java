package com.eungaehospital.member.domain;

import com.eungaehospital.appointment.domain.Appointment;
import com.eungaehospital.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import java.util.ArrayList;
import java.util.List;

@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Table(name = "member")
@Entity
public class Member extends BaseEntity {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long memberSeq;

    @Column(nullable = false, unique = true, updatable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 15)
    private String phoneNumber;

    @Column(nullable = false)
    private String birthDate;

    @Column(nullable = false)
    private String address;

    private String addressDetail;

    @Column(nullable = false)
    private String zipCode;

    private Integer xCoordinate;

    private Integer yCoordinate;

    private String provider;
    private String providerId;

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<Children> children = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<Appointment> appointments = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<FavoritesHospital> favoritesHospitals = new ArrayList<>();

    public void addChildren(Children children) {
        this.children.add(children);
    }
}
