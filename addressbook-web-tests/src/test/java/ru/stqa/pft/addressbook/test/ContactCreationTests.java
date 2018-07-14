package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().goToContactList();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getNavigationHelper().goToContactPage();
    app.getContactHelper().creationContact(new ContactData("Firstname1", "Middlename", "Lastname",
              "Nickname", "Titel", "Company", "Address", "7-77-77-77", "2-22-22-22",
              "1-11-11-11", "Test@mail.com", "test11"), true);
    app.getNavigationHelper().goToContactList();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

  }

}
