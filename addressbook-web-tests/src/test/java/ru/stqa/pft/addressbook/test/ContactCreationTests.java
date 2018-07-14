package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().goToContactList();
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData("Firstname1", "Middlename", "Lastname",
            "Nickname", "Titel", "Company", "Address", "7-77-77-77", "2-22-22-22",
            "1-11-11-11", "Test@mail.com", "test11");
    app.getNavigationHelper().goToContactPage();
    app.getContactHelper().creationContact(contact, true);
    app.getNavigationHelper().goToContactList();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }

}
