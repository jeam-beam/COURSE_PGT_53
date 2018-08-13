package ru.stqa.pft.mantis.tests;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePassTest extends TestBase {


  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testChangePass() throws IOException, MessagingException {
    app.changePass().login("administrator", "root");
    app.changePass().goToManageUsers();
    app.changePass().selectUser();
    String user = app.changePass().getSelectedUserName();
    String email = app.changePass().getSelectedUserEmail();
    app.changePass().resetPass();

    long now = System.currentTimeMillis();
    String newPass = String.format("pass%s", now);

    //app.james().createUser(user, password);
    //app.registration().start(user, email);
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    //List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, newPass);
    HttpSession session = app.newSession();
    assertTrue(session.login(user, newPass));
    assertTrue(session.isLoggedInAs(user));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}
