package org.ergea.utils;

import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Utils {
    public static final Scanner INPUT = new Scanner(System.in);
    public static final String csvMenuFilePath = Paths.get(System.getProperty("user.dir"), "menu.csv").toString();
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static final LocalDate LOCAL_DATE_NOW = LocalDate.now();
    public static String generateCsvFileName(int uniqueNumber) {
        return String.format("STRUK-%d-[%s].txt", uniqueNumber, DATE_TIME_FORMATTER.format(LOCAL_DATE_NOW));
    }
    public static String formatToRupiah(int amount) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        symbols.setMonetaryDecimalSeparator(',');
        DecimalFormat format = new DecimalFormat("'Rp.' #,###", symbols);
        return format.format(amount);
    }
}
