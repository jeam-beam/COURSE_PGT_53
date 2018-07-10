package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().goToContactList();
    int before = app.getContactHelper().getContactCount();
    if (! app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().goToContactPage();
      app.getContactHelper().creationContact(new ContactData("Firstname1", "Middlename", "Lastname",
              "Nickname", "Titel", "Company", "Address", "7-77-77-77", "2-22-22-22",
              "1-11-11-11", "Test@mail.com", "test11"), true);
    }
    app.getContactHelper().selectContact(before - 1);
    app.getContactHelper().deleteContact();
    app.getContactHelper().accept();
    app.getNavigationHelper().goToContactList();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before - 1);
  }
}
