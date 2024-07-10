package com.krainet.test.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    private Integer timeSpent;
    private LocalDate date;
}