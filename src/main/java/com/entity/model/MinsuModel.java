package com.entity.model;

import com.entity.MinsuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 民宿
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class MinsuModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 民宿名称
     */
    private String minsuName;


    /**
     * 民宿编号
     */
    private String minsuUuidNumber;


    /**
     * 民宿照片
     */
    private String minsuPhoto;


    /**
     * 民宿特色
     */
    private String minsuTese;


    /**
     * 民宿类型
     */
    private Integer minsuTypes;


    /**
     * 租赁获得积分
     */
    private Integer minsuPrice;


    /**
     * 民宿原价
     */
    private Double minsuOldMoney;


    /**
     * 现价/天
     */
    private Double minsuNewMoney;


    /**
     * 房间热度
     */
    private Integer minsuClicknum;


    /**
     * 房间介绍
     */
    private String minsuContent;


    /**
     * 是否上架
     */
    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */
    private Integer minsuDelete;


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
	 * 获取：民宿名称
	 */
    public String getMinsuName() {
        return minsuName;
    }


    /**
	 * 设置：民宿名称
	 */
    public void setMinsuName(String minsuName) {
        this.minsuName = minsuName;
    }
    /**
	 * 获取：民宿编号
	 */
    public String getMinsuUuidNumber() {
        return minsuUuidNumber;
    }


    /**
	 * 设置：民宿编号
	 */
    public void setMinsuUuidNumber(String minsuUuidNumber) {
        this.minsuUuidNumber = minsuUuidNumber;
    }
    /**
	 * 获取：民宿照片
	 */
    public String getMinsuPhoto() {
        return minsuPhoto;
    }


    /**
	 * 设置：民宿照片
	 */
    public void setMinsuPhoto(String minsuPhoto) {
        this.minsuPhoto = minsuPhoto;
    }
    /**
	 * 获取：民宿特色
	 */
    public String getMinsuTese() {
        return minsuTese;
    }


    /**
	 * 设置：民宿特色
	 */
    public void setMinsuTese(String minsuTese) {
        this.minsuTese = minsuTese;
    }
    /**
	 * 获取：民宿类型
	 */
    public Integer getMinsuTypes() {
        return minsuTypes;
    }


    /**
	 * 设置：民宿类型
	 */
    public void setMinsuTypes(Integer minsuTypes) {
        this.minsuTypes = minsuTypes;
    }
    /**
	 * 获取：租赁获得积分
	 */
    public Integer getMinsuPrice() {
        return minsuPrice;
    }


    /**
	 * 设置：租赁获得积分
	 */
    public void setMinsuPrice(Integer minsuPrice) {
        this.minsuPrice = minsuPrice;
    }
    /**
	 * 获取：民宿原价
	 */
    public Double getMinsuOldMoney() {
        return minsuOldMoney;
    }


    /**
	 * 设置：民宿原价
	 */
    public void setMinsuOldMoney(Double minsuOldMoney) {
        this.minsuOldMoney = minsuOldMoney;
    }
    /**
	 * 获取：现价/天
	 */
    public Double getMinsuNewMoney() {
        return minsuNewMoney;
    }


    /**
	 * 设置：现价/天
	 */
    public void setMinsuNewMoney(Double minsuNewMoney) {
        this.minsuNewMoney = minsuNewMoney;
    }
    /**
	 * 获取：房间热度
	 */
    public Integer getMinsuClicknum() {
        return minsuClicknum;
    }


    /**
	 * 设置：房间热度
	 */
    public void setMinsuClicknum(Integer minsuClicknum) {
        this.minsuClicknum = minsuClicknum;
    }
    /**
	 * 获取：房间介绍
	 */
    public String getMinsuContent() {
        return minsuContent;
    }


    /**
	 * 设置：房间介绍
	 */
    public void setMinsuContent(String minsuContent) {
        this.minsuContent = minsuContent;
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
    public Integer getMinsuDelete() {
        return minsuDelete;
    }


    /**
	 * 设置：逻辑删除
	 */
    public void setMinsuDelete(Integer minsuDelete) {
        this.minsuDelete = minsuDelete;
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
