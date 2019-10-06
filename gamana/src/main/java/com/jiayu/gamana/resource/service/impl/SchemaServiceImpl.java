package com.jiayu.gamana.resource.service.impl;

import com.jiayu.gamana.resource.dao.AppMapper;
import com.jiayu.gamana.resource.dao.SchemaMapper;
import com.jiayu.gamana.resource.dto.*;
import com.jiayu.gamana.resource.service.AppService;
import com.jiayu.gamana.resource.service.SchemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Implement <code>UserService</code>
 * @author Carrie
 */
@Service
public class SchemaServiceImpl implements SchemaService {


	@Autowired
	SchemaMapper schemaMapper;


	@Override
	public List<Schema> getByAppId(String appid) {
		return schemaMapper.findByAppid(appid);
	}

	@Override
	public Schema getByAppidXwhat(String appid, String xwhat) {
		return schemaMapper.findByAppidXwhat(appid, xwhat);
	}

	@Override
	public List<Field> getFieldByAppid(String appid) {
		List<String> schemaList = schemaMapper.findFieldByAppid(appid);
		List<String> fieldList = new ArrayList<>();
		for (String schema : schemaList) {
			String[] fields = schema.split(",");
			for (int i=0; i<fields.length; i++) {
				if (!fieldList.contains(fields[i])) {
					fieldList.add(fields[i]);
				}
			}
		}
		List<Field> fields = schemaMapper.findFieldMapByAppid(appid);
		Map<String,Field> FieldMap = new HashMap<>();
		for (Field f : fields) {
			FieldMap.put(f.getField(), f);
		}
		Set<String> keys = FieldMap.keySet();
		for (String f : fieldList) {
			if (!keys.contains(f)) {
				Field field = new Field();
				field.setAppid(appid);
				field.setField(f);
				field.setField_alias(f);
				FieldMap.put(f,field);
			}
		}
		return new ArrayList<Field>(FieldMap.values());
	}

	@Override
	public List<Event> getEventsByAppid(String appid) {
		List<String> xwhats = schemaMapper.findXwhatByAppid(appid);
		List<Event> events = schemaMapper.findEventMapByAppid(appid);
		Map<String,Event> eventMap = new HashMap<>();
		for (Event e : events) {
			eventMap.put(e.getEvent(), e);
		}
		Set<String> keys = eventMap.keySet();
		for (String xwhat : xwhats) {
			if (!keys.contains(xwhat)) {
				Event event = new Event();
				event.setAppid(appid);
				event.setEvent(xwhat);
				event.setEvent_alias(xwhat);
				eventMap.put(xwhat,event);
			}
		}
		return new ArrayList<Event>(eventMap.values());
	}

	@Override
	public List<Table> findTables() {
		List<Table> tableList = new ArrayList<>();
		List<App> appidList = schemaMapper.findTableApp();
		for (App a : appidList) {

			String appid = a.getAppid();
			int eventCount = getEventsByAppid(appid).size();
			int fieldCount = getFieldByAppid(appid).size();
			Table table = new Table(appid,a.getAppname(),eventCount,fieldCount);
			tableList.add(table);
		}
		return tableList;
	}
}
