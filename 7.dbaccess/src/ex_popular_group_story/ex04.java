package ex_popular_group_story;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ex04 {
  public static void main(String[] args) {
    /**
     * 大野さん脱退 -> 新メンバー伊賀さん(update set)
     * UPDATE members SET name = '山田 太郎', birth_day = '1997-02-12' WHERE name = '大野 智';
     */

    String url = "jdbc:postgresql://localhost:5432/student";
    String user = "postgres";
    String password = "postgres";

    Connection con = null;
    PreparedStatement pstmt = null;
    String sql = null;

    try {
      con = DriverManager.getConnection(url, user, password);
      sql = """
        UPDATE members
        SET name = '山田 太郎', birth_day = '1997-02-12'
        WHERE name = '大野 智';
          """;
      //SQL実行準備
      pstmt = con.prepareStatement(sql);
      //SQL実行
      int numOfUpdate = pstmt.executeUpdate();
      System.out.println(numOfUpdate + "件のデータを操作しました。");
      
    } catch (SQLException ex) {
      System.out.println("SQL= " + sql);
      ex.printStackTrace();
    
    } finally {
      try {
        if(con != null) {
          con.close();
        }
        if(pstmt != null) {
          pstmt.close();
        }
      } catch(SQLException e) {
        e.printStackTrace();
      }
    } 
  }
}
