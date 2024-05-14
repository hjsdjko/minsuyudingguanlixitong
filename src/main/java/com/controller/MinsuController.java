
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
 * 民宿
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/minsu")
public class MinsuController {
    private static final Logger logger = LoggerFactory.getLogger(MinsuController.class);

    @Autowired
    private MinsuService minsuService;


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
        params.put("minsuDeleteStart",1);params.put("minsuDeleteEnd",1);
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = minsuService.queryPage(params);

        //字典表数据转换
        List<MinsuView> list =(List<MinsuView>)page.getList();
        for(MinsuView c:list){
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
        MinsuEntity minsu = minsuService.selectById(id);
        if(minsu !=null){
            //entity转view
            MinsuView view = new MinsuView();
            BeanUtils.copyProperties( minsu , view );//把实体数据重构到view中

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
    public R save(@RequestBody MinsuEntity minsu, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,minsu:{}",this.getClass().getName(),minsu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<MinsuEntity> queryWrapper = new EntityWrapper<MinsuEntity>()
            .eq("minsu_name", minsu.getMinsuName())
            .eq("minsu_uuid_number", minsu.getMinsuUuidNumber())
            .eq("minsu_tese", minsu.getMinsuTese())
            .eq("minsu_types", minsu.getMinsuTypes())
            .eq("minsu_price", minsu.getMinsuPrice())
            .eq("minsu_clicknum", minsu.getMinsuClicknum())
            .eq("shangxia_types", minsu.getShangxiaTypes())
            .eq("minsu_delete", minsu.getMinsuDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        MinsuEntity minsuEntity = minsuService.selectOne(queryWrapper);
        if(minsuEntity==null){
            minsu.setMinsuClicknum(1);
            minsu.setShangxiaTypes(1);
            minsu.setMinsuDelete(1);
            minsu.setCreateTime(new Date());
            minsuService.insert(minsu);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody MinsuEntity minsu, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,minsu:{}",this.getClass().getName(),minsu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        //根据字段查询是否有相同数据
        Wrapper<MinsuEntity> queryWrapper = new EntityWrapper<MinsuEntity>()
            .notIn("id",minsu.getId())
            .andNew()
            .eq("minsu_name", minsu.getMinsuName())
            .eq("minsu_uuid_number", minsu.getMinsuUuidNumber())
            .eq("minsu_tese", minsu.getMinsuTese())
            .eq("minsu_types", minsu.getMinsuTypes())
            .eq("minsu_price", minsu.getMinsuPrice())
            .eq("minsu_clicknum", minsu.getMinsuClicknum())
            .eq("shangxia_types", minsu.getShangxiaTypes())
            .eq("minsu_delete", minsu.getMinsuDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        MinsuEntity minsuEntity = minsuService.selectOne(queryWrapper);
        if("".equals(minsu.getMinsuPhoto()) || "null".equals(minsu.getMinsuPhoto())){
                minsu.setMinsuPhoto(null);
        }
        if(minsuEntity==null){
            minsuService.updateById(minsu);//根据id更新
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
        ArrayList<MinsuEntity> list = new ArrayList<>();
        for(Integer id:ids){
            MinsuEntity minsuEntity = new MinsuEntity();
            minsuEntity.setId(id);
            minsuEntity.setMinsuDelete(2);
            list.add(minsuEntity);
        }
        if(list != null && list.size() >0){
            minsuService.updateBatchById(list);
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
            List<MinsuEntity> minsuList = new ArrayList<>();//上传的东西
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
                            MinsuEntity minsuEntity = new MinsuEntity();
//                            minsuEntity.setMinsuName(data.get(0));                    //民宿名称 要改的
//                            minsuEntity.setMinsuUuidNumber(data.get(0));                    //民宿编号 要改的
//                            minsuEntity.setMinsuPhoto("");//详情和图片
//                            minsuEntity.setMinsuTese(data.get(0));                    //民宿特色 要改的
//                            minsuEntity.setMinsuTypes(Integer.valueOf(data.get(0)));   //民宿类型 要改的
//                            minsuEntity.setMinsuPrice(Integer.valueOf(data.get(0)));   //租赁获得积分 要改的
//                            minsuEntity.setMinsuOldMoney(data.get(0));                    //民宿原价 要改的
//                            minsuEntity.setMinsuNewMoney(data.get(0));                    //现价/天 要改的
//                            minsuEntity.setMinsuClicknum(Integer.valueOf(data.get(0)));   //房间热度 要改的
//                            minsuEntity.setMinsuContent("");//详情和图片
//                            minsuEntity.setShangxiaTypes(Integer.valueOf(data.get(0)));   //是否上架 要改的
//                            minsuEntity.setMinsuDelete(1);//逻辑删除字段
//                            minsuEntity.setCreateTime(date);//时间
                            minsuList.add(minsuEntity);


                            //把要查询是否重复的字段放入map中
                                //民宿编号
                                if(seachFields.containsKey("minsuUuidNumber")){
                                    List<String> minsuUuidNumber = seachFields.get("minsuUuidNumber");
                                    minsuUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> minsuUuidNumber = new ArrayList<>();
                                    minsuUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("minsuUuidNumber",minsuUuidNumber);
                                }
                        }

                        //查询是否重复
                         //民宿编号
                        List<MinsuEntity> minsuEntities_minsuUuidNumber = minsuService.selectList(new EntityWrapper<MinsuEntity>().in("minsu_uuid_number", seachFields.get("minsuUuidNumber")).eq("minsu_delete", 1));
                        if(minsuEntities_minsuUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(MinsuEntity s:minsuEntities_minsuUuidNumber){
                                repeatFields.add(s.getMinsuUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [民宿编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        minsuService.insertBatch(minsuList);
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
        PageUtils page = minsuService.queryPage(params);

        //字典表数据转换
        List<MinsuView> list =(List<MinsuView>)page.getList();
        for(MinsuView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        MinsuEntity minsu = minsuService.selectById(id);
            if(minsu !=null){

                //点击数量加1
                minsu.setMinsuClicknum(minsu.getMinsuClicknum()+1);
                minsuService.updateById(minsu);

                //entity转view
                MinsuView view = new MinsuView();
                BeanUtils.copyProperties( minsu , view );//把实体数据重构到view中

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
    public R add(@RequestBody MinsuEntity minsu, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,minsu:{}",this.getClass().getName(),minsu.toString());
        Wrapper<MinsuEntity> queryWrapper = new EntityWrapper<MinsuEntity>()
            .eq("minsu_name", minsu.getMinsuName())
            .eq("minsu_uuid_number", minsu.getMinsuUuidNumber())
            .eq("minsu_tese", minsu.getMinsuTese())
            .eq("minsu_types", minsu.getMinsuTypes())
            .eq("minsu_price", minsu.getMinsuPrice())
            .eq("minsu_clicknum", minsu.getMinsuClicknum())
            .eq("shangxia_types", minsu.getShangxiaTypes())
            .eq("minsu_delete", minsu.getMinsuDelete())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        MinsuEntity minsuEntity = minsuService.selectOne(queryWrapper);
        if(minsuEntity==null){
            minsu.setMinsuDelete(1);
            minsu.setCreateTime(new Date());
        minsuService.insert(minsu);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }


}
