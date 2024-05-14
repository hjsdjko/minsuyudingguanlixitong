
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
 * 餐饮订单
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/canyinOrder")
public class CanyinOrderController {
    private static final Logger logger = LoggerFactory.getLogger(CanyinOrderController.class);

    @Autowired
    private CanyinOrderService canyinOrderService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private CanyinService canyinService;
    @Autowired
    private YonghuService yonghuService;
@Autowired
private CanyinCommentbackService canyinCommentbackService;
@Autowired
private CartService cartService;



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
        PageUtils page = canyinOrderService.queryPage(params);

        //字典表数据转换
        List<CanyinOrderView> list =(List<CanyinOrderView>)page.getList();
        for(CanyinOrderView c:list){
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
        CanyinOrderEntity canyinOrder = canyinOrderService.selectById(id);
        if(canyinOrder !=null){
            //entity转view
            CanyinOrderView view = new CanyinOrderView();
            BeanUtils.copyProperties( canyinOrder , view );//把实体数据重构到view中

                //级联表
                CanyinEntity canyin = canyinService.selectById(canyinOrder.getCanyinId());
                if(canyin != null){
                    BeanUtils.copyProperties( canyin , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setCanyinId(canyin.getId());
                }
                //级联表
                YonghuEntity yonghu = yonghuService.selectById(canyinOrder.getYonghuId());
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
    public R save(@RequestBody CanyinOrderEntity canyinOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,canyinOrder:{}",this.getClass().getName(),canyinOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            canyinOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        canyinOrder.setInsertTime(new Date());
        canyinOrder.setCreateTime(new Date());
        canyinOrderService.insert(canyinOrder);
        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody CanyinOrderEntity canyinOrder, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,canyinOrder:{}",this.getClass().getName(),canyinOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            canyinOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<CanyinOrderEntity> queryWrapper = new EntityWrapper<CanyinOrderEntity>()
            .eq("id",0)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        CanyinOrderEntity canyinOrderEntity = canyinOrderService.selectOne(queryWrapper);
        if(canyinOrderEntity==null){
            canyinOrderService.updateById(canyinOrder);//根据id更新
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
        canyinOrderService.deleteBatchIds(Arrays.asList(ids));
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
            List<CanyinOrderEntity> canyinOrderList = new ArrayList<>();//上传的东西
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
                            CanyinOrderEntity canyinOrderEntity = new CanyinOrderEntity();
//                            canyinOrderEntity.setCanyinOrderUuidNumber(data.get(0));                    //餐饮订单号 要改的
//                            canyinOrderEntity.setCanyinId(Integer.valueOf(data.get(0)));   //餐饮 要改的
//                            canyinOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            canyinOrderEntity.setBuyNumber(Integer.valueOf(data.get(0)));   //购买数量 要改的
//                            canyinOrderEntity.setCanyinOrderTruePrice(data.get(0));                    //实付价格 要改的
//                            canyinOrderEntity.setCanyinOrderCourierName(data.get(0));                    //派送人 要改的
//                            canyinOrderEntity.setCanyinOrderCourierNumber(data.get(0));                    //派送人联系方式 要改的
//                            canyinOrderEntity.setCanyinOrderTypes(Integer.valueOf(data.get(0)));   //订单类型 要改的
//                            canyinOrderEntity.setCanyinOrderPaymentTypes(Integer.valueOf(data.get(0)));   //支付类型 要改的
//                            canyinOrderEntity.setInsertTime(date);//时间
//                            canyinOrderEntity.setCreateTime(date);//时间
                            canyinOrderList.add(canyinOrderEntity);


                            //把要查询是否重复的字段放入map中
                                //餐饮订单号
                                if(seachFields.containsKey("canyinOrderUuidNumber")){
                                    List<String> canyinOrderUuidNumber = seachFields.get("canyinOrderUuidNumber");
                                    canyinOrderUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> canyinOrderUuidNumber = new ArrayList<>();
                                    canyinOrderUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("canyinOrderUuidNumber",canyinOrderUuidNumber);
                                }
                        }

                        //查询是否重复
                         //餐饮订单号
                        List<CanyinOrderEntity> canyinOrderEntities_canyinOrderUuidNumber = canyinOrderService.selectList(new EntityWrapper<CanyinOrderEntity>().in("canyin_order_uuid_number", seachFields.get("canyinOrderUuidNumber")));
                        if(canyinOrderEntities_canyinOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(CanyinOrderEntity s:canyinOrderEntities_canyinOrderUuidNumber){
                                repeatFields.add(s.getCanyinOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [餐饮订单号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        canyinOrderService.insertBatch(canyinOrderList);
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
        PageUtils page = canyinOrderService.queryPage(params);

        //字典表数据转换
        List<CanyinOrderView> list =(List<CanyinOrderView>)page.getList();
        for(CanyinOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        CanyinOrderEntity canyinOrder = canyinOrderService.selectById(id);
            if(canyinOrder !=null){


                //entity转view
                CanyinOrderView view = new CanyinOrderView();
                BeanUtils.copyProperties( canyinOrder , view );//把实体数据重构到view中

                //级联表
                    CanyinEntity canyin = canyinService.selectById(canyinOrder.getCanyinId());
                if(canyin != null){
                    BeanUtils.copyProperties( canyin , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setCanyinId(canyin.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(canyinOrder.getYonghuId());
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
    public R add(@RequestBody CanyinOrderEntity canyinOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,canyinOrder:{}",this.getClass().getName(),canyinOrder.toString());
            CanyinEntity canyinEntity = canyinService.selectById(canyinOrder.getCanyinId());
            if(canyinEntity == null){
                return R.error(511,"查不到该餐饮");
            }
            // Double canyinNewMoney = canyinEntity.getCanyinNewMoney();

            if(false){
            }
            else if((canyinEntity.getCanyinKucunNumber() -canyinOrder.getBuyNumber())<0){
                return R.error(511,"购买数量不能大于库存数量");
            }
            else if(canyinEntity.getCanyinNewMoney() == null){
                return R.error(511,"餐饮价格不能为空");
            }

            //计算所获得积分
            Double buyJifen =0.0;
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");
            double balance = yonghuEntity.getNewMoney() - canyinEntity.getCanyinNewMoney()*canyinOrder.getBuyNumber();//余额
            buyJifen = new BigDecimal(canyinEntity.getCanyinPrice()).multiply(new BigDecimal(canyinOrder.getBuyNumber())).doubleValue();//所获积分
            if(balance<0)
                return R.error(511,"余额不够支付");
            canyinOrder.setCanyinOrderTypes(1); //设置订单状态为已支付
            canyinOrder.setCanyinOrderTruePrice(canyinEntity.getCanyinNewMoney()*canyinOrder.getBuyNumber()); //设置实付价格
            canyinOrder.setYonghuId(userId); //设置订单支付人id
            canyinOrder.setCanyinOrderUuidNumber(String.valueOf(new Date().getTime()));
            canyinOrder.setCanyinOrderPaymentTypes(1);
            canyinOrder.setInsertTime(new Date());
            canyinOrder.setCreateTime(new Date());
                canyinEntity.setCanyinKucunNumber( canyinEntity.getCanyinKucunNumber() -canyinOrder.getBuyNumber());
                canyinService.updateById(canyinEntity);
                canyinOrderService.insert(canyinOrder);//新增订单
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
     * 添加订单
     */
    @RequestMapping("/order")
    public R add(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("order方法:,,Controller:{},,params:{}",this.getClass().getName(),params.toString());
        String canyinOrderUuidNumber = String.valueOf(new Date().getTime());

        //获取当前登录用户的id
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        Integer addressId = Integer.valueOf(String.valueOf(params.get("addressId")));

        Integer canyinOrderPaymentTypes = Integer.valueOf(String.valueOf(params.get("canyinOrderPaymentTypes")));//支付类型

        String data = String.valueOf(params.get("canyins"));
        JSONArray jsonArray = JSON.parseArray(data);
        List<Map> canyins = JSON.parseObject(jsonArray.toString(), List.class);

        //获取当前登录用户的个人信息
        YonghuEntity yonghuEntity = yonghuService.selectById(userId);

        //当前订单表
        List<CanyinOrderEntity> canyinOrderList = new ArrayList<>();
        //商品表
        List<CanyinEntity> canyinList = new ArrayList<>();
        //购物车ids
        List<Integer> cartIds = new ArrayList<>();

        BigDecimal zhekou = new BigDecimal(1.0);
        // 获取折扣
        Wrapper<DictionaryEntity> dictionary = new EntityWrapper<DictionaryEntity>()
                .eq("dic_code", "huiyuandengji_types")
                .eq("dic_name", "会员等级类型")
                .eq("code_index", yonghuEntity.getHuiyuandengjiTypes())
                ;
        DictionaryEntity dictionaryEntity = dictionaryService.selectOne(dictionary);
        if(dictionaryEntity != null ){
            zhekou = BigDecimal.valueOf(Double.valueOf(dictionaryEntity.getBeizhu()));
        }

        //循环取出需要的数据
        for (Map<String, Object> map : canyins) {
           //取值
            Integer canyinId = Integer.valueOf(String.valueOf(map.get("canyinId")));//商品id
            Integer buyNumber = Integer.valueOf(String.valueOf(map.get("buyNumber")));//购买数量
            CanyinEntity canyinEntity = canyinService.selectById(canyinId);//购买的商品
            String id = String.valueOf(map.get("id"));
            if(StringUtil.isNotEmpty(id))
                cartIds.add(Integer.valueOf(id));

            //判断商品的库存是否足够
            if(canyinEntity.getCanyinKucunNumber() < buyNumber){
                //商品库存不足直接返回
                return R.error(canyinEntity.getCanyinName()+"的库存不足");
            }else{
                //商品库存充足就减库存
                canyinEntity.setCanyinKucunNumber(canyinEntity.getCanyinKucunNumber() - buyNumber);
            }

            //订单信息表增加数据
            CanyinOrderEntity canyinOrderEntity = new CanyinOrderEntity<>();

            //赋值订单信息
            canyinOrderEntity.setCanyinOrderUuidNumber(canyinOrderUuidNumber);//餐饮订单号
            canyinOrderEntity.setCanyinId(canyinId);//餐饮
            canyinOrderEntity.setYonghuId(userId);//用户
            canyinOrderEntity.setBuyNumber(buyNumber);//购买数量 ？？？？？？
            canyinOrderEntity.setCanyinOrderTypes(1);//订单类型
            canyinOrderEntity.setCanyinOrderPaymentTypes(canyinOrderPaymentTypes);//支付类型
            canyinOrderEntity.setInsertTime(new Date());//订单创建时间
            canyinOrderEntity.setCreateTime(new Date());//创建时间

            //判断是什么支付方式 1代表余额 2代表积分
            if(canyinOrderPaymentTypes == 1){//余额支付
                //计算金额
                Double money = new BigDecimal(canyinEntity.getCanyinNewMoney()).multiply(new BigDecimal(buyNumber)).multiply(zhekou).doubleValue();

                if(yonghuEntity.getNewMoney() - money <0 ){
                    return R.error("余额不足,请充值！！！");
                }else{
                    //计算所获得积分
                    Double buyJifen =0.0;
                        buyJifen = new BigDecimal(canyinEntity.getCanyinPrice()).multiply(new BigDecimal(buyNumber)).doubleValue();
                    yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() - money); //设置金额
                    yonghuEntity.setYonghuSumJifen(yonghuEntity.getYonghuSumJifen() + buyJifen); //设置总积分
                    yonghuEntity.setYonghuNewJifen(yonghuEntity.getYonghuNewJifen() + buyJifen); //设置现积分
                        if(yonghuEntity.getYonghuSumJifen()  < 10000)
                            yonghuEntity.setHuiyuandengjiTypes(1);
                        else if(yonghuEntity.getYonghuSumJifen()  < 100000)
                            yonghuEntity.setHuiyuandengjiTypes(2);
                        else if(yonghuEntity.getYonghuSumJifen()  < 1000000)
                            yonghuEntity.setHuiyuandengjiTypes(3);


                    canyinOrderEntity.setCanyinOrderTruePrice(money);

                }
            }
            canyinOrderList.add(canyinOrderEntity);
            canyinList.add(canyinEntity);

        }
        canyinOrderService.insertBatch(canyinOrderList);
        canyinService.updateBatchById(canyinList);
        yonghuService.updateById(yonghuEntity);
        if(cartIds != null && cartIds.size()>0)
            cartService.deleteBatchIds(cartIds);
        return R.ok();
    }

    /**
    * 退款
    */
    @RequestMapping("/refund")
    public R refund(Integer id, HttpServletRequest request){
        logger.debug("refund方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));

            CanyinOrderEntity canyinOrder = canyinOrderService.selectById(id);
            Integer buyNumber = canyinOrder.getBuyNumber();
            Integer canyinOrderPaymentTypes = canyinOrder.getCanyinOrderPaymentTypes();
            Integer canyinId = canyinOrder.getCanyinId();
            if(canyinId == null)
                return R.error(511,"查不到该餐饮");
            CanyinEntity canyinEntity = canyinService.selectById(canyinId);
            if(canyinEntity == null)
                return R.error(511,"查不到该餐饮");
            Double canyinNewMoney = canyinEntity.getCanyinNewMoney();
            if(canyinNewMoney == null)
                return R.error(511,"餐饮价格不能为空");

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
            if(canyinOrderPaymentTypes == 1){//余额支付
                //计算金额
                Double money = canyinEntity.getCanyinNewMoney() * buyNumber  * zhekou;
                //计算所获得积分
                Double buyJifen = 0.0;
                buyJifen = new BigDecimal(canyinEntity.getCanyinPrice()).multiply(new BigDecimal(buyNumber)).doubleValue();
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

            canyinEntity.setCanyinKucunNumber(canyinEntity.getCanyinKucunNumber() + buyNumber);



            canyinOrder.setCanyinOrderTypes(2);//设置订单状态为退款
            canyinOrderService.updateById(canyinOrder);//根据id更新
            yonghuService.updateById(yonghuEntity);//更新用户信息
            canyinService.updateById(canyinEntity);//更新订单中餐饮的信息
            return R.ok();
    }


    /**
     * 派送
     */
    @RequestMapping("/deliver")
    public R deliver(Integer id ,String canyinOrderCourierNumber, String canyinOrderCourierName){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        CanyinOrderEntity  canyinOrderEntity = new  CanyinOrderEntity();;
        canyinOrderEntity.setId(id);
        canyinOrderEntity.setCanyinOrderTypes(3);
        canyinOrderEntity.setCanyinOrderCourierNumber(canyinOrderCourierNumber);
        canyinOrderEntity.setCanyinOrderCourierName(canyinOrderCourierName);
        boolean b =  canyinOrderService.updateById( canyinOrderEntity);
        if(!b){
            return R.error("派送出错");
        }
        return R.ok();
    }














    /**
     * 收货
     */
    @RequestMapping("/receiving")
    public R receiving(Integer id){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        CanyinOrderEntity  canyinOrderEntity = new  CanyinOrderEntity();
        canyinOrderEntity.setId(id);
        canyinOrderEntity.setCanyinOrderTypes(4);
        boolean b =  canyinOrderService.updateById( canyinOrderEntity);
        if(!b){
            return R.error("收货出错");
        }
        return R.ok();
    }



    /**
    * 评价
    */
    @RequestMapping("/commentback")
    public R commentback(Integer id, String commentbackText, Integer canyinCommentbackPingfenNumber, HttpServletRequest request){
        logger.debug("commentback方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
            CanyinOrderEntity canyinOrder = canyinOrderService.selectById(id);
        if(canyinOrder == null)
            return R.error(511,"查不到该订单");
        if(canyinOrder.getCanyinOrderTypes() != 4)
            return R.error(511,"您不能评价");
        Integer canyinId = canyinOrder.getCanyinId();
        if(canyinId == null)
            return R.error(511,"查不到该餐饮");

        CanyinCommentbackEntity canyinCommentbackEntity = new CanyinCommentbackEntity();
            canyinCommentbackEntity.setId(id);
            canyinCommentbackEntity.setCanyinId(canyinId);
            canyinCommentbackEntity.setYonghuId((Integer) request.getSession().getAttribute("userId"));
            canyinCommentbackEntity.setCanyinCommentbackText(commentbackText);
            canyinCommentbackEntity.setInsertTime(new Date());
            canyinCommentbackEntity.setReplyText(null);
            canyinCommentbackEntity.setUpdateTime(null);
            canyinCommentbackEntity.setCreateTime(new Date());
            canyinCommentbackService.insert(canyinCommentbackEntity);

            canyinOrder.setCanyinOrderTypes(5);//设置订单状态为已评价
            canyinOrderService.updateById(canyinOrder);//根据id更新
            return R.ok();
    }












}
