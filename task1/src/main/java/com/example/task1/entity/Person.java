package com.example.task1.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "person")
@Getter
@Setter
@Accessors(chain = true)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    private String password;
    @Column
    private String nickname;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<Animal> animals = new ArrayList<>();

    public Role getRole()
    {
        return Role.USER;
    }

}
