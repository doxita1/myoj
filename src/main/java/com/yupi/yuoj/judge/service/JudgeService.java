package com.yupi.yuoj.judge.service;

import com.yupi.yuoj.model.dto.questionsubmit.JudgeInfo;

public interface JudgeService {
    JudgeInfo doJudgeCode(Long questionSubmitId);
}
