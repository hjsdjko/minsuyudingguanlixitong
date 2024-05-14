
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
 * 特产订单
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/techanOrder")
public class TechanOrderController {
    private static final Logger logger = LoggerFactory.getLogger(TechanOrderController.class);

    @Autowired
    private TechanOrderService techanOrderService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private AddressService addressService;
    @Autowired
    private TechanService techanService;
    @Autowired
    private YonghuService yonghuService;
@Autowired
private CartService cartService;
@Autowired
private TechanCommentbackService techanCommentbackService;



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
        PageUtils page = techanOrderService.queryPage(params);

        //字典表数据转换
        List<TechanOrderView> list =(List<TechanOrderView>)page.getList();
        for(TechanOrderView c:list){
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
        TechanOrderEntity techanOrder = techanOrderService.selectById(id);
        if(techanOrder !=null){
            //entity转view
            TechanOrderView view = new TechanOrderView();
            BeanUtils.copyProperties( techanOrder , view );//把实体数据重构到view中

                //级联表
                AddressEntity address = addressService.selectById(techanOrder.getAddressId());
                if(address != null){
                    BeanUtils.copyProperties( address , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setAddressId(address.getId());
                    view.setAddressYonghuId(address.getYonghuId());
                }
                //级联表
                TechanEntity techan = techanService.selectById(techanOrder.getTechanId());
                if(techan != null){
                    BeanUtils.copyProperties( techan , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setTechanId(techan.getId());
                }
                //级联表
                YonghuEntity yonghu = yonghuService.selectById(techanOrder.getYonghuId());
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
    public R save(@RequestBody TechanOrderEntity techanOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,techanOrder:{}",this.getClass().getName(),techanOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            techanOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        techanOrder.setInsertTime(new Date());
        techanOrder.setCreateTime(new Date());
        techanOrderService.insert(techanOrder);
        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody TechanOrderEntity techanOrder, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,techanOrder:{}",this.getClass().getName(),techanOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            techanOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<TechanOrderEntity> queryWrapper = new EntityWrapper<TechanOrderEntity>()
            .eq("id",0)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        TechanOrderEntity techanOrderEntity = techanOrderService.selectOne(queryWrapper);
        if(techanOrderEntity==null){
            techanOrderService.updateById(techanOrder);//根据id更新
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
        techanOrderService.deleteBatchIds(Arrays.asList(ids));
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
            List<TechanOrderEntity> techanOrderList = new ArrayList<>();//上传的东西
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
                            TechanOrderEntity techanOrderEntity = new TechanOrderEntity();
//                            techanOrderEntity.setTechanOrderUuidNumber(data.get(0));                    //订单号 要改的
//                            techanOrderEntity.setAddressId(Integer.valueOf(data.get(0)));   //收货地址 要改的
//                            techanOrderEntity.setTechanId(Integer.valueOf(data.get(0)));   //特产 要改的
//                            techanOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            techanOrderEntity.setBuyNumber(Integer.valueOf(data.get(0)));   //购买数量 要改的
//                            techanOrderEntity.setTechanOrderTruePrice(data.get(0));                    //实付价格 要改的
//                            techanOrderEntity.setTechanOrderCourierName(data.get(0));                    //快递公司 要改的
//                            techanOrderEntity.setTechanOrderCourierNumber(data.get(0));                    //订单快递单号 要改的
//                            techanOrderEntity.setTechanOrderTypes(Integer.valueOf(data.get(0)));   //订单类型 要改的
//                            techanOrderEntity.setTechanOrderPaymentTypes(Integer.valueOf(data.get(0)));   //支付类型 要改的
//                            techanOrderEntity.setInsertTime(date);//时间
//                            techanOrderEntity.setCreateTime(date);//时间
                            techanOrderList.add(techanOrderEntity);


                            //把要查询是否重复的字段放入map中
                                //订单号
                                if(seachFields.containsKey("techanOrderUuidNumber")){
                                    List<String> techanOrderUuidNumber = seachFields.get("techanOrderUuidNumber");
                                    techanOrderUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> techanOrderUuidNumber = new ArrayList<>();
                                    techanOrderUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("techanOrderUuidNumber",techanOrderUuidNumber);
                                }
                        }

                        //查询是否重复
                         //订单号
                        List<TechanOrderEntity> techanOrderEntities_techanOrderUuidNumber = techanOrderService.selectList(new EntityWrapper<TechanOrderEntity>().in("techan_order_uuid_number", seachFields.get("techanOrderUuidNumber")));
                        if(techanOrderEntities_techanOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(TechanOrderEntity s:techanOrderEntities_techanOrderUuidNumber){
                                repeatFields.add(s.getTechanOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [订单号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        techanOrderService.insertBatch(techanOrderList);
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
        PageUtils page = techanOrderService.queryPage(params);

        //字典表数据转换
        List<TechanOrderView> list =(List<TechanOrderView>)page.getList();
        for(TechanOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        TechanOrderEntity techanOrder = techanOrderService.selectById(id);
            if(techanOrder !=null){


                //entity转view
                TechanOrderView view = new TechanOrderView();
                BeanUtils.copyProperties( techanOrder , view );//把实体数据重构到view中

                //级联表
                    AddressEntity address = addressService.selectById(techanOrder.getAddressId());
                if(address != null){
                    BeanUtils.copyProperties( address , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setAddressId(address.getId());
                }
                //级联表
                    TechanEntity techan = techanService.selectById(techanOrder.getTechanId());
                if(techan != null){
                    BeanUtils.copyProperties( techan , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setTechanId(techan.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(techanOrder.getYonghuId());
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
    public R add(@RequestBody TechanOrderEntity techanOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,techanOrder:{}",this.getClass().getName(),techanOrder.toString());
            TechanEntity techanEntity = techanService.selectById(techanOrder.getTechanId());
            if(techanEntity == null){
                return R.error(511,"查不到该特产");
            }
            // Double techanNewMoney = techanEntity.getTechanNewMoney();

            if(false){
            }
            else if((techanEntity.getTechanKucunNumber() -techanOrder.getBuyNumber())<0){
                return R.error(511,"购买数量不能大于库存数量");
            }
            else if(techanEntity.getTechanNewMoney() == null){
                return R.error(511,"特产价格不能为空");
            }

            //计算所获得积分
            Double buyJifen =0.0;
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");
            double balance = yonghuEntity.getNewMoney() - techanEntity.getTechanNewMoney()*techanOrder.getBuyNumber();//余额
            buyJifen = new BigDecimal(techanEntity.getTechanPrice()).multiply(new BigDecimal(techanOrder.getBuyNumber())).doubleValue();//所获积分
            if(balance<0)
                return R.error(511,"余额不够支付");
            techanOrder.setTechanOrderTypes(1); //设置订单状态为已支付
            techanOrder.setTechanOrderTruePrice(techanEntity.getTechanNewMoney()*techanOrder.getBuyNumber()); //设置实付价格
            techanOrder.setYonghuId(userId); //设置订单支付人id
            techanOrder.setTechanOrderUuidNumber(String.valueOf(new Date().getTime()));
            techanOrder.setTechanOrderPaymentTypes(1);
            techanOrder.setInsertTime(new Date());
            techanOrder.setCreateTime(new Date());
                techanEntity.setTechanKucunNumber( techanEntity.getTechanKucunNumber() -techanOrder.getBuyNumber());
                techanService.updateById(techanEntity);
                techanOrderService.insert(techanOrder);//新增订单
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
        String techanOrderUuidNumber = String.valueOf(new Date().getTime());

        //获取当前登录用户的id
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        Integer addressId = Integer.valueOf(String.valueOf(params.get("addressId")));

        Integer techanOrderPaymentTypes = Integer.valueOf(String.valueOf(params.get("techanOrderPaymentTypes")));//支付类型

        String data = String.valueOf(params.get("techans"));
        JSONArray jsonArray = JSON.parseArray(data);
        List<Map> techans = JSON.parseObject(jsonArray.toString(), List.class);

        //获取当前登录用户的个人信息
        YonghuEntity yonghuEntity = yonghuService.selectById(userId);

        //当前订单表
        List<TechanOrderEntity> techanOrderList = new ArrayList<>();
        //商品表
        List<TechanEntity> techanList = new ArrayList<>();
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
        for (Map<String, Object> map : techans) {
           //取值
            Integer techanId = Integer.valueOf(String.valueOf(map.get("techanId")));//商品id
            Integer buyNumber = Integer.valueOf(String.valueOf(map.get("buyNumber")));//购买数量
            TechanEntity techanEntity = techanService.selectById(techanId);//购买的商品
            String id = String.valueOf(map.get("id"));
            if(StringUtil.isNotEmpty(id))
                cartIds.add(Integer.valueOf(id));

            //判断商品的库存是否足够
            if(techanEntity.getTechanKucunNumber() < buyNumber){
                //商品库存不足直接返回
                return R.error(techanEntity.getTechanName()+"的库存不足");
            }else{
                //商品库存充足就减库存
                techanEntity.setTechanKucunNumber(techanEntity.getTechanKucunNumber() - buyNumber);
            }

            //订单信息表增加数据
            TechanOrderEntity techanOrderEntity = new TechanOrderEntity<>();

            //赋值订单信息
            techanOrderEntity.setTechanOrderUuidNumber(techanOrderUuidNumber);//订单号
            techanOrderEntity.setAddressId(addressId);//收货地址
            techanOrderEntity.setTechanId(techanId);//特产
            techanOrderEntity.setYonghuId(userId);//用户
            techanOrderEntity.setBuyNumber(buyNumber);//购买数量 ？？？？？？
            techanOrderEntity.setTechanOrderTypes(1);//订单类型
            techanOrderEntity.setTechanOrderPaymentTypes(techanOrderPaymentTypes);//支付类型
            techanOrderEntity.setInsertTime(new Date());//订单创建时间
            techanOrderEntity.setCreateTime(new Date());//创建时间

            //判断是什么支付方式 1代表余额 2代表积分
            if(techanOrderPaymentTypes == 1){//余额支付
                //计算金额
                Double money = new BigDecimal(techanEntity.getTechanNewMoney()).multiply(new BigDecimal(buyNumber)).multiply(zhekou).doubleValue();

                if(yonghuEntity.getNewMoney() - money <0 ){
                    return R.error("余额不足,请充值！！！");
                }else{
                    //计算所获得积分
                    Double buyJifen =0.0;
                        buyJifen = new BigDecimal(techanEntity.getTechanPrice()).multiply(new BigDecimal(buyNumber)).doubleValue();
                    yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() - money); //设置金额
                    yonghuEntity.setYonghuSumJifen(yonghuEntity.getYonghuSumJifen() + buyJifen); //设置总积分
                    yonghuEntity.setYonghuNewJifen(yonghuEntity.getYonghuNewJifen() + buyJifen); //设置现积分
                        if(yonghuEntity.getYonghuSumJifen()  < 10000)
                            yonghuEntity.setHuiyuandengjiTypes(1);
                        else if(yonghuEntity.getYonghuSumJifen()  < 100000)
                            yonghuEntity.setHuiyuandengjiTypes(2);
                        else if(yonghuEntity.getYonghuSumJifen()  < 1000000)
                            yonghuEntity.setHuiyuandengjiTypes(3);


                    techanOrderEntity.setTechanOrderTruePrice(money);

                }
            }
            techanOrderList.add(techanOrderEntity);
            techanList.add(techanEntity);

        }
        techanOrderService.insertBatch(techanOrderList);
        techanService.updateBatchById(techanList);
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

            TechanOrderEntity techanOrder = techanOrderService.selectById(id);
            Integer buyNumber = techanOrder.getBuyNumber();
            Integer techanOrderPaymentTypes = techanOrder.getTechanOrderPaymentTypes();
            Integer techanId = techanOrder.getTechanId();
            if(techanId == null)
                return R.error(511,"查不到该特产");
            TechanEntity techanEntity = techanService.selectById(techanId);
            if(techanEntity == null)
                return R.error(511,"查不到该特产");
            Double techanNewMoney = techanEntity.getTechanNewMoney();
            if(techanNewMoney == null)
                return R.error(511,"特产价格不能为空");

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
            if(techanOrderPaymentTypes == 1){//余额支付
                //计算金额
                Double money = techanEntity.getTechanNewMoney() * buyNumber  * zhekou;
                //计算所获得积分
                Double buyJifen = 0.0;
                buyJifen = new BigDecimal(techanEntity.getTechanPrice()).multiply(new BigDecimal(buyNumber)).doubleValue();
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

            techanEntity.setTechanKucunNumber(techanEntity.getTechanKucunNumber() + buyNumber);



            techanOrder.setTechanOrderTypes(2);//设置订单状态为退款
            techanOrderService.updateById(techanOrder);//根据id更新
            yonghuService.updateById(yonghuEntity);//更新用户信息
            techanService.updateById(techanEntity);//更新订单中特产的信息
            return R.ok();
    }


    /**
     * 发货
     */
    @RequestMapping("/deliver")
    public R deliver(Integer id ,String techanOrderCourierNumber, String techanOrderCourierName){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        TechanOrderEntity  techanOrderEntity = new  TechanOrderEntity();;
        techanOrderEntity.setId(id);
        techanOrderEntity.setTechanOrderTypes(3);
        techanOrderEntity.setTechanOrderCourierNumber(techanOrderCourierNumber);
        techanOrderEntity.setTechanOrderCourierName(techanOrderCourierName);
        boolean b =  techanOrderService.updateById( techanOrderEntity);
        if(!b){
            return R.error("发货出错");
        }
        return R.ok();
    }














    /**
     * 收货
     */
    @RequestMapping("/receiving")
    public R receiving(Integer id){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        TechanOrderEntity  techanOrderEntity = new  TechanOrderEntity();
        techanOrderEntity.setId(id);
        techanOrderEntity.setTechanOrderTypes(4);
        boolean b =  techanOrderService.updateById( techanOrderEntity);
        if(!b){
            return R.error("收货出错");
        }
        return R.ok();
    }



    /**
    * 评价
    */
    @RequestMapping("/commentback")
    public R commentback(Integer id, String commentbackText, Integer techanCommentbackPingfenNumber, HttpServletRequest request){
        logger.debug("commentback方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
            TechanOrderEntity techanOrder = techanOrderService.selectById(id);
        if(techanOrder == null)
            return R.error(511,"查不到该订单");
        if(techanOrder.getTechanOrderTypes() != 4)
            return R.error(511,"您不能评价");
        Integer techanId = techanOrder.getTechanId();
        if(techanId == null)
            return R.error(511,"查不到该特产");

        TechanCommentbackEntity techanCommentbackEntity = new TechanCommentbackEntity();
            techanCommentbackEntity.setId(id);
            techanCommentbackEntity.setTechanId(techanId);
            techanCommentbackEntity.setYonghuId((Integer) request.getSession().getAttribute("userId"));
            techanCommentbackEntity.setTechanCommentbackText(commentbackText);
            techanCommentbackEntity.setInsertTime(new Date());
            techanCommentbackEntity.setReplyText(null);
            techanCommentbackEntity.setUpdateTime(null);
            techanCommentbackEntity.setCreateTime(new Date());
            techanCommentbackService.insert(techanCommentbackEntity);

            techanOrder.setTechanOrderTypes(5);//设置订单状态为已评价
            techanOrderService.updateById(techanOrder);//根据id更新
            return R.ok();
    }












}
