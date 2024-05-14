package com.entity.view;

import com.entity.TechanCommentbackEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 特产评价
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("techan_commentback")
public class TechanCommentbackView extends TechanCommentbackEntity implements Serializable {
    private static final long serialVersionUID = 1L;




		//级联表 techan
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
				* 特产类型的值
				*/
				private String techanValue;
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
				* 是否上架的值
				*/
				private String shangxiaValue;
			/**
			* 逻辑删除
			*/
			private Integer techanDelete;

		//级联表 yonghu
			/**
			* 用户姓名
			*/
			private String yonghuName;
			/**
			* 用户手机号
			*/
			private String yonghuPhone;
			/**
			* 用户身份证号
			*/
			private String yonghuIdNumber;
			/**
			* 用户头像
			*/
			private String yonghuPhoto;
			/**
			* 电子邮箱
			*/
			private String yonghuEmail;
			/**
			* 余额
			*/
			private Double newMoney;
			/**
			* 总积分
			*/
			private Double yonghuSumJifen;
			/**
			* 现积分
			*/
			private Double yonghuNewJifen;
			/**
			* 会员等级
			*/
			private Integer huiyuandengjiTypes;
				/**
				* 会员等级的值
				*/
				private String huiyuandengjiValue;

	public TechanCommentbackView() {

	}

