package ru.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemoveFromGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().newContact();
      app.contact().create(new ContactData().withFirstname("Firstname1").withLastname("Lastname"), false);
    }
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Test1"));
    }
  }

  @Test
  public void testContactRemoveFromGroup() {
    Contacts before = app.db().contacts();
    ContactData ContForDel = findContactWithGroup();
    Contacts beforeWithoutAddContact = before.without(ContForDel);
    GroupData delGroup = ContForDel.getGroups().iterator().next();
    app.contact().removeGroup(ContForDel, delGroup);
    ContactData changeContact = ContForDel.without(delGroup);

    Contacts after = app.db().contacts();
    assertThat(after.size(), equalTo(before.size()));
    assertThat(after, equalTo(beforeWithoutAddContact.withAdded(changeContact)));
  }


  private ContactData findContactWithGroup() {
    Contacts contacts = app.db().contacts();
    ContactData ContForDel = null;
    for (ContactData contact : contacts) {
      if (contact.getGroups().size() != 0) {
        ContForDel = contact;
        break;
      }
    }
    return ContForDel;
  }
}
