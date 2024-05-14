package com.entity.model;

import com.entity.CanyinOrderEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 餐饮订单
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class CanyinOrderModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 餐饮订单号
     */
    private String canyinOrderUuidNumber;


    /**
     * 餐饮
     */
    private Integer canyinId;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 购买数量
     */
    private Integer buyNumber;


    /**
     * 实付价格
     */
    private Double canyinOrderTruePrice;


    /**
     * 派送人
     */
    private String canyinOrderCourierName;


    /**
     * 派送人联系方式
     */
    private String canyinOrderCourierNumber;


    /**
     * 订单类型
     */
    private Integer canyinOrderTypes;


    /**
     * 支付类型
     */
    private Integer canyinOrderPaymentTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间 show3
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：餐饮订单号
	 */
    public String getCanyinOrderUuidNumber() {
        return canyinOrderUuidNumber;
    }


    /**
	 * 设置：餐饮订单号
	 */
    public void setCanyinOrderUuidNumber(String canyinOrderUuidNumber) {
        this.canyinOrderUuidNumber = canyinOrderUuidNumber;
    }
    /**
	 * 获取：餐饮
	 */
    public Integer getCanyinId() {
        return canyinId;
    }


    /**
	 * 设置：餐饮
	 */
    public void setCanyinId(Integer canyinId) {
        this.canyinId = canyinId;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：用户
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：购买数量
	 */
    public Integer getBuyNumber() {
        return buyNumber;
    }


    /**
	 * 设置：购买数量
	 */
    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }
    /**
	 * 获取：实付价格
	 */
    public Double getCanyinOrderTruePrice() {
        return canyinOrderTruePrice;
    }


    /**
	 * 设置：实付价格
	 */
    public void setCanyinOrderTruePrice(Double canyinOrderTruePrice) {
        this.canyinOrderTruePrice = canyinOrderTruePrice;
    }
    /**
	 * 获取：派送人
	 */
    public String getCanyinOrderCourierName() {
        return canyinOrderCourierName;
    }


    /**
	 * 设置：派送人
	 */
    public void setCanyinOrderCourierName(String canyinOrderCourierName) {
        this.canyinOrderCourierName = canyinOrderCourierName;
    }
    /**
	 * 获取：派送人联系方式
	 */
    public String getCanyinOrderCourierNumber() {
        return canyinOrderCourierNumber;
    }


    /**
	 * 设置：派送人联系方式
	 */
    public void setCanyinOrderCourierNumber(String canyinOrderCourierNumber) {
        this.canyinOrderCourierNumber = canyinOrderCourierNumber;
    }
    /**
	 * 获取：订单类型
	 */
    public Integer getCanyinOrderTypes() {
        return canyinOrderTypes;
    }


    /**
	 * 设置：订单类型
	 */
    public void setCanyinOrderTypes(Integer canyinOrderTypes) {
        this.canyinOrderTypes = canyinOrderTypes;
    }
    /**
	 * 获取：支付类型
	 */
    public Integer getCanyinOrderPaymentTypes() {
        return canyinOrderPaymentTypes;
    }


    /**
	 * 设置：支付类型
	 */
    public void setCanyinOrderPaymentTypes(Integer canyinOrderPaymentTypes) {
        this.canyinOrderPaymentTypes = canyinOrderPaymentTypes;
    }
    /**
	 * 获取：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：订单创建时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间 show3
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show3
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
