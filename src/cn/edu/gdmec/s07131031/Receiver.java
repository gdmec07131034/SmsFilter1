package cn.edu.gdmec.s07131031;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class Receiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Bundle bundle = intent.getExtras();   
		//通过pdus可以获得接收到的所有信息               
        Object[] object = (Object[])bundle.get("pdus");   
      //构建短信对象array，并依据收到的对象长度来创建array的大小    
        SmsMessage sms[]=new SmsMessage[object.length];   
        for(int i=0;i<object.length;i++)   
        {   
            sms[0] = SmsMessage.createFromPdu((byte[])object[i]);  
			//如果在电话号码等于被拦截的号码，就弹窗出来，并终止广播，这样，系统就不会有受到短信的提示，用户也就收不到短信了
    		if(sms[i].getDisplayOriginatingAddress().toString().equals("110")){
                Toast.makeText(context, "号码："+sms[i].getDisplayOriginatingAddress()+" 发来的短信已被拦截，内容是："+sms[i].getDisplayMessageBody(), Toast.LENGTH_SHORT).show();   
    			abortBroadcast();                                  
    		};
        }   
 	}  
}


