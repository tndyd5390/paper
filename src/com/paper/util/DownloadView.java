package com.paper.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class DownloadView extends AbstractView{
	public void Download(){
		setContentType("application/download; utf-8");
	}
	 @Override
	    protected void renderMergedOutputModel(Map<String, Object> model,
	            HttpServletRequest request, HttpServletResponse response) throws Exception {
	        File file = (File)model.get("downloadFile");
	        String filename = CmmUtil.nvl((String)model.get("fileOrgName"));
	        response.setContentType(getContentType());
	        response.setContentLength((int)file.length());
	        String userAgent = request.getHeader("User-Agent");
	        boolean ie = userAgent.indexOf("MSIE") > -1;
	        String browser = getBrowser(request);
	        String encodedFilename = null; 
	        if (browser.equals("MSIE")) {
	        	encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20"); 
	        } else if (browser.equals("Firefox")) {
	        	encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\""; 
	        } else if (browser.equals("Opera")) {
	        	encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\""; 
	        } else if (browser.equals("Chrome")) { 
	        	StringBuffer sb = new StringBuffer(); 
	        	for (int i = 0; i < filename.length(); i++) { 
	        		char c = filename.charAt(i); 
	        		if (c > '~') { 
	        			sb.append(URLEncoder.encode("" + c, "UTF-8")); 
	        		} else { 
	        			sb.append(c); 
	        			} 
	        		} encodedFilename = sb.toString(); 
	        } else if(browser.equals("Trident")){
	        	encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
	        }else { 
	        	throw new RuntimeException("Not supported browser"); 
	        }

	        response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedFilename + "\";");
	        response.setHeader("Content-Transfer-Encoding", "binary");
	        OutputStream out = response.getOutputStream();
	        FileInputStream fis = null;
	         
	        try {
	            fis = new FileInputStream(file);
	            FileCopyUtils.copy(fis, out);
	        } catch(Exception e){
	            e.printStackTrace();
	        }finally{
	            if(fis != null){
	                try{
	                    fis.close();
	                }catch(Exception e){}
	            }
	        }// try end;
	        out.flush();
	    }// render() end;
	 
	private String getBrowser(HttpServletRequest request) { 
		String header = request.getHeader("User-Agent"); 
		String returnString = "";
		if (header.indexOf("MSIE") > -1) { 
			returnString ="MSIE"; 
		} else if (header.indexOf("Chrome") > -1) { 
			returnString ="Chrome";
		} else if (header.indexOf("Opera") > -1) { 
			returnString ="Opera"; 
		} else if (header.indexOf("Trident") > -1) {   // IE11 ¹®ÀÚ¿­ ±úÁü ¹æÁö
            returnString ="Trident";
		}else{
			returnString ="FireFox";
		}
		return returnString;
	}

	}
