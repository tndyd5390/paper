package com.paper.util;

import java.text.DecimalFormat;

public class CmmUtil {
	public static String nvl(String str, String chg_str) {
		String res;

		if (str == null) {
			res = chg_str;
		} else if (str.equals("")) {
			res = chg_str;
		} else {
			res = str;
		}
		return res;
	}
	
	public static String nvl(String str){
		return nvl(str,"");
	}
	
	public static String[] nvlArr(String[] strs){
		for(int i = 0; i< strs.length; i++){
			strs[i] = nvl(strs[i]);
		}
		return strs;
	}
	
	public static String nvlInt(String str){
		return nvl(str, "0");
	}
	
	public static String checked(String str, String com_str){
		if(str.equals(com_str)){
			return " checked";
		}else{
			return "";
		}
	}
	
	public static String checked(String[] str, String com_str){
		for(int i=0;i<str.length;i++){
			if(str[i].equals(com_str))
				return " checked";
		}
		return "";
	}
	
	public static String select(String str,String com_str){
		if(str.equals(com_str)){
			return " selected";
		}else{
			return "";
		}
	}
	
	public static String exchangeEscape(String value){
		value = value.replaceAll("& lt;", "<").replaceAll("& gt;", ">");
        value = value.replaceAll("& #40;", "\\(").replaceAll("& #41;", "\\)");
        value = value.replaceAll("& #39;", "'");
        return value;
	}
	public static String replaceBr(String str){
		str = str.replaceAll("\n", "</br>");
		return str;
	}	
	public static String exchangeEscapeNvl(String value){
		value = CmmUtil.nvl(value);
		value = exchangeEscape(value);
		return value;
	}
	
	public static String addComma(int value){
		DecimalFormat df = new DecimalFormat("#,##0");
		return df.format(value);
	}
	
	public static String addComma(String value){
		if("".equals(CmmUtil.nvl(value))){
			return addComma(0);
		}
		return addComma(Integer.parseInt(value));
	}
}
