package com.thanos.controller.client.o2o;

import com.thanos.common.*;
import com.thanos.model.o2o.O2OModule;
import com.thanos.service.client.O2OCategoryService;
import com.thanos.model.o2o.O2OCategory;
import com.thanos.service.client.O2OBannerService;
import com.thanos.service.client.O2OModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.Map;

/**
 * Created by Thanos Yu on 2017/3/28.
 */
@Component
@Path("/o2o")
public class O2O4ClientController {

    @Autowired
    private O2OCategoryService categoryService;

    @Autowired
    private O2OBannerService bannerService;

    @Autowired
    private O2OModuleService moduleService;

    @POST
    @Path("/category")
    @Consumes(UTF8MediaType.JSON)
    @Produces(UTF8MediaType.JSON)
    public ZLResult getCategory(@Context HttpServletRequest request, Map<String, String> map) {
        String id = map.get("id");
        O2OCategory model = bannerService.getUser(Integer.valueOf(id));
        return ZLResult.success(model);
    }

    @GET
    @Path("/categories")
    @Produces(UTF8MediaType.JSON)
    public ZLResult getCategories(@Context HttpServletRequest request, @Context UriInfo ui) {
        List<O2OCategory> list = categoryService.getAll();
        return ZLResult.success(list);
    }

    @GET
    @Path("/module")
    @Produces(UTF8MediaType.JSON)
    public ZLResult getModule(@Context HttpServletRequest request, @Context UriInfo ui) {
        Map<String, String> map = AppUtils.prepareParameters(ui.getQueryParameters());
        String id = map.get("id");
        O2OModule model = moduleService.get(Integer.valueOf(id));
        return ZLResult.success(model);
    }

    @POST
    @Path("/modules")
    @Consumes(UTF8MediaType.JSON)
    @Produces(UTF8MediaType.JSON)
    public ZLResult getModules(@Context HttpServletRequest request, Map<String, String> map) {
        String id = map.get("id");
        O2OCategory model = categoryService.get(Integer.valueOf(id));
        List<O2OModule> list = model.getModules();
        return ZLResult.success(list);
    }

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(UTF8MediaType.JSON)
    public ZLResult save(@Context HttpServletRequest request) {
        try {
            return ZLResult.success(categoryService.save(AppUtils.convertRequestBody(request, O2OCategory.class)));
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
