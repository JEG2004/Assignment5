package org.example.Assignment;

import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;

public class FilterInvoiceTest {
    @Test
    void filterinvoiceTest(){
        Database database = new Database();
        FilterInvoice filterInvoice = new FilterInvoice();

        //I don't know how to use withSQL. Might just submit after this.
        //changing this so I can recommit.
        //But I can explain my thoughts without undertanding database.
        //In Q4 we are supposed to test without stubbing
        //Not sure how that would pass before we refactor (it would not)
        //So then you stub the database making sure that when you
        //you would stub the so that when you create a new FilterInvoice and it
        //takes in your stub that has invoices in it and you assert equals
        //checking size.
    }

    @Test
    void filterInvoiceTest(){

    }


}
