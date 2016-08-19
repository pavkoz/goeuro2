package com.challange;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

@Service
public class RouteService implements InitializingBean{
	
	private Logger logger = LogManager.getLogger(this.getClass());
	private Map<Integer,Set<Integer>> routes;
	
	public boolean isDirectConnection(Integer start, Integer stop){
		return routes.get(start) != null && routes.get(start).contains(stop);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("init");
		routes = new HashMap<Integer,Set<Integer>>();	
		try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/data.txt"))) {
		    String line;
		    while ((line = br.readLine()) != null) { 
				List<String> stops = Arrays.asList(line.replaceFirst("\\d+\\s", "").split("\\s"));
				
				for(int i=0; i< stops.size(); i++){
					Integer startStop = new Integer( stops.get(i)); 
					Set<Integer> termStops = routes.get( startStop) == null ? new HashSet<Integer>() : routes.get( startStop);
					for(int j=i+1; j< stops.size(); j++){
						Integer stopStop = new Integer( stops.get(j));
						termStops.add( stopStop);
					}				
					routes.put(startStop, termStops);
				}
				
		    }
		}
	}
	
}