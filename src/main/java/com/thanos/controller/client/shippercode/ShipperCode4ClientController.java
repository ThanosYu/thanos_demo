package com.thanos.controller.client.shippercode;

import com.thanos.common.UTF8MediaType;
import com.thanos.common.ZLData;
import com.thanos.common.ZLException;
import com.thanos.common.ZLResult;
import com.thanos.service.client.shippercode.ShipperCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import java.util.Map;

/**
 * @author Thanos Yu
 * @date 2017/11/24
 */
@Component
@Path("/shipper/code")
public class ShipperCode4ClientController {


    @Autowired
    private ShipperCodeService shipperCodeService;

    @POST
    @Path("/save")
    @Consumes(UTF8MediaType.JSON)
    @Produces(UTF8MediaType.JSON)
    public ZLResult getToken(@Context HttpServletRequest request, Map<String, String> map) {
        try {
            String path = map.get("path");
            System.out.println("---------------path: "+path);
            shipperCodeService.saveData("D:\\ASUS_SVN\\项目文档\\新零售\\8、开发环境及第三方组件\\快递鸟\\2018快递鸟接口支持快递公司编码.xlsx");
            return ZLResult.success();
        } catch (ZLException e) {
            return ZLResult.failed(e.getCode(), e.getMessage());
        } catch (RuntimeException r) {
            return ZLResult.failed(ZLData.RUNTIME_ERROR_CODE, r.getMessage());
        } catch (Exception ex) {
            return ZLResult.failed(ZLData.ERROR_CODE, ex.getMessage());
        }
    }
}
