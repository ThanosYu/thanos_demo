package com.thanos.service;

import com.thanos.common.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hibernate.criterion.Restrictions.*;

/**
 * Created by wufei on 16/1/15.
 */

@Service
@Transactional(readOnly = true)
public abstract class ZlTemplateServiceImpl<T> extends BaseHibernateDaoSupport implements ZlTemplateService<T> {
    private Class<T> modelClass;

    public ZlTemplateServiceImpl() {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        modelClass = (Class) params[0];
    }

    @Override
    public T get(Serializable id) throws ZLException {
        T object = (T) getSession().get(this.getModelClass(), id);
        //获取懒加载的数据
        initializeForGet(object);
        return object;
    }

    @Override
    @Transactional(readOnly = false)
    public Serializable save(T model) throws ZLException {
        return getSession().save(model);
    }

    @Override
    @Transactional(readOnly = false)
    public Object update(T model) throws ZLException {
        getSession().update(model);
        return model;
    }

    @Override
    @Transactional(readOnly = false)
    public Object delete(T model) throws ZLException {
        getSession().delete(model);
        return ZLData.OK;
    }

    @Override
    @Transactional(readOnly = false)
    public Object deleteById(Integer id) throws ZLException {
        return delete(get(id));
    }

    /**
     * 通用检索接口，不分页
     *
     * @param con
     * @return
     */
    @Override
    public List<T> searchList(Map<String, String> con) throws ZLException {
        Session session = this.getSession();
        int start = StrUtil.nullToInt(con.get("start"), 0);
        int limit = StrUtil.nullToInt(con.get("limit"), 0);
        Criteria cri = generateSearchCriteria(session.createCriteria(this.getModelClass()), con, false);
        if (limit > 0) {
            cri = cri.setFirstResult(start).setMaxResults(limit);
        }

        List<T> list = cri.list();

        //获取检索时懒加载的数据
        initializeForSearchList(list);

        return list;
    }

    /**
     * 通用计数接口，不分页
     *
     * @param con
     * @return
     */
    @Override
    public Long count(Map<String, String> con) throws ZLException {
        Session session = this.getSession();
        Criteria pageCri = generateSearchCriteria(session.createCriteria(this.getModelClass()), con, true);

        Long total = (Long) pageCri.setProjection(Projections.rowCount())
                .uniqueResult();

        return total;
    }

    /**
     * 通用检索接口，实现分页
     *
     * @param con
     * @return
     */
    @Override
    public ZLPage<T> search(Map<String, String> con) throws ZLException {
        Session session = this.getSession();
        int start = StrUtil.nullToInt(con.get("start"), 0);
        int limit = StrUtil.nullToInt(con.get("limit"), pageSize);
        Criteria cri = generateSearchCriteria(session.createCriteria(this.getModelClass()), con, false);
        Criteria pageCri = generateSearchCriteria(session.createCriteria(this.getModelClass()), con, true);

        Long total = (Long) pageCri.setProjection(Projections.rowCount())
                .uniqueResult();
        List<T> list = cri
                .setFirstResult(start).setMaxResults(limit)
                .list();

        //获取检索时懒加载的数据
        initializeForSearch(list);

        ZLPage<T> result = ZLPage.get(list, total);
        return result;
    }

    /**
     * 设置查询条件
     *
     * @param cri
     * @param con     检索条件
     * @param isCount 是否count语句
     * @return
     */
    protected Criteria generateSearchCriteria(Criteria cri, Map<String, String> con, boolean isCount) {
        if (!isCount) {
            super.dealSorts(cri, con);
        }

        return cri;
    }

    /**
     * 设置查询条件
     *
     * @param cri
     * @param con          检索条件
     * @param propertyName 列名
     * @return
     */
    protected Criteria setIntEq(Criteria cri, Map<String, String> con, String propertyName) {
        String value = con.get(propertyName);
        if (StringUtils.isEmpty(value)) {
            return cri;
        }
        int intValue = StrUtil.nullToInt(value, -1);
        if (intValue < 0) {
            return cri;
        }
        cri.add(eq(propertyName, intValue));
        return cri;
    }

