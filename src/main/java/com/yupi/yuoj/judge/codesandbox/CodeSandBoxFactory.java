package com.yupi.yuoj.judge.codesandbox;

import com.yupi.yuoj.judge.codesandbox.impl.ExampleCodeSandBox;
import com.yupi.yuoj.judge.codesandbox.impl.RemoteCodeSandBox;
import com.yupi.yuoj.judge.codesandbox.impl.ThirdPartCodeSandBox;

/**
 * 工厂模式来选择对应的代码沙箱
 */
public class CodeSandBoxFactory {
    public static CodeSandBox newInstance(String type){
        switch (type){
            case "remote":
                return new RemoteCodeSandBox();
            case "thirdPart":
                return new ThirdPartCodeSandBox();
            default:
                return new ExampleCodeSandBox();
        }
    }
}
