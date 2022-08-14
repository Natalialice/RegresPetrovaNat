package by.homework21.dto.rest;

import lombok.*;
import lombok.experimental.Accessors;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class User {
    public int id;
    public String email;
    public String first_name;
    public String last_name;
    public String avatar;
}
