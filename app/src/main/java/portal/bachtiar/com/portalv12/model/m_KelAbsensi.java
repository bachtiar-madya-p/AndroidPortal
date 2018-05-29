package portal.bachtiar.com.portalv12.model;

/**
 * Created by Bachtiar M Permadi on 14/11/2017.
 */

public class m_KelAbsensi {

    private String content;
    private String date;
    private String status;
    private Integer id;
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
     */
    public m_KelAbsensi() {
    }

    /**
     * @param id
     * @param status
     * @param content
     * @param date
     * @param nik
     */
    public m_KelAbsensi(String content, String date, String status, Integer id,
                        String dept, String email, String imgUser, String name,
                        Integer nik, String phone, String plant, String pwd, String regDate,
                        String tag, String gcmToken) {
        super();
        this.content = content;
        this.date = date;
        this.status = status;
        this.id = id;
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
        this.gcmToken= gcmToken;
    }
    public String getGcmToken()
    {
        return gcmToken;
    }
    public void setGcmToken(String gcmToken)
    {
        this.gcmToken = gcmToken;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