    /**
     * 设置查询条件
     *
     * @param cri
     * @param con          检索条件
     * @param propertyName 列名
     * @return
     */
    protected Criteria setStringEq(Criteria cri, Map<String, String> con, String propertyName) {
        String value = con.get(propertyName);
        if (StringUtils.isEmpty(value)) {
            return cri;
        }
        cri.add(eq(propertyName, value));
        return cri;
    }

    protected Criteria setStringEq(Criteria cri, Map<String, String> con, String propertyName, String defaultValue) {
        String value = con.get(propertyName);
        if (StringUtils.isEmpty(value) || defaultValue.equals(value)) {
            return cri;
        }
        cri.add(eq(propertyName, value));
        return cri;
    }

    /**
     * 设置模糊查询条件
     *
     * @param cri
     * @param con          检索条件
     * @param propertyName 列名
     * @return
     */
    protected Criteria setStringLike(Criteria cri, Map<String, String> con, String propertyName) {
        String value = con.get(propertyName + ZLData.CONDITION_SUFFIX.LIKE);
        if (StringUtils.isEmpty(value)) {
            return cri;
        }
        cri.add(like(propertyName, value, MatchMode.ANYWHERE));
        return cri;
    }


    protected Criteria setStringLike(Criteria cri, Map<String, String> con, String propertyName, String defaultValue) {
        String value = con.get(propertyName + ZLData.CONDITION_SUFFIX.LIKE);
        if (StringUtils.isEmpty(value) || defaultValue.equals(value)) {
            return cri;
        }
        cri.add(like(propertyName, value, MatchMode.ANYWHERE));
        return cri;
    }

    /**
     * 设置查询条件
     *
     * @param cri
     * @param propertyName 列名
     * @return
     */
    protected Criteria setNotNull(Criteria cri, Map<String, String> con, String propertyName) {
        if (!con.containsKey(propertyName + ZLData.CONDITION_SUFFIX.NOT_NULL)) {
            return cri;
        }
        cri.add(isNotNull(propertyName));
        return cri;
    }


    protected Criteria setNull(Criteria cri, Map<String, String> con, String propertyName) {
        if (!con.containsKey(propertyName + ZLData.CONDITION_SUFFIX.NULL)) {
            return cri;
        }
        cri.add(isNull(propertyName));
        return cri;
    }

    /**
     * 设置查询条件(<=)
     *
     * @param cri
     * @param con          检索条件
     * @param propertyName 列名
     * @return
     */
    protected Criteria setStringEnd(Criteria cri, Map<String, String> con, String propertyName) {
        String value = con.get(propertyName + ZLData.CONDITION_SUFFIX.END);
        if (StringUtils.isEmpty(value)) {
            return cri;
        }
        cri.add(le(propertyName, value));
        return cri;
    }

    /**
     * 设置查询条件(>=)
     *
     * @param cri
     * @param con          检索条件
     * @param propertyName 列名
     * @return
     */
    protected Criteria setStringStart(Criteria cri, Map<String, String> con, String propertyName) {
        String value = con.get(propertyName + ZLData.CONDITION_SUFFIX.START);
        if (StringUtils.isEmpty(value)) {
            return cri;
        }
        cri.add(ge(propertyName, value));
        return cri;
    }

    /**
     * 设置日期查询条件(>=)，格式（yyyy-MM-dd HH:mm:ss）
     *
     * @param cri
     * @param con          检索条件
     * @param propertyName 列名
     * @return
     */
    protected Criteria setDateTimeStart(Criteria cri, Map<String, String> con, String propertyName) {
        String value = con.get(propertyName + ZLData.CONDITION_SUFFIX.START);
        if (StringUtils.isEmpty(value)) {
            return cri;
        }
        cri.add(ge(propertyName, DatetimeUtil.stringToDateTime(value)));
        return cri;
    }

