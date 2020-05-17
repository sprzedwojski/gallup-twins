package com.przedwojski.gallup.api;

import com.przedwojski.gallup.Gallup;
import com.przedwojski.gallup.ReadCSV;
import com.przedwojski.gallup.SimilarityResults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/redownload")
    public RedirectView redownload() {
        try {
            Gallup.downloadSheet();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new RedirectView("/");
    }

    @GetMapping("/find")
    public String find(@RequestParam(name = "name") String name, Model model) {
        System.out.println("Looking for " + name);

        try {
            SimilarityResults similarityResults = new ReadCSV().getFor(name);
            model.addAttribute("results", similarityResults);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", "Nie znaleziono takiej osoby.");
        }
        return "index";
    }

    @ModelAttribute("lastSyncTime")
    String lastSyncTime() {
        return Gallup.getSheetLastSyncTime();
    }
}
