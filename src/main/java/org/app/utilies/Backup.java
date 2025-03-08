package org.app.utilies;

import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Backup {
    private static final String BACKUP_DIR = "D:\\HRD\\Java Programming\\Java Mini Project\\02_KPS_Mini_Project\\src\\main\\java\\org\\app\\backupfile\\";
    private static final Scanner scanner = new Scanner(System.in);

    public static void doBackup() {
        CredentialLoader.loadProperties();
        String username = CredentialLoader.properties.getProperty("db_username");
        String password = CredentialLoader.properties.getProperty("db_password");
        try {
            // Create backup directory if it doesn't exist
            Path backupDir = Paths.get(BACKUP_DIR);
            Files.createDirectories(backupDir);

            // Find the latest Version number
            int nextVersion = 1;
            File[] existingBackups = backupDir.toFile().listFiles((dir, name) ->
                    name.matches("backup-product-Version\\d+-\\d{4}-\\d{2}-\\d{2}\\.sql") // Match "versionX.sql"
            );

            if (existingBackups != null && existingBackups.length > 0) {
                Pattern pattern = Pattern.compile("backup-product-Version(\\d+)-\\d{4}-\\d{2}-\\d{2}\\.sql");
                int maxVersion = 0;
                for (File file : existingBackups) {
                    Matcher matcher = pattern.matcher(file.getName());
                    if (matcher.find()) {
                        int currentVersion = Integer.parseInt(matcher.group(1));
                        if (currentVersion > maxVersion) {
                            maxVersion = currentVersion;
                        }
                    }
                }
                nextVersion = maxVersion + 1;
            }
            LocalDate currentDate = LocalDate.now();
            String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String backupFileName = String.format("backup-product-Version%d-%s.sql", nextVersion, formattedDate);
            String backupFilePath = BACKUP_DIR + backupFileName;

            // Define the backup command
            ProcessBuilder pb = new ProcessBuilder(
                    "C:\\Program Files\\PostgreSQL\\17\\bin\\pg_dump.exe",
                    "-U", username,
                    "-h", "localhost",
                    "-p", "5432",
                    "--clean",
                    "-d", "mini_project",
                    "-f", backupFilePath
            );

            pb.environment().put("PGPASSWORD", password);
            Process process = pb.start();

            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println(Color.BRIGHT_GREEN + "Backup successful!" + Color.RESET);
            } else {
                System.err.println(Color.BRIGHT_RED + "Backup failed with exit code: " + exitCode + Color.RESET);
            }
            System.out.println(Color.BRIGHT_YELLOW + "Press Enter To Continue..." + Color.RESET);
            scanner.nextLine();
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void doRestore() {
        CredentialLoader.loadProperties();
        String username = CredentialLoader.properties.getProperty("db_username");
        String password = CredentialLoader.properties.getProperty("db_password");

        Table displayBackupFile = new Table(2, BorderStyle.UNICODE_BOX, ShownBorders.ALL);
        displayBackupFile.addCell("ID", new CellStyle(CellStyle.HorizontalAlign.CENTER), 1);
        displayBackupFile.addCell("Backup's File", new CellStyle(CellStyle.HorizontalAlign.CENTER), 1);
        displayBackupFile.setColumnWidth(0, 10, 60);
        displayBackupFile.setColumnWidth(1, 10, 60);
        File[] backupFiles = getBackupFilePath();
        for (int i = 0; i < backupFiles.length; i++) {
            displayBackupFile.addCell("" + (i + 1), new CellStyle(CellStyle.HorizontalAlign.CENTER), 1);
            displayBackupFile.addCell("   " + backupFiles[i].getName() + "   ", new CellStyle(CellStyle.HorizontalAlign.CENTER), 1);

        }
        System.out.println(displayBackupFile.render());
        String choice;
        while (true) {
            choice = UserInput.Input("Enter Backup Id To Restore : ", "\\d+", "Invalid Version ID Input!");
            int versionId = Integer.parseInt(choice);
            if (versionId < 0 || versionId > backupFiles.length) {
                System.out.println(Color.BRIGHT_RED + "Selected Version out of range!" + Color.RESET);
            } else if (versionId == 0) {
                return;
            } else break;
        }
        String backupFilePath = BACKUP_DIR + backupFiles[Integer.parseInt(choice) - 1].getName();
        try {

            ProcessBuilder pb = new ProcessBuilder(
                    "psql",
                    "-U", username,
                    "-h", "localhost",
                    "-p", "5432",
                    "-d", "mini_project",
                    "-f", backupFilePath
            );

            pb.environment().put("PGPASSWORD", password);
            Process process = pb.start();

            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println(Color.BRIGHT_GREEN + "Restore successful!" + Color.RESET);
            } else {
                System.err.println(Color.BRIGHT_RED + "Restore failed with exit code: " + exitCode + Color.RESET);
            }
            System.out.println(Color.BRIGHT_YELLOW + "Press Enter To Continue..." + Color.RESET);
            scanner.nextLine();
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    private static File[] getBackupFilePath() {
        Path backupDir = Paths.get(BACKUP_DIR);
        return backupDir.toFile().listFiles((dir, name) ->
                name.matches("backup-product-Version(\\d+)-\\d{4}-\\d{2}-\\d{2}\\.sql") // Match "versionX.sql"
        );

    }
}