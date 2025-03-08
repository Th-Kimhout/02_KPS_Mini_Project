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

import java.util.Arrays;
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

            // Find the latest version number
            int nextVersion = 1;
            File[] existingBackups = backupDir.toFile().listFiles((dir, name) ->
                    name.matches("backup_version\\d+\\.sql") // Match "versionX.sql"
            );

            if (existingBackups != null && existingBackups.length > 0) {
                Pattern pattern = Pattern.compile("backup_version(\\d+)\\.sql");
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

            String backupFileName = String.format("backup_version%d.sql", nextVersion);
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

            // Execute the command
            pb.environment().put("PGPASSWORD", password);
            Process process = pb.start();

            // Wait for the process to complete
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Backup successful!");
            } else {
                System.err.println("Backup failed with exit code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void doRestore() {
        CredentialLoader.loadProperties();
        String username = CredentialLoader.properties.getProperty("db_username");
        String password = CredentialLoader.properties.getProperty("db_password");
        try {

            Table displayBackupFile = new Table(2, BorderStyle.CLASSIC_COMPATIBLE, ShownBorders.ALL);
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
            System.out.print("Select backup file path: ");
            String choice = scanner.nextLine();

            String backupFilePath = BACKUP_DIR + backupFiles[Integer.parseInt(choice)-1].getName();
            System.out.println(backupFilePath);
            // Define the backup command
            ProcessBuilder pb = new ProcessBuilder(
                    "psql",
                    "-U", username,
                    "-h", "localhost",
                    "-p", "5432",
                    "-d", "mini_project",
                    "-f", backupFilePath
            );

            // Execute the command
            pb.environment().put("PGPASSWORD", password);
            Process process = pb.start();

            // Wait for the process to complete
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Restore successful!");
            } else {
                System.err.println("Restore failed with exit code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static File[] getBackupFilePath() {
        Path backupDir = Paths.get(BACKUP_DIR);
        return backupDir.toFile().listFiles((dir, name) ->
                name.matches("backup_version\\d+\\.sql") // Match "versionX.sql"
        );

    }
}