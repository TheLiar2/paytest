package com.btba.controller.wxtest;


import com.btba.dataobject.PreOrder;
import com.btba.utils.HttpUtil;
import com.btba.utils.MD5Util;
import com.btba.utils.XmlUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * @author xiaokuli
 * @date 2019/5/30 - 16:12
 */
@Controller()
@RequestMapping("/wxpay")
@Slf4j
public class WxTest {
    //应用id
    @Value("${pay.wxpay.appId}")
    private String appId;

    //应用密钥
    @Value("${pay.wxpay.appSecret}")
    private String appSecret;

    //商户id
    @Value("${pay.wxpay.mchId}")
    private String mchId;

    //商户密钥
    @Value("${pay.wxpay.mchSecret}")
    private String mchSecret;

    //服务器ip地址
    @Value("${pay.wxpay.SPBILL_CREATE_IP}")
    private String SPBILL_CREATE_IP;

    //微信异步回调地址
    @Value("${pay.wxpay.NOTIFY_URL}")
    private String NOTIFY_URL;

    //支付方式
    @Value("${pay.wxpay.TRADE_TYPE}")
    private String TRADE_TYPE;

    //统一下单地址
    @Value("${pay.wxpay.PLACEANORDER_URL}")
    private String PLACEANORDER_URL;

    @RequestMapping("/index")
    public String wxIndex(Model model) throws Exception {
        model.addAttribute("createQcodeUrl","http://9c92e1ab.ngrok.io/wxpay/createPreOrder");
        return "index";
    }

    @RequestMapping("/createPreOrder")
    public String createPreOrder(Model model) throws Exception {
        System.out.println("111进入支付部分");
        // 商品描述
        String body = "广州挂靠服务";
        // 商户订单号
        String out_trade_no =String.valueOf(System.currentTimeMillis());
        // 订单价格
        String total_fee = "2";

        //生成预付款订单
        PreOrder preOrder = new PreOrder();

        //生成随机字符串
        String nonce_str = UUID.randomUUID().toString().trim().replaceAll("-", "");
        preOrder.setAppid(this.appId);
        preOrder.setBody(body);
        preOrder.setMch_id(this.mchId);
        preOrder.setNotify_url(this.NOTIFY_URL);
        preOrder.setOut_trade_no(out_trade_no);
        preOrder.setTotal_fee(Integer.parseInt(total_fee));
        preOrder.setNonce_str(nonce_str);
        preOrder.setTrade_type(this.TRADE_TYPE);
        preOrder.setSpbill_create_ip(this.SPBILL_CREATE_IP);

        SortedMap<Object, Object> p = new TreeMap<Object, Object>();
        p.put("appid", this.appId);
        p.put("mch_id", this.mchId);

        p.put("body", body);
        p.put("nonce_str", nonce_str);
        p.put("out_trade_no", out_trade_no);
        p.put("total_fee", total_fee);
        p.put("spbill_create_ip", this.SPBILL_CREATE_IP);
        p.put("notify_url", this.NOTIFY_URL);
        p.put("trade_type", this.TRADE_TYPE);

        // 获得签名
        String sign = createSign("utf-8", p, this.mchSecret);
        preOrder.setSign(sign);
        //object -> String
        String xml = XmlUtil.object2Xml(preOrder);

        //调用统一下单接口
        String returnXml = HttpUtil.sendPost(this.PLACEANORDER_URL,xml);

        System.out.println(2222+""+returnXml);
        //String -> object
        PreOrder result = XmlUtil.xml2Object(returnXml,PreOrder.class);

        model.addAttribute("qrCodeUrl",result.getCode_url());
        return "payQrCode";
    }

    public static String createSign(String characterEncoding,SortedMap<Object,Object> parameters,String key){
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();//所有参与传参的参数按照accsii排序（升序）
        Iterator it = es.iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            Object v = entry.getValue();
            if(null != v && !"".equals(v)
                    && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + key);
//        System.out.println("签名字符串:"+sb.toString());
//        System.out.println("签名MD5未变大写：" + MD5Util.MD5Encode(sb.toString(), characterEncoding));
        String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
        return sign;
    }


    @RequestMapping(value = "/notify")
    public void Mynotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request==null||response==null) {
            System.out.println("请求出错");
        }
        System.out.println("进入回调地址函数");
        InputStream inStream = request.getInputStream();
        BufferedReader in = null;
        String result = "";
        in = new BufferedReader(
                new InputStreamReader(inStream));
        String line;
        while ((line = in.readLine()) != null) {
            result += line;
        }
        PreOrder pr = (PreOrder)XmlUtil.xml2Object(result, PreOrder.class);

        System.out.println(pr.toString());

        boolean isPaid = pr.getReturn_code().equals("SUCCESS") ? true : false;
        // 查询该笔订单在微信那边是否成功支付
        // 支付成功，商户处理后同步返回给微信参数
        PrintWriter writer = response.getWriter();
        if (isPaid) {
            System.out.println("================================= 支付成功 =================================");

            // ====================== 操作商户自己的业务，比如修改订单状态，生成支付流水等 start ==========================
            // TODO
            //this.isOrderPaid = true;
            // ============================================ 业务结束， end ==================================
            // 通知微信已经收到消息，不要再给我发消息了，否则微信会8连击调用本接口
            // String noticeStr = setXML("SUCCESS", "");
            Map<String,String> map = new HashMap<>();
            map.put("return_code","SUCCESS");
            map.put("return_msg","OK");
            String s = XmlUtil.mapToXml(map);
//            String noticeStr = XmlUtil.object2Xml("SUCCESS");
            writer.write(s);
            writer.flush();

        } else {
            System.out.println("================================= 支付失败 =================================");

            // 支付失败
            //String noticeStr = setXML("FAIL", "");
            String noticeStr = XmlUtil.object2Xml("FAIL");
            writer.write(noticeStr);
            writer.flush();
        }
    }




}
