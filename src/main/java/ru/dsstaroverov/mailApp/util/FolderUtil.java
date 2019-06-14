package ru.dsstaroverov.mailApp.util;

import ru.dsstaroverov.mailApp.model.Folder;
import ru.dsstaroverov.mailApp.to.FolderTo;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static ru.dsstaroverov.mailApp.web.SecurityUtil.getAuthUser;

public class FolderUtil {
    public static FolderTo asTo(Folder folder) {
        return new FolderTo(folder.getId(),folder.getName());
    }

    public static List<FolderTo> getListFolderTo(Collection<Folder> folders){
        return folders.stream()
                .map(FolderUtil::asTo)
                .collect(Collectors.toList());
    }

    public static Folder fromTo(FolderTo folder){
        return new Folder(folder.getId(),folder.getName(),getAuthUser().getCurrentEmail());
    }
}
