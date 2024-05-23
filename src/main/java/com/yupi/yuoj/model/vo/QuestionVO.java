package com.yupi.yuoj.model.vo;


import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.yupi.yuoj.model.dto.question.JudgeConfig;
import com.yupi.yuoj.model.entity.Question;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 帖子视图
 *
 */
@Data
public class QuestionVO implements Serializable {

    private Long id;
    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签列表（json 数组）
     */
    private List<String> tags;

    /**
     * 判题配置,json对象
     */
    private JudgeConfig judgeConfig;

    /**
     * 题目提交数
     */
    private Integer submitNum;

    /**
     * 题目通过数
     */
    private Integer acceptedNum;


    /**
     * 点赞数
     */
    private Integer thumbNum;

    /**
     * 收藏数
     */
    private Integer favourNum;

    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 用户信息
     */
    private UserVO userVO;

    private static final long serialVersionUID = 1L;

    public static Question voToObj(QuestionVO questionVO) {
        if (questionVO == null) {
            return null;
        }
        Question question = new Question();
        BeanUtils.copyProperties(questionVO, question);

        if (questionVO.getTags() != null) {
            question.setTags(JSONUtil.toJsonStr(questionVO.getTags()));
        }

        if (questionVO.getJudgeConfig() != null) {
            question.setJudgeConfig(JSONUtil.toJsonStr(questionVO.getJudgeConfig()));
        }
        return question;
    }

    /**
     * 对象转包装类
     * @param question
     * @return
     */
    public static QuestionVO objToVo(Question question) {
        if (question == null) {
            return null;
        }
        QuestionVO questionVO = new QuestionVO();
        BeanUtils.copyProperties(question, questionVO);

        if (question.getTags() != null) {
            questionVO.setTags(JSONUtil.toList(question.getTags(), String.class));
        }

        if (question.getJudgeConfig() != null) {
            questionVO.setJudgeConfig(JSONUtil.toBean(question.getJudgeConfig(), JudgeConfig.class));
        }
        return questionVO;
    }
}

