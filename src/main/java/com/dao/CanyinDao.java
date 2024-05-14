package com.dao;

import com.entity.CanyinEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.CanyinView;

/**
 * 餐饮 Dao 接口
 *
 * @author 
 */
public interface CanyinDao extends BaseMapper<CanyinEntity> {

   List<CanyinView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
