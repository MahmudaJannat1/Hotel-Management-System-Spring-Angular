package com.My.Spring_Final_Project.Entity.booking;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "item_names")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemName {

    @Id
    private String name; // Primary key as string

}
