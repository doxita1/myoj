package com.yupi.yuoj.model.dto.question;

import lombok.Data;

@Data
public class JudgeConfig {
    /**
     * 时间限制(ms_)
     */
    private Long timeLimit;
    /**
     * 内存限制(kb)
     */
    private Long memoryLimit;
    /**
     * 堆栈限制(kb)
     */
    private Long stackLimit;
}
