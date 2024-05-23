package com.yupi.yuoj.judge.codesandbox.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.yupi.yuoj.common.ErrorCode;
import com.yupi.yuoj.exception.BusinessException;
import com.yupi.yuoj.judge.codesandbox.CodeSandBox;
import com.yupi.yuoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.yupi.yuoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.yupi.yuoj.utils.ApiSigner;

public class RemoteCodeSandBox implements CodeSandBox {
    
    private static final String API_KEY = "your_api_key";
    private static final String SECRET_KEY = "your_secret_key";
    
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("remote system out");
//        String url1 = "192.168.101.129:8090/executeCode";
        String url = "localhost:8090/executeCode";
        String executeCode = JSONUtil.toJsonStr(executeCodeRequest);
        
        String security = ApiSigner.generateSignature(API_KEY, SECRET_KEY);
        
        
        String response = HttpUtil
                .createPost(url)
                .body(executeCode)
                .header("API-Key", API_KEY)
                .header("security",security)
                .execute()
                .body();
        
        if (StrUtil.isBlank(response)){
            throw new BusinessException(ErrorCode.API_REQUEST_ERROR,"remote codesandBox error");
        }
        ExecuteCodeResponse executeCodeResponse = JSONUtil.toBean(response, ExecuteCodeResponse.class);
        return executeCodeResponse;
    }
}
