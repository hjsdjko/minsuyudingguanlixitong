package com.entity.vo;

import com.entity.TechanOrderEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 特产订单
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("techan_order")
public class TechanOrderVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 订单号
     */

    @TableField(value = "techan_order_uuid_number")
    private String techanOrderUuidNumber;


    /**
     * 收货地址
     */

    @TableField(value = "address_id")
    private Integer addressId;


    /**
     * 特产
     */

    @TableField(value = "techan_id")
    private Integer techanId;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 购买数量
     */

    @TableField(value = "buy_number")
    private Integer buyNumber;


    /**
     * 实付价格
     */

    @TableField(value = "techan_order_true_price")
    private Double techanOrderTruePrice;


    /**
     * 快递公司
     */

    @TableField(value = "techan_order_courier_name")
    private String techanOrderCourierName;


    /**
     * 订单快递单号
     */

    @TableField(value = "techan_order_courier_number")
    private String techanOrderCourierNumber;


    /**
     * 订单类型
     */

    @TableField(value = "techan_order_types")
    private Integer techanOrderTypes;


    /**
     * 支付类型
     */

    @TableField(value = "techan_order_payment_types")
    private Integer techanOrderPaymentTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间 show3
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
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
    public String getTechanOrderUuidNumber() {
        return techanOrderUuidNumber;
    }


    /**
	 * 获取：订单号
	 */

    public void setTechanOrderUuidNumber(String techanOrderUuidNumber) {
        this.techanOrderUuidNumber = techanOrderUuidNumber;
    }
    /**
	 * 设置：收货地址
	 */
    public Integer getAddressId() {
        return addressId;
    }


    /**
	 * 获取：收货地址
	 */

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }
    /**
	 * 设置：特产
	 */
    public Integer getTechanId() {
        return techanId;
    }


    /**
	 * 获取：特产
	 */

    public void setTechanId(Integer techanId) {
        this.techanId = techanId;
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
	 * 设置：购买数量
	 */
    public Integer getBuyNumber() {
        return buyNumber;
    }


    /**
	 * 获取：购买数量
	 */

    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }
    /**
	 * 设置：实付价格
	 */
    public Double getTechanOrderTruePrice() {
        return techanOrderTruePrice;
    }


    /**
	 * 获取：实付价格
	 */

    public void setTechanOrderTruePrice(Double techanOrderTruePrice) {
        this.techanOrderTruePrice = techanOrderTruePrice;
    }
    /**
	 * 设置：快递公司
	 */
    public String getTechanOrderCourierName() {
        return techanOrderCourierName;
    }


    /**
	 * 获取：快递公司
	 */

    public void setTechanOrderCourierName(String techanOrderCourierName) {
        this.techanOrderCourierName = techanOrderCourierName;
    }
    /**
	 * 设置：订单快递单号
	 */
    public String getTechanOrderCourierNumber() {
        return techanOrderCourierNumber;
    }


    /**
	 * 获取：订单快递单号
	 */

    public void setTechanOrderCourierNumber(String techanOrderCourierNumber) {
        this.techanOrderCourierNumber = techanOrderCourierNumber;
    }
    /**
	 * 设置：订单类型
	 */
    public Integer getTechanOrderTypes() {
        return techanOrderTypes;
    }


    /**
	 * 获取：订单类型
	 */

    public void setTechanOrderTypes(Integer techanOrderTypes) {
        this.techanOrderTypes = techanOrderTypes;
    }
    /**
	 * 设置：支付类型
	 */
    public Integer getTechanOrderPaymentTypes() {
        return techanOrderPaymentTypes;
    }


    /**
	 * 获取：支付类型
	 */

    public void setTechanOrderPaymentTypes(Integer techanOrderPaymentTypes) {
        this.techanOrderPaymentTypes = techanOrderPaymentTypes;
    }
    /**
	 * 设置：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：订单创建时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间 show3
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show3
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
