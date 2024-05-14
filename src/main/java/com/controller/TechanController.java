
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
 * 特产
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/techan")
public class TechanController {
    private static final Logger logger = LoggerFactory.getLogger(TechanController.class);

    @Autowired
    private TechanService techanService;


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
        params.put("techanDeleteStart",1);params.put("techanDeleteEnd",1);
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = techanService.queryPage(params);

        //字典表数据转换
        List<TechanView> list =(List<TechanView>)page.getList();
        for(TechanView c:list){
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
        TechanEntity techan = techanService.selectById(id);
        if(techan !=null){
            //entity转view
            TechanView view = new TechanView();
            BeanUtils.copyProperties( techan , view );//把实体数据重构到view中

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
    public R save(@RequestBody TechanEntity techan, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,techan:{}",this.getClass().getName(),techan.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<TechanEntity> queryWrapper = new EntityWrapper<TechanEntity>()
            .eq("techan_name", techan.getTechanName())
            .eq("techan_uuid_number", techan.getTechanUuidNumber())
            .eq("jingdian_name", techan.getJingdianName())
            .eq("jingdian_address", techan.getJingdianAddress())
            .eq("techan_types", techan.getTechanTypes())
            .eq("techan_kucun_number", techan.getTechanKucunNumber())
            .eq("techan_price", techan.getTechanPrice())
            .eq("techan_clicknum", techan.getTechanClicknum())
            .eq("shangxia_types", techan.getShangxiaTypes())
            .eq("techan_delete", techan.getTechanDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        TechanEntity techanEntity = techanService.selectOne(queryWrapper);
        if(techanEntity==null){
            techan.setTechanClicknum(1);
            techan.setShangxiaTypes(1);
            techan.setTechanDelete(1);
            techan.setCreateTime(new Date());
            techanService.insert(techan);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody TechanEntity techan, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,techan:{}",this.getClass().getName(),techan.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        //根据字段查询是否有相同数据
        Wrapper<TechanEntity> queryWrapper = new EntityWrapper<TechanEntity>()
            .notIn("id",techan.getId())
            .andNew()
            .eq("techan_name", techan.getTechanName())
            .eq("techan_uuid_number", techan.getTechanUuidNumber())
            .eq("jingdian_name", techan.getJingdianName())
            .eq("jingdian_address", techan.getJingdianAddress())
            .eq("techan_types", techan.getTechanTypes())
            .eq("techan_kucun_number", techan.getTechanKucunNumber())
            .eq("techan_price", techan.getTechanPrice())
            .eq("techan_clicknum", techan.getTechanClicknum())
            .eq("shangxia_types", techan.getShangxiaTypes())
            .eq("techan_delete", techan.getTechanDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        TechanEntity techanEntity = techanService.selectOne(queryWrapper);
        if("".equals(techan.getTechanPhoto()) || "null".equals(techan.getTechanPhoto())){
                techan.setTechanPhoto(null);
        }
        if(techanEntity==null){
            techanService.updateById(techan);//根据id更新
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
        ArrayList<TechanEntity> list = new ArrayList<>();
        for(Integer id:ids){
            TechanEntity techanEntity = new TechanEntity();
            techanEntity.setId(id);
            techanEntity.setTechanDelete(2);
            list.add(techanEntity);
        }
        if(list != null && list.size() >0){
            techanService.updateBatchById(list);
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
            List<TechanEntity> techanList = new ArrayList<>();//上传的东西
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
                            TechanEntity techanEntity = new TechanEntity();
//                            techanEntity.setTechanName(data.get(0));                    //特产名称 要改的
//                            techanEntity.setTechanUuidNumber(data.get(0));                    //特产编号 要改的
//                            techanEntity.setJingdianName(data.get(0));                    //所属景点 要改的
//                            techanEntity.setJingdianAddress(data.get(0));                    //景点位置 要改的
//                            techanEntity.setTechanPhoto("");//详情和图片
//                            techanEntity.setTechanTypes(Integer.valueOf(data.get(0)));   //特产类型 要改的
//                            techanEntity.setTechanKucunNumber(Integer.valueOf(data.get(0)));   //特产库存 要改的
//                            techanEntity.setTechanPrice(Integer.valueOf(data.get(0)));   //购买获得积分 要改的
//                            techanEntity.setTechanOldMoney(data.get(0));                    //特产原价 要改的
//                            techanEntity.setTechanNewMoney(data.get(0));                    //现价 要改的
//                            techanEntity.setTechanClicknum(Integer.valueOf(data.get(0)));   //特产热度 要改的
//                            techanEntity.setTechanContent("");//详情和图片
//                            techanEntity.setShangxiaTypes(Integer.valueOf(data.get(0)));   //是否上架 要改的
//                            techanEntity.setTechanDelete(1);//逻辑删除字段
//                            techanEntity.setCreateTime(date);//时间
                            techanList.add(techanEntity);


                            //把要查询是否重复的字段放入map中
                                //特产编号
                                if(seachFields.containsKey("techanUuidNumber")){
                                    List<String> techanUuidNumber = seachFields.get("techanUuidNumber");
                                    techanUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> techanUuidNumber = new ArrayList<>();
                                    techanUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("techanUuidNumber",techanUuidNumber);
                                }
                        }

                        //查询是否重复
                         //特产编号
                        List<TechanEntity> techanEntities_techanUuidNumber = techanService.selectList(new EntityWrapper<TechanEntity>().in("techan_uuid_number", seachFields.get("techanUuidNumber")).eq("techan_delete", 1));
                        if(techanEntities_techanUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(TechanEntity s:techanEntities_techanUuidNumber){
                                repeatFields.add(s.getTechanUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [特产编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        techanService.insertBatch(techanList);
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
        PageUtils page = techanService.queryPage(params);

        //字典表数据转换
        List<TechanView> list =(List<TechanView>)page.getList();
        for(TechanView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        TechanEntity techan = techanService.selectById(id);
            if(techan !=null){

                //点击数量加1
                techan.setTechanClicknum(techan.getTechanClicknum()+1);
                techanService.updateById(techan);

                //entity转view
                TechanView view = new TechanView();
                BeanUtils.copyProperties( techan , view );//把实体数据重构到view中

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
    public R add(@RequestBody TechanEntity techan, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,techan:{}",this.getClass().getName(),techan.toString());
        Wrapper<TechanEntity> queryWrapper = new EntityWrapper<TechanEntity>()
            .eq("techan_name", techan.getTechanName())
            .eq("techan_uuid_number", techan.getTechanUuidNumber())
            .eq("jingdian_name", techan.getJingdianName())
            .eq("jingdian_address", techan.getJingdianAddress())
            .eq("techan_types", techan.getTechanTypes())
            .eq("techan_kucun_number", techan.getTechanKucunNumber())
            .eq("techan_price", techan.getTechanPrice())
            .eq("techan_clicknum", techan.getTechanClicknum())
            .eq("shangxia_types", techan.getShangxiaTypes())
            .eq("techan_delete", techan.getTechanDelete())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        TechanEntity techanEntity = techanService.selectOne(queryWrapper);
        if(techanEntity==null){
            techan.setTechanDelete(1);
            techan.setCreateTime(new Date());
        techanService.insert(techan);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }


}
