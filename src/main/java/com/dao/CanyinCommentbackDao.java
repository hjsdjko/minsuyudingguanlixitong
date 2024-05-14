package com.dao;

import com.entity.CanyinCommentbackEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.CanyinCommentbackView;

/**
 * 餐饮评价 Dao 接口
 *
 * @author 
 */
public interface CanyinCommentbackDao extends BaseMapper<CanyinCommentbackEntity> {

   List<CanyinCommentbackView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
