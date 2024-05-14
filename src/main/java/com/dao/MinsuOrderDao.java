package com.dao;

import com.entity.MinsuOrderEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.MinsuOrderView;

/**
 * 民宿订单 Dao 接口
 *
 * @author 
 */
public interface MinsuOrderDao extends BaseMapper<MinsuOrderEntity> {

   List<MinsuOrderView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
