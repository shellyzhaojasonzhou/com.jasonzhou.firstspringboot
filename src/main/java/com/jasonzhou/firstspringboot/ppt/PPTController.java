package com.jasonzhou.firstspringboot.ppt;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aspose.slides.Presentation;
import com.aspose.slides.SaveFormat;
import com.google.gson.Gson;

@Controller
@RequestMapping("pptController")
public class PPTController {
	
	@RequestMapping("/toScreenshotsView")
	@ResponseBody
    public ModelAndView  toScreenshotsView() {
        return new ModelAndView("html2canvas/screenshots");
    }
	
	@RequestMapping(value="/toDownloadppt",method= RequestMethod.GET)
	@ResponseBody
	public void toDownloadppt(@RequestParam("base64List")  String base64List,HttpServletResponse res)
	{
		Gson gson = new Gson();
		ParameterizedTypeReference<List<String>> type = new ParameterizedTypeReference<List<String>>() {
		};
		List<String> list = gson.fromJson(base64List, type.getType());
		Presentation presentation = new Presentation();
			presentation.save("e:\\pptDemo.ppt", SaveFormat.Ppt);
			String fileName = "pptDemo.ppt";
		    res.setHeader("content-type", "application/octet-stream");
		    res.setContentType("application/octet-stream");
		    res.setHeader("Content-Disposition", "attachment;filename=" +fileName);
		    byte[] buff = new byte[1024];
		    BufferedInputStream bis = null;
		    OutputStream os = null;
		    try {
		      os = res.getOutputStream();
		      bis = new BufferedInputStream(new FileInputStream(new File("e:\\"+fileName)));
		      int i = bis.read(buff);
		      while (i != -1) {
		        os.write(buff, 0, buff.length);
		        os.flush();
		        i = bis.read(buff);
		      }
		    } catch (IOException e) {
		      e.printStackTrace();
		    } finally {
		      if (bis != null) {
		        try {
		          bis.close();
		        } catch (IOException e) {
		          e.printStackTrace();
		        }
		      }
		    }
		    System.out.println("success");
	}
}
