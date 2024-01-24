package ex_popular_group_story.AdvancedExam;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ex03 {
  public static void main(String[] args) {

     //定義
     String url = "jdbc:postgresql://localhost:5432/student";
     String user = "postgres";
     String password = "postgres";

     Connection con = null;
     PreparedStatement pstmt = null;
     ResultSet rs = null;
     String sql = null;

     try {

      //接続
      con = DriverManager.getConnection(url, user, password);

      //トランザクション開始
      con.setAutoCommit(false);

      //SQL作成
      sql = """
        SELECT
          m.name
          ,m.birth_day
          ,m.gender
          ,c.name AS color_name
        FROM members m
        INNER JOIN colors c
        ON m.color_id = c.id
        ORDER BY c.id;
          """;

      //SQL実行準備
      pstmt = con.prepareStatement(sql);

      //実行
      rs = pstmt.executeQuery();
      //出力
      while (rs.next()) {
        String name = rs.getString("name");
        Date birth_day = rs.getDate("birth_day");
        String gender = rs.getString("gender");
        String color_name = rs.getString("color_name");
        System.out.print("name = " + name);
        System.out.print(" birth_day = " + birth_day);
        System.out.print(" gender = " + gender);
        System.out.print(" color_name = " + color_name);
        System.out.println();
      }

      //コミット
      con.commit();
      
     } catch (SQLException ex) {
        System.out.println("SQL = " + sql);
        ex.printStackTrace();

        //ロールバック
        if(con != null) {
          try {
            con.rollback();
            System.out.println("ロールバックしました。");
          } catch (SQLException e) {
            e.printStackTrace();
          }
        }

     } finally {
      try {
        if (con != null ) {
          con.close();          
        }
        if (pstmt != null) {
          pstmt.close();
        }
        if(rs != null) {
          rs. close();
        }
  
      } catch(SQLException e) {
        e.printStackTrace();
      }
     }
  }
}
