package com.kt.cloud.cop.infrastructure.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Slf4j
public class CmdUtils {

    public static void exec(Map<String, String> environment, File dir, String... cmd) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.directory(dir);
        processBuilder.environment().putAll(environment);
        processBuilder.command(cmd);
        InputStream inputStream = null;
        try {
            Process start = processBuilder.start();
            inputStream = start.getInputStream();
//            String s = StreamUtils.copyToString(inputStream, Charset.defaultCharset());
//            Logs.debug("exec command info: {}", s);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }
}
