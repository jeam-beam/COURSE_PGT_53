package ru.stqa.pft.addressbook;

public class ContactData {
  private final String firstname;
  private final String midlename;
  private final String lastname;
  private final String nickname;
  private final String title;
  private final String company;
  private final String address;
  private final String homephone;
  private final String mobilephone;
  private final String workphone;
  private final String email;

  public ContactData(String firstname, String midlename, String lastname, String nickname, String title, String company, String address, String homephone, String mobilephone, String workphone, String email) {
    this.firstname = firstname;
    this.midlename = midlename;
    this.lastname = lastname;
    this.nickname = nickname;
    this.title = title;
    this.company = company;
    this.address = address;
    this.homephone = homephone;
    this.mobilephone = mobilephone;
    this.workphone = workphone;
    this.email = email;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getMidlename() {
    return midlename;
  }

  public String getLastname() {
    return lastname;
  }

  public String getNickname() {
    return nickname;
  }

  public String getTitle() {
    return title;
  }

  public String getCompany() {
    return company;
  }

  public String getAddress() {
    return address;
  }

  public String getHomephone() {
    return homephone;
  }

  public String getMobilephone() {
    return mobilephone;
  }

  public String getWorkphone() {
    return workphone;
  }

  public String getEmail() {
    return email;
  }
}
