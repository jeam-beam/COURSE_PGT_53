package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().contactPage();
    if (app.contact().all().size() == 0) {
    app.goTo().newContact();
    app.contact().create(new ContactData()
            .withFirstname("Firstname1").withLastname("Lastname"), false);
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstname("Firstname1").withMidlename("Middlename2222")
            .withLastname("Lastname2222").withNickname("Nickname").withTitle("Titel").withCompany("Company")
            .withAddress("Address").withHomephone("7-77-77-77").withMobilephone("2-22-22-22")
            .withWorkphone("11-11-11-11").withEmail("Test@mail.com").withGroup("test11");
    app.contact().modify(contact);
    Contacts after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());

    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }
}
