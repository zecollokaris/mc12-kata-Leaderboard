import org.sql2o.Connection;
import java.util.List;
import java.util.ArrayList;
public class Dataquery {
    public List<board> getAll(){
        return DB.sql2o.open().createQuery("SELECT * FROM board;").executeAndFetch(board.class);
    }
}
