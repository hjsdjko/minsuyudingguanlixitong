package com.entity.view;

import com.entity.MinsuOrderEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 民宿订单
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("minsu_order")
public class MinsuOrderView extends MinsuOrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 订单类型的值
		*/
		private String minsuOrderValue;
		/**
		* 支付类型的值
		*/
		private String minsuOrderPaymentValue;



		//级联表 minsu
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
				* 民宿类型的值
				*/
				private String minsuValue;
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
				* 是否上架的值
				*/
				private String shangxiaValue;
			/**
			* 逻辑删除
			*/
			private Integer minsuDelete;

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

	public MinsuOrderView() {

	}

	public MinsuOrderView(MinsuOrderEntity minsuOrderEntity) {
		try {
			BeanUtils.copyProperties(this, minsuOrderEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 订单类型的值
			*/
			public String getMinsuOrderValue() {
				return minsuOrderValue;
			}
			/**
			* 设置： 订单类型的值
			*/
			public void setMinsuOrderValue(String minsuOrderValue) {
				this.minsuOrderValue = minsuOrderValue;
			}
			/**
			* 获取： 支付类型的值
			*/
			public String getMinsuOrderPaymentValue() {
				return minsuOrderPaymentValue;
			}
			/**
			* 设置： 支付类型的值
			*/
			public void setMinsuOrderPaymentValue(String minsuOrderPaymentValue) {
				this.minsuOrderPaymentValue = minsuOrderPaymentValue;
			}





































				//级联表的get和set minsu

					/**
					* 获取： 民宿名称
					*/
					public String getMinsuName() {
						return minsuName;
					}
					/**
					* 设置： 民宿名称
					*/
					public void setMinsuName(String minsuName) {
						this.minsuName = minsuName;
					}

					/**
					* 获取： 民宿编号
					*/
					public String getMinsuUuidNumber() {
						return minsuUuidNumber;
					}
					/**
					* 设置： 民宿编号
					*/
					public void setMinsuUuidNumber(String minsuUuidNumber) {
						this.minsuUuidNumber = minsuUuidNumber;
					}

					/**
					* 获取： 民宿照片
					*/
					public String getMinsuPhoto() {
						return minsuPhoto;
					}
					/**
					* 设置： 民宿照片
					*/
					public void setMinsuPhoto(String minsuPhoto) {
						this.minsuPhoto = minsuPhoto;
					}

					/**
					* 获取： 民宿特色
					*/
					public String getMinsuTese() {
						return minsuTese;
					}
					/**
					* 设置： 民宿特色
					*/
					public void setMinsuTese(String minsuTese) {
						this.minsuTese = minsuTese;
					}

					/**
					* 获取： 民宿类型
					*/
					public Integer getMinsuTypes() {
						return minsuTypes;
					}
					/**
					* 设置： 民宿类型
					*/
					public void setMinsuTypes(Integer minsuTypes) {
						this.minsuTypes = minsuTypes;
					}


						/**
						* 获取： 民宿类型的值
						*/
						public String getMinsuValue() {
							return minsuValue;
						}
						/**
						* 设置： 民宿类型的值
						*/
						public void setMinsuValue(String minsuValue) {
							this.minsuValue = minsuValue;
						}

					/**
					* 获取： 租赁获得积分
					*/
					public Integer getMinsuPrice() {
						return minsuPrice;
					}
					/**
					* 设置： 租赁获得积分
					*/
					public void setMinsuPrice(Integer minsuPrice) {
						this.minsuPrice = minsuPrice;
					}

					/**
					* 获取： 民宿原价
					*/
					public Double getMinsuOldMoney() {
						return minsuOldMoney;
					}
					/**
					* 设置： 民宿原价
					*/
					public void setMinsuOldMoney(Double minsuOldMoney) {
						this.minsuOldMoney = minsuOldMoney;
					}

					/**
					* 获取： 现价/天
					*/
					public Double getMinsuNewMoney() {
						return minsuNewMoney;
					}
					/**
					* 设置： 现价/天
					*/
					public void setMinsuNewMoney(Double minsuNewMoney) {
						this.minsuNewMoney = minsuNewMoney;
					}

					/**
					* 获取： 房间热度
					*/
					public Integer getMinsuClicknum() {
						return minsuClicknum;
					}
					/**
					* 设置： 房间热度
					*/
					public void setMinsuClicknum(Integer minsuClicknum) {
						this.minsuClicknum = minsuClicknum;
					}

					/**
					* 获取： 房间介绍
					*/
					public String getMinsuContent() {
						return minsuContent;
					}
					/**
					* 设置： 房间介绍
					*/
					public void setMinsuContent(String minsuContent) {
						this.minsuContent = minsuContent;
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
					public Integer getMinsuDelete() {
						return minsuDelete;
					}
					/**
					* 设置： 逻辑删除
					*/
					public void setMinsuDelete(Integer minsuDelete) {
						this.minsuDelete = minsuDelete;
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
