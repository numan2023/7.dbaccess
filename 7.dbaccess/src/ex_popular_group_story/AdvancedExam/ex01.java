package ex_popular_group_story.AdvancedExam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ex01 {
  public static void main(String[] args) {

    //定義
    String url = "jdbc:postgresql://localhost:5432/student";
    String user = "postgres";
    String password = "postgres";
    String sql = """
      DROP TABLE IF EXISTS colors;
      CREATE TABLE colors(
        id integer primary key,
        name text
      );

      DROP TABLE IF EXISTS members;
      CREATE TABLE members(
        id serial primary key,
        name text not null,
        birth_day date,
        gender varchar(1),
        color_id integer REFERENCES colors(id)
      );
        """;

    try 
    ( Connection con = DriverManager.getConnection(url, user, password);
      PreparedStatement pstmt = con.prepareStatement(sql);    
    ) 
    { int numOfUpdate = pstmt.executeUpdate();
      System.out.println(numOfUpdate + "件のデータを操作しました。");
      System.out.println("コミットしました。");
      
    } catch(SQLException ex) {
      System.out.println("SQL = " + sql);
      ex.printStackTrace();
    }
  }
}
