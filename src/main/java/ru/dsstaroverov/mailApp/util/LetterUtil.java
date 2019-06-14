package ru.dsstaroverov.mailApp.util;

import ru.dsstaroverov.mailApp.model.Letter;
import ru.dsstaroverov.mailApp.to.LetterTo;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static ru.dsstaroverov.mailApp.web.SecurityUtil.getAuthUser;

public class LetterUtil {
    public static LetterTo asTo(Letter letter) {
        return new LetterTo(letter.getId(),letter.getSender().getAddress(),
                            letter.getRecipient().getAddress(),letter.getTitle(),
                            letter.getMessage(),letter.getSendTime());
    }

    public static List<LetterTo> getListLetterTo(Collection<Letter> letters){
        return letters.stream()
                .map(LetterUtil::asTo)
                .collect(Collectors.toList());
    }

    public static Letter fromTo(LetterTo letter){
        return new Letter(letter.getId(),null,null,null,letter.getSendTime(),letter.getTitle(),letter.getMessage());
    }
}
