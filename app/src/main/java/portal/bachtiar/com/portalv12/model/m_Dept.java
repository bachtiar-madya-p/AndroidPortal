package portal.bachtiar.com.portalv12.model;

/**
 * Created by bachtiar on 06/12/17.
 */

public class m_Dept {
    private String dept;
    private Integer idDept;

    /**
     * No args constructor for use in serialization
     *
     */
    public m_Dept() {
    }

    /**
     *
     * @param idDept
     * @param dept
     */
    public m_Dept(String dept, Integer idDept) {
        super();
        this.dept = dept;
        this.idDept = idDept;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public Integer getIdDept() {
        return idDept;
    }

    public void setIdDept(Integer idDept) {
        this.idDept = idDept;
    }
}
