package Models;

public class staff {
    int staffid;
    String staffpassword, staffusername;

    public staff(int staffid, String staffpassword, String staffusername) {
        this.staffid = staffid;
        this.staffpassword = staffpassword;
        this.staffusername = staffusername;
    }

    public int getStaffid() {
        return staffid;
    }

    public void setStaffid(int staffid) {
        this.staffid = staffid;
    }

    public String getStaffpassword() {
        return staffpassword;
    }

    public void setStaffpassword(String staffpassword) {
        this.staffpassword = staffpassword;
    }

    public String getStaffusername() {
        return staffusername;
    }

    public void setStaffusername(String staffusername) {
        this.staffusername = staffusername;
    }
}
