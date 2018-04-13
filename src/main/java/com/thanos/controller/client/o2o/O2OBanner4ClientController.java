package com.thanos.controller.client.o2o;

import com.thanos.common.UTF8MediaType;
import com.thanos.common.ZLResult;
import com.thanos.model.o2o.O2OBanner;
import com.thanos.service.client.O2OBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * @author Thanos Yu
 * @date 2017/12/31
 */
@Component
@Path("/banner")
public class O2OBanner4ClientController {

    @Autowired
    private O2OBannerService bannerService;

    @POST
    @Path("/select")
    @Consumes(UTF8MediaType.JSON)
    @Produces(UTF8MediaType.JSON)
    public ZLResult selectBanner() {
        List<O2OBanner> list = bannerService.select();
        return ZLResult.success(list);
    }
}
