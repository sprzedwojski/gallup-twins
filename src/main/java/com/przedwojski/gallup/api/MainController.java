package com.przedwojski.gallup.api;

import com.przedwojski.gallup.Gallup;
import com.przedwojski.gallup.ReadCSV;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/find")
    public String find(@RequestParam(name="name") String name, Model model) {
        try {
            Gallup.downloadSheet();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Looking for " + name);

        List<String> results = new ReadCSV().getFor(name);

        model.addAttribute("results", results);
        return "index";
    }

}
