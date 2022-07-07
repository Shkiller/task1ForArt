package com.example.task1.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "animal")
@Getter
@Setter
@Accessors(chain = true)
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    private LocalDate birthday;
    @Column
    private String gender;
    @Column
    private String name;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