	public TechanCommentbackView(TechanCommentbackEntity techanCommentbackEntity) {
		try {
			BeanUtils.copyProperties(this, techanCommentbackEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


























































				//级联表的get和set techan

					/**
					* 获取： 特产名称
					*/
					public String getTechanName() {
						return techanName;
					}
					/**
					* 设置： 特产名称
					*/
					public void setTechanName(String techanName) {
						this.techanName = techanName;
					}

					/**
					* 获取： 特产编号
					*/
					public String getTechanUuidNumber() {
						return techanUuidNumber;
					}
					/**
					* 设置： 特产编号
					*/
					public void setTechanUuidNumber(String techanUuidNumber) {
						this.techanUuidNumber = techanUuidNumber;
					}

					/**
					* 获取： 所属景点
					*/
					public String getJingdianName() {
						return jingdianName;
					}
					/**
					* 设置： 所属景点
					*/
					public void setJingdianName(String jingdianName) {
						this.jingdianName = jingdianName;
					}

					/**
					* 获取： 景点位置
					*/
					public String getJingdianAddress() {
						return jingdianAddress;
					}
					/**
					* 设置： 景点位置
					*/
					public void setJingdianAddress(String jingdianAddress) {
						this.jingdianAddress = jingdianAddress;
					}

					/**
					* 获取： 特产照片
					*/
					public String getTechanPhoto() {
						return techanPhoto;
					}
					/**
					* 设置： 特产照片
					*/
					public void setTechanPhoto(String techanPhoto) {
						this.techanPhoto = techanPhoto;
					}

					/**
					* 获取： 特产类型
					*/
					public Integer getTechanTypes() {
						return techanTypes;
					}
					/**
					* 设置： 特产类型
					*/
					public void setTechanTypes(Integer techanTypes) {
						this.techanTypes = techanTypes;
					}


						/**
						* 获取： 特产类型的值
						*/
						public String getTechanValue() {
							return techanValue;
						}
						/**
						* 设置： 特产类型的值
						*/
						public void setTechanValue(String techanValue) {
							this.techanValue = techanValue;
						}

					/**
					* 获取： 特产库存
					*/
					public Integer getTechanKucunNumber() {
						return techanKucunNumber;
					}
					/**
					* 设置： 特产库存
					*/
					public void setTechanKucunNumber(Integer techanKucunNumber) {
						this.techanKucunNumber = techanKucunNumber;
					}

					/**
					* 获取： 购买获得积分
					*/
					public Integer getTechanPrice() {
						return techanPrice;
					}
					/**
					* 设置： 购买获得积分
					*/
					public void setTechanPrice(Integer techanPrice) {
						this.techanPrice = techanPrice;
					}

					/**
					* 获取： 特产原价
					*/
					public Double getTechanOldMoney() {
						return techanOldMoney;
					}
					/**
					* 设置： 特产原价
					*/
					public void setTechanOldMoney(Double techanOldMoney) {
						this.techanOldMoney = techanOldMoney;
					}

					/**
					* 获取： 现价
					*/
					public Double getTechanNewMoney() {
						return techanNewMoney;
					}
					/**
					* 设置： 现价
					*/
					public void setTechanNewMoney(Double techanNewMoney) {
						this.techanNewMoney = techanNewMoney;
					}

					/**
					* 获取： 特产热度
					*/
					public Integer getTechanClicknum() {
						return techanClicknum;
					}
					/**
					* 设置： 特产热度
					*/
					public void setTechanClicknum(Integer techanClicknum) {
						this.techanClicknum = techanClicknum;
					}

					/**
					* 获取： 特产介绍
					*/
					public String getTechanContent() {
						return techanContent;
					}
					/**
					* 设置： 特产介绍
					*/
					public void setTechanContent(String techanContent) {
						this.techanContent = techanContent;
					}

					/**
					* 获取： 是否上架
					*/
					public Integer getShangxiaTypes() {
						return shangxiaTypes;
					}
					/**
					* 设置： 是否上架
					*/
					public void setShangxiaTypes(Integer shangxiaTypes) {
						this.shangxiaTypes = shangxiaTypes;
					}


						/**
						* 获取： 是否上架的值
						*/
						public String getShangxiaValue() {
							return shangxiaValue;
						}
						/**
						* 设置： 是否上架的值
						*/
						public void setShangxiaValue(String shangxiaValue) {
							this.shangxiaValue = shangxiaValue;
						}

					/**
					* 获取： 逻辑删除
					*/
					public Integer getTechanDelete() {
						return techanDelete;
					}
					/**
					* 设置： 逻辑删除
					*/
					public void setTechanDelete(Integer techanDelete) {
						this.techanDelete = techanDelete;
					}
















				//级联表的get和set yonghu

					/**
					* 获取： 用户姓名
					*/
					public String getYonghuName() {
						return yonghuName;
					}
					/**
					* 设置： 用户姓名
					*/
					public void setYonghuName(String yonghuName) {
						this.yonghuName = yonghuName;
					}

					/**
					* 获取： 用户手机号
					*/
					public String getYonghuPhone() {
						return yonghuPhone;
					}
					/**
					* 设置： 用户手机号
					*/
					public void setYonghuPhone(String yonghuPhone) {
						this.yonghuPhone = yonghuPhone;
					}

					/**
					* 获取： 用户身份证号
					*/
					public String getYonghuIdNumber() {
						return yonghuIdNumber;
					}
					/**
					* 设置： 用户身份证号
					*/
					public void setYonghuIdNumber(String yonghuIdNumber) {
						this.yonghuIdNumber = yonghuIdNumber;
					}

					/**
					* 获取： 用户头像
					*/
					public String getYonghuPhoto() {
						return yonghuPhoto;
					}
					/**
					* 设置： 用户头像
					*/
					public void setYonghuPhoto(String yonghuPhoto) {
						this.yonghuPhoto = yonghuPhoto;
					}

					/**
					* 获取： 电子邮箱
					*/
					public String getYonghuEmail() {
						return yonghuEmail;
					}
					/**
					* 设置： 电子邮箱
					*/
					public void setYonghuEmail(String yonghuEmail) {
						this.yonghuEmail = yonghuEmail;
					}

					/**
					* 获取： 余额
					*/
					public Double getNewMoney() {
						return newMoney;
					}
					/**
					* 设置： 余额
					*/
					public void setNewMoney(Double newMoney) {
						this.newMoney = newMoney;
					}

					/**
					* 获取： 总积分
					*/
					public Double getYonghuSumJifen() {
						return yonghuSumJifen;
					}
					/**
					* 设置： 总积分
					*/
					public void setYonghuSumJifen(Double yonghuSumJifen) {
						this.yonghuSumJifen = yonghuSumJifen;
					}

					/**
					* 获取： 现积分
					*/
					public Double getYonghuNewJifen() {
						return yonghuNewJifen;
					}
					/**
					* 设置： 现积分
					*/
					public void setYonghuNewJifen(Double yonghuNewJifen) {
						this.yonghuNewJifen = yonghuNewJifen;
					}

					/**
					* 获取： 会员等级
					*/
					public Integer getHuiyuandengjiTypes() {
						return huiyuandengjiTypes;
					}
					/**
					* 设置： 会员等级
					*/
					public void setHuiyuandengjiTypes(Integer huiyuandengjiTypes) {
						this.huiyuandengjiTypes = huiyuandengjiTypes;
					}


						/**
						* 获取： 会员等级的值
						*/
						public String getHuiyuandengjiValue() {
							return huiyuandengjiValue;
						}
						/**
						* 设置： 会员等级的值
						*/
						public void setHuiyuandengjiValue(String huiyuandengjiValue) {
							this.huiyuandengjiValue = huiyuandengjiValue;
						}



}
