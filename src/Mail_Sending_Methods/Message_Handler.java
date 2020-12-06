package Mail_Sending_Methods;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entities.AboveGod;
import Entities.Customer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
//import javax.activation.*;
import java.time.LocalDate; // import the LocalDate class
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author rotti
 */
public class Message_Handler implements AboveGod {



    public void Deploy_Message() throws ParseException, IOException {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Deadline_Message msg = new Deadline_Message();




            LocalDate currentDate = LocalDate.now();






            for(Map.Entry<String, Customer>entry : customerMap.entrySet())
            {
                String email = entry.getValue().getEmail();


                for(int i = 0 ; i < entry.getValue().DomainsList.size() ; i++)
                {
                    String DomainName = entry.getValue().DomainsList.get(i).getName();

                    java.sql.Date ExpirationDate = entry.getValue().DomainsList.get(i).getExpiry_Date();

                    long timeToDeadLine = ChronoUnit.DAYS.between(currentDate, ExpirationDate.toLocalDate());


                    int InvoiceId = entry.getValue().DomainsList.get(i).getInvoice_Id();
                    float price = 0;
                    for(int j = 0 ; j < entry.getValue().GetInvoicesList().size() ; j++)
                    {
                        if( entry.getValue().GetInvoicesList().get(j).getId() == InvoiceId)
                        {
                            price = entry.getValue().GetInvoicesList().get(j).getPrice();
                            break;
                        }

                    }

                    System.out.println("Time = "+timeToDeadLine + " Email = "+email + " Price = "+price + " Expiration day = "+ExpirationDate.toString());

                    msg.Send_Message(timeToDeadLine,email, String.valueOf(price),DomainName,ExpirationDate.toString());
                    // price = entry.getValue().GetInvoicesList().
                }
            }














    }


}
