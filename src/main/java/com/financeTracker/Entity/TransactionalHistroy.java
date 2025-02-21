package com.financeTracker.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "transactional")
public class TransactionalHistroy {
    @Id
    private ObjectId id;

    @NonNull
    private ObjectId groupId;

    @NonNull
    private ObjectId paidBy; // Who paid

    @NonNull
    private ObjectId paidTo; // Who received the money

    private Double amount;

    private LocalDateTime date;

}
