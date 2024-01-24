import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectSample {
  public static void main(String[] args) {
    //接続情報
    String url = "jdbc:postgresql://localhost:5432/student";
    String user = "postgres";
    String password = "postgres";

    Connection con = null; //使用する変数の宣言
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = null;

    try {
      //(1)DBに接続
      con = DriverManager.getConnection(url, user, password);

      //(2)SQL文を作成 (複数行文字列 :テキストブロック)
      sql = """
        SELECT id, name, age
        FROM employees
        ORDER BY age;
      """;

      //(3)SQL実行準備
      pstmt = con.prepareStatement(sql);

      //(4)SQL実行
      rs = pstmt.executeQuery();

      // (5)結果の出力
      while(rs.next()) {
        int id = rs.getInt("id"); // id列をint型で取り出す

        String name = rs.getString("name");
        int age = rs.getInt("age");
        System.out.print("id=" + id);
        System.out.print(" name=" + name);
        System.out.print(" age=" + age);
        System.out.println();
      }
    } catch (SQLException ex) {
      System.out.println("SQL関連の例外が発生しました。");
      System.out.println("発行したSQLは「"+ sql +"」");
      ex.printStackTrace();
    } finally {
      // (6)メモリの解放(切断処理)
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
