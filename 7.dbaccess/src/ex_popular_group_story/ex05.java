package ex_popular_group_story;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ex05 {
  
/**
 * delete文の処理記述
 * SQL
 * DELETE FROM members
 * WHERE name = '山田 太郎' or name = '櫻井 翔';
 * 
 * Q. executeDelete() ??
 * -> A. executeUpdate() のままでOK -> SQL文でdeleteを使う 
 */

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
    
    //SQl文作成
    sql = """
      DELETE FROM members
      WHERE name = '山田 太郎' or name = '櫻井 翔';
        """;

    //SQL実行準備
    pstmt = con.prepareStatement(sql);

    //SQL実行
    int numOfUpdate = pstmt.executeUpdate();
    System.out.println(numOfUpdate + "件のデータを操作しました。");

  } catch (SQLException ex) {
    System.out.println("SQL = " + sql);
    ex.printStackTrace();

  } finally {
    try {
      if(con != null) {
        con.close();
      }
      if(pstmt != null) {
        pstmt.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
 }
}
