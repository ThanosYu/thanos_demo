package com.thanos.service.client;




import com.thanos.common.ZLException;
import com.thanos.model.o2o.O2OCategory;
import com.thanos.service.ZlTemplateService;

import java.io.Serializable;
import java.util.List;

/**
 * @author Thanos Yu on 2017/3/28.
 */
public interface O2OCategoryService extends ZlTemplateService<O2OCategory> {
    /**
     * fetch O2OCategory by id
     * @param id O2OCategory id
     * @return O2OCategory
     */
    public O2OCategory get(Integer id);

    /**
     * save O2OCategory
     * @param model O2OCategory
     * @return null
     */
    @Override
    public Serializable save(O2OCategory model) throws ZLException;

    /**
     * update O2OCategory
     * @param model O2OCategory
     * @return null
     */
    @Override
    public Object update(O2OCategory model);

    /**
     * fetch O2OCategory
     * @return List<O2OCategory>
     */
    public List<O2OCategory> getAll();
}
