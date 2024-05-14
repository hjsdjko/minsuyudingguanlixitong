package com.dao;

import com.entity.TechanCollectionEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.TechanCollectionView;

/**
 * 特产收藏 Dao 接口
 *
 * @author 
 */
public interface TechanCollectionDao extends BaseMapper<TechanCollectionEntity> {

   List<TechanCollectionView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
