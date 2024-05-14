package com.dao;

import com.entity.CanyinCollectionEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.CanyinCollectionView;

/**
 * 餐饮收藏 Dao 接口
 *
 * @author 
 */
public interface CanyinCollectionDao extends BaseMapper<CanyinCollectionEntity> {

   List<CanyinCollectionView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
