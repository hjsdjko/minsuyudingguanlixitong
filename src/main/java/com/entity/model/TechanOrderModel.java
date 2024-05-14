package com.entity.model;

import com.entity.TechanOrderEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 特产订单
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class TechanOrderModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 订单号
     */
    private String techanOrderUuidNumber;


    /**
     * 收货地址
     */
    private Integer addressId;


    /**
     * 特产
     */
    private Integer techanId;


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
    private Double techanOrderTruePrice;


    /**
     * 快递公司
     */
    private String techanOrderCourierName;


    /**
     * 订单快递单号
     */
    private String techanOrderCourierNumber;


    /**
     * 订单类型
     */
    private Integer techanOrderTypes;


    /**
     * 支付类型
     */
    private Integer techanOrderPaymentTypes;


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
	 * 获取：订单号
	 */
    public String getTechanOrderUuidNumber() {
        return techanOrderUuidNumber;
    }


    /**
	 * 设置：订单号
	 */
    public void setTechanOrderUuidNumber(String techanOrderUuidNumber) {
        this.techanOrderUuidNumber = techanOrderUuidNumber;
    }
    /**
	 * 获取：收货地址
	 */
    public Integer getAddressId() {
        return addressId;
    }


    /**
	 * 设置：收货地址
	 */
    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }
    /**
	 * 获取：特产
	 */
    public Integer getTechanId() {
        return techanId;
    }


    /**
	 * 设置：特产
	 */
    public void setTechanId(Integer techanId) {
        this.techanId = techanId;
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
    public Double getTechanOrderTruePrice() {
        return techanOrderTruePrice;
    }


    /**
	 * 设置：实付价格
	 */
    public void setTechanOrderTruePrice(Double techanOrderTruePrice) {
        this.techanOrderTruePrice = techanOrderTruePrice;
    }
    /**
	 * 获取：快递公司
	 */
    public String getTechanOrderCourierName() {
        return techanOrderCourierName;
    }


    /**
	 * 设置：快递公司
	 */
    public void setTechanOrderCourierName(String techanOrderCourierName) {
        this.techanOrderCourierName = techanOrderCourierName;
    }
    /**
	 * 获取：订单快递单号
	 */
    public String getTechanOrderCourierNumber() {
        return techanOrderCourierNumber;
    }


    /**
	 * 设置：订单快递单号
	 */
    public void setTechanOrderCourierNumber(String techanOrderCourierNumber) {
        this.techanOrderCourierNumber = techanOrderCourierNumber;
    }
    /**
	 * 获取：订单类型
	 */
    public Integer getTechanOrderTypes() {
        return techanOrderTypes;
    }


    /**
	 * 设置：订单类型
	 */
    public void setTechanOrderTypes(Integer techanOrderTypes) {
        this.techanOrderTypes = techanOrderTypes;
    }
    /**
	 * 获取：支付类型
	 */
    public Integer getTechanOrderPaymentTypes() {
        return techanOrderPaymentTypes;
    }


    /**
	 * 设置：支付类型
	 */
    public void setTechanOrderPaymentTypes(Integer techanOrderPaymentTypes) {
        this.techanOrderPaymentTypes = techanOrderPaymentTypes;
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
