package com.thanos.model.o2o;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Thanos Yu on 2016/11/14.
 */
@Entity
@Table(name = "o2o_category",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name"}),
                @UniqueConstraint(columnNames = {"title","description"})
        }
)
@NamedQueries({
        @NamedQuery(name = "O2OCategory.all", query = "from O2OCategory")
})
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
public class O2OCategory implements Serializable {

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
     * 描述
     */
    @Column(name = "description")
    private String description;


    /**
     * 内容（Json端不显示）
     */
    @JsonIgnore
    @Column(name = "content")
    private String content;

    /**
     * 视频（表格端不显示）
     */
    @Transient
    @Column(name = "video")
    private String video;

    /**
     * 排序
     */
    @Column(name = "order_no")
    @OrderColumn
    private Integer orderNo;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @OrderBy(value = "orderNo")
    private List<O2OModule> modules;

    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_datetime", length = 19)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8:00")
    private Date createDateTime = new Date();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public List<O2OModule> getModules() {
        return modules;
    }

    public void setModules(List<O2OModule> modules) {
        this.modules = modules;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }
}
