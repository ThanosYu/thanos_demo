package com.thanos.tools.upload;


import java.io.File;

/**
 * @author Thanos_Yu
 * @date 2018/3/28.
 */
public class FileUtil {
    private static final int retryTimes = 10;

    public static boolean mkdirs(String folderName) {
        try {
            File file = new File(folderName);
            if(file.exists()) {
                return true;
            }
            return file.mkdirs();
        } catch (Exception e) {
            return false;
        }
    }

    public static String createFolder(String baseDir, int subSize) {
        return createFolder(baseDir, subSize, 2);
    }

    public static String createFolder(String baseDir, int subSize, int folderNameLength) {
        for (int i = 0; i < retryTimes; i++) {
            String dir = createFolderName(baseDir, subSize, folderNameLength);
            if(mkdirs(dir)) {
                return dir;
            };
        }
        return null;
    }

    private static String createFolderName(String baseDir, int subSize, int folderNameLength) {
        StringBuffer dir = new StringBuffer();
        dir.append(baseDir);
        for (int i = 0; i < subSize; i++) {
            String subDir = StringUtil.getRandomString(folderNameLength);
            dir.append("/");
            dir.append(subDir);
        }
        return dir.toString();
    }

}
