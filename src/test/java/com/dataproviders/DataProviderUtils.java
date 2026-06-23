package com.dataproviders;

import java.util.Iterator;

import org.testng.annotations.DataProvider;

import com.api.utils.CsvReaderUtil;
import com.dataproviders.api.bean.UserPojo;

public class DataProviderUtils {
	
	@DataProvider(name = "LoginAPIDataProvider",parallel = true)
	public static Iterator<UserPojo> loginAPIDataProvider() {
		return CsvReaderUtil.loadCSV("test-data/LoginCreds.csv");
		
	}
}
