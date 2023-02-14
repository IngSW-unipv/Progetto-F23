package it.unipv.sfw.findme.model.register;

import org.junit.Before;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegistrationTest {
    private Registration registration;
    private String name;
    private String lastName;
    private String idNumber;
    private String email;
    private String emailTermination;
    private char[] password;
    private char[] confirmPassword;
    private String userType;

    @Before
    public void setUp() {
        name = "TestName";
        lastName = "TestLastName";
        idNumber = "123456";
        email = "testemail";
        emailTermination = "@universitadipavia.it";
        password = "password123".toCharArray();
        confirmPassword = "password123".toCharArray();
        userType = "STUDENT";
        registration = new Registration(name, lastName, idNumber, email, emailTermination, password, confirmPassword, userType);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test01NamesCheck() {
        registration.namesCheck();
    }

    @Test(expected = IllegalArgumentException.class)
    public void test02EmailCheck() {
        registration.emailCheck();
    }

    @Test(expected = IllegalArgumentException.class)
    public void test03PasswordCheck() {
        registration.passwordCheck();
    }

    @Test(expected = IllegalArgumentException.class)
    public void test04CheckDuplicate() throws Exception {
        registration.checkDuplicate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void test05TypeCheck() {
        registration.TypeCheck();
    }
}