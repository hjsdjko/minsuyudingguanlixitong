
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
 * 民宿订单
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/minsuOrder")
public class MinsuOrderController {
    private static final Logger logger = LoggerFactory.getLogger(MinsuOrderController.class);

    @Autowired
    private MinsuOrderService minsuOrderService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private MinsuService minsuService;
    @Autowired
    private YonghuService yonghuService;
@Autowired
private CartService cartService;
@Autowired
private MinsuCommentbackService minsuCommentbackService;



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
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = minsuOrderService.queryPage(params);

        //字典表数据转换
        List<MinsuOrderView> list =(List<MinsuOrderView>)page.getList();
        for(MinsuOrderView c:list){
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
        MinsuOrderEntity minsuOrder = minsuOrderService.selectById(id);
        if(minsuOrder !=null){
            //entity转view
            MinsuOrderView view = new MinsuOrderView();
            BeanUtils.copyProperties( minsuOrder , view );//把实体数据重构到view中

                //级联表
                MinsuEntity minsu = minsuService.selectById(minsuOrder.getMinsuId());
                if(minsu != null){
                    BeanUtils.copyProperties( minsu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setMinsuId(minsu.getId());
                }
                //级联表
                YonghuEntity yonghu = yonghuService.selectById(minsuOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
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
    public R save(@RequestBody MinsuOrderEntity minsuOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,minsuOrder:{}",this.getClass().getName(),minsuOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            minsuOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        minsuOrder.setInsertTime(new Date());
        minsuOrder.setCreateTime(new Date());
        minsuOrderService.insert(minsuOrder);
        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody MinsuOrderEntity minsuOrder, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,minsuOrder:{}",this.getClass().getName(),minsuOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            minsuOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<MinsuOrderEntity> queryWrapper = new EntityWrapper<MinsuOrderEntity>()
            .eq("id",0)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        MinsuOrderEntity minsuOrderEntity = minsuOrderService.selectOne(queryWrapper);
        if(minsuOrderEntity==null){
            minsuOrderService.updateById(minsuOrder);//根据id更新
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
        minsuOrderService.deleteBatchIds(Arrays.asList(ids));
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
            List<MinsuOrderEntity> minsuOrderList = new ArrayList<>();//上传的东西
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
                            MinsuOrderEntity minsuOrderEntity = new MinsuOrderEntity();
//                            minsuOrderEntity.setMinsuOrderUuidNumber(data.get(0));                    //订单号 要改的
//                            minsuOrderEntity.setMinsuId(Integer.valueOf(data.get(0)));   //民宿 要改的
//                            minsuOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            minsuOrderEntity.setRuzhuTime(sdf.parse(data.get(0)));          //入住日期 要改的
//                            minsuOrderEntity.setBuyNumber(Integer.valueOf(data.get(0)));   //预定天数 要改的
//                            minsuOrderEntity.setMinsuOrderTruePrice(data.get(0));                    //实付价格 要改的
//                            minsuOrderEntity.setMinsuOrderTypes(Integer.valueOf(data.get(0)));   //订单类型 要改的
//                            minsuOrderEntity.setMinsuOrderPaymentTypes(Integer.valueOf(data.get(0)));   //支付类型 要改的
//                            minsuOrderEntity.setInsertTime(date);//时间
//                            minsuOrderEntity.setCreateTime(date);//时间
                            minsuOrderList.add(minsuOrderEntity);


                            //把要查询是否重复的字段放入map中
                                //订单号
                                if(seachFields.containsKey("minsuOrderUuidNumber")){
                                    List<String> minsuOrderUuidNumber = seachFields.get("minsuOrderUuidNumber");
                                    minsuOrderUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> minsuOrderUuidNumber = new ArrayList<>();
                                    minsuOrderUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("minsuOrderUuidNumber",minsuOrderUuidNumber);
                                }
                        }

                        //查询是否重复
                         //订单号
                        List<MinsuOrderEntity> minsuOrderEntities_minsuOrderUuidNumber = minsuOrderService.selectList(new EntityWrapper<MinsuOrderEntity>().in("minsu_order_uuid_number", seachFields.get("minsuOrderUuidNumber")));
                        if(minsuOrderEntities_minsuOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(MinsuOrderEntity s:minsuOrderEntities_minsuOrderUuidNumber){
                                repeatFields.add(s.getMinsuOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [订单号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        minsuOrderService.insertBatch(minsuOrderList);
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
        PageUtils page = minsuOrderService.queryPage(params);

        //字典表数据转换
        List<MinsuOrderView> list =(List<MinsuOrderView>)page.getList();
        for(MinsuOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        MinsuOrderEntity minsuOrder = minsuOrderService.selectById(id);
            if(minsuOrder !=null){


                //entity转view
                MinsuOrderView view = new MinsuOrderView();
                BeanUtils.copyProperties( minsuOrder , view );//把实体数据重构到view中

                //级联表
                    MinsuEntity minsu = minsuService.selectById(minsuOrder.getMinsuId());
                if(minsu != null){
                    BeanUtils.copyProperties( minsu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setMinsuId(minsu.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(minsuOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
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
    public R add(@RequestBody MinsuOrderEntity minsuOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,minsuOrder:{}",this.getClass().getName(),minsuOrder.toString());
            MinsuEntity minsuEntity = minsuService.selectById(minsuOrder.getMinsuId());
            if(minsuEntity == null){
                return R.error(511,"查不到该民宿");
            }
            // Double minsuNewMoney = minsuEntity.getMinsuNewMoney();

            if(false){
            }
            else if(minsuEntity.getMinsuNewMoney() == null){
                return R.error(511,"民宿价格不能为空");
            }

            //计算所获得积分
            Double buyJifen =0.0;
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");
            double balance = yonghuEntity.getNewMoney() - minsuEntity.getMinsuNewMoney()*minsuOrder.getBuyNumber();//余额
            buyJifen = new BigDecimal(minsuEntity.getMinsuPrice()).multiply(new BigDecimal(minsuOrder.getBuyNumber())).doubleValue();//所获积分
            if(balance<0)
                return R.error(511,"余额不够支付");
            minsuOrder.setMinsuOrderTypes(1); //设置订单状态为已支付
            minsuOrder.setMinsuOrderTruePrice(minsuEntity.getMinsuNewMoney()*minsuOrder.getBuyNumber()); //设置实付价格
            minsuOrder.setYonghuId(userId); //设置订单支付人id
            minsuOrder.setMinsuOrderUuidNumber(String.valueOf(new Date().getTime()));
            minsuOrder.setMinsuOrderPaymentTypes(1);
            minsuOrder.setInsertTime(new Date());
            minsuOrder.setCreateTime(new Date());
                minsuOrderService.insert(minsuOrder);//新增订单
            yonghuEntity.setNewMoney(balance);//设置金额
            yonghuEntity.setYonghuSumJifen(yonghuEntity.getYonghuSumJifen() + buyJifen); //设置总积分
            yonghuEntity.setYonghuNewJifen(yonghuEntity.getYonghuNewJifen() + buyJifen); //设置现积分
                if(yonghuEntity.getYonghuSumJifen()  < 10000)
                    yonghuEntity.setHuiyuandengjiTypes(1);
                else if(yonghuEntity.getYonghuSumJifen()  < 100000)
                    yonghuEntity.setHuiyuandengjiTypes(2);
                else if(yonghuEntity.getYonghuSumJifen()  < 1000000)
                    yonghuEntity.setHuiyuandengjiTypes(3);
            yonghuService.updateById(yonghuEntity);
            return R.ok();
    }


    /**
    * 退款
    */
    @RequestMapping("/refund")
    public R refund(Integer id, HttpServletRequest request){
        logger.debug("refund方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));

            MinsuOrderEntity minsuOrder = minsuOrderService.selectById(id);
            Integer buyNumber = minsuOrder.getBuyNumber();
            Integer minsuOrderPaymentTypes = minsuOrder.getMinsuOrderPaymentTypes();
            Integer minsuId = minsuOrder.getMinsuId();
            if(minsuId == null)
                return R.error(511,"查不到该民宿");
            MinsuEntity minsuEntity = minsuService.selectById(minsuId);
            if(minsuEntity == null)
                return R.error(511,"查不到该民宿");
            Double minsuNewMoney = minsuEntity.getMinsuNewMoney();
            if(minsuNewMoney == null)
                return R.error(511,"民宿价格不能为空");

            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");

            Double zhekou = 1.0;
            // 获取折扣
            Wrapper<DictionaryEntity> dictionary = new EntityWrapper<DictionaryEntity>()
                    .eq("dic_code", "huiyuandengji_types")
                    .eq("dic_name", "会员等级类型")
                    .eq("code_index", yonghuEntity.getHuiyuandengjiTypes())
                    ;
            DictionaryEntity dictionaryEntity = dictionaryService.selectOne(dictionary);
            if(dictionaryEntity != null ){
                zhekou = Double.valueOf(dictionaryEntity.getBeizhu());
            }


            //判断是什么支付方式 1代表余额 2代表积分
            if(minsuOrderPaymentTypes == 1){//余额支付
                //计算金额
                Double money = minsuEntity.getMinsuNewMoney() * buyNumber  * zhekou;
                //计算所获得积分
                Double buyJifen = 0.0;
                buyJifen = new BigDecimal(minsuEntity.getMinsuPrice()).multiply(new BigDecimal(buyNumber)).doubleValue();
                yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() + money); //设置金额
                yonghuEntity.setYonghuSumJifen(yonghuEntity.getYonghuSumJifen() - buyJifen); //设置总积分
                if(yonghuEntity.getYonghuNewJifen() - buyJifen <0 )
                    return R.error("积分已经消费,无法退款！！！");
                yonghuEntity.setYonghuNewJifen(yonghuEntity.getYonghuNewJifen() - buyJifen); //设置现积分

                if(yonghuEntity.getYonghuSumJifen()  < 10000)
                    yonghuEntity.setHuiyuandengjiTypes(1);
                else if(yonghuEntity.getYonghuSumJifen()  < 100000)
                    yonghuEntity.setHuiyuandengjiTypes(2);
                else if(yonghuEntity.getYonghuSumJifen()  < 1000000)
                    yonghuEntity.setHuiyuandengjiTypes(3);

            }




            minsuOrder.setMinsuOrderTypes(2);//设置订单状态为退款
            minsuOrderService.updateById(minsuOrder);//根据id更新
            yonghuService.updateById(yonghuEntity);//更新用户信息
            minsuService.updateById(minsuEntity);//更新订单中民宿的信息
            return R.ok();
    }


    /**
     * 抵达
     */
    @RequestMapping("/deliver")
    public R deliver(Integer id ){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        MinsuOrderEntity  minsuOrderEntity = new  MinsuOrderEntity();;
        minsuOrderEntity.setId(id);
        minsuOrderEntity.setMinsuOrderTypes(3);
        boolean b =  minsuOrderService.updateById( minsuOrderEntity);
        if(!b){
            return R.error("抵达出错");
        }
        return R.ok();
    }














    /**
     * 使用
     */
    @RequestMapping("/receiving")
    public R receiving(Integer id){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        MinsuOrderEntity  minsuOrderEntity = new  MinsuOrderEntity();
        minsuOrderEntity.setId(id);
        minsuOrderEntity.setMinsuOrderTypes(4);
        boolean b =  minsuOrderService.updateById( minsuOrderEntity);
        if(!b){
            return R.error("使用出错");
        }
        return R.ok();
    }



    /**
    * 评价
    */
    @RequestMapping("/commentback")
    public R commentback(Integer id, String commentbackText, Integer minsuCommentbackPingfenNumber, HttpServletRequest request){
        logger.debug("commentback方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
            MinsuOrderEntity minsuOrder = minsuOrderService.selectById(id);
        if(minsuOrder == null)
            return R.error(511,"查不到该订单");
        if(minsuOrder.getMinsuOrderTypes() != 4)
            return R.error(511,"您不能评价");
        Integer minsuId = minsuOrder.getMinsuId();
        if(minsuId == null)
            return R.error(511,"查不到该民宿");

        MinsuCommentbackEntity minsuCommentbackEntity = new MinsuCommentbackEntity();
            minsuCommentbackEntity.setId(id);
            minsuCommentbackEntity.setMinsuId(minsuId);
            minsuCommentbackEntity.setYonghuId((Integer) request.getSession().getAttribute("userId"));
            minsuCommentbackEntity.setMinsuCommentbackText(commentbackText);
            minsuCommentbackEntity.setInsertTime(new Date());
            minsuCommentbackEntity.setReplyText(null);
            minsuCommentbackEntity.setUpdateTime(null);
            minsuCommentbackEntity.setCreateTime(new Date());
            minsuCommentbackService.insert(minsuCommentbackEntity);

            minsuOrder.setMinsuOrderTypes(5);//设置订单状态为已评价
            minsuOrderService.updateById(minsuOrder);//根据id更新
            return R.ok();
    }












}
