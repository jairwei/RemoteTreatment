package com.example.remotetreatment.util;

import com.example.remotetreatment.Base;
import com.example.remotetreatment.model.UserAccount;

public class AccountUtil {

	public static UserAccount getUserAccount() {
		try {
			UserAccount user = (UserAccount) SerialUtil.readObject(Base.ACCOUNT_FILE);
			if (user == null) {
				user = new UserAccount();
				user.setId(1);
				user.setName("魏吉光");
				user.setAvatar("魏吉光");
				user.setPhone("13601361337");
				user.setPassword("123456");
				user.setToken("123");
				user.setEmail("weijiguang@gmail.com");
				user.setIdType(Base.ID_TYPE);
				user.setIdNumber("2390001929318238");
				user.setGender("男");
			}

			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void setUserAccount(UserAccount user) {
		try {
			SerialUtil.writeObject(user, Base.ACCOUNT_FILE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
