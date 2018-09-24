package com.jasonzhou.firstspringboot.ppttopdf;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("ppttopdfController")
public class PpttopdfController {
	
	@RequestMapping(value="ppttopdf")
	@ResponseBody
	public String ppttopdf(HttpServletRequest httpRequest) {
		String exe = "python";
		//URL url = this.getClass().getClassLoader().getResource("manyppttopdf.py");
		
		System.out.println(System.getProperty("user.dir")+"\\src\\main\\resources\\manyppttopdf.py");
		System.out.println(this.getClass().getClassLoader().getResource("").getPath());

        String command =System.getProperty("user.dir")+"\\src\\main\\resources\\manyppttopdf.py";
        String num1 =System.getProperty("user.dir")+"\\src\\main\\resources\\111.pptx";
       String num2 = "E:\\222"+System.currentTimeMillis()+".pdf";
        String[] cmdArr = new String[] {exe,command,num1,num2};
        Process process;
		try {
			process = Runtime.getRuntime().exec(cmdArr);
			InputStream is = process.getInputStream();
	        DataInputStream dis = new DataInputStream(is);
	        @SuppressWarnings("deprecation")
			String str = dis.readLine();
	        process.waitFor();
	        System.out.println(str);
			
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       return "123";
	}
	
	public static void main(String[] args) {
		String exe = "python";
        String command ="F:\\eclipse-workspace\\com.jasonzhou.firstspringboot\\src\\main\\resources\\manyppttopdf.py";
        System.out.println(command);
        String num1 ="E:\\111.pptx";
       String num2 = "E:\\222"+System.currentTimeMillis()+".pdf";
        String[] cmdArr = new String[] {exe,command,num1,num2};
        Process process;
		try {
			process = Runtime.getRuntime().exec(cmdArr);
			InputStream is = process.getInputStream();
	        DataInputStream dis = new DataInputStream(is);
	        @SuppressWarnings("deprecation")
			String str = dis.readLine();
	        process.waitFor();
	        System.out.println(str);
			
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
