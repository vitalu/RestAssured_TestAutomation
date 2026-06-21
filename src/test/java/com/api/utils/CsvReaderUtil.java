package com.api.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.dataproviders.api.bean.UserPojo;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CsvReaderUtil {

	private CsvReaderUtil() {

	}

	public static void loadCSV(String pathOfCSVFile) {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathOfCSVFile);
		InputStreamReader isr = new InputStreamReader(is);
		CSVReader csvReader = new CSVReader(isr);

		CsvToBean<UserPojo> csvToBean = new CsvToBeanBuilder(csvReader)
				.withType(UserPojo.class)
				.withIgnoreEmptyLine(true).build();

		List<UserPojo> userList = csvToBean.parse();
		System.out.println(userList);

	}
}
