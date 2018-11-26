package com.sise.shop.utilis.shortMessage;

import java.util.Random;

public class MessageSentUtils {

	public  String messageUtil(String phoneNumber) throws Exception {
		try {
			//请根据实际 accesskey 和 secretkey 进行开发，以下只作为演示 sdk 使用
			String accesskey = "5bf76e0587b65f6111742406";
			String secretkey = "332ee0734743455984d0deb5ff535f5c";
			//初始化单发
			SmsSingleSender singleSender = new SmsSingleSender(accesskey, secretkey);
			SmsSingleSenderResult singleSenderResult;
			//随机生成数
			Random random = new Random();
			String result = "";
			for (int i = 0; i < 6; i++) {
				result += random.nextInt(10);
			}
			//普通单发,注意前面必须为【】符号包含，置于头或者尾部。
			singleSenderResult = singleSender.send(0, "86", phoneNumber, "【Kewail科技】尊敬的用户：您的验证码：" + result + "，工作人员不会索取，请勿泄漏。", "", "");
			System.out.println(singleSenderResult);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}



	    	//语音验证码发送
    		//SmsVoiceVerifyCodeSender smsVoiceVerifyCodeSender = new SmsVoiceVerifyCodeSender(accesskey,secretkey);
    		//SmsVoiceVerifyCodeSenderResult smsVoiceVerifyCodeSenderResult = smsVoiceVerifyCodeSender.send("86",phoneNumber, "444144",2,"");
    		//System.out.println(smsVoiceVerifyCodeSenderResult);
