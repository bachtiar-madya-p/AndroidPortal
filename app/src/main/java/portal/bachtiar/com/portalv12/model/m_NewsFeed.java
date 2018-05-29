package portal.bachtiar.com.portalv12.model;

/**
 * Created by Bachtiar M Permadi on 08/11/2017.
 */

public class m_NewsFeed {

    private String content;
    private String imgContent;
    private String posdate;
    private Integer roomId;
    private String count;
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

    public m_NewsFeed() {
    }

    public m_NewsFeed(String content, String imgContent, String posdate, Integer roomId,String count,
                      String dept, String email, String imgUser, String name, Integer nik, String phone,
                      String plant, String pwd, String regDate, String tag, String gcmToken) {
        super();
        this.content = content;
        this.imgContent = imgContent;
        this.posdate = posdate;
        this.roomId = roomId;
        this.count = count;
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
        this.gcmToken=gcmToken;
    }
    public String getGcmToken()
    {
        return gcmToken;
    }
    public void setGcmToken(String gcmToken)
    {
        this.gcmToken = gcmToken;
    }
    public String getCount()
    {
        return count;
    }
    public void setCount(String count)
    {
        this.count = count;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgContent() {
        return imgContent;
    }

    public void setImgContent(String imgContent) {
        this.imgContent = imgContent;
    }

    public String getPosdate() {
        return posdate;
    }

    public void setPosdate(String posdate) {
        this.posdate = posdate;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
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
