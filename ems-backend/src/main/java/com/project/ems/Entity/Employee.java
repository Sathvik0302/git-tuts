package com.project.ems.Entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="EMS_Details")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="EmployeeID")
    private long id;

    @Column(name="EmployeeFirstName")
    private String firstName;

    @Column(name="EmployeeLastName")
    private String lastName;

    @Column(name="EmployeeEmail",nullable = false,unique = true)
    private String email;


}
