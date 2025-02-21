package com.financeTracker.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@Document(collection = "groups")
public class Groups {
    @Id
    private ObjectId id;
    @NonNull
    private String groupname;
    private List<ObjectId> members;
    private List<ObjectId> expenses;
}
