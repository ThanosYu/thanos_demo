package com.thanos.service.client;

import com.thanos.model.o2o.O2OModule;
import com.thanos.service.ZlTemplateService;

/**
 * @author Thanos Yu on 2017/8/3.
 */
public interface O2OModuleService extends ZlTemplateService<O2OModule> {
    /**
     * 获得 O2OModule
     * @param id O2OModule id
     * @return O2OModule
     */
    public O2OModule get(Integer id);
}
