package com.thanos.controller.client.imageupload;

import com.thanos.common.UTF8MediaType;
import com.thanos.common.ZLData;
import com.thanos.common.ZLException;
import com.thanos.common.ZLResult;
import com.thanos.tools.upload.Image;
import com.thanos.tools.upload.ImageUploadUtils;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 * @author Thanos_Yu
 * @date 2018/3/28.
 */
@Controller
@Path("/imageUpload")
public class ImageUpload4ClientController {

    @Autowired
    ImageUploadUtils imageUploadUtils;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(UTF8MediaType.JSON)
    @Path("/upload")
    public ZLResult upload(FormDataMultiPart multiPart, @Context UriInfo ui, @Context HttpServletRequest request) {
        try {
            System.out.println("-----------------------------------baseUri: "+ui.getBaseUri().toString());
            Image zlImage = imageUploadUtils.upload(multiPart, ui.getBaseUri().toString());
            return ZLResult.success(zlImage);
        } catch (ZLException e) {
            return ZLResult.failed(e.getCode(), e.getMessage());
        } catch (RuntimeException r) {
            return ZLResult.failed(ZLData.RUNTIME_ERROR_CODE, r.getMessage());
        } catch (Exception ex) {
            return ZLResult.failed(ZLData.ERROR_CODE, ex.getMessage());
        }
    }
}
