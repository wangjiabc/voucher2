package com.voucher.weixin.controller;

import java.io.BufferedReader;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.voucher.manage.dao.FinanceDAO;
import com.voucher.manage.model.Notice;
import com.voucher.manage.model.Users;
import com.voucher.manage.model.WeiXin;
import com.voucher.manage.service.NoticeService;
import com.voucher.manage.service.UserService;
import com.voucher.manage.service.WeiXinService;
import com.voucher.manage.singleton.Singleton;
import com.voucher.manage.tools.Md5;
import com.voucher.manage.tools.MyTestUtil;
import com.voucher.sqlserver.context.Connect;
import com.voucher.weixin.util.HttpUtils;
import com.voucher.weixin.wxpay.sdk.WXConstant;
import com.voucher.weixin.wxpay.sdk.WXPayUtil;


@Controller
@RequestMapping("/mobile/weinXinPay")
public class WeinXinPayController {

	Logger logger = LoggerFactory.getLogger(WeinXinPayController.class);
	
	ApplicationContext applicationContext=new Connect().get();
		
	FinanceDAO financeDAO=(FinanceDAO) applicationContext.getBean("financeDao");
	
	private UserService userService;

	private WeiXinService weixinService;
	
	private NoticeService noticeService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setWeixinService(WeiXinService weixinService) {
		this.weixinService = weixinService;
	}

	@Autowired
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	@RequestMapping("/getHire")
	public @ResponseBody List getHire(String value, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JSONObject json = JSONObject.parseObject(value);

		Integer campusId = json.getInteger("campusId");
		
		String homeUrl=request.getHeader("Host")+request.getContextPath();

		String spbill_create_ip = WXConstant.spbill_create_ip;

		WeiXin weixin = weixinService.getCampusById(campusId);
		
		String notify_url = weixin.getUrl()+WXConstant.notify_url;

		String text = json.getString("text");
		String guids = json.getString("guid");
		int hire = (int) (json.getFloat("hire")* 100);

		String[] guidsString = guids.split(",");

		String openId = (String) request.getSession().getAttribute("openId");

		System.out.println("openId=" + openId);

		String appId = weixin.getAppId();

		String mch_id = weixin.getMchId();

		String api = weixin.getApi();

		String nonce_str = WXPayUtil.generateNonceStr();

		String out_trade_no = Md5.GetMD5Code(UUID.randomUUID().toString());

		int total_fee = hire;

		Map<String, String> map = new HashMap<String, String>();
		//生成sign所需字段
		map.put("appid", appId);
		map.put("body", WXConstant.body);
		map.put("mch_id", mch_id);
		map.put("nonce_str", nonce_str);
		map.put("openid", openId);
		map.put("out_trade_no", out_trade_no);
		map.put("spbill_create_ip", spbill_create_ip);
		map.put("total_fee", String.valueOf(total_fee));
		map.put("trade_type", WXConstant.trade_type);
		map.put("notify_url", notify_url);
		
		
		String sign = WXPayUtil.generateSignature(map, weixin.getApi());

		logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++sign:{}", sign);
		System.out.println(sign);

		map.put("sign", sign);


		String xml = WXPayUtil.mapToXml(map);

		logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++xml:{}", xml);

		HttpUtils httpUtils=new HttpUtils();
		
		String xmlStr=httpUtils.doPost(WXConstant.unifiedorder_url, xml);
				 
		String prepay_id = "";// 预支付id

		List result = new ArrayList();

		Map<String, String> returnMap = new HashMap<String, String>();

		returnMap = WXPayUtil.xmlToMap(xmlStr);
		
		logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++returnMap:{}", returnMap);

		String return_code = returnMap.get("return_code");

		if (return_code.equals("SUCCESS")) {

			String result_code = returnMap.get("result_code");

			if (result_code.equals("SUCCESS")) {

				Map<String, String> payMap = new HashMap<String, String>();
				
				payMap.put("appId", appId);

				payMap.put("timeStamp", String.valueOf(WXPayUtil.getCurrentTimestampMs()));

				payMap.put("nonceStr", WXPayUtil.generateNonceStr());
				
				payMap.put("signType", "MD5");

				prepay_id = returnMap.get("prepay_id");

				payMap.put("package", "prepay_id=" + prepay_id);

				String paySign = WXPayUtil.generateSignature(payMap, weixin.getApi());
				
				payMap.put("paySign", paySign);

				MyTestUtil.print(payMap);
				
				String xmlpay=WXPayUtil.mapToXml(payMap);
				
				result.add("SUCCESS");
				
				result.add(payMap);

				LinkedHashMap<String,Map<String, Object>> registerMap=Singleton.getInstance().getRegisterMapLong();
				
				Map tradeMap=new HashMap<>();
				
				tradeMap.put("guids", guids);
				
				tradeMap.put("startTime", new Date());
				
				tradeMap.put("map", map);
				
				registerMap.put(out_trade_no, tradeMap);
								
				return result;
				
			} else {

				Map<String, String> payMap = new HashMap<String, String>();
				
				String err_code = returnMap.get("err_code");

				String err_code_des = returnMap.get("err_code_des");

				payMap.put("err_code", err_code);

				payMap.put("err_code_des", err_code_des);

				result.add("ERR");
				
				result.add(payMap);

				return result;
				
			}

		} else if (return_code.equals("FAIL")) {

			Map<String, String> payMap = new HashMap<String, String>();
			
			String return_msg = returnMap.get("return_msg");

			payMap.put("return_msg", return_msg);

			result.add("FAIL");
			
			result.add(payMap);

			return result;

		}
		
		return result;

		
	}

	
	@RequestMapping("/get")
	public void  get(@RequestParam String out_trade_no) {
		
		
		LinkedHashMap<String,Map<String, Object>> registerMap=Singleton.getInstance().getRegisterMapLong();
		
		Map tradeMap=registerMap.get(out_trade_no);
		
		MyTestUtil.print(tradeMap);
		
	}
	
