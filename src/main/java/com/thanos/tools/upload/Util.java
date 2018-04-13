package com.thanos.tools.upload;

import com.thanos.common.ZLData;
import org.apache.commons.io.FilenameUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.Random;

/**
 * @author Thanos_Yu
 * @date 2018/3/28.
 */
public class Util {

    private static final Random random = new Random();

    public static void convertDownloadUrl(Image object, String baseUri, String uploadFolder) {
        object.setUrl(createImageUrl(baseUri, object.getPath(), uploadFolder));
    }

    public static String createImageUrl(String baseUri, String downloadUrl, String uploadFolder) {
        if (StringUtils.isEmpty(uploadFolder)) {
            return downloadUrl;
        }
        return createFileUrl(baseUri, downloadUrl, uploadFolder);
    }

    public static String createFileUrl(String baseUri, String downloadUrl, String folder) {
        if (StringUtils.isEmpty(baseUri) || StringUtils.isEmpty(downloadUrl) || downloadUrl.startsWith("http://") || downloadUrl.startsWith("https://")) {
            return downloadUrl;
        }
        StringBuffer sb = new StringBuffer();
//        sb.append(baseUri.replace("http://", "https://").replace("/rs/", "/"));
//        sb.append(downloadUrl);
        sb.append(ZLData.FILE_DOWNLOAD_SERVER);
        sb.append(ZLData.FILE_DOWNLOAD_PATH);
        sb.append(folder);
        sb.append("/");
        sb.append(downloadUrl);

        return sb.toString();
    }

    public static File createImageFile(String folder, String fileFullName) {
        File file = new File(folder + "/" + createImageFileName(fileFullName));
        if (file.exists()) {
            return createImageFile(folder, fileFullName);
        }
        return file;
    }

    public static String createImageFileName(String fileFullName) {
        String fileName = FilenameUtils.getBaseName(fileFullName);
        String suffix = FilenameUtils.getExtension(fileFullName);

        StringBuffer sb = new StringBuffer();
        sb.append(fileName);
        sb.append("(");
        sb.append(System.currentTimeMillis());
        sb.append(random.nextInt(100));
        sb.append(")");
        String s = StringUtil.encodeMD5(sb.toString());
        sb = new StringBuffer(s);
        sb.append(".");
        sb.append(suffix);
        return sb.toString();
    }
}
