package com.medilabosolutions.PatientUI.beans;

import lombok.Data;

import java.util.Date;

@Data
public class PatientBean {

    private Integer id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String gender;
    private String address;
    private String phoneNumber;
}
