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
 * 旅游路线
 *
 * @author 
 * @email
 */
@TableName("lvyouluxian")
public class LvyouluxianEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public LvyouluxianEntity() {

	}

	public LvyouluxianEntity(T t) {
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
     * 旅游路线名称
     */
    @TableField(value = "lvyouluxian_name")

    private String lvyouluxianName;


    /**
     * 旅游路线编号
     */
    @TableField(value = "lvyouluxian_uuid_number")

    private String lvyouluxianUuidNumber;


    /**
     * 旅游路线照片
     */
    @TableField(value = "lvyouluxian_photo")

    private String lvyouluxianPhoto;


    /**
     * 旅游路线
     */
    @TableField(value = "lvyouluxian_luxian")

    private String lvyouluxianLuxian;


    /**
     * 旅游路线类型
     */
    @TableField(value = "lvyouluxian_types")

    private Integer lvyouluxianTypes;


    /**
     * 旅游路线热度
     */
    @TableField(value = "lvyouluxian_clicknum")

    private Integer lvyouluxianClicknum;


    /**
     * 旅游路线详细内容
     */
    @TableField(value = "lvyouluxian_content")

    private String lvyouluxianContent;


    /**
     * 是否上架
     */
    @TableField(value = "shangxia_types")

    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */
    @TableField(value = "lvyouluxian_delete")

    private Integer lvyouluxianDelete;


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
	 * 设置：旅游路线名称
	 */
    public String getLvyouluxianName() {
        return lvyouluxianName;
    }
    /**
	 * 获取：旅游路线名称
	 */

    public void setLvyouluxianName(String lvyouluxianName) {
        this.lvyouluxianName = lvyouluxianName;
    }
    /**
	 * 设置：旅游路线编号
	 */
    public String getLvyouluxianUuidNumber() {
        return lvyouluxianUuidNumber;
    }
    /**
	 * 获取：旅游路线编号
	 */

    public void setLvyouluxianUuidNumber(String lvyouluxianUuidNumber) {
        this.lvyouluxianUuidNumber = lvyouluxianUuidNumber;
    }
    /**
	 * 设置：旅游路线照片
	 */
    public String getLvyouluxianPhoto() {
        return lvyouluxianPhoto;
    }
    /**
	 * 获取：旅游路线照片
	 */

    public void setLvyouluxianPhoto(String lvyouluxianPhoto) {
        this.lvyouluxianPhoto = lvyouluxianPhoto;
    }
    /**
	 * 设置：旅游路线
	 */
    public String getLvyouluxianLuxian() {
        return lvyouluxianLuxian;
    }
    /**
	 * 获取：旅游路线
	 */

    public void setLvyouluxianLuxian(String lvyouluxianLuxian) {
        this.lvyouluxianLuxian = lvyouluxianLuxian;
    }
    /**
	 * 设置：旅游路线类型
	 */
    public Integer getLvyouluxianTypes() {
        return lvyouluxianTypes;
    }
    /**
	 * 获取：旅游路线类型
	 */

    public void setLvyouluxianTypes(Integer lvyouluxianTypes) {
        this.lvyouluxianTypes = lvyouluxianTypes;
    }
    /**
	 * 设置：旅游路线热度
	 */
    public Integer getLvyouluxianClicknum() {
        return lvyouluxianClicknum;
    }
    /**
	 * 获取：旅游路线热度
	 */

    public void setLvyouluxianClicknum(Integer lvyouluxianClicknum) {
        this.lvyouluxianClicknum = lvyouluxianClicknum;
    }
    /**
	 * 设置：旅游路线详细内容
	 */
    public String getLvyouluxianContent() {
        return lvyouluxianContent;
    }
    /**
	 * 获取：旅游路线详细内容
	 */

    public void setLvyouluxianContent(String lvyouluxianContent) {
        this.lvyouluxianContent = lvyouluxianContent;
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
    public Integer getLvyouluxianDelete() {
        return lvyouluxianDelete;
    }
    /**
	 * 获取：逻辑删除
	 */

    public void setLvyouluxianDelete(Integer lvyouluxianDelete) {
        this.lvyouluxianDelete = lvyouluxianDelete;
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
        return "Lvyouluxian{" +
            "id=" + id +
            ", lvyouluxianName=" + lvyouluxianName +
            ", lvyouluxianUuidNumber=" + lvyouluxianUuidNumber +
            ", lvyouluxianPhoto=" + lvyouluxianPhoto +
            ", lvyouluxianLuxian=" + lvyouluxianLuxian +
            ", lvyouluxianTypes=" + lvyouluxianTypes +
            ", lvyouluxianClicknum=" + lvyouluxianClicknum +
            ", lvyouluxianContent=" + lvyouluxianContent +
            ", shangxiaTypes=" + shangxiaTypes +
            ", lvyouluxianDelete=" + lvyouluxianDelete +
            ", createTime=" + createTime +
        "}";
    }
}
