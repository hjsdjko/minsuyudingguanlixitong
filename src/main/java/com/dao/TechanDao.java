package com.dao;

import com.entity.TechanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.TechanView;

/**
 * 特产 Dao 接口
 *
 * @author 
 */
public interface TechanDao extends BaseMapper<TechanEntity> {

   List<TechanView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
