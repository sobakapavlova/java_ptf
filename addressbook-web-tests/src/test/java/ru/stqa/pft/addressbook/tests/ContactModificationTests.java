package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {


    @Test
    public void testContactModification() {
        ContactData contact = new ContactData("Anastasija", "Sergeevna",
                "Shchegolkova", "sobakapavlova", "Ms", "HomeTesting",
                "Moscow, Marshala Timoshenko st.", "36", "891988854",
                "sobakapavlova@gmail.com", "1993", "Test", "test1");
        ContactData contactModificated = new ContactData("Sergey", "Anastasovich",
                "Shchegolkova", "sobakapavlova", "Ms", "HomeTesting",
                "Moscow, Marshala Timoshenko st.", "36", "891988854",
                "sobakapavlova@gmail.com", "1993", "Test", "test1");
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().gotoAddNewPage();
            app.getContactHelper().createContact(contact, true);
        }
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> before  = app.getContactHelper().getContactList();
        app.getContactHelper().initContactUpdate();
        //TODO edit contact by id
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitUpdateContact();
        before.remove(contact);
        before.add(contactModificated);
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Comparator<? super ContactData> ById = (g1, g2) -> Integer.compare(g1.getId(),g2.getId() );
        before.sort(ById);
        after.sort(ById);
        Assert.assertEquals(before, after);
        System.out.println(before);
        System.out.println(after);
    }

}
