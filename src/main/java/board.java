import org.sql2o.Connection;

public class board {

    private String fname;
    private String lname;
    private String speed;
    private String kata;
    private String link;
    private int id;

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setKata(String kata) {
        this.kata = kata;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getFname() {
        return fname;
    }

    public String getKata() {
        return kata;
    }

    public String getLink() {
        return link;
    }

    public String getLname() {
        return lname;
    }

    public String getSpeed() {
        return speed;
    }

    public int getId() {
        return id;
    }

    public void save(){
        try (Connection conn = DB.sql2o.open()){
            String sql = "INSERT INTO board (fname,lname,speed,kata,link)VALUES(:fname,:lname,:speed,:kata,:link)";
             this.id=(int)conn.createQuery(sql,true).addParameter("fname",fname)
                    .addParameter("lname",lname)
                    .addParameter("speed",speed)
                    .addParameter("kata",kata)
                    .addParameter("link",link)
                    .executeUpdate()
                    .getKey();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
