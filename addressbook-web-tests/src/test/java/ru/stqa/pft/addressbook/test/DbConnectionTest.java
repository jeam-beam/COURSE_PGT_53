package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.sql.*;

public class DbConnectionTest {

  @Test
  public void testDbConnection() {
    Connection conn = null;
    try {
      conn =
              DriverManager.getConnection("jdbc:mysql://localhost/addressbook?" +
                      "user=root&password=&serverTimezone=UTC");
      // Do something with the Connection
      Statement st = conn.createStatement();
      ResultSet rS = st.executeQuery("select group_id,group_name,group_header,group_footer from group_list");
      Groups groups = new Groups();
      while (rS.next()) {
        groups.add(new GroupData().withId(rS.getInt("group_id"))
                .withName(rS.getString("group_name"))
                .withHeader(rS.getString("group_header"))
                .withFooter(rS.getString("group_footer")));
      }
      rS.close();
      st.close();
      conn.close();

      System.out.println(groups);

    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
  }

}
