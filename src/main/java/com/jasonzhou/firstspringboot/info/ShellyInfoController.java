package com.jasonzhou.firstspringboot.info;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("shellyInfoController")
public class ShellyInfoController {

	@RequestMapping(value="person",method = RequestMethod.GET)
	@ResponseBody
	 String person() {
		return "dbsajdbsjakdknasd";
	}
	
}
