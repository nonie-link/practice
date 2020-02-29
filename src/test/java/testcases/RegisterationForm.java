package testcases;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.TestBase;
import utilites.TestUtil;

public class RegisterationForm extends TestBase {
	
	
	@Test(dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void registerationForm(Hashtable <String, String> data)  {
		
		sendInput("username_ID", data.get("username"));
		sendInput("password_ID", data.get("password"));
		sendInput("username_description_ID", data.get("username_desr"));
		click("login_btn_CSS");
		Assert.assertTrue(isElementPresent("Logout"), "This has been found");
		click("logout_LINK");
	}
	

}
