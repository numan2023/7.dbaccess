package ex_popular_group_story;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ex03 {
  public static void main(String[] args) {
    //接続情報
    String url = "jdbc:postgresql://localhost:5432/student";
    String user = "postgres";
    String password = "postgres";

    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = null;

    try {

      //DB接続
      con = DriverManager.getConnection(url, user, password);

      //SQL文作成
      sql = """
        SELECT name, birth_day, gender, color_id
        FROM members
          """;

      //SQL実行準備
      pstmt = con.prepareStatement(sql);

      //SQL実行
      rs = pstmt.executeQuery();

      //結果の出力
      while (rs.next()) {
        String name = rs.getString("name");
        Date birth_day = rs.getDate("birth_day");
        String gender = rs.getString("gender");
        int color_id = rs.getInt("color_id");
        System.out.print("name= " + name);
        System.out.print(" birth_day: " + birth_day);
        System.out.print(" gender+ " + gender);
        System.out.print(" color_id+ " + color_id);
        System.out.println();
      }

    } catch (SQLException ex) {
      System.out.println("SQL関連の例外が発生しました。");
      System.out.println("発行したSQLは「" + sql + "」");
      ex.printStackTrace();
    } finally {
      try {
        if(con != null) {
          con.close();
        }
        if(pstmt != null) {
          pstmt.close();
        }
        if(rs != null) {
          rs.close();
        }
      } catch(SQLException e) {
        e.printStackTrace();
      }
    }
   }
}
