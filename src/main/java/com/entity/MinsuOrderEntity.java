package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 民宿订单
 *
 * @author 
 * @email
 */
@TableName("minsu_order")
public class MinsuOrderEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public MinsuOrderEntity() {

	}

	public MinsuOrderEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;


    /**
     * 订单号
     */
    @TableField(value = "minsu_order_uuid_number")

    private String minsuOrderUuidNumber;


    /**
     * 民宿
     */
    @TableField(value = "minsu_id")

    private Integer minsuId;


    /**
     * 用户
     */
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 入住日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@DateTimeFormat
    @TableField(value = "ruzhu_time")

    private Date ruzhuTime;


    /**
     * 预定天数
     */
    @TableField(value = "buy_number")

    private Integer buyNumber;


    /**
     * 实付价格
     */
    @TableField(value = "minsu_order_true_price")

    private Double minsuOrderTruePrice;


    /**
     * 订单类型
     */
    @TableField(value = "minsu_order_types")

    private Integer minsuOrderTypes;


    /**
     * 支付类型
     */
    @TableField(value = "minsu_order_payment_types")

    private Integer minsuOrderPaymentTypes;


    /**
     * 预定时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }
    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：订单号
	 */
    public String getMinsuOrderUuidNumber() {
        return minsuOrderUuidNumber;
    }
    /**
	 * 获取：订单号
	 */

    public void setMinsuOrderUuidNumber(String minsuOrderUuidNumber) {
        this.minsuOrderUuidNumber = minsuOrderUuidNumber;
    }
    /**
	 * 设置：民宿
	 */
    public Integer getMinsuId() {
        return minsuId;
    }
    /**
	 * 获取：民宿
	 */

    public void setMinsuId(Integer minsuId) {
        this.minsuId = minsuId;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }
    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：入住日期
	 */
    public Date getRuzhuTime() {
        return ruzhuTime;
    }
    /**
	 * 获取：入住日期
	 */

    public void setRuzhuTime(Date ruzhuTime) {
        this.ruzhuTime = ruzhuTime;
    }
    /**
	 * 设置：预定天数
	 */
    public Integer getBuyNumber() {
        return buyNumber;
    }
    /**
	 * 获取：预定天数
	 */

    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }
    /**
	 * 设置：实付价格
	 */
    public Double getMinsuOrderTruePrice() {
        return minsuOrderTruePrice;
    }
    /**
	 * 获取：实付价格
	 */

    public void setMinsuOrderTruePrice(Double minsuOrderTruePrice) {
        this.minsuOrderTruePrice = minsuOrderTruePrice;
    }
    /**
	 * 设置：订单类型
	 */
    public Integer getMinsuOrderTypes() {
        return minsuOrderTypes;
    }
    /**
	 * 获取：订单类型
	 */

    public void setMinsuOrderTypes(Integer minsuOrderTypes) {
        this.minsuOrderTypes = minsuOrderTypes;
    }
    /**
	 * 设置：支付类型
	 */
    public Integer getMinsuOrderPaymentTypes() {
        return minsuOrderPaymentTypes;
    }
    /**
	 * 获取：支付类型
	 */

    public void setMinsuOrderPaymentTypes(Integer minsuOrderPaymentTypes) {
        this.minsuOrderPaymentTypes = minsuOrderPaymentTypes;
    }
    /**
	 * 设置：预定时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 获取：预定时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "MinsuOrder{" +
            "id=" + id +
            ", minsuOrderUuidNumber=" + minsuOrderUuidNumber +
            ", minsuId=" + minsuId +
            ", yonghuId=" + yonghuId +
            ", ruzhuTime=" + ruzhuTime +
            ", buyNumber=" + buyNumber +
            ", minsuOrderTruePrice=" + minsuOrderTruePrice +
            ", minsuOrderTypes=" + minsuOrderTypes +
            ", minsuOrderPaymentTypes=" + minsuOrderPaymentTypes +
            ", insertTime=" + insertTime +
            ", createTime=" + createTime +
        "}";
    }
}
