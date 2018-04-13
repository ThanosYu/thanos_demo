package com.thanos.service;

import com.thanos.common.ZLException;
import com.thanos.common.ZLPage;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by wufei on 16/1/15.
 */
public interface ZlTemplateService<T> {

    Serializable save(T model) throws ZLException;

    Object update(T model) throws ZLException;

    Object delete(T model) throws ZLException;

    Object deleteById(Integer id) throws ZLException;

    ZLPage<T> search(Map<String, String> con) throws ZLException;

    List<T> searchList(Map<String, String> con) throws ZLException;

    Long count(Map<String, String> con) throws ZLException;

    T get(Serializable id) throws ZLException;

    List<T> getAll(Map<String, String> con) throws ZLException;

    Object addSubItemsIntoItem(String subItemIds, String itemId) throws ZLException;

    Object removeSubItemsFromItem(String subItemIds, String itemId) throws ZLException;

    Object updateSubItemOrder(String itemId, String subItemId, String orderNo) throws ZLException;

}
