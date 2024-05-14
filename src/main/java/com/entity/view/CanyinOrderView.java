package com.entity.view;

import com.entity.CanyinOrderEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 餐饮订单
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("canyin_order")
public class CanyinOrderView extends CanyinOrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 订单类型的值
		*/
		private String canyinOrderValue;
		/**
		* 支付类型的值
		*/
		private String canyinOrderPaymentValue;



		//级联表 canyin
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
				* 餐饮类型的值
				*/
				private String canyinValue;
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
				* 是否上架的值
				*/
				private String shangxiaValue;
			/**
			* 逻辑删除
			*/
			private Integer canyinDelete;

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

	public CanyinOrderView() {

	}

	public CanyinOrderView(CanyinOrderEntity canyinOrderEntity) {
		try {
			BeanUtils.copyProperties(this, canyinOrderEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 订单类型的值
			*/
			public String getCanyinOrderValue() {
				return canyinOrderValue;
			}
			/**
			* 设置： 订单类型的值
			*/
			public void setCanyinOrderValue(String canyinOrderValue) {
				this.canyinOrderValue = canyinOrderValue;
			}
			/**
			* 获取： 支付类型的值
			*/
			public String getCanyinOrderPaymentValue() {
				return canyinOrderPaymentValue;
			}
			/**
			* 设置： 支付类型的值
			*/
			public void setCanyinOrderPaymentValue(String canyinOrderPaymentValue) {
				this.canyinOrderPaymentValue = canyinOrderPaymentValue;
			}







				//级联表的get和set canyin

					/**
					* 获取： 餐饮名称
					*/
					public String getCanyinName() {
						return canyinName;
					}
					/**
					* 设置： 餐饮名称
					*/
					public void setCanyinName(String canyinName) {
						this.canyinName = canyinName;
					}

					/**
					* 获取： 餐饮编号
					*/
					public String getCanyinUuidNumber() {
						return canyinUuidNumber;
					}
					/**
					* 设置： 餐饮编号
					*/
					public void setCanyinUuidNumber(String canyinUuidNumber) {
						this.canyinUuidNumber = canyinUuidNumber;
					}

					/**
					* 获取： 餐饮照片
					*/
					public String getCanyinPhoto() {
						return canyinPhoto;
					}
					/**
					* 设置： 餐饮照片
					*/
					public void setCanyinPhoto(String canyinPhoto) {
						this.canyinPhoto = canyinPhoto;
					}

					/**
					* 获取： 餐饮类型
					*/
					public Integer getCanyinTypes() {
						return canyinTypes;
					}
					/**
					* 设置： 餐饮类型
					*/
					public void setCanyinTypes(Integer canyinTypes) {
						this.canyinTypes = canyinTypes;
					}


						/**
						* 获取： 餐饮类型的值
						*/
						public String getCanyinValue() {
							return canyinValue;
						}
						/**
						* 设置： 餐饮类型的值
						*/
						public void setCanyinValue(String canyinValue) {
							this.canyinValue = canyinValue;
						}

					/**
					* 获取： 餐饮库存
					*/
					public Integer getCanyinKucunNumber() {
						return canyinKucunNumber;
					}
					/**
					* 设置： 餐饮库存
					*/
					public void setCanyinKucunNumber(Integer canyinKucunNumber) {
						this.canyinKucunNumber = canyinKucunNumber;
					}

					/**
					* 获取： 购买获得积分
					*/
					public Integer getCanyinPrice() {
						return canyinPrice;
					}
					/**
					* 设置： 购买获得积分
					*/
					public void setCanyinPrice(Integer canyinPrice) {
						this.canyinPrice = canyinPrice;
					}

					/**
					* 获取： 餐饮原价
					*/
					public Double getCanyinOldMoney() {
						return canyinOldMoney;
					}
					/**
					* 设置： 餐饮原价
					*/
					public void setCanyinOldMoney(Double canyinOldMoney) {
						this.canyinOldMoney = canyinOldMoney;
					}

					/**
					* 获取： 现价
					*/
					public Double getCanyinNewMoney() {
						return canyinNewMoney;
					}
					/**
					* 设置： 现价
					*/
					public void setCanyinNewMoney(Double canyinNewMoney) {
						this.canyinNewMoney = canyinNewMoney;
					}

					/**
					* 获取： 热度
					*/
					public Integer getCanyinClicknum() {
						return canyinClicknum;
					}
					/**
					* 设置： 热度
					*/
					public void setCanyinClicknum(Integer canyinClicknum) {
						this.canyinClicknum = canyinClicknum;
					}

					/**
					* 获取： 餐饮介绍
					*/
					public String getCanyinContent() {
						return canyinContent;
					}
					/**
					* 设置： 餐饮介绍
					*/
					public void setCanyinContent(String canyinContent) {
						this.canyinContent = canyinContent;
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
					public Integer getCanyinDelete() {
						return canyinDelete;
					}
					/**
					* 设置： 逻辑删除
					*/
					public void setCanyinDelete(Integer canyinDelete) {
						this.canyinDelete = canyinDelete;
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
