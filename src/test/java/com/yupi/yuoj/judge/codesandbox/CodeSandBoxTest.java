package com.yupi.yuoj.judge.codesandbox;

import com.yupi.yuoj.common.ErrorCode;
import com.yupi.yuoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.yupi.yuoj.model.enums.QuestionSubmitLanguageEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class CodeSandBoxTest {

    @Value("${codesandbox.value}")
    private String type;

    @Test
    void executeCode() {
        CodeSandBox codeSandBox = CodeSandBoxFactory.newInstance(type);
        codeSandBox = new CodeSandProxy(codeSandBox);// 使用他的代理对象
        String code = "public class Main {\n" +
                "    public static void main(String[] args) {\n" +
                "        int a = Integer.parseInt(args[0]);\n" +
                "        int b = Integer.parseInt(args[1]);\n" +
                "        System.out.println(\"结果是:\"+(a+b));\n" +
                "    }\n" +
                "}";
        
        String cppCode = "#include <iostream>\n" +
                "\n" +
                "int main() {\n" +
                "    int num1, num2, sum;\n" +
                "    std::cin >> num1;\n" +
                "    std::cin >> num2;\n" +
                "    sum = num1 + num2;\n" +
                "    std::cout << \"Sum = \" << sum << std::endl;\n" +
                "    return 0;\n" +
                "}\n";
        String language = QuestionSubmitLanguageEnum.CPLUSPLUS.getValue();
        List<String> inputList = Arrays.asList("1 2","3 4");

        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(cppCode)
                .language(language)
                .inputList(inputList)
                .build();
        codeSandBox.executeCode(executeCodeRequest);
    }
    
    
    @Test
    public void testEnum(){
        ErrorCode paramsError = ErrorCode.PARAMS_ERROR;
        
        System.out.println(paramsError);
    }
}