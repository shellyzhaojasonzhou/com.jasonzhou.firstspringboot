package com.jasonzhou.firstspringboot.ppt;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aspose.slides.IAutoShape;
import com.aspose.slides.IParagraph;
import com.aspose.slides.IParagraphCollection;
import com.aspose.slides.IPortionCollection;
import com.aspose.slides.ISlide;
import com.aspose.slides.ITextFrame;
import com.aspose.slides.License;
import com.aspose.slides.Presentation;
import com.aspose.slides.Collections.Generic.IGenericEnumerator;

@Controller
@RequestMapping("pptController")
public class PPTController {

	private static InputStream license;
    //private static InputStream slides;

    /**
     * 获取license
     * 
     * @return
     */
    public static boolean getLicense() {
        boolean result = false;
        try {
            license = PPTController.class.getClassLoader().getResourceAsStream("\\license.xml");   // license路径
            //slides = PPTController.class.getClassLoader().getResourceAsStream("\\test.ppt");   // 原始ppt路径
            License aposeLic = new License();
            aposeLic.setLicense(license);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
	@RequestMapping("/toScreenshotsView")
	@ResponseBody
	public ModelAndView toScreenshotsView() {
		return new ModelAndView("html2canvas/screenshots");
	}

	@RequestMapping(value = "/addImageToPPT")
	@ResponseBody
	public String addImageToPPT() {
		// 验证License
        if (!getLicense()) {
            return "license未找到";
        }
		/*
		 * Gson gson = new Gson(); ParameterizedTypeReference<List<String>> type = new
		 * ParameterizedTypeReference<List<String>>() { }; List<String> list =
		 * gson.fromJson(base64List, type.getType());
		 */
		Presentation presentation = new Presentation("e:\\1.pptx");
		ISlide slide = presentation.getSlides().get_Item(0);
		IAutoShape shape = (IAutoShape) slide.getShapes().get_Item(0);
		ITextFrame textframe = shape.getTextFrame();
		IParagraphCollection paras = textframe.getParagraphs();
		int len = paras.getCount();
		IPortionCollection portionCollection = paras.get_Item(0).getPortions();
		int para =portionCollection.getCount();
		IGenericEnumerator<IParagraph>  gene = paras.iterator();
		while (gene.hasNext()) {
			System.err.println(gene.next().getText());
		}
		System.err.println("段落有多少"+len);
		System.err.println("行数有多少"+para);
		for(int i=0;i<para;i++)
		{
			System.err.println(portionCollection.get_Item(i).getText());
		}
		//presentation.save("e:\\pptDemo.ppt", SaveFormat.Ppt);
		return "pptDemo.ppt";
	}

	@RequestMapping(value = "/toDownloadppt", method = RequestMethod.GET)
	public void toDownloadppt(@RequestParam("fileName") String fileName, HttpServletResponse res) {
		res.setContentType("application/octet-stream;charset=UTF-8");
		res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		byte[] buff = new byte[1024];
		BufferedInputStream bis = null;
		OutputStream os = null;
		try {
			os = res.getOutputStream();
			bis = new BufferedInputStream(new FileInputStream(new File("e:\\" + fileName)));
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
	}
}
