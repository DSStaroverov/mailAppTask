package ru.dsstaroverov.mailApp.util;

import ru.dsstaroverov.mailApp.model.Email;
import ru.dsstaroverov.mailApp.to.EmailTo;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class EmailUtil {
    public static EmailTo asTo(Email email) {
        return new EmailTo(email.getId(),email.getAddress());
    }

    public static List<EmailTo> getListEmailTo(Collection<Email> emails){
        return emails.stream()
                .map(EmailUtil::asTo)
                .collect(Collectors.toList());
    }

    public static Email fromTo(EmailTo email){
        return new Email(email.getId(),email.getAddress(),null, null );
    }

}
