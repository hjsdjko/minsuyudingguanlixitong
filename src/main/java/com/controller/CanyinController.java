
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 餐饮
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/canyin")
public class CanyinController {
    private static final Logger logger = LoggerFactory.getLogger(CanyinController.class);

    @Autowired
    private CanyinService canyinService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service

    @Autowired
    private YonghuService yonghuService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        params.put("canyinDeleteStart",1);params.put("canyinDeleteEnd",1);
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = canyinService.queryPage(params);

        //字典表数据转换
        List<CanyinView> list =(List<CanyinView>)page.getList();
        for(CanyinView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        CanyinEntity canyin = canyinService.selectById(id);
        if(canyin !=null){
            //entity转view
            CanyinView view = new CanyinView();
            BeanUtils.copyProperties( canyin , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody CanyinEntity canyin, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,canyin:{}",this.getClass().getName(),canyin.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<CanyinEntity> queryWrapper = new EntityWrapper<CanyinEntity>()
            .eq("canyin_name", canyin.getCanyinName())
            .eq("canyin_uuid_number", canyin.getCanyinUuidNumber())
            .eq("canyin_types", canyin.getCanyinTypes())
            .eq("canyin_kucun_number", canyin.getCanyinKucunNumber())
            .eq("canyin_price", canyin.getCanyinPrice())
            .eq("canyin_clicknum", canyin.getCanyinClicknum())
            .eq("shangxia_types", canyin.getShangxiaTypes())
            .eq("canyin_delete", canyin.getCanyinDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        CanyinEntity canyinEntity = canyinService.selectOne(queryWrapper);
        if(canyinEntity==null){
            canyin.setCanyinClicknum(1);
            canyin.setShangxiaTypes(1);
            canyin.setCanyinDelete(1);
            canyin.setCreateTime(new Date());
            canyinService.insert(canyin);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody CanyinEntity canyin, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,canyin:{}",this.getClass().getName(),canyin.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        //根据字段查询是否有相同数据
        Wrapper<CanyinEntity> queryWrapper = new EntityWrapper<CanyinEntity>()
            .notIn("id",canyin.getId())
            .andNew()
            .eq("canyin_name", canyin.getCanyinName())
            .eq("canyin_uuid_number", canyin.getCanyinUuidNumber())
            .eq("canyin_types", canyin.getCanyinTypes())
            .eq("canyin_kucun_number", canyin.getCanyinKucunNumber())
            .eq("canyin_price", canyin.getCanyinPrice())
            .eq("canyin_clicknum", canyin.getCanyinClicknum())
            .eq("shangxia_types", canyin.getShangxiaTypes())
            .eq("canyin_delete", canyin.getCanyinDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        CanyinEntity canyinEntity = canyinService.selectOne(queryWrapper);
        if("".equals(canyin.getCanyinPhoto()) || "null".equals(canyin.getCanyinPhoto())){
                canyin.setCanyinPhoto(null);
        }
        if(canyinEntity==null){
            canyinService.updateById(canyin);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        ArrayList<CanyinEntity> list = new ArrayList<>();
        for(Integer id:ids){
            CanyinEntity canyinEntity = new CanyinEntity();
            canyinEntity.setId(id);
            canyinEntity.setCanyinDelete(2);
            list.add(canyinEntity);
        }
        if(list != null && list.size() >0){
            canyinService.updateBatchById(list);
        }
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<CanyinEntity> canyinList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("../../upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            CanyinEntity canyinEntity = new CanyinEntity();
//                            canyinEntity.setCanyinName(data.get(0));                    //餐饮名称 要改的
//                            canyinEntity.setCanyinUuidNumber(data.get(0));                    //餐饮编号 要改的
//                            canyinEntity.setCanyinPhoto("");//详情和图片
//                            canyinEntity.setCanyinTypes(Integer.valueOf(data.get(0)));   //餐饮类型 要改的
//                            canyinEntity.setCanyinKucunNumber(Integer.valueOf(data.get(0)));   //餐饮库存 要改的
//                            canyinEntity.setCanyinPrice(Integer.valueOf(data.get(0)));   //购买获得积分 要改的
//                            canyinEntity.setCanyinOldMoney(data.get(0));                    //餐饮原价 要改的
//                            canyinEntity.setCanyinNewMoney(data.get(0));                    //现价 要改的
//                            canyinEntity.setCanyinClicknum(Integer.valueOf(data.get(0)));   //热度 要改的
//                            canyinEntity.setCanyinContent("");//详情和图片
//                            canyinEntity.setShangxiaTypes(Integer.valueOf(data.get(0)));   //是否上架 要改的
//                            canyinEntity.setCanyinDelete(1);//逻辑删除字段
//                            canyinEntity.setCreateTime(date);//时间
                            canyinList.add(canyinEntity);


                            //把要查询是否重复的字段放入map中
                                //餐饮编号
                                if(seachFields.containsKey("canyinUuidNumber")){
                                    List<String> canyinUuidNumber = seachFields.get("canyinUuidNumber");
                                    canyinUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> canyinUuidNumber = new ArrayList<>();
                                    canyinUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("canyinUuidNumber",canyinUuidNumber);
                                }
                        }

                        //查询是否重复
                         //餐饮编号
                        List<CanyinEntity> canyinEntities_canyinUuidNumber = canyinService.selectList(new EntityWrapper<CanyinEntity>().in("canyin_uuid_number", seachFields.get("canyinUuidNumber")).eq("canyin_delete", 1));
                        if(canyinEntities_canyinUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(CanyinEntity s:canyinEntities_canyinUuidNumber){
                                repeatFields.add(s.getCanyinUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [餐饮编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        canyinService.insertBatch(canyinList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }





    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        // 没有指定排序字段就默认id倒序
        if(StringUtil.isEmpty(String.valueOf(params.get("orderBy")))){
            params.put("orderBy","id");
        }
        PageUtils page = canyinService.queryPage(params);

        //字典表数据转换
        List<CanyinView> list =(List<CanyinView>)page.getList();
        for(CanyinView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        CanyinEntity canyin = canyinService.selectById(id);
            if(canyin !=null){

                //点击数量加1
                canyin.setCanyinClicknum(canyin.getCanyinClicknum()+1);
                canyinService.updateById(canyin);

                //entity转view
                CanyinView view = new CanyinView();
                BeanUtils.copyProperties( canyin , view );//把实体数据重构到view中

                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody CanyinEntity canyin, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,canyin:{}",this.getClass().getName(),canyin.toString());
        Wrapper<CanyinEntity> queryWrapper = new EntityWrapper<CanyinEntity>()
            .eq("canyin_name", canyin.getCanyinName())
            .eq("canyin_uuid_number", canyin.getCanyinUuidNumber())
            .eq("canyin_types", canyin.getCanyinTypes())
            .eq("canyin_kucun_number", canyin.getCanyinKucunNumber())
            .eq("canyin_price", canyin.getCanyinPrice())
            .eq("canyin_clicknum", canyin.getCanyinClicknum())
            .eq("shangxia_types", canyin.getShangxiaTypes())
            .eq("canyin_delete", canyin.getCanyinDelete())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        CanyinEntity canyinEntity = canyinService.selectOne(queryWrapper);
        if(canyinEntity==null){
            canyin.setCanyinDelete(1);
            canyin.setCreateTime(new Date());
        canyinService.insert(canyin);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }


}