	//微信支付回调函数
	@RequestMapping("/callback")
	public String callback(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		BufferedReader reader = null;

		reader = request.getReader();

		String line = "";

		String xmlString = null;

		StringBuffer inputString = new StringBuffer();

		while ((line = reader.readLine()) != null) {
			inputString.append(line);
		}

		xmlString = inputString.toString();

		request.getReader().close();

		Map<String, String> map = new HashMap<String, String>();

		String result_code = "";

		map = WXPayUtil.xmlToMap(xmlString);

		result_code = map.get("result_code");

		if (result_code.equals("SUCCESS")) {

			LinkedHashMap<String,Map<String, Object>> registerMap=Singleton.getInstance().getRegisterMapLong();
			
			String out_trade_no=map.get("out_trade_no");
			
			Map tradeMap=registerMap.get(out_trade_no);
			
			//System.out.println("trademp==========");
			
			//MyTestUtil.print(tradeMap);
			
			if(tradeMap.get("guids")==null)
				return WXConstant.FAIL;
			
			
			if(tradeMap.get("guids")!=null&&!tradeMap.get("guids").equals("")){
				
				String guids=(String) tradeMap.get("guids");
				
				String[] filesString = guids.split(",");
				
				ArrayList<String> list=new ArrayList<>();
				for (String fileString : filesString) {

					list.add(URLDecoder.decode(fileString,"utf-8"));
					
				}
				
				int i=financeDAO.updateHireSetHireListWinXinPay(list);
				
				if(i>0){
										
					Map map2=(Map) tradeMap.get("map");
					
					String openId=(String) map2.get("openid");
					
					SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			    	Date date=new Date();
					String time=sdf.format(date);
					
					Users users=userService.getUserByOnlyOpenId(openId);
					
					String name=users.getName();
					
					float total_fee=Float.valueOf(map.get("total_fee"))/100;
					
					WechatSendMessageController wechatSendMessageController=new WechatSendMessageController();
					
					Notice notice = noticeService.getTemplateIdByTitle("支付成功提醒");
					
					wechatSendMessageController.sendMessage(openId, notice.getTemplateId(),
							"支付成功提醒", "", 
							name+"你好，你已支付成功", out_trade_no, String.valueOf(total_fee), "支付租金", time,
							"", "感谢你的使用");					

					return WXConstant.SUCCESS;
					
				}else if(i==-1){
					return WXConstant.SUCCESS;
				}else{
					return WXConstant.FAIL;
				}
				
			}else{
				
				return WXConstant.FAIL;
				
			}

		}

		return WXConstant.FAIL;

	}

}
