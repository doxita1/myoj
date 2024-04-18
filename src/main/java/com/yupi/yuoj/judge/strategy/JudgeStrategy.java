package com.yupi.yuoj.judge.strategy;

import com.yupi.yuoj.model.dto.questionsubmit.JudgeInfo;

public interface JudgeStrategy {
    /**
     * 执行的策略
     * @param judgeContext 上下文
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext);
}
