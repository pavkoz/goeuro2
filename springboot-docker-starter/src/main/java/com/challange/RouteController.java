package com.challange;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="/rest/provider/goeurobus/")
public class RouteController {

	private Logger logger = LogManager.getLogger(this.getClass());
	
	@RequestMapping(value="direct/{dep_sid}/{arr_sid}")	
	public DirectRouteResponse direct(Model model, @PathVariable("dep_sid") Integer depSid, @PathVariable("arr_sid") Integer arrSid) throws IOException{
		logger.info("direct: depSid:" + depSid + ", arrSid:" + arrSid);
		int numOfRows=0;
		boolean connectionExist = false;
		try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/data.txt"))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	numOfRows+=1;
		    	if(numOfRows==1){
		    		continue;
		    	}
				List<String> items = Arrays.asList(line.replaceFirst("\\d+\\s", "").split("\\s"));
				int beg = items.indexOf(""+depSid);
				int end = items.indexOf(""+arrSid);
				if(beg >-1 && end >-1 && end>beg){
					connectionExist= true;
					break;
				}
		    }
		}
		DirectRouteResponse ret = new  DirectRouteResponse();
		ret.setArrSid(arrSid);
		ret.setDepSid(depSid);
		ret.setDirectBusRoute(connectionExist);		
		return ret;
	}
}