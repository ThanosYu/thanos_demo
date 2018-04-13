package com.thanos.model.o2o;


import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Thanos Yu on 2017/08/02.
 */
@Entity
@Table(name = "o2o_banner")
@NamedQueries({
        @NamedQuery(name = "O2OBanner.all", query = "from O2OBanner"),
        @NamedQuery(name = "O2OBanner.select", query = "select a from O2OBanner a,O2OModule b where a.name = b.name and b.id = 2"),

})
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
public class O2OBanner implements Serializable {

    /**
     * 地址，唯一的
     */
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    /**
     * 名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 排序
     */
    @Column(name = "order_no")
    @OrderColumn
    private Integer orderNo;

    /**
     * 模块
     */
    @ManyToOne
    @JoinColumn(name = "category_id")
    private O2OCategory category;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public O2OCategory getCategory() {
        return category;
    }

    public void setCategory(O2OCategory category) {
        this.category = category;
    }
}
