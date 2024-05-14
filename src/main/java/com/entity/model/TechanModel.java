package com.entity.model;

import com.entity.TechanEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 特产
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class TechanModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 特产名称
     */
    private String techanName;


    /**
     * 特产编号
     */
    private String techanUuidNumber;


    /**
     * 所属景点
     */
    private String jingdianName;


    /**
     * 景点位置
     */
    private String jingdianAddress;


    /**
     * 特产照片
     */
    private String techanPhoto;


    /**
     * 特产类型
     */
    private Integer techanTypes;


    /**
     * 特产库存
     */
    private Integer techanKucunNumber;


    /**
     * 购买获得积分
     */
    private Integer techanPrice;


    /**
     * 特产原价
     */
    private Double techanOldMoney;


    /**
     * 现价
     */
    private Double techanNewMoney;


    /**
     * 特产热度
     */
    private Integer techanClicknum;


    /**
     * 特产介绍
     */
    private String techanContent;


    /**
     * 是否上架
     */
    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */
    private Integer techanDelete;


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
	 * 获取：特产名称
	 */
    public String getTechanName() {
        return techanName;
    }


    /**
	 * 设置：特产名称
	 */
    public void setTechanName(String techanName) {
        this.techanName = techanName;
    }
    /**
	 * 获取：特产编号
	 */
    public String getTechanUuidNumber() {
        return techanUuidNumber;
    }


    /**
	 * 设置：特产编号
	 */
    public void setTechanUuidNumber(String techanUuidNumber) {
        this.techanUuidNumber = techanUuidNumber;
    }
    /**
	 * 获取：所属景点
	 */
    public String getJingdianName() {
        return jingdianName;
    }


    /**
	 * 设置：所属景点
	 */
    public void setJingdianName(String jingdianName) {
        this.jingdianName = jingdianName;
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
	 * 获取：特产照片
	 */
    public String getTechanPhoto() {
        return techanPhoto;
    }


    /**
	 * 设置：特产照片
	 */
    public void setTechanPhoto(String techanPhoto) {
        this.techanPhoto = techanPhoto;
    }
    /**
	 * 获取：特产类型
	 */
    public Integer getTechanTypes() {
        return techanTypes;
    }


    /**
	 * 设置：特产类型
	 */
    public void setTechanTypes(Integer techanTypes) {
        this.techanTypes = techanTypes;
    }
    /**
	 * 获取：特产库存
	 */
    public Integer getTechanKucunNumber() {
        return techanKucunNumber;
    }


    /**
	 * 设置：特产库存
	 */
    public void setTechanKucunNumber(Integer techanKucunNumber) {
        this.techanKucunNumber = techanKucunNumber;
    }
    /**
	 * 获取：购买获得积分
	 */
    public Integer getTechanPrice() {
        return techanPrice;
    }


    /**
	 * 设置：购买获得积分
	 */
    public void setTechanPrice(Integer techanPrice) {
        this.techanPrice = techanPrice;
    }
    /**
	 * 获取：特产原价
	 */
    public Double getTechanOldMoney() {
        return techanOldMoney;
    }


    /**
	 * 设置：特产原价
	 */
    public void setTechanOldMoney(Double techanOldMoney) {
        this.techanOldMoney = techanOldMoney;
    }
    /**
	 * 获取：现价
	 */
    public Double getTechanNewMoney() {
        return techanNewMoney;
    }


    /**
	 * 设置：现价
	 */
    public void setTechanNewMoney(Double techanNewMoney) {
        this.techanNewMoney = techanNewMoney;
    }
    /**
	 * 获取：特产热度
	 */
    public Integer getTechanClicknum() {
        return techanClicknum;
    }


    /**
	 * 设置：特产热度
	 */
    public void setTechanClicknum(Integer techanClicknum) {
        this.techanClicknum = techanClicknum;
    }
    /**
	 * 获取：特产介绍
	 */
    public String getTechanContent() {
        return techanContent;
    }


    /**
	 * 设置：特产介绍
	 */
    public void setTechanContent(String techanContent) {
        this.techanContent = techanContent;
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
    public Integer getTechanDelete() {
        return techanDelete;
    }


    /**
	 * 设置：逻辑删除
	 */
    public void setTechanDelete(Integer techanDelete) {
        this.techanDelete = techanDelete;
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
