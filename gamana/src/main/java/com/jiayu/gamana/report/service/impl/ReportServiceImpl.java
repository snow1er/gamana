package com.jiayu.gamana.report.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jiayu.gamana.report.service.ReportService;

/**
 * Implement <code>ReportService</code>
 * @author Neo.Li
 */
@Service
public class ReportServiceImpl implements ReportService {

	private static final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);
	
	@Override
	public Map<String, List<?>> getReportData(String platform, String reportName, String appid,
			Map<String, String> conditions, int isCache) {
		logger.info("getReportData, param platform : {}, reportname : {}, appid : {}", platform, reportName, appid);
		Map<String, List<?>> data = new HashMap<>();
		data.put("platform", Arrays.asList(platform));
		data.put("reportName", Arrays.asList(reportName));
		data.put("appid", Arrays.asList(appid));
		return data;
	}

}
