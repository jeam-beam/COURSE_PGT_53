package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.getNavigationHelper().goToContactList();
    if (! app.getContactHelper().isThereAContact()) {
    app.getNavigationHelper().goToContactPage();
    app.getContactHelper().creationContact(new ContactData("Firstname1", "Middlename", "Lastname",
            "Nickname", "Titel", "Company", "Address", "7-77-77-77", "2-22-22-22",
            "1-11-11-11", "Test@mail.com", "test11"), true);
    }
  }

  @Test
  public void testContactModification() {
    List<ContactData> before = app.getContactHelper().getContactList();
    int index = before.size() - 1;
    ContactData contact = new ContactData(before.get(index).getId(), "Firstname1", "Middlename", "Lastname",
            "Nickname", "Titel", "Company", "Address", "7-77-77-77", "2-22-22-22",
            "1-11-11-11", "Test@mail.com", "test11");
    app.getContactHelper().modifyContact(index, contact);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
//    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

  }


}
