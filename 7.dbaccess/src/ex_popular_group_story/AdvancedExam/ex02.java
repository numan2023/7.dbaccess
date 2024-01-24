package ex_popular_group_story.AdvancedExam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ex02 {
  public static void main(String[] args) {

     //定義
     String url = "jdbc:postgresql://localhost:5432/student";
     String user = "postgres";
     String password = "postgres";

     Connection con = null;
     PreparedStatement pstmt = null;
     String sql = null;

     try {
      //接続
      con = DriverManager.getConnection(url, user, password);

      //トランザクション開始
      con.setAutoCommit(false);

      //SQL作成
      sql = """
        INSERT INTO colors(id, name) VALUES(1, 'blue');
        INSERT INTO colors(id, name) VALUES(2, 'red');
        INSERT INTO colors(id, name) VALUES(3, 'green');
        INSERT INTO colors(id, name) VALUES(4, 'yellow');
        INSERT INTO colors(id, name) VALUES(5, 'purple');
        INSERT INTO colors(id, name) VALUES(6, 'orange');
          """;
      
      //SQL実行準備
      pstmt = con.prepareStatement(sql);
      int numOfUpdate = pstmt.executeUpdate();
      System.out.println(numOfUpdate + "件のデータが操作されました。");

      //コミット
      con.commit();

     } catch (SQLException ex) {
      System.out.println("SQL = " + sql);
      ex.printStackTrace();

      //ロールバック
      if (con != null) {
        try {
          con.rollback();
          System.out.println("ロールバックしました。");
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }

     } finally {
      try {
        if (con != null) {
            con.close();
        }
        if (pstmt != null) {
          pstmt.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
     }
  }
}
