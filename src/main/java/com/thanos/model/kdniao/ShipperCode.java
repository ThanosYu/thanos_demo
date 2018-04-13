package com.thanos.model.kdniao;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Thanos_Yu
 * @date 2018/1/12.
 */
@Entity
@Table(
        name = "t_shipper_code"
)

@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@NamedQueries({
        @NamedQuery(name = "ZlBsCost.all", query = "from ShipperCode"),
})
public class ShipperCode implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    /**
     * 快递公司
     */
    @Column(name = "company", length = 32)
    private String company;

    /**
     * 编码
     */
    @Column(name = "code", length = 32)
    private String code;

    /**
     * 轨迹查询
     */
    @Column(name = "trace", length = 64)
    private String trace;

    /**
     * 电子面单
     */
    @Column(name = "e_ticket", length = 16)
    private String eTicket;

    /**
     * 预约取件
     */
    @Column(name = "reservation", length = 16)
    private String reservation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }

    public String geteTicket() {
        return eTicket;
    }

    public void seteTicket(String eTicket) {
        this.eTicket = eTicket;
    }

    public String getReservation() {
        return reservation;
    }

    public void setReservation(String reservation) {
        this.reservation = reservation;
    }
}
