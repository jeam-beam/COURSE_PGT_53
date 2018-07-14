package ru.stqa.pft.addressbook.test;

import javafx.collections.ObservableList;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test

  public void testContactModification() {
    app.getNavigationHelper().goToContactList();
    if (! app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().goToContactPage();
      app.getContactHelper().creationContact(new ContactData("Firstname1", "Middlename", "Lastname",
              "Nickname", "Titel", "Company", "Address", "7-77-77-77", "2-22-22-22",
              "1-11-11-11", "Test@mail.com", "test11"), true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().editContact();
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Firstname1", "Middlename", "Lastname",
            "Nickname", "Titel", "Company", "Address", "7-77-77-77", "2-22-22-22",
            "1-11-11-11", "Test@mail.com", "test11");

    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToContactPage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
//    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

  }
}
