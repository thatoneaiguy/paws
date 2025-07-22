package com.everest.server;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.*;

public class ChecksumVerifier {
    // replace with aws server when i get it set up
    // ! do not forget to hide from github
    private static final String SERVER_URL = "";

    public static boolean verifyChecksum(File folder) {
        try {
            String localChecksum = folder.exists() ? computeFolderChecksum(folder) : "";
            String remoteChecksum = requestChecksumFromServer();

            return remoteChecksum.equalsIgnoreCase(localChecksum);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String requestChecksumFromServer() throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(SERVER_URL).openConnection();
        conn.setRequestMethod("GET");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            return reader.readLine().trim(); // expects plain checksum
        }
    }

    private static String computeFolderChecksum(File folder) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        List<File> files = listAllFilesSorted(folder);
        for (File file : files) {
            // update hash with relative file path bytes (to detect rename changes too)
            String relativePath = folder.toPath().relativize(file.toPath()).toString().replace("\\", "/");
            digest.update(relativePath.getBytes());

            try (InputStream is = new FileInputStream(file)) {
                byte[] buffer = new byte[8192];
                int read;
                while ((read = is.read(buffer)) > 0) {
                    digest.update(buffer, 0, read);
                }
            }
        }

        byte[] hash = digest.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) sb.append(String.format("%02x", b));
        return sb.toString();
    }

    private static List<File> listAllFilesSorted(File root) {
        List<File> files = new ArrayList<>();
        Queue<File> dirs = new ArrayDeque<>();
        dirs.add(root);

        while (!dirs.isEmpty()) {
            File dir = dirs.poll();
            File[] entries = dir.listFiles();
            if (entries != null) {
                Arrays.sort(entries, Comparator.comparing(File::getName)); // consistent order
                for (File entry : entries) {
                    if (entry.isDirectory()) {
                        dirs.add(entry);
                    } else {
                        files.add(entry);
                    }
                }
            }
        }

        return files;
    }
}
