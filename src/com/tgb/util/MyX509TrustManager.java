package com.tgb.util;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * 自定义信任管理器
 * @author Administrator
 *
 */
public class MyX509TrustManager implements X509TrustManager{

	@Override
	public void checkClientTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {
		//检查客户端证书，若不信任则抛出异常，该处不需要验证，故不做处理
		
	}

	@Override
	public void checkServerTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {
			//检查服务端证书，若不信任则抛出异常，该处不需验证，故不做处理
	}

	//返回受信任的X509证书组
	@Override
	public X509Certificate[] getAcceptedIssuers(){
		return null;
	}

}
