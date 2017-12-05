package cz.trask.tdd;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public interface UserExport {

    //@Value("#{target.firstname}")
    String getFirstName();

    String getSurname();

    List<UserEntryExport> getUserEntryExports();

    interface UserEntryExport {

        String getName();

    }

}
