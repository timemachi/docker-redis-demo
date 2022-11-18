package timemachi.springdockerredistodoList.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDo implements Serializable {
    private static final long serialVersionUID = -706395356436142142L;

    private long id;
    private String task;
    private boolean completed;

    public ToDo(long id, String task) {
        super();
        this.id = id;
        this.task = task;
    }
}
