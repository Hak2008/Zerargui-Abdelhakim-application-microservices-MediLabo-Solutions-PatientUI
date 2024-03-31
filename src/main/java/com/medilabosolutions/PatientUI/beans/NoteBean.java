package com.medilabosolutions.PatientUI.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NoteBean {

    private String id;
    private Integer patId;
    private String patient;
    private String note;

}
