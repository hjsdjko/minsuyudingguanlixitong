package com.dao;

import com.entity.MinsuLiuyanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.MinsuLiuyanView;

/**
 * 民宿留言 Dao 接口
 *
 * @author 
 */
public interface MinsuLiuyanDao extends BaseMapper<MinsuLiuyanEntity> {

   List<MinsuLiuyanView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
