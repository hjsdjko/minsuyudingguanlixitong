package com.dao;

import com.entity.CanyinLiuyanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.CanyinLiuyanView;

/**
 * 餐饮留言 Dao 接口
 *
 * @author 
 */
public interface CanyinLiuyanDao extends BaseMapper<CanyinLiuyanEntity> {

   List<CanyinLiuyanView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
