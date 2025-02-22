package com.financeTracker.Service;

import com.financeTracker.Entity.Groups;
import com.financeTracker.Repository.Grouprepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {
    @Autowired
    private Grouprepository grouprepository;

    public void saveNewGroup(Groups groups){
        grouprepository.save(groups);
    }

    public void SaveGroup(Groups groups) {
        grouprepository.save(groups);
    }
}
