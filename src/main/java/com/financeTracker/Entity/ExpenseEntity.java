package com.financeTracker.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

@Document(collection = "expenses")
@Data
@NoArgsConstructor
public class ExpenseEntity {
    @Id
    private ObjectId id;

    @NonNull
    private ObjectId groupId;

    @NonNull
    private ObjectId paidBy;

    private Double amount;

    private LocalDateTime date;

    private Map<ObjectId, Double> pendingBalances; // Map<UserId, AmountOwed>
}
