package com.przedwojski.gallup;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Gallup {
    private static final String url =
            "https://docs.google.com/spreadsheets/d/18B7tI6tvgqYFeO3PeCV_pSjfUiYQrkJjKZOT0Q4K5YM/export?format=tsv";

    public static void main(String[] args) throws IOException {
        downloadSheet();
    }

    public static void downloadSheet() throws IOException {
        String USER_DIR = System.getProperty("user.dir");
        InputStream in = new URL(url).openStream();
        Path filePath = Paths.get(USER_DIR, "gallup.tsv");
        Path filePathFixed = Paths.get(USER_DIR, "gallup_fixed.tsv");
        Files.copy(in, filePath, StandardCopyOption.REPLACE_EXISTING);
        String csv = new String(Files.readAllBytes(filePath));
        String[] parts = csv.split("Dominik Juszczyk");
        String fixedCsv = "Dominik Juszczyk" + parts[1];
        Files.writeString(filePathFixed, fixedCsv);
    }
}
