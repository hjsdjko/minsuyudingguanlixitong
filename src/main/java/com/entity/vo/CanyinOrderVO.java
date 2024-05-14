package com.entity.vo;

import com.entity.CanyinOrderEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 餐饮订单
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("canyin_order")
public class CanyinOrderVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 餐饮订单号
     */

    @TableField(value = "canyin_order_uuid_number")
    private String canyinOrderUuidNumber;


    /**
     * 餐饮
     */

    @TableField(value = "canyin_id")
    private Integer canyinId;


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

    @TableField(value = "canyin_order_true_price")
    private Double canyinOrderTruePrice;


    /**
     * 派送人
     */

    @TableField(value = "canyin_order_courier_name")
    private String canyinOrderCourierName;


    /**
     * 派送人联系方式
     */

    @TableField(value = "canyin_order_courier_number")
    private String canyinOrderCourierNumber;


    /**
     * 订单类型
     */

    @TableField(value = "canyin_order_types")
    private Integer canyinOrderTypes;


    /**
     * 支付类型
     */

    @TableField(value = "canyin_order_payment_types")
    private Integer canyinOrderPaymentTypes;


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
	 * 设置：餐饮订单号
	 */
    public String getCanyinOrderUuidNumber() {
        return canyinOrderUuidNumber;
    }


    /**
	 * 获取：餐饮订单号
	 */

    public void setCanyinOrderUuidNumber(String canyinOrderUuidNumber) {
        this.canyinOrderUuidNumber = canyinOrderUuidNumber;
    }
    /**
	 * 设置：餐饮
	 */
    public Integer getCanyinId() {
        return canyinId;
    }


    /**
	 * 获取：餐饮
	 */

    public void setCanyinId(Integer canyinId) {
        this.canyinId = canyinId;
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
    public Double getCanyinOrderTruePrice() {
        return canyinOrderTruePrice;
    }


    /**
	 * 获取：实付价格
	 */

    public void setCanyinOrderTruePrice(Double canyinOrderTruePrice) {
        this.canyinOrderTruePrice = canyinOrderTruePrice;
    }
    /**
	 * 设置：派送人
	 */
    public String getCanyinOrderCourierName() {
        return canyinOrderCourierName;
    }


    /**
	 * 获取：派送人
	 */

    public void setCanyinOrderCourierName(String canyinOrderCourierName) {
        this.canyinOrderCourierName = canyinOrderCourierName;
    }
    /**
	 * 设置：派送人联系方式
	 */
    public String getCanyinOrderCourierNumber() {
        return canyinOrderCourierNumber;
    }


    /**
	 * 获取：派送人联系方式
	 */

    public void setCanyinOrderCourierNumber(String canyinOrderCourierNumber) {
        this.canyinOrderCourierNumber = canyinOrderCourierNumber;
    }
    /**
	 * 设置：订单类型
	 */
    public Integer getCanyinOrderTypes() {
        return canyinOrderTypes;
    }


    /**
	 * 获取：订单类型
	 */

    public void setCanyinOrderTypes(Integer canyinOrderTypes) {
        this.canyinOrderTypes = canyinOrderTypes;
    }
    /**
	 * 设置：支付类型
	 */
    public Integer getCanyinOrderPaymentTypes() {
        return canyinOrderPaymentTypes;
    }


    /**
	 * 获取：支付类型
	 */

    public void setCanyinOrderPaymentTypes(Integer canyinOrderPaymentTypes) {
        this.canyinOrderPaymentTypes = canyinOrderPaymentTypes;
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
