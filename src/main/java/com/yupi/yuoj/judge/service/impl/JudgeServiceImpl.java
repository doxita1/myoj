package com.yupi.yuoj.judge.service.impl;

import com.yupi.yuoj.common.ErrorCode;
import com.yupi.yuoj.exception.BusinessException;
import com.yupi.yuoj.judge.service.JudgeService;
import com.yupi.yuoj.model.dto.questionsubmit.JudgeInfo;
import com.yupi.yuoj.model.entity.QuestionSubmit;
import com.yupi.yuoj.model.enums.QuestionSubmitStatusEnum;
import com.yupi.yuoj.service.QuestionService;
import com.yupi.yuoj.service.QuestionSubmitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class JudgeServiceImpl implements JudgeService {

    @Resource
    QuestionSubmitService questionSubmitService;

    @Override
    public JudgeInfo doJudgeCode(Long questionSubmitId) {

        //1:获取题目提交信息
        //4:调用沙箱, 获取到执行结果
        //5:根据执行结果, 设置题目的判题状态和信息


        QuestionSubmit questionSubmit = questionSubmitService.getById(questionSubmitId);

        if (questionSubmit == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"题目提交信息不存在");
        }


        Long id = questionSubmit.getId();
        String language = questionSubmit.getLanguage();
        String code = questionSubmit.getCode();
        Integer status = questionSubmit.getStatus();
        String judgeInfo = questionSubmit.getJudgeInfo();
        Long questionId = questionSubmit.getQuestionId();
        Long userId = questionSubmit.getUserId();
        Date createTime = questionSubmit.getCreateTime();
        Date updateTime = questionSubmit.getUpdateTime();
        Integer isDelete = questionSubmit.getIsDelete();

        //2: 如果题目提交状态不为等待中(等待判题), 就不用重复执行
        if(!status.equals(QuestionSubmitStatusEnum.WAITING.getValue())){
            return null;
        }

        //3:更改状态为判题中, 防止重复判题, 同时让用户看到状态
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        questionSubmitService.updateById(questionSubmitUpdate);


        return null;
    }
}
