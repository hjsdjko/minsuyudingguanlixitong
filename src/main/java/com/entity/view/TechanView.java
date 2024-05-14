package com.entity.view;

import com.entity.TechanEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 特产
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("techan")
public class TechanView extends TechanEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 特产类型的值
		*/
		private String techanValue;
		/**
		* 是否上架的值
		*/
		private String shangxiaValue;



	public TechanView() {

	}

	public TechanView(TechanEntity techanEntity) {
		try {
			BeanUtils.copyProperties(this, techanEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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


























}
