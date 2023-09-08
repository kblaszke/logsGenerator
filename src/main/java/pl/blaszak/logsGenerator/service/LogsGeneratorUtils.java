package pl.blaszak.logsGenerator.service;

import org.springframework.util.ResourceUtils;

import java.io.*;

public class LogsGeneratorUtils {

    public static String[] loadResourceFile(String fileName) throws FileNotFoundException {
        FileInputStream stream = new FileInputStream(ResourceUtils.getFile("classpath:" + fileName));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines().toArray(String[]::new);
    }
}
