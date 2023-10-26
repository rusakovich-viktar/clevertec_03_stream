package by.clevertec.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class House {

    private int id;
    private String buildingType;
    private List<Person> personList;
}
