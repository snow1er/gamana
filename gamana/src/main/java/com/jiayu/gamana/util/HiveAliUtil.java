package com.jiayu.gamana.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.security.UserGroupInformation;

import java.io.IOException;
import java.sql.*;
import java.util.*;


/**
 * 阿里云的hive链接工具
 *
 * @author caojian
 * @date 2019-03-22
 */
public class HiveAliUtil {

    private static HiveAliUtil instance;

    static String hiveUrl = "";
    static String hiveUsername = "";
    static String hivePassowrd = "";
    static String hiveDriverClass = "";
    //static Connection conn = null;

    private HiveAliUtil() {
        init();
    }

    public void init() {
        System.out.println("ali hive init connection...");

        ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
        hiveDriverClass = resourceBundle.getString("hive.driver");
        hiveUrl = resourceBundle.getString("hive.url");
        hiveUsername = resourceBundle.getString("hive.username");
        hivePassowrd = resourceBundle.getString("hive.pass");

        System.out.println(hiveUrl);

        Configuration conf = new Configuration();
        conf.set("hadoop.security.authentication", "kerberos");
//        System.setProperty("java.security.krb5.kdc", "ip-x-x-x-x.bc9.internal");
//        System.setProperty("java.security.krb5.realm", "ABCDEV.COM");

        //UserGroupInformation.loginUserFromKeytab("etl@DNS.RIGHTPADDLE.COM", "etl.keytab");
        try {
            UserGroupInformation.loginUserFromKeytab("bigdata@DNS.RIGHTPADDLE.COM", "/home/bigdata/security/keytabs/bigdata.keytab");
        } catch (IOException e) {
            e.printStackTrace();
        }

        UserGroupInformation.setConfiguration(conf);
        // Hive Connection
        try {
            Class.forName(hiveDriverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static HiveAliUtil getInstance() {
        if (instance == null) {
            instance = new HiveAliUtil();
        }
        return instance;
    }


    public Connection getHiveConn() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(hiveUrl, hiveUsername, hivePassowrd);
        } catch (Exception e) {
            System.out.println("ERROR: fail to connect to hive server,message is " + e.getMessage());
        }

        return conn;
    }

    public static void main(String[] args) throws SQLException {
        Connection conn = HiveAliUtil.getInstance().getHiveConn();
        String sql = "show tables";
        Statement st = conn.createStatement();
        st.execute("use dim_v8sp");
        ResultSet rs = st.executeQuery(sql);
        List<String> tableNames = new ArrayList<>();
        while (rs.next()) {
            tableNames.add(rs.getString(1));
        }
        System.out.println(tableNames);
        Map<String, Map> tableMap = new HashMap<>();
        for (String table : tableNames) {
            ResultSet fields = st.executeQuery("desc " + table);
            Map<String, String> fieldMap = new HashMap<>();
            while (fields.next()) {
                fieldMap.put(fields.getString(1),fields.getString(2));
            }
            tableMap.put(table, fieldMap);
        }
        System.out.println(tableMap.toString());
        if (rs != null) {
            rs.close();
        }
        if (st != null) {
            st.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

}
