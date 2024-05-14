package com.entity.model;

import com.entity.CanyinEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 餐饮
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class CanyinModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 餐饮名称
     */
    private String canyinName;


    /**
     * 餐饮编号
     */
    private String canyinUuidNumber;


    /**
     * 餐饮照片
     */
    private String canyinPhoto;


    /**
     * 餐饮类型
     */
    private Integer canyinTypes;


    /**
     * 餐饮库存
     */
    private Integer canyinKucunNumber;


    /**
     * 购买获得积分
     */
    private Integer canyinPrice;


    /**
     * 餐饮原价
     */
    private Double canyinOldMoney;


    /**
     * 现价
     */
    private Double canyinNewMoney;


    /**
     * 热度
     */
    private Integer canyinClicknum;


    /**
     * 餐饮介绍
     */
    private String canyinContent;


    /**
     * 是否上架
     */
    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */
    private Integer canyinDelete;


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
	 * 获取：餐饮名称
	 */
    public String getCanyinName() {
        return canyinName;
    }


    /**
	 * 设置：餐饮名称
	 */
    public void setCanyinName(String canyinName) {
        this.canyinName = canyinName;
    }
    /**
	 * 获取：餐饮编号
	 */
    public String getCanyinUuidNumber() {
        return canyinUuidNumber;
    }


    /**
	 * 设置：餐饮编号
	 */
    public void setCanyinUuidNumber(String canyinUuidNumber) {
        this.canyinUuidNumber = canyinUuidNumber;
    }
    /**
	 * 获取：餐饮照片
	 */
    public String getCanyinPhoto() {
        return canyinPhoto;
    }


    /**
	 * 设置：餐饮照片
	 */
    public void setCanyinPhoto(String canyinPhoto) {
        this.canyinPhoto = canyinPhoto;
    }
    /**
	 * 获取：餐饮类型
	 */
    public Integer getCanyinTypes() {
        return canyinTypes;
    }


    /**
	 * 设置：餐饮类型
	 */
    public void setCanyinTypes(Integer canyinTypes) {
        this.canyinTypes = canyinTypes;
    }
    /**
	 * 获取：餐饮库存
	 */
    public Integer getCanyinKucunNumber() {
        return canyinKucunNumber;
    }


    /**
	 * 设置：餐饮库存
	 */
    public void setCanyinKucunNumber(Integer canyinKucunNumber) {
        this.canyinKucunNumber = canyinKucunNumber;
    }
    /**
	 * 获取：购买获得积分
	 */
    public Integer getCanyinPrice() {
        return canyinPrice;
    }


    /**
	 * 设置：购买获得积分
	 */
    public void setCanyinPrice(Integer canyinPrice) {
        this.canyinPrice = canyinPrice;
    }
    /**
	 * 获取：餐饮原价
	 */
    public Double getCanyinOldMoney() {
        return canyinOldMoney;
    }


    /**
	 * 设置：餐饮原价
	 */
    public void setCanyinOldMoney(Double canyinOldMoney) {
        this.canyinOldMoney = canyinOldMoney;
    }
    /**
	 * 获取：现价
	 */
    public Double getCanyinNewMoney() {
        return canyinNewMoney;
    }


    /**
	 * 设置：现价
	 */
    public void setCanyinNewMoney(Double canyinNewMoney) {
        this.canyinNewMoney = canyinNewMoney;
    }
    /**
	 * 获取：热度
	 */
    public Integer getCanyinClicknum() {
        return canyinClicknum;
    }


    /**
	 * 设置：热度
	 */
    public void setCanyinClicknum(Integer canyinClicknum) {
        this.canyinClicknum = canyinClicknum;
    }
    /**
	 * 获取：餐饮介绍
	 */
    public String getCanyinContent() {
        return canyinContent;
    }


    /**
	 * 设置：餐饮介绍
	 */
    public void setCanyinContent(String canyinContent) {
        this.canyinContent = canyinContent;
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
    public Integer getCanyinDelete() {
        return canyinDelete;
    }


    /**
	 * 设置：逻辑删除
	 */
    public void setCanyinDelete(Integer canyinDelete) {
        this.canyinDelete = canyinDelete;
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
