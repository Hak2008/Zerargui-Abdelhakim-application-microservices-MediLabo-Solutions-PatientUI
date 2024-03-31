package com.medilabosolutions.PatientUI.proxies;

import com.medilabosolutions.PatientUI.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "Gateway", url = "localhost:9090")
public interface PatientInfoServiceProxy {

    @GetMapping("/patient/list")
    List<PatientBean> getAllPatients();

    @GetMapping("/patient/details/{id}")
    PatientBean getPatientById(@PathVariable("id") int id);

    @PostMapping("/patient/validate")
    PatientBean addPatient(@RequestBody PatientBean patient);

    @PutMapping("/patient/update/{id}")
    PatientBean updatePatient(@PathVariable("id") int id, @RequestBody PatientBean patient);

    @DeleteMapping("/patient/delete/{id}")
    void deletePatient(@PathVariable("id") int id);
    
}