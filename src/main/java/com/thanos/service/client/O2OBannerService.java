package com.thanos.service.client;


import com.thanos.model.o2o.O2OBanner;
import com.thanos.model.o2o.O2OCategory;
import com.thanos.service.ZlTemplateService;

import java.util.List;

/**
 * @author Thanos Yu on 2017/8/3.
 */
public interface O2OBannerService extends ZlTemplateService<O2OBanner> {
    /**
     * fetch O2OBanner by id
     *
     * @param id O2OBanner id
     * @return O2OBanner
     */
    public O2OBanner get(Integer id);

    /**
     * fetch O2OCategory by id
     *
     * @param id O2OCategory id
     * @return O2OCategory
     */
    public O2OCategory getUser(Integer id);

    /**
     * fetch O2OBanner by id
     *
     * @return O2OBanner
     */
    public List<O2OBanner> select();
}
