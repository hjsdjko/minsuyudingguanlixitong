package com.dao;

import com.entity.MinsuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.MinsuView;

/**
 * 民宿 Dao 接口
 *
 * @author 
 */
public interface MinsuDao extends BaseMapper<MinsuEntity> {

   List<MinsuView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
