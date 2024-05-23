package com.yupi.yuoj.model.dto.questionsubmit;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.yupi.yuoj.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 帖子点赞请求
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QuestionSubmitQueryRequest extends PageRequest implements Serializable {

    private Long id;

    /**
     * 编程语言
     */
    private String language;

    /**
     * 判题状态,(0 - 等待, 1 判题中, 2成功, 3 失败)
     */
    private Integer status;

    /**
     * 问题id
     */
    private Long questionId;

    /**
     * 提交人 id
     */
    private Long userId;


    private static final long serialVersionUID = 1L;
}