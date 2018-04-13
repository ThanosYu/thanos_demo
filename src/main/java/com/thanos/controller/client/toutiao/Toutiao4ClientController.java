package com.thanos.controller.client.toutiao;

import com.thanos.common.UTF8MediaType;
import com.thanos.common.ZLData;
import com.thanos.common.ZLException;
import com.thanos.common.ZLResult;
import com.thanos.job.toutiao.UpdateToutiao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import java.util.Map;
import java.util.Random;

/**
 * @author Thanos Yu
 * @date 2017/11/24
 */
@Component
@Path("/toutiao")
public class Toutiao4ClientController {

    @Autowired
    private UpdateToutiao updateToutiao;

    @POST
    @Path("/token")
    @Consumes(UTF8MediaType.JSON)
    @Produces(UTF8MediaType.JSON)
    public ZLResult getToken(@Context HttpServletRequest request, Map<String, String> map) {
        try {
            String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
            String nounce = String.valueOf(new Random().nextLong());
            String token = updateToutiao.getRedisToken(timestamp, nounce);
            return ZLResult.success(token);
        } catch (ZLException e) {
            return ZLResult.failed(e.getCode(), e.getMessage());
        } catch (RuntimeException r) {
            r.printStackTrace();
            return ZLResult.failed(ZLData.RUNTIME_ERROR_CODE, r.getMessage());
        } catch (Exception ex) {
            return ZLResult.failed(ZLData.ERROR_CODE, ex.getMessage());
        }
    }
}
