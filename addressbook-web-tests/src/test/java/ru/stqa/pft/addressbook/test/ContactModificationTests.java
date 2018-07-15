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
    app.goTo().contactPage();
    if (app.contact().list().size() == 0) {
    app.goTo().newContact();
    app.contact().creationContact(new ContactData()
            .withFirstname("Firstname1").withLastname("Lastname"), false);
    }
  }

  @Test
  public void testContactModification() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    ContactData contact = new ContactData()
            .withId(before.get(index).getId()).withFirstname("Firstname1").withMidlename("Middlename")
            .withLastname("Lastname").withNickname("Nickname").withTitle("Titel").withCompany("Company")
            .withAddress("Address").withHomephone("7-77-77-77").withMobilephone("2-22-22-22")
            .withWorkphone("11-11-11-11").withEmail("Test@mail.com").withGroup("test11");
    app.contact().modify(index, contact);
    List<ContactData> after = app.contact().list();
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
