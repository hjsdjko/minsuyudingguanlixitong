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
 * 民宿
 *
 * @author 
 * @email
 */
@TableName("minsu")
public class MinsuEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public MinsuEntity() {

	}

	public MinsuEntity(T t) {
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
     * 民宿名称
     */
    @TableField(value = "minsu_name")

    private String minsuName;


    /**
     * 民宿编号
     */
    @TableField(value = "minsu_uuid_number")

    private String minsuUuidNumber;


    /**
     * 民宿照片
     */
    @TableField(value = "minsu_photo")

    private String minsuPhoto;


    /**
     * 民宿特色
     */
    @TableField(value = "minsu_tese")

    private String minsuTese;


    /**
     * 民宿类型
     */
    @TableField(value = "minsu_types")

    private Integer minsuTypes;


    /**
     * 租赁获得积分
     */
    @TableField(value = "minsu_price")

    private Integer minsuPrice;


    /**
     * 民宿原价
     */
    @TableField(value = "minsu_old_money")

    private Double minsuOldMoney;


    /**
     * 现价/天
     */
    @TableField(value = "minsu_new_money")

    private Double minsuNewMoney;


    /**
     * 房间热度
     */
    @TableField(value = "minsu_clicknum")

    private Integer minsuClicknum;


    /**
     * 房间介绍
     */
    @TableField(value = "minsu_content")

    private String minsuContent;


    /**
     * 是否上架
     */
    @TableField(value = "shangxia_types")

    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */
    @TableField(value = "minsu_delete")

    private Integer minsuDelete;


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
	 * 设置：民宿名称
	 */
    public String getMinsuName() {
        return minsuName;
    }
    /**
	 * 获取：民宿名称
	 */

    public void setMinsuName(String minsuName) {
        this.minsuName = minsuName;
    }
    /**
	 * 设置：民宿编号
	 */
    public String getMinsuUuidNumber() {
        return minsuUuidNumber;
    }
    /**
	 * 获取：民宿编号
	 */

    public void setMinsuUuidNumber(String minsuUuidNumber) {
        this.minsuUuidNumber = minsuUuidNumber;
    }
    /**
	 * 设置：民宿照片
	 */
    public String getMinsuPhoto() {
        return minsuPhoto;
    }
    /**
	 * 获取：民宿照片
	 */

    public void setMinsuPhoto(String minsuPhoto) {
        this.minsuPhoto = minsuPhoto;
    }
    /**
	 * 设置：民宿特色
	 */
    public String getMinsuTese() {
        return minsuTese;
    }
    /**
	 * 获取：民宿特色
	 */

    public void setMinsuTese(String minsuTese) {
        this.minsuTese = minsuTese;
    }
    /**
	 * 设置：民宿类型
	 */
    public Integer getMinsuTypes() {
        return minsuTypes;
    }
    /**
	 * 获取：民宿类型
	 */

    public void setMinsuTypes(Integer minsuTypes) {
        this.minsuTypes = minsuTypes;
    }
    /**
	 * 设置：租赁获得积分
	 */
    public Integer getMinsuPrice() {
        return minsuPrice;
    }
    /**
	 * 获取：租赁获得积分
	 */

    public void setMinsuPrice(Integer minsuPrice) {
        this.minsuPrice = minsuPrice;
    }
    /**
	 * 设置：民宿原价
	 */
    public Double getMinsuOldMoney() {
        return minsuOldMoney;
    }
    /**
	 * 获取：民宿原价
	 */

    public void setMinsuOldMoney(Double minsuOldMoney) {
        this.minsuOldMoney = minsuOldMoney;
    }
    /**
	 * 设置：现价/天
	 */
    public Double getMinsuNewMoney() {
        return minsuNewMoney;
    }
    /**
	 * 获取：现价/天
	 */

    public void setMinsuNewMoney(Double minsuNewMoney) {
        this.minsuNewMoney = minsuNewMoney;
    }
    /**
	 * 设置：房间热度
	 */
    public Integer getMinsuClicknum() {
        return minsuClicknum;
    }
    /**
	 * 获取：房间热度
	 */

    public void setMinsuClicknum(Integer minsuClicknum) {
        this.minsuClicknum = minsuClicknum;
    }
    /**
	 * 设置：房间介绍
	 */
    public String getMinsuContent() {
        return minsuContent;
    }
    /**
	 * 获取：房间介绍
	 */

    public void setMinsuContent(String minsuContent) {
        this.minsuContent = minsuContent;
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
    public Integer getMinsuDelete() {
        return minsuDelete;
    }
    /**
	 * 获取：逻辑删除
	 */

    public void setMinsuDelete(Integer minsuDelete) {
        this.minsuDelete = minsuDelete;
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
        return "Minsu{" +
            "id=" + id +
            ", minsuName=" + minsuName +
            ", minsuUuidNumber=" + minsuUuidNumber +
            ", minsuPhoto=" + minsuPhoto +
            ", minsuTese=" + minsuTese +
            ", minsuTypes=" + minsuTypes +
            ", minsuPrice=" + minsuPrice +
            ", minsuOldMoney=" + minsuOldMoney +
            ", minsuNewMoney=" + minsuNewMoney +
            ", minsuClicknum=" + minsuClicknum +
            ", minsuContent=" + minsuContent +
            ", shangxiaTypes=" + shangxiaTypes +
            ", minsuDelete=" + minsuDelete +
            ", createTime=" + createTime +
        "}";
    }
}
