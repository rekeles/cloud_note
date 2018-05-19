package cn.tedu.cloud_note.util;

import java.security.MessageDigest;
import java.util.UUID;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class NoteUtil {
	
	//UUID获取id
	public static String createId() {
		UUID uuid=UUID.randomUUID();
		String id=uuid.toString();
		//把生成的UUID字符串中的-去掉
		return id.replace("-", "");
	}
	
	//md5加密
	public static String md5(String str) {
		try {

			MessageDigest md = MessageDigest.getInstance("MD5");

			byte[] input = str.getBytes();
			byte[] output = md.digest(input);

			// 采用base64算法将字节数组转换为字符串
			String ret = Base64.encode(output);
			return ret;
		} catch (Exception e) {
			throw new NoteException("加密失败", e);
		}
	}

	public static void main(String[] args) {
		System.out.println(md5("123"));
	}
}
