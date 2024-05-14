package com.dao;

import com.entity.CanyinOrderEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.CanyinOrderView;

/**
 * 餐饮订单 Dao 接口
 *
 * @author 
 */
public interface CanyinOrderDao extends BaseMapper<CanyinOrderEntity> {

   List<CanyinOrderView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
