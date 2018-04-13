package com.thanos.model.o2o;


import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Thanos Yu on 2017/08/02.
 */
@Entity
@Table(name = "o2o_module")
@NamedQueries({
        @NamedQuery(name = "O2OModule.all", query = "from O2OModule"),
})
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
public class O2OModule implements Serializable {

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
}
