import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ex01 {
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
      //DBに接続
      con = DriverManager.getConnection(url, user, password);

      sql =  """
          SELECT id, name
          FROM departments;
          """;

          //SQL実行準備
          pstmt = con.prepareStatement(sql);

          //SQL実行
          rs = pstmt.executeQuery();

          //結果の出力
          while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            System.out.print("id=" + id);
            System.out.print(" name=" + name);
            System.out.println();
          }

     } catch (SQLException ex) {
      System.out.println("SQL関連の例外が発生しました");
      System.out.println("発行したSQLは「" + sql + "」");
      ex.printStackTrace();

     } finally {
      //メモリの解放
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
