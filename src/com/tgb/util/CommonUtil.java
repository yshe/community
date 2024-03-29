package com.tgb.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;





import com.tgb.entity.Token;
/**
 * 通用工具类
 * @author Administrator
 *
 */
public class CommonUtil {
	private static Logger log=Logger.getLogger(CommonUtil.class);
	//凭证获取（GET）
	public final static String token_url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	
	
	/**
	 * 发送https请求
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方法
	 * @param outputStr 提交的数据
	 * @return JSONObject（通过jsonobject.get(key)的方式获取JSON对象的属性值）
	 */
	public static JSONObject httpsRequest(String requestUrl,String requestMethod,String outputStr){
		JSONObject jsonObject=null;
		try {
			//创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm={new MyX509TrustManager()};
			SSLContext sslContext=SSLContext.getInstance("SSL","SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			//从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf=sslContext.getSocketFactory();
			
			URL url=new URL(requestUrl);
			HttpsURLConnection conn=(HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);
			
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			
			//设置请求方式（GET/POST）
			conn.setRequestMethod(requestMethod);
			
			//当outputStr 不为null时，向输出流写数据
			if(null!=outputStr){
				OutputStream outputStream=conn.getOutputStream();
				//注意编码格式
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			//从输入流读取返回内容
			InputStream inputStream=conn.getInputStream();
			InputStreamReader inputStreamReader=new InputStreamReader(inputStream,"utf-8");
			BufferedReader buffere=new BufferedReader(inputStreamReader);
			String str=null;
			StringBuffer buffer=new StringBuffer();
			while((str=buffere.readLine())!=null){
				buffer.append(str);
			}
			//释放资源
			buffere.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream=null;
			conn.disconnect();
			jsonObject=JSONObject.fromObject(buffer.toString());
		} catch(ConnectException e){
			log.error("连接超时：{}",e);
		}
		catch (Exception e) {
			log.error("https请求异常：{}"+e);
		}
		return jsonObject;
	}
	
/**
 * 获取接口访问凭证
 * @param appid  凭证
 * @param appsecret 秘钥
 * @return
 */
	public static Token getToken(String appid,String appsecret){
		
		Token token=null;
		String requestUrl=token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
		//发送GET请求获取凭证
		JSONObject jsonObject=httpsRequest(requestUrl, "GET", null);
		
		if(null!=jsonObject){
			try {
				token=new Token();
				token.setAccessToken(jsonObject.getString("access_token"));
				token.setExpiresIn(jsonObject.getInt("expires_in"));
			} catch (Exception e) {
				token=null;
				//获取token失败
				log.error("获取token失败，errcode{} errmsg:{}:"+jsonObject.getInt("errcode")+","+jsonObject.getString("errmsg"));
			}
		}
		return token;
	}
	
	
	
	
	/**
	 * 授权回调地址需要进行URL编码
	 * @return
	 */
	public static String urlEncodeUTF8(String resourceurl){
		String result=resourceurl;
		try {
			result=java.net.URLEncoder.encode(resourceurl,"utf-8");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据内容类型判断文件扩展名
	 * @param contentType 内容类型
	 * @return
	 */
	public static String getFileExt(String contentType){
		String fileExt="";
		if("image/jpeg".equals(contentType)){
			fileExt=".jpg";
		}else if("audio/mpeg".equals(contentType)){
			fileExt=".mp3";
		}else if ("audio/amr".equals(contentType)) {
			fileExt=".amr";
		}else if ("video/mp4".equals(contentType)) {
			fileExt=".mp4";
		}else if ("video/mpeg4".equals(contentType)) {
			fileExt=".mp4";
			
		}
		return fileExt;
	}
}
