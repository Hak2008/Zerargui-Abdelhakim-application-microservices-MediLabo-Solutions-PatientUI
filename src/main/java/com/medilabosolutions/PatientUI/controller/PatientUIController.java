package com.medilabosolutions.PatientUI.controller;

import com.medilabosolutions.PatientUI.beans.PatientBean;
import com.medilabosolutions.PatientUI.proxies.PatientInfoServiceProxy;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class PatientUIController {

    private final PatientInfoServiceProxy patientInfoServiceProxy;

    public PatientUIController(PatientInfoServiceProxy patientInfoServiceProxy) {
        this.patientInfoServiceProxy = patientInfoServiceProxy;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("/patient/list")
    public String getAllPatients(Model model) {
        List<PatientBean> patient =  patientInfoServiceProxy.getAllPatients();
        model.addAttribute("patient", patient);
        return "patient/list";
    }

    @GetMapping("/patient/details/{id}")
    public String getPatientById(@PathVariable Integer id, Model model) {
        PatientBean patientDetails = patientInfoServiceProxy.getPatientById(id);
        model.addAttribute("patient", patientDetails);
        return "patient/details";
    }

    @GetMapping("/patient/add")
    public String addPatientForm(PatientBean patient) {
        return "patient/add";
    }

    @PostMapping("/patient/validate")
    public String addPatient(@ModelAttribute @Valid PatientBean patient, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "patient/add";
        }
        patientInfoServiceProxy.addPatient(patient);
        redirectAttributes.addFlashAttribute("successMessage", "Patient added successfully");
        return "redirect:/patient/list";
    }

    @GetMapping("/patient/update/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        PatientBean patient = patientInfoServiceProxy.getPatientById(id);
        model.addAttribute("patient", patient);
        return "patient/update";
    }

    @PostMapping("/patient/update/{id}")
    public String updatePatient(@PathVariable Integer id, @ModelAttribute @Valid PatientBean patient, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "patient/update";
        }
        PatientBean updatedPatient = patientInfoServiceProxy.updatePatient(id, patient);
        redirectAttributes.addFlashAttribute("updatedPatient", updatedPatient);
        return "redirect:/patient/details/" + id;
    }

    @GetMapping("/patient/delete/{id}")
    public String deletePatient(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        patientInfoServiceProxy.deletePatient(id);
        redirectAttributes.addFlashAttribute("successMessage", "Patient deleted successfully");
        return "redirect:/patient/list";
    }
}