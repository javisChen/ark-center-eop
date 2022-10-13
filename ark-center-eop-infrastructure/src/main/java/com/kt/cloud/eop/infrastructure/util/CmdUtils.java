package com.ark.center.eop.infrastructure.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.RuntimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Map;

@Slf4j
public class CmdUtils {

    public static void exec(Map<String, String> environment, File dir, String... cmd) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.directory(dir);
        if (CollectionUtil.isNotEmpty(environment)) {
            processBuilder.environment().putAll(environment);
        }
        log.info("exec command: {}", Arrays.toString(cmd));
        processBuilder.command(cmd);
        InputStream inputStream = null;
        Process process = null;
        try {
            process = processBuilder.start();
            inputStream = process.getInputStream();
            String s = StreamUtils.copyToString(inputStream, Charset.defaultCharset());
            log.info("exec command info: {}", s);
            try {
                process.waitFor();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                log.info("subprocess is finish");
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (process != null) {
                process.destroy();
            }
        }
    }
}
