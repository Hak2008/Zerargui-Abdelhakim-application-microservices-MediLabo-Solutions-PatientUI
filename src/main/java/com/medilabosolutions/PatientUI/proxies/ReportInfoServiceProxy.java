package com.medilabosolutions.PatientUI.proxies;

import com.medilabosolutions.PatientUI.beans.ReportBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Gateway", url = "localhost:9090")
public interface ReportInfoServiceProxy {

    @GetMapping("/report/{id}")
    ReportBean getReportById(@PathVariable("id") int id);

}
