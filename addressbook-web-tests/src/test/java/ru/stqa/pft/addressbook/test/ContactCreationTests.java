package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {

    app.getNavigationHelper().goToContactPage();
    app.getContactHelper().fillContactForm(new ContactData("Firstname1", "Middlename", "Lastname",
            "Nickname", "Titel", "Company", "Address", "7-77-77-77", "2-22-22-22",
            "1-11-11-11", "Test@mail.com", "test11"), true);
    app.getContactHelper().inputContact();
    app.getContactHelper().returnToContactPage();
  }

}
