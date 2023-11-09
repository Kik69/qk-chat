package com.qk.chat.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qk.chat.server.domain.entity.ImExamineInfo;
import com.qk.chat.server.domain.entity.ImFriendshipInfo;
import com.qk.chat.server.domain.param.*;
import com.qk.chat.server.domain.vo.FriendRelationVO;
import com.qk.chat.server.domain.vo.UserFriendApplyVO;
import com.qk.chat.server.domain.vo.UserFriendListVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author codehd
 * @since 2023年11月02日
 */
public interface ImFriendshipService extends IService<ImFriendshipInfo> {
    boolean addFriendService(FriendAddParam friendAddParam);

    boolean applyFriendService(FriendApplyParam friendApplyParam);

    UserFriendApplyVO findFriendService(FindUserSecretParam findUserSecretParam);
    
    boolean deleteFriendService(FriendDeleteParam deleteParam);

    List<UserFriendListVO> listFriendService(Integer pageNum,Integer pageSize,String keyword);
    
    List<FriendRelationVO> verifyFriendRelation(CheckFriendParam checkFriendParam);
}
