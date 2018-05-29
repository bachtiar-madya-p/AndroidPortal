package portal.bachtiar.com.portalv12.model;

/**
 * Created by bachtiar on 25/11/17.
 */

public class m_FeedComment {

    private String cmtcontent;
    private Integer id;
    private String cmtposdate;
    private Integer roomId;
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
    public m_FeedComment() {
    }

    /**
     * @param id
     * @param cmtcontent
     * @param cmtposdate
     */
    public m_FeedComment(String cmtcontent, String cmtposdate, Integer id, Integer roomId,
                         String dept, String email, String imgUser, String name, Integer nik,
                         String phone, String plant, String pwd, String regDate, String tag, String gcmToken) {
        super();
        this.cmtcontent = cmtcontent;
        this.cmtposdate = cmtposdate;
        this.id = id;
        this.roomId = roomId;
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



    public String getCmtcontent() {
        return cmtcontent;
    }

    public void setCmtcontent(String cmtcontent) {
        this.cmtcontent = cmtcontent;
    }

    public String getCmtposdate() {
        return cmtposdate;
    }

    public void setCmtposdate(String cmtposdate) {
        this.cmtposdate = cmtposdate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
