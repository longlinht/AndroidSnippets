/*Copyright ©2015 TommyLemon(https://github.com/TommyLemon)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/
package io.github.longlinht.library.base.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**MD5加密工具类
 */
public class MD5Util {

	/**
	 * 对字符串进行 MD5 加密
	 * 
	 * @param str
	 *            待加密字符串
	 * @return 加密后字符串
	 */
	public static String MD5(String str) {
		if (StringUtil.isNotEmpty(str, false) == false) {
			return "";
		}
		try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes("UTF-8"));

			byte[] hash = md.digest();
			StringBuilder secpwd = new StringBuilder();
			for (int i = 0; i < hash.length; i++) {
				int v = hash[i] & 0xFF;
				if (v < 16) secpwd.append(0);
				secpwd.append(Integer.toString(v, 16));
			}
			return secpwd.toString();
        } catch (Exception e) {
		    return "";
        }
	}

	public static String md5(String str){
		try {
			MessageDigest md=MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte[] b=md.digest();

			int temp;
			StringBuffer sb=new StringBuffer("");
			for ( int offset = 0; offset <b.length ; offset++ ) {
				temp=b[offset];
				if(temp<0) temp+=256;
				if(temp<16) sb.append("0");
				sb.append(Integer.toHexString(temp));
			}
			str=sb.toString();

		} catch ( NoSuchAlgorithmException e ) {
			e.printStackTrace();
		}
		return str;
	}
}
