package com.medilabosolutions.PatientUI.proxies;

import com.medilabosolutions.PatientUI.beans.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "Gateway", url = "gateway:9090")
public interface NoteInfoServiceProxy {

    @GetMapping("/note/details/{patId}")
    List<NoteBean> getNotesByPatId(@PathVariable("patId") int patId);

    @PostMapping("/note/validate")
    NoteBean addNote(@RequestBody NoteBean note);

    @DeleteMapping("/note/delete/notes/{patId}")
    void deleteNoteByPatId(@PathVariable("patId") int id);

}
