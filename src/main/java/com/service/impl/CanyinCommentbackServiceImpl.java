package com.service.impl;

import com.utils.StringUtil;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;
import java.util.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import com.dao.CanyinCommentbackDao;
import com.entity.CanyinCommentbackEntity;
import com.service.CanyinCommentbackService;
import com.entity.view.CanyinCommentbackView;

/**
 * 餐饮评价 服务实现类
 */
@Service("canyinCommentbackService")
@Transactional
public class CanyinCommentbackServiceImpl extends ServiceImpl<CanyinCommentbackDao, CanyinCommentbackEntity> implements CanyinCommentbackService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        Page<CanyinCommentbackView> page =new Query<CanyinCommentbackView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}
