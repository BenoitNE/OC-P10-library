package fr.benoitne.libraryweb.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.springframework.stereotype.Service;

@Service
public class DateTool {

	public String convert(String dateToConvert) {
		String inputFormat = "yyyy-MM-dd'T'HH:mm:ss";
		String outputFormat = "dd-MM-yyyy";
		Date date=null;
		try {
			date = new SimpleDateFormat(inputFormat).parse(dateToConvert);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String dateConvert = new SimpleDateFormat(outputFormat).format(date);
		return dateConvert;
	}
	

}
