package com.example.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by kimyongyeon on 2016-12-27.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private @Id
    @GeneratedValue
    Long id;
    private String firstName;
    private String lastName;
    private String description;
    public Employee(String firstName, String lastName, String description) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
    }
}
