package com.yupi.yuoj.judge.strategy;

import com.yupi.yuoj.model.dto.questionsubmit.JudgeInfo;
import com.yupi.yuoj.model.entity.QuestionSubmit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JudgeManager {

    /**
     * 执行的策略
     * @param judgeContext 上下文
     * @return
     */
    public JudgeInfo doJudge(JudgeContext judgeContext){
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();

        JudgeStrategy judgeStrategy = new ExampleJudgeStrategy();
        if(language.equals("java")){
            log.warn("使用java判题沙箱");
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }
}
