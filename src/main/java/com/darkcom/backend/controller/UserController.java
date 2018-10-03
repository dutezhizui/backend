package com.darkcom.backend.controller;

import com.alibaba.fastjson.JSON;
import com.darkcom.backend.common.Result;
import com.darkcom.backend.common.enums.BizCodeEnum;
import com.darkcom.backend.constants.Urls;
import com.darkcom.backend.dto.request.UserRequest;
import com.darkcom.backend.dto.response.UserResponse;
import com.darkcom.backend.model.User;
import com.darkcom.backend.service.UserService;
import com.darkcom.backend.utils.AssertUtil;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户管理
 *
 * @author yjy
 */
@RestController
@RequestMapping(Urls.User.ROOT)
@Api("用户相关")
public class UserController {
    public static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private JavaMailSender sender;

    @PostMapping(Urls.User.USER)
    public Result<UserResponse> addUser(@RequestBody UserRequest userRequest) {
        logger.info("[addUser],userRequest={}", JSON.toJSONString(userRequest));
        userService.addUser(userRequest);
        return Result.succeed();
    }

    @GetMapping(Urls.User.USER_INFO)
    public Result<UserResponse> getUserInfo(@RequestParam("account") String account) {
        User user = userService.getUserByAccount(account);
        UserResponse userResponse = new UserResponse();
        AssertUtil.isTrueForRpc(!ObjectUtils.isEmpty(user), BizCodeEnum.DATA_NOT_FOUND);
        BeanUtils.copyProperties(user, userResponse);
        return new Result<>(userResponse);
    }

    @PostMapping("/send")
    public void sendmail() {
        MimeMessage message = sender.createMimeMessage();
        //true表示需要创建一个multipart message
        MimeMessageHelper helper = null;
        User user1 = new User();
        user1.setAccount("yaojiaoyi");
        user1.setPhone("15989510265");
        User user2 = new User();
        user2.setAccount("yaojiaoyi");
        user2.setPhone("15989510265");
        List<User> list = new ArrayList();
        list.add(user1);
        list.add(user2);
        String html = "";
        for (int i = 0; i < list.size(); i++) {

            html += "  <tr>\n" +
                    "    <td>" + list.get(i).getAccount() + "</td>\n" +
                    "    <td>" + list.get(i).getPhone() + "</td>\n" +
                    "  </tr>\n";
        }
        String content = "<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封Html邮件!</h3>\n" +
                "<p>这是一封测试邮件，一下这些组合超过限额</>"+
                "<table border=\"1\">\n" +
                "  <tr>\n" +
                "    <th>Month</th>\n" +
                "    <th>Savings</th>\n" +
                html +
                "</table>" +
                "</body>\n" +
                "</html>";
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom("841646726@qq.com");
            helper.setTo("841646726@qq.com");
            helper.setSubject("html mail");
            helper.setText(content, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        sender.send(message);
    }
}
