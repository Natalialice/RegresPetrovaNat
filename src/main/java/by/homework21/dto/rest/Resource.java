package by.homework21.dto.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class Resource {
    public int id;
    public String name;
    public int year;
    public String color;
    public String pantone_value;
}

