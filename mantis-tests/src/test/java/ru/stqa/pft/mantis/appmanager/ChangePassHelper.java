package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

public class ChangePassHelper extends HelperBase {

  public ChangePassHelper(ApplicationManager app) {
    super(app);
  }

  public void login(String username, String pass) throws IOException {
    type(By.xpath("//input[@name='username']"), username);
    click(By.xpath("//input[@value='Login']"));
    type(By.xpath("//input[@name='password']"), pass);
    click(By.xpath("//input[@value='Login']"));
  }

  public void goToManageUsers() {
    wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
  }

  public void selectUser() {
    List<WebElement> users = wd.findElements(By.xpath("//table//tbody//tr"));
    click(By.xpath("//tbody//tr["+(users.size()-1)+"]//a"));
  }

  public void resetPass() {
    wd.findElement(By.xpath("//form[@id='manage-user-reset-form']/fieldset/span/input")).click();
  }

  public String getSelectedUserEmail(){
    String userEmail = wd.findElement(By.xpath("//tr[3]//input[@name='email']")).getAttribute("value");
    return userEmail;
  }

  public String getSelectedUserName(){
    String userName = wd.findElement(By.xpath("//tr[1]//input[@name='username']")).getAttribute("value");
    return userName;
  }

}
