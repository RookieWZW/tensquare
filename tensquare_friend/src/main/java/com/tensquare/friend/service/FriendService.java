package com.tensquare.friend.service;

import com.tensquare.friend.client.UserClient;
import com.tensquare.friend.dao.FriendDao;
import com.tensquare.friend.dao.NoFriendDao;
import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by RookieWangZhiWei on 2019/1/14.
 */
@Service
public class FriendService {


    @Autowired
    private FriendDao friendDao;

    @Autowired
    private UserClient userClient;

    @Transactional
    public int addFriend(String userid, String friendid) {

        if (friendDao.selectCount(userid, friendid)>0) {
            return 0;
        }

        Friend friend = new Friend();
        friend.setUserid(userid);
        friend.setFriendid(friendid);
        friend.setIslike("0");
        userClient.incFanscount(userid,1);
        userClient.incFollowcount(friendid,1);
        friendDao.save(friend);


        if (friendDao.selectCount(friendid, userid) > 0) {
            friendDao.updateLike(userid, friendid, "1");
            friendDao.updateLike(friendid, userid, "1");
        }
        return 1;
    }

    @Autowired
    private NoFriendDao noFriendDao;


    public void addNoFriend(String userid,String friendid){
        NoFriend noFriend = new NoFriend();

        noFriend.setUserid(userid);
        noFriend.setFrinedid(friendid);

        noFriendDao.save(noFriend);

    }

    @Transactional
    public void deleteFriend(String userid,String friendid){

        userClient.incFanscount(userid,-1);
        userClient.incFollowcount(friendid,-1);
        friendDao.deleteFriend(userid,friendid);

        friendDao.updateLike(friendid,userid,"0");

        addNoFriend(userid,friendid);
    }


}
