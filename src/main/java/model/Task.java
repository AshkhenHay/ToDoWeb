package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {

    private long id;
    private String title;
    private String description;
    private Date deadLine;
    private TaskStatus status;
    private User user;

}
