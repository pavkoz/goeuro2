package com.challange;

import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/rest/provider/goeurobus/")
public class RouteController {

	private Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	RouteService routeService;
	
    public RouteController(){
    	logger.info("initt");
    }
	
	@RequestMapping(value="direct/{dep_sid}/{arr_sid}")	
	public @ResponseBody DirectRouteResponse direct(Model model, @PathVariable("dep_sid") Integer depSid, @PathVariable("arr_sid") Integer arrSid) throws IOException{
		logger.info("direct: depSid:" + depSid + ", arrSid:" + arrSid);
		boolean connectionExist = routeService.isDirectConnection(depSid, arrSid);

		DirectRouteResponse ret = new  DirectRouteResponse();
		ret.setArrSid(arrSid);
		ret.setDepSid(depSid);
		ret.setDirectBusRoute(connectionExist);		
		return ret;
	}
}