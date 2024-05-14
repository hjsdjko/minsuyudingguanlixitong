package com.dao;

import com.entity.MinsuCommentbackEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.MinsuCommentbackView;

/**
 * 民宿评价 Dao 接口
 *
 * @author 
 */
public interface MinsuCommentbackDao extends BaseMapper<MinsuCommentbackEntity> {

   List<MinsuCommentbackView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
