package com.sdx.lx.common.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sdx.lx.common.dal.DalClient;
import com.sdx.lx.common.utils.DateUtils;
import com.sdx.lx.common.utils.OrderBuilderTypeConstants;

@Component
public class OrderNoBuilder {

    private static final Logger logger = LoggerFactory.getLogger(OrderNoBuilder.class);
    
    @Autowired
    private DalClient dalClient;
    
    
    private final static String PREFIX_TRANSFER_OUTER_ORDER = "TOO";
    
    private final static String PREFIX_TASK_ORDER = "TAS";
    
    private int startIndex = 1;
    /**
     * 产生制定业务类型的订单号,需要初始化数据sql
     * 订单号为当天日期8位开头，后面8位是1到99999999，不足前面补0，支持一天生成1E订单号
     * 比如：2014102000001001
     * 通过乐观锁来控制并发,如果更新失败则进入嵌套循环
     * @param type
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public String createOrderNo(final String type){
        Map<String,Object> condtion = new HashMap<String,Object>();
        Date currentDate = new Date();
        String currentDateString = DateUtils.formatDate(currentDate, DateUtils.DATEFORMATE_YYYYMMDD);
        condtion.put("type", type);
        Map<String, Object> resultMap = dalClient.queryForMap("C_CODE_BUILDER.SELECT_BY_KEY", condtion);
        if(currentDateString.equals(resultMap.get("LAST_CREATE_DATE"))){
            long lastNumber = Long.valueOf(resultMap.get("LAST_NUMBER").toString());
            condtion.put("lastNumber", lastNumber+1);
            condtion.put("lastUpdateDate", currentDate);
            condtion.put("originalLastCreateDate", resultMap.get("LAST_CREATE_DATE"));
            condtion.put("originalLastNumber", resultMap.get("LAST_NUMBER"));
            int i = dalClient.execute("C_CODE_BUILDER.UPDATE_BY_FIELDS", condtion);
            logger.debug("更新行数为:{},更新条件为:{},{},{}",new Object[]{i,resultMap.get("LAST_CREATE_DATE"),resultMap.get("LAST_NUMBER"),resultMap.get("type")});
            if(i == 1){
                return formatString(currentDateString, String.valueOf(lastNumber), null);
            }else{
                return createOrderNo(type);
            }
        }
        condtion.put("lastCreateDate", currentDateString);
        condtion.put("lastNumber", startIndex+1);
        condtion.put("lastUpdateDate", currentDate);
        condtion.put("originalLastCreateDate", resultMap.get("LAST_CREATE_DATE"));
        condtion.put("originalLastNumber", resultMap.get("LAST_NUMBER"));
        int i = dalClient.execute("C_CODE_BUILDER.UPDATE_BY_FIELDS", condtion);
        if(i == 1){
            return formatString(currentDateString, String.valueOf(startIndex), null);
        }else{
            return createOrderNo(type);
        }
    }
    
    
    public String createCashOrderNo(){
        return createOrderNo(OrderBuilderTypeConstants.BUILDER_TYPE_CASH_ORDER);
    }
    
    public String createDepositOrderNo(){
        return createOrderNo(OrderBuilderTypeConstants.BUILDER_TYPE_DEPOSIT_ORDER);
    }
    
    public String createOfflineDepositOrderNo(){
        return createOrderNo(OrderBuilderTypeConstants.BUILDER_TYPE_OFFLINE_DEPOSIT_ORDER);
    }
    
    public String createTaskOrderNo(){
        return PREFIX_TASK_ORDER + createOrderNo(OrderBuilderTypeConstants.BUILDER_TYPE_TASK_ORDER);
    }
    
    public String createTradeOrderNo(){
        return createOrderNo(OrderBuilderTypeConstants.BUILDER_TYPE_TRADE_ORDER);
    }
    
    public String createTransferOuterOrderNo(){
        return PREFIX_TRANSFER_OUTER_ORDER + createOrderNo(OrderBuilderTypeConstants.BUILDER_TYPE_TRANSFER_OUTER_ORDER);
    }
    private String formatString(String prefix,String suffix,Integer length){
        Integer stringLength = null == length ? 16 : length;
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        int count = stringLength - prefix.length() - suffix.length();
        if(count <= 0){
            sb.append(suffix);
            return sb.toString();
        }
        for(int i = 0; i < count; i++){
            sb.append("0");
        }
        sb.append(suffix);
        return sb.toString();
    }
}
