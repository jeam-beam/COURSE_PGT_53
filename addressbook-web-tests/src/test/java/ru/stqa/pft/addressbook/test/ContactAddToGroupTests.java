package ru.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroupTests extends TestBase {

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
  public void testContactAddToGroup() {
    Contacts before = app.db().contacts();
    ContactData addContact = findContactWithoutGroup();
    Contacts beforeWithoutAddContact = before.without(addContact);
    GroupData findGroup = findGroupWithoutContact(addContact);
    app.contact().addToGroup(addContact, findGroup);
    ContactData changeContact = addContact.inGroup(findGroup);

    Contacts after = app.db().contacts();
    assertThat(after.size(), equalTo(before.size()));
    assertThat(after, equalTo(beforeWithoutAddContact.withAdded(changeContact)));
  }

  private GroupData findGroupWithoutContact(ContactData contact) {
    Groups groups = app.db().groups();
    GroupData chooseGroup = null;
    for (GroupData group : groups) {
      if (!group.getContacts().contains(contact)) {
        chooseGroup = group;
        break;
      }
    }
    return chooseGroup;
  }

  private ContactData findContactWithoutGroup() {
    Groups groups = app.db().groups();
    Contacts contacts = app.db().contacts();
    ContactData addContact = null;
    for (ContactData contact : contacts) {
      if (contact.getGroups().size() != groups.size()) {
        addContact = contact;
        break;
      }
    }
    return addContact;
  }
}

