package portal.bachtiar.com.portalv12.model;

/**
 * Created by bachtiar on 25/11/17.
 */

public class m_Bpjs {
    private String date;
    private String doc;
    private Integer id;
    private String nokk;
    private String noktp;
    private String status;
    private String dept;
    private String email;
    private String imgUser;
    private String name;
    private Integer nik;
    private String phone;
    private String plant;
    private String pwd;
    private String regDate;
    private String tag;
    private String gcmToken;

    /**
     * No args constructor for use in serialization
     *
     */
    public m_Bpjs() {
    }

    /**
     *
     * @param id
     * @param status
     * @param doc
     * @param date
     * @param noktp
     * @param nokk
     */
    public m_Bpjs(String date, String doc, Integer id, String nokk, String noktp, String status,
                  String dept, String email, String imgUser, String name,
                  Integer nik, String phone, String plant, String pwd, String regDate,
                  String tag, String gcmToken) {
        super();
        this.date = date;
        this.doc = doc;
        this.id = id;
        this.nokk = nokk;
        this.noktp = noktp;
        this.status = status;
        this.dept = dept;
        this.email = email;
        this.imgUser = imgUser;
        this.name = name;
        this.nik = nik;
        this.phone = phone;
        this.plant = plant;
        this.pwd = pwd;
        this.regDate = regDate;
        this.tag = tag;
        this.gcmToken =  gcmToken;
    }
    public String getGcmToken()
    {
        return gcmToken;
    }
    public void setGcmToken(String gcmToken)
    {
        this.gcmToken = gcmToken;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNokk() {
        return nokk;
    }

    public void setNokk(String nokk) {
        this.nokk = nokk;
    }

    public String getNoktp() {
        return noktp;
    }

    public void setNoktp(String noktp) {
        this.noktp = noktp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getDept() {
        return dept;
    }
    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImgUser() {
        return imgUser;
    }

    public void setImgUser(String imgUser) {
        this.imgUser = imgUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNik() {
        return nik;
    }

    public void setNik(Integer nik) {
        this.nik = nik;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
