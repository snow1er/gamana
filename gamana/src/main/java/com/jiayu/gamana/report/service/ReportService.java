package com.jiayu.gamana.report.service;

import java.util.List;
import java.util.Map;

/**
 * Service contract for report
 * @author Neo.Li
 */
public interface ReportService {

	Map<String, List<?>> getReportData(String platform, String reportName, String appid, Map<String, String> queryParameter, int isCache);

	String generateSql(String appid, String name, String platform, Map<String, String> queryParameter);
}
