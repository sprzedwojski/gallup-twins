package com.przedwojski.gallup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class GallupApplication {

    private static void syncSheetIfNotExists() throws IOException {
        Path path = Paths.get(System.getProperty("user.dir"), "gallup_fixed.tsv");
        if (!new File(path.toUri()).exists()) {
            Gallup.downloadSheet();
        }
    }

    public static void main(String[] args) throws IOException {
        syncSheetIfNotExists();
        SpringApplication.run(GallupApplication.class, args);
    }
}
