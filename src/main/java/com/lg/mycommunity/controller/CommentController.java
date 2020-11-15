package com.lg.mycommunity.controller;

import com.lg.mycommunity.service.CommentService;
import com.lg.mycommunity.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.lg.mycommunity.entity.Comment;

import java.util.Date;

@Controller
@RequestMapping("/comment")
public class CommentController {


    @Autowired
    CommentService commentService;


    @Autowired
    HostHolder hostHolder;



    @RequestMapping(path="/add/{discussPostId}",method= RequestMethod.POST)
    public String addComment(@PathVariable("discussPostId") int discussPostId,Comment comment){
        comment.setUserId(hostHolder.getUser().getId());
        comment.setStatus(0);
        comment.setCreateTime(new Date());
        commentService.addComment(comment);

        return "redirect:/discuss/detail/" + discussPostId;
    }





}
