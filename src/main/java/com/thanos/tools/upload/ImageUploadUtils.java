package com.thanos.tools.upload;

import com.thanos.common.Console;
import com.thanos.common.ZLData;
import com.thanos.common.ZLException;
import org.glassfish.jersey.media.multipart.BodyPart;
import org.glassfish.jersey.media.multipart.BodyPartEntity;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.List;

/**
 * Created by Toni_Zhang on 2015/9/23.
 */
@Component
public class ImageUploadUtils {

    public Image upload(FormDataMultiPart multiPart, String baseUri) throws ZLException {
        return upload(multiPart, baseUri, null);
    }

    public Image upload(FormDataMultiPart multiPart, String baseUri, String uploadFolder) throws ZLException {
        Image image = new Image();
        OutputStream outputStream = null;
        InputStream inputStream = null;
        if (StringUtils.isEmpty(uploadFolder)) {
//            Console.info("rabbit_debug---console>>文件夹为空");
            uploadFolder = ZLData.IMAGE_UPLOAD_PATH;
        }
        if (!uploadFolder.startsWith("/")) {
            uploadFolder = "/" + uploadFolder;
//            Console.info("rabbit_debug---uploadFolder>>" + uploadFolder);
        }

        try {
//            String baseDir = ZLData.FILE_UPLOAD_DIR + ZLData.IMAGE_UPLOAD_PATH;
            String baseDir = ZLData.FILE_UPLOAD_DIR + uploadFolder;
            String realPath = FileUtil.createFolder(baseDir, 2);
            String filePath = realPath.replace(baseDir + "/", "");
            List<BodyPart> bodyPartList = multiPart.getBodyParts();

            for (BodyPart bodyPart : bodyPartList) {
                String mediaType = bodyPart.getMediaType().getSubtype();
                if (ZLData.ICON_FILE_TYPE.contains(mediaType.toLowerCase())) {
                    String fileFullName = bodyPart.getContentDisposition().getFileName();
                    File file = Util.createImageFile(realPath, fileFullName);
                    outputStream = new FileOutputStream(file);
                    BodyPartEntity bodyPartEntity = (BodyPartEntity) bodyPart.getEntity();
                    inputStream = bodyPartEntity.getInputStream();
                    int length = 0;
                    byte[] buff = new byte[1024];
                    long size = 0;
                    while (-1 != (length = inputStream.read(buff))) {
                        outputStream.write(buff, 0, length);
                        size += length;
                    }
                    outputStream.flush();

                    //更新APP数据

                    String path = filePath + "/" + file.getName();
                    image.setPath(path);
                    image.setSize(size);
                    Util.convertDownloadUrl(image, baseUri, uploadFolder);
                    Console.debug("图片:" + fileFullName + "上传成功！");
                    break;
                }
            }
        } catch (RuntimeException r) {
//            result = ZLResult.failed(ZLData.RUNTIME_ERROR_CODE, r.getMessage());
            Console.error("图片" + "上传失败！：Exception" + r.getMessage());
            r.printStackTrace();
        } catch (Exception ex) {
//            result = ZLResult.failed(ZLData.ERROR_CODE, ex.getMessage());
            Console.error("图片" + "上传失败！：Exception" + ex.getMessage());
            ex.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
        }
//        if (result == null) {
//            result = ZLResult.failed(ZLData.ERROR_CODE, "上传图片失败.");
//        }

        return image;
    }
}
