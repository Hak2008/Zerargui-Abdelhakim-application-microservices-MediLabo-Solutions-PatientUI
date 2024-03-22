package com.medilabosolutions.PatientUI.controller;

import com.medilabosolutions.PatientUI.beans.NoteBean;
import com.medilabosolutions.PatientUI.proxies.NoteInfoServiceProxy;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/UI")
public class NoteUIController {

    private final NoteInfoServiceProxy noteInfoServiceProxy;

    public NoteUIController(NoteInfoServiceProxy noteInfoServiceProxy) {
        this.noteInfoServiceProxy = noteInfoServiceProxy;
    }


    @GetMapping("/note/add")
    public String addNoteForm(NoteBean note) {
        return "note/add";
    }

    @PostMapping("/note/validate")
    public String addNote(@ModelAttribute @Valid NoteBean note, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "note/add";
        }
        noteInfoServiceProxy.addNote(note);
        redirectAttributes.addFlashAttribute("successMessage", "Note added successfully");

        String redirectUrl = "redirect:/UI/patient/details/" + note.getPatId();
        return redirectUrl;
    }

    @GetMapping("/note/update/{id}")
    public String showUpdateForm(@PathVariable String id, Model model) {
        NoteBean note = noteInfoServiceProxy.getNoteById(id);
        model.addAttribute("note", note);
        return "note/update";
    }

    @PostMapping("/note/update/{id}")
    public String updateNote(@PathVariable String id, @ModelAttribute @Valid NoteBean note, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "note/update";
        }
        NoteBean updatedNote = noteInfoServiceProxy.updateNote(id, note);
        redirectAttributes.addFlashAttribute("updatedNote", updatedNote);
        return "redirect:/patient/details/" + note.getPatId();
    }

    @GetMapping("/note/delete/{id}")
    public String deleteNote(@PathVariable String id, RedirectAttributes redirectAttributes) {
        noteInfoServiceProxy.deleteNote(id);
        redirectAttributes.addFlashAttribute("successMessage", "Note deleted successfully");
        return "redirect:/UI/patient/list/";
    }
}
