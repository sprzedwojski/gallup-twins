package com.przedwojski.gallup;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class Gallup {
    private static final String url =
            "https://docs.google.com/spreadsheets/d/18B7tI6tvgqYFeO3PeCV_pSjfUiYQrkJjKZOT0Q4K5YM/export?format=tsv";
    private static final String FIRST_PERSON_NAME_IN_TSV = "Dominik Juszczyk";
    private static final String USER_DIR = System.getProperty("user.dir");

    public static void main(String[] args) throws IOException {
        downloadSheet();
    }

    public static void downloadSheet() throws IOException {
        InputStream in = new URL(url).openStream();
        Path filePath = Paths.get(USER_DIR, "gallup.tsv");
        Path filePathFixed = Paths.get(USER_DIR, "gallup_fixed.tsv");
        Files.copy(in, filePath, StandardCopyOption.REPLACE_EXISTING);
        String csv = new String(Files.readAllBytes(filePath));
        String[] parts = csv.split(FIRST_PERSON_NAME_IN_TSV);
        String fixedCsv = FIRST_PERSON_NAME_IN_TSV + parts[1];
        Files.writeString(filePathFixed, fixedCsv);
    }

    public static String getSheetLastSyncTime() {
        try {
            FileTime creationTime = (FileTime) Files.getAttribute(Paths.get(USER_DIR, "gallup_fixed.tsv"), "lastModifiedTime");
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofLocalizedDateTime( FormatStyle.SHORT )
                            .withLocale( Locale.UK )
                            .withZone( ZoneId.systemDefault() );
            return formatter.format(creationTime.toInstant());
        } catch (IOException ex) {
            // handle exception
            return null; // TODO
        }
    }
}
