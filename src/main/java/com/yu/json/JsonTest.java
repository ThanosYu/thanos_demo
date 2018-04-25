package com.yu.json;

import com.thanos.common.ZLJsonUtil;

/**
 * @author Thanos Yu
 * @date 2018/4/25
 */
public class JsonTest {

    public static void main(String[] args) {
        Data data = ZLJsonUtil.fromJson("{\"code\":200,\"data\":[{\"class\":\"com.asus.backend.dal.domain.goods.GoodsImageLabelPO\",\"createTime\":1495795137000,\"defaultFlag\":1,\"id\":2,\"modTime\":null,\"storeId\":1,\"title\":\"未分组\"},{\"class\":\"com.asus.backend.dal.domain.goods.GoodsImageLabelPO\",\"createTime\":1495795137000,\"defaultFlag\":0,\"id\":1,\"modTime\":null,\"storeId\":1,\"title\":\"大图\"},{\"class\":\"com.asus.backend.dal.domain.goods.GoodsImageLabelPO\",\"createTime\":1495795137000,\"defaultFlag\":0,\"id\":3,\"modTime\":null,\"storeId\":1,\"title\":\"小图\"}],\"msg\":\"请求成功!\"}",Data.class);
        System.out.println(data.getData().get(0).getTitle());
        System.out.println(data.getData().get(1).getTitle());
        System.out.println(data.getData().get(2).getTitle());
    }
}
