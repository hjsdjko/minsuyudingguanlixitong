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
 * 餐饮
 *
 * @author 
 * @email
 */
@TableName("canyin")
public class CanyinEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public CanyinEntity() {

	}

	public CanyinEntity(T t) {
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
     * 餐饮名称
     */
    @TableField(value = "canyin_name")

    private String canyinName;


    /**
     * 餐饮编号
     */
    @TableField(value = "canyin_uuid_number")

    private String canyinUuidNumber;


    /**
     * 餐饮照片
     */
    @TableField(value = "canyin_photo")

    private String canyinPhoto;


    /**
     * 餐饮类型
     */
    @TableField(value = "canyin_types")

    private Integer canyinTypes;


    /**
     * 餐饮库存
     */
    @TableField(value = "canyin_kucun_number")

    private Integer canyinKucunNumber;


    /**
     * 购买获得积分
     */
    @TableField(value = "canyin_price")

    private Integer canyinPrice;


    /**
     * 餐饮原价
     */
    @TableField(value = "canyin_old_money")

    private Double canyinOldMoney;


    /**
     * 现价
     */
    @TableField(value = "canyin_new_money")

    private Double canyinNewMoney;


    /**
     * 热度
     */
    @TableField(value = "canyin_clicknum")

    private Integer canyinClicknum;


    /**
     * 餐饮介绍
     */
    @TableField(value = "canyin_content")

    private String canyinContent;


    /**
     * 是否上架
     */
    @TableField(value = "shangxia_types")

    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */
    @TableField(value = "canyin_delete")

    private Integer canyinDelete;


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
	 * 设置：餐饮名称
	 */
    public String getCanyinName() {
        return canyinName;
    }
    /**
	 * 获取：餐饮名称
	 */

    public void setCanyinName(String canyinName) {
        this.canyinName = canyinName;
    }
    /**
	 * 设置：餐饮编号
	 */
    public String getCanyinUuidNumber() {
        return canyinUuidNumber;
    }
    /**
	 * 获取：餐饮编号
	 */

    public void setCanyinUuidNumber(String canyinUuidNumber) {
        this.canyinUuidNumber = canyinUuidNumber;
    }
    /**
	 * 设置：餐饮照片
	 */
    public String getCanyinPhoto() {
        return canyinPhoto;
    }
    /**
	 * 获取：餐饮照片
	 */

    public void setCanyinPhoto(String canyinPhoto) {
        this.canyinPhoto = canyinPhoto;
    }
    /**
	 * 设置：餐饮类型
	 */
    public Integer getCanyinTypes() {
        return canyinTypes;
    }
    /**
	 * 获取：餐饮类型
	 */

    public void setCanyinTypes(Integer canyinTypes) {
        this.canyinTypes = canyinTypes;
    }
    /**
	 * 设置：餐饮库存
	 */
    public Integer getCanyinKucunNumber() {
        return canyinKucunNumber;
    }
    /**
	 * 获取：餐饮库存
	 */

    public void setCanyinKucunNumber(Integer canyinKucunNumber) {
        this.canyinKucunNumber = canyinKucunNumber;
    }
    /**
	 * 设置：购买获得积分
	 */
    public Integer getCanyinPrice() {
        return canyinPrice;
    }
    /**
	 * 获取：购买获得积分
	 */

    public void setCanyinPrice(Integer canyinPrice) {
        this.canyinPrice = canyinPrice;
    }
    /**
	 * 设置：餐饮原价
	 */
    public Double getCanyinOldMoney() {
        return canyinOldMoney;
    }
    /**
	 * 获取：餐饮原价
	 */

    public void setCanyinOldMoney(Double canyinOldMoney) {
        this.canyinOldMoney = canyinOldMoney;
    }
    /**
	 * 设置：现价
	 */
    public Double getCanyinNewMoney() {
        return canyinNewMoney;
    }
    /**
	 * 获取：现价
	 */

    public void setCanyinNewMoney(Double canyinNewMoney) {
        this.canyinNewMoney = canyinNewMoney;
    }
    /**
	 * 设置：热度
	 */
    public Integer getCanyinClicknum() {
        return canyinClicknum;
    }
    /**
	 * 获取：热度
	 */

    public void setCanyinClicknum(Integer canyinClicknum) {
        this.canyinClicknum = canyinClicknum;
    }
    /**
	 * 设置：餐饮介绍
	 */
    public String getCanyinContent() {
        return canyinContent;
    }
    /**
	 * 获取：餐饮介绍
	 */

    public void setCanyinContent(String canyinContent) {
        this.canyinContent = canyinContent;
    }
    /**
	 * 设置：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }
    /**
	 * 获取：是否上架
	 */

    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getCanyinDelete() {
        return canyinDelete;
    }
    /**
	 * 获取：逻辑删除
	 */

    public void setCanyinDelete(Integer canyinDelete) {
        this.canyinDelete = canyinDelete;
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
        return "Canyin{" +
            "id=" + id +
            ", canyinName=" + canyinName +
            ", canyinUuidNumber=" + canyinUuidNumber +
            ", canyinPhoto=" + canyinPhoto +
            ", canyinTypes=" + canyinTypes +
            ", canyinKucunNumber=" + canyinKucunNumber +
            ", canyinPrice=" + canyinPrice +
            ", canyinOldMoney=" + canyinOldMoney +
            ", canyinNewMoney=" + canyinNewMoney +
            ", canyinClicknum=" + canyinClicknum +
            ", canyinContent=" + canyinContent +
            ", shangxiaTypes=" + shangxiaTypes +
            ", canyinDelete=" + canyinDelete +
            ", createTime=" + createTime +
        "}";
    }
}
