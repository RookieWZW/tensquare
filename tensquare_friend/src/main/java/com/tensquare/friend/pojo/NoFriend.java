package com.tensquare.friend.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by RookieWangZhiWei on 2019/1/14.
 */
@Entity
@Table(name="tb_nofriend")
@IdClass(NoFriend.class)
public class NoFriend  implements Serializable{

    @Id
    private String userid;

    @Id
    private String frinedid;


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFrinedid() {
        return frinedid;
    }

    public void setFrinedid(String frinedid) {
        this.frinedid = frinedid;
    }
}
