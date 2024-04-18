package com.yupi.yuoj.judge.strategy;

import cn.hutool.json.JSONUtil;
import com.yupi.yuoj.model.dto.question.JudgeCase;
import com.yupi.yuoj.model.dto.question.JudgeConfig;
import com.yupi.yuoj.model.dto.questionsubmit.JudgeInfo;
import com.yupi.yuoj.model.entity.Question;
import com.yupi.yuoj.model.enums.JudgeInfoMessageEnum;

import java.util.List;

public class JavaLanguageJudgeStrategy implements JudgeStrategy {
    @Override
    public JudgeInfo doJudge(JudgeContext judgeContext) {
        JudgeInfo responseJudgeInfo = judgeContext.getJudgeInfo();
        List<String> inputList = judgeContext.getInputList();
        List<String> responseOutputList = judgeContext.getOutputList();
        Question question = judgeContext.getQuestion();
        List<JudgeCase> judgeCase = judgeContext.getJudgeCase();

        Long responseJudgeInfoTime = responseJudgeInfo.getTime();
        Long responseJudgeInfoMemory = responseJudgeInfo.getMemory();

        //返回信息
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setTime(responseJudgeInfoTime);
        judgeInfo.setMemory(responseJudgeInfoMemory);

        // 判断执行情况
        String judgeConfig = question.getJudgeConfig();
        JudgeConfig questionJudgeConfig = JSONUtil.toBean(judgeConfig, JudgeConfig.class);
        Long timeLimit = questionJudgeConfig.getTimeLimit();
        Long memoryLimit = questionJudgeConfig.getMemoryLimit();

        // 用时超限
        final Long JAVA_TIME_NEED = 1000L;
        if(responseJudgeInfoTime - JAVA_TIME_NEED > timeLimit){
            judgeInfo.setMessage(JudgeInfoMessageEnum.TIME_OUT_LIMIT.getValue());
            return judgeInfo;
        }
        // 内存超限
        if(responseJudgeInfoMemory > memoryLimit){
            judgeInfo.setMessage(JudgeInfoMessageEnum.MEMORY_OUT_LIMIT.getValue());
            return judgeInfo;
        }

        // 判断做题是否正确
        if(responseOutputList.size() != inputList.size()){
            judgeInfo.setMessage((JudgeInfoMessageEnum.WRONG_ANSWER.getValue()));
            return judgeInfo;
        }
        for (int i = 0; i < responseOutputList.size(); i++) {
            JudgeCase judgeCase1 = judgeCase.get(i);
            if(!responseOutputList.get(i).equals(judgeCase1.getOutput())){
                judgeInfo.setMessage((JudgeInfoMessageEnum.WRONG_ANSWER.getValue()));
                return judgeInfo;
            }
        }
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getValue());
        return judgeInfo;
    }
}
