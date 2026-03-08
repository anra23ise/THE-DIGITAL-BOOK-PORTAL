package com.DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
   private static Connection conn;

   public static Connection getConn() {
      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "admin");
      } catch (Exception var1) {
         var1.printStackTrace();
      }

      return conn;
   }
}