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
	        String fileOrgName = CmmUtil.nvl((String)model.get("fileOrgName"));
	        response.setContentType(getContentType());
	        response.setContentLength((int)file.length());
	        String userAgent = request.getHeader("User-Agent");
	        boolean ie = userAgent.indexOf("MSIE") > -1;
	        
	        if(ie){
	            fileOrgName = URLEncoder.encode(fileOrgName, "utf-8");
	        } else {
	            fileOrgName = new String(fileOrgName.getBytes("utf-8"));
	        }// end if;
	        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileOrgName + "\";");
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
	}
