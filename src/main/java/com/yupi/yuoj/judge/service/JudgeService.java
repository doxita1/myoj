package com.yupi.yuoj.judge.service;

import com.yupi.yuoj.model.entity.QuestionSubmit;

public interface JudgeService {
    QuestionSubmit doJudgeCode(Long questionSubmitId);
}
