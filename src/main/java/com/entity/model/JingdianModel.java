package com.entity.model;

import com.entity.JingdianEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 景点
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class JingdianModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 景点名称
     */
    private String jingdianName;


    /**
     * 景点编号
     */
    private String jingdianUuidNumber;


    /**
     * 景点照片
     */
    private String jingdianPhoto;


    /**
     * 景点位置
     */
    private String jingdianAddress;


    /**
     * 景点类型
     */
    private Integer jingdianTypes;


    /**
     * 景点星级
     */
    private Integer jingdianXingjiTypes;


    /**
     * 景点门票价
     */
    private Double jingdianNewMoney;


    /**
     * 景点热度
     */
    private Integer jingdianClicknum;


    /**
     * 景点详细介绍
     */
    private String jingdianContent;


    /**
     * 是否上架
     */
    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */
    private Integer jingdianDelete;


    /**
     * 创建时间  show1 show2 photoShow
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
	 * 获取：景点名称
	 */
    public String getJingdianName() {
        return jingdianName;
    }


    /**
	 * 设置：景点名称
	 */
    public void setJingdianName(String jingdianName) {
        this.jingdianName = jingdianName;
    }
    /**
	 * 获取：景点编号
	 */
    public String getJingdianUuidNumber() {
        return jingdianUuidNumber;
    }


    /**
	 * 设置：景点编号
	 */
    public void setJingdianUuidNumber(String jingdianUuidNumber) {
        this.jingdianUuidNumber = jingdianUuidNumber;
    }
    /**
	 * 获取：景点照片
	 */
    public String getJingdianPhoto() {
        return jingdianPhoto;
    }


    /**
	 * 设置：景点照片
	 */
    public void setJingdianPhoto(String jingdianPhoto) {
        this.jingdianPhoto = jingdianPhoto;
    }
    /**
	 * 获取：景点位置
	 */
    public String getJingdianAddress() {
        return jingdianAddress;
    }


    /**
	 * 设置：景点位置
	 */
    public void setJingdianAddress(String jingdianAddress) {
        this.jingdianAddress = jingdianAddress;
    }
    /**
	 * 获取：景点类型
	 */
    public Integer getJingdianTypes() {
        return jingdianTypes;
    }


    /**
	 * 设置：景点类型
	 */
    public void setJingdianTypes(Integer jingdianTypes) {
        this.jingdianTypes = jingdianTypes;
    }
    /**
	 * 获取：景点星级
	 */
    public Integer getJingdianXingjiTypes() {
        return jingdianXingjiTypes;
    }


    /**
	 * 设置：景点星级
	 */
    public void setJingdianXingjiTypes(Integer jingdianXingjiTypes) {
        this.jingdianXingjiTypes = jingdianXingjiTypes;
    }
    /**
	 * 获取：景点门票价
	 */
    public Double getJingdianNewMoney() {
        return jingdianNewMoney;
    }


    /**
	 * 设置：景点门票价
	 */
    public void setJingdianNewMoney(Double jingdianNewMoney) {
        this.jingdianNewMoney = jingdianNewMoney;
    }
    /**
	 * 获取：景点热度
	 */
    public Integer getJingdianClicknum() {
        return jingdianClicknum;
    }


    /**
	 * 设置：景点热度
	 */
    public void setJingdianClicknum(Integer jingdianClicknum) {
        this.jingdianClicknum = jingdianClicknum;
    }
    /**
	 * 获取：景点详细介绍
	 */
    public String getJingdianContent() {
        return jingdianContent;
    }


    /**
	 * 设置：景点详细介绍
	 */
    public void setJingdianContent(String jingdianContent) {
        this.jingdianContent = jingdianContent;
    }
    /**
	 * 获取：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }


    /**
	 * 设置：是否上架
	 */
    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getJingdianDelete() {
        return jingdianDelete;
    }


    /**
	 * 设置：逻辑删除
	 */
    public void setJingdianDelete(Integer jingdianDelete) {
        this.jingdianDelete = jingdianDelete;
    }
    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