    /**
     * 设置日期查询条件(<=)，格式（yyyy-MM-dd HH:mm:ss）
     *
     * @param cri
     * @param con          检索条件
     * @param propertyName 列名
     * @return
     */
    protected Criteria setDateTimeEnd(Criteria cri, Map<String, String> con, String propertyName) {
        String value = con.get(propertyName + ZLData.CONDITION_SUFFIX.END);
        if (StringUtils.isEmpty(value)) {
            return cri;
        }
        cri.add(le(propertyName, DatetimeUtil.stringToDateTime(value)));
        return cri;
    }

    /**
     * 获取model的class类型
     * 由于泛型无法返回class类型，需要的子类可继承此方法
     *
     * @return
     */
    protected Class getModelClass() {
        return modelClass;
    }

    /**
     * 获取检索时懒加载的数据
     * 由于泛型无法返回class类型，需要的子类可继承此方法
     *
     * @return
     */
    protected void initializeForSearchList(List<T> list) {
    }

    /**
     * 获取检索时懒加载的数据
     * 由于泛型无法返回class类型，需要的子类可继承此方法
     *
     * @return
     */
    protected void initializeForSearch(List<T> list) {
    }

    protected void initializeForGet(T object) {

    }


    @Override
    public List<T> getAll(Map<String, String> con) throws ZLException {
        return null;
    }

    /**
     * 检查关联数据是否存在
     *
     * @return
     */
    protected T checkReleDataExist(String itemId, String subItemId) {
//        例：
//        Criteria cri = getSession().createCriteria(ZlRAppCateApp.class);
//        cri.add(eq("appId", Integer.parseInt(itemId))).add(eq("cateId", Integer.parseInt(subItemId)));
//        List<ZlRAppCateApp> list = cri.list();
//        if (null != list && !list.isEmpty()) {
//            return list.get(0);
//        }
//        return null;
        return null;
    }

    /**
     * 保存关联数据
     *
     * @return
     */
    @Transactional(readOnly = false)
    protected void saveReleData(String itemId, String subItemId) throws ZLException {
//        例
//        ZlRAppCateApp model = new ZlRAppCateApp(Integer.parseInt(itemId), Integer.parseInt(subItemId), 10000);
//        save(model);
    }

    /**
     * 更新model中的字段
     *
     * @return
     */
    @Transactional(readOnly = false)
    protected void updateField(T model, Map<String, String> fields) throws ZLException {
//        例
//        model.setOrderNo(Integer.parseInt(orderNo));
    }

    @Override
    @Transactional(readOnly = false)
    public Object addSubItemsIntoItem(String subItemIds, String itemId) throws ZLException {
        String[] idList = subItemIds.split(",");
        if (null != idList && 0 != idList.length) {
            for (String id : idList) {
                if (null == checkReleDataExist(itemId, id)) {
                    saveReleData(itemId, id);
                }
            }
        }
        return ZLData.OK;
    }

    @Override
    @Transactional(readOnly = false)
    public Object removeSubItemsFromItem(String subItemIds, String itemId) throws ZLException {
        String[] idList = subItemIds.split(",");
        for (String id : idList) {
            T model = checkReleDataExist(itemId, id);
            delete(model);
        }
        return ZLData.OK;

    }

    @Override
    @Transactional(readOnly = false)
    public Object updateSubItemOrder(String itemId, String subItemId, String orderNo) throws ZLException {
        T model = checkReleDataExist(itemId, subItemId);
        if (null != model && !StringUtils.isEmpty(orderNo)) {
            Map<String, String> fields = new HashMap<>();
            fields.put("orderNo", orderNo);
            updateField(model, fields);
            return ZLData.OK;
        }
        return ZLData.ERR;
    }
}
