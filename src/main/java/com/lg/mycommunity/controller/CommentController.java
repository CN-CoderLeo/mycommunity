package com.lg.mycommunity.controller;

import com.lg.mycommunity.entity.DiscussPost;
import com.lg.mycommunity.entity.Event;
import com.lg.mycommunity.event.EventProducer;
import com.lg.mycommunity.service.CommentService;
import com.lg.mycommunity.service.DiscussPostService;
import com.lg.mycommunity.util.CommunityConstant;
import com.lg.mycommunity.util.HostHolder;
import com.lg.mycommunity.util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.lg.mycommunity.entity.Comment;

import java.util.Date;

@Controller
@RequestMapping("/comment")
public class CommentController  implements CommunityConstant {


    @Autowired
    CommentService commentService;


    @Autowired
    HostHolder hostHolder;


    @Autowired
    EventProducer eventProducer;

    @Autowired
    DiscussPostService discussPostService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(path="/add/{discussPostId}",method= RequestMethod.POST)
    public String addComment(@PathVariable("discussPostId") int discussPostId,Comment comment){
        comment.setUserId(hostHolder.getUser().getId());
        comment.setStatus(0);
        comment.setCreateTime(new Date());
        commentService.addComment(comment);

        //触发评论事件
        Event event = new Event()
                .setTopic(TOPIC_COMMENT)
                .setUserId(hostHolder.getUser().getId())
                .setEntityType(comment.getEntityType())
                .setEntityUserId(comment.getEntityId())
                .setData("postId", discussPostId);

        if (comment.getEntityType() == ENTITY_TYPE_POST) {
            DiscussPost target = discussPostService.findDiscussPostById(comment.getEntityId());
            event.setEntityUserId(target.getUserId());
        } else if (comment.getEntityType() == ENTITY_TYPE_COMMENT) {
            Comment target = commentService.findCommentById(comment.getEntityId());
            event.setEntityUserId(target.getUserId());
        }
        eventProducer.fireEvent(event);


            // 计算帖子分数
        String redisKey = RedisKeyUtil.getPostScoreKey();
        redisTemplate.opsForSet().add(redisKey,discussPostId);



        return "redirect:/discuss/detail/" + discussPostId;
    }





}
