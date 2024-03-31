package com.medilabosolutions.PatientUI.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReportBean {

    private Integer patId;
    private String expectedRisk;

}
