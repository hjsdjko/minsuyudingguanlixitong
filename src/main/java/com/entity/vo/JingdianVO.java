package com.entity.vo;

import com.entity.JingdianEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 景点
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("jingdian")
public class JingdianVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 景点名称
     */

    @TableField(value = "jingdian_name")
    private String jingdianName;


    /**
     * 景点编号
     */

    @TableField(value = "jingdian_uuid_number")
    private String jingdianUuidNumber;


    /**
     * 景点照片
     */

    @TableField(value = "jingdian_photo")
    private String jingdianPhoto;


    /**
     * 景点位置
     */

    @TableField(value = "jingdian_address")
    private String jingdianAddress;


    /**
     * 景点类型
     */

    @TableField(value = "jingdian_types")
    private Integer jingdianTypes;


    /**
     * 景点星级
     */

    @TableField(value = "jingdian_xingji_types")
    private Integer jingdianXingjiTypes;


    /**
     * 景点门票价
     */

    @TableField(value = "jingdian_new_money")
    private Double jingdianNewMoney;


    /**
     * 景点热度
     */

    @TableField(value = "jingdian_clicknum")
    private Integer jingdianClicknum;


    /**
     * 景点详细介绍
     */

    @TableField(value = "jingdian_content")
    private String jingdianContent;


    /**
     * 是否上架
     */

    @TableField(value = "shangxia_types")
    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */

    @TableField(value = "jingdian_delete")
    private Integer jingdianDelete;


    /**
     * 创建时间  show1 show2 photoShow
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
	 * 设置：景点名称
	 */
    public String getJingdianName() {
        return jingdianName;
    }


    /**
	 * 获取：景点名称
	 */

    public void setJingdianName(String jingdianName) {
        this.jingdianName = jingdianName;
    }
    /**
	 * 设置：景点编号
	 */
    public String getJingdianUuidNumber() {
        return jingdianUuidNumber;
    }


    /**
	 * 获取：景点编号
	 */

    public void setJingdianUuidNumber(String jingdianUuidNumber) {
        this.jingdianUuidNumber = jingdianUuidNumber;
    }
    /**
	 * 设置：景点照片
	 */
    public String getJingdianPhoto() {
        return jingdianPhoto;
    }


    /**
	 * 获取：景点照片
	 */

    public void setJingdianPhoto(String jingdianPhoto) {
        this.jingdianPhoto = jingdianPhoto;
    }
    /**
	 * 设置：景点位置
	 */
    public String getJingdianAddress() {
        return jingdianAddress;
    }


    /**
	 * 获取：景点位置
	 */

    public void setJingdianAddress(String jingdianAddress) {
        this.jingdianAddress = jingdianAddress;
    }
    /**
	 * 设置：景点类型
	 */
    public Integer getJingdianTypes() {
        return jingdianTypes;
    }


    /**
	 * 获取：景点类型
	 */

    public void setJingdianTypes(Integer jingdianTypes) {
        this.jingdianTypes = jingdianTypes;
    }
    /**
	 * 设置：景点星级
	 */
    public Integer getJingdianXingjiTypes() {
        return jingdianXingjiTypes;
    }


    /**
	 * 获取：景点星级
	 */

    public void setJingdianXingjiTypes(Integer jingdianXingjiTypes) {
        this.jingdianXingjiTypes = jingdianXingjiTypes;
    }
    /**
	 * 设置：景点门票价
	 */
    public Double getJingdianNewMoney() {
        return jingdianNewMoney;
    }


    /**
	 * 获取：景点门票价
	 */

    public void setJingdianNewMoney(Double jingdianNewMoney) {
        this.jingdianNewMoney = jingdianNewMoney;
    }
    /**
	 * 设置：景点热度
	 */
    public Integer getJingdianClicknum() {
        return jingdianClicknum;
    }


    /**
	 * 获取：景点热度
	 */

    public void setJingdianClicknum(Integer jingdianClicknum) {
        this.jingdianClicknum = jingdianClicknum;
    }
    /**
	 * 设置：景点详细介绍
	 */
    public String getJingdianContent() {
        return jingdianContent;
    }


    /**
	 * 获取：景点详细介绍
	 */

    public void setJingdianContent(String jingdianContent) {
        this.jingdianContent = jingdianContent;
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
    public Integer getJingdianDelete() {
        return jingdianDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setJingdianDelete(Integer jingdianDelete) {
        this.jingdianDelete = jingdianDelete;
    }
    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
