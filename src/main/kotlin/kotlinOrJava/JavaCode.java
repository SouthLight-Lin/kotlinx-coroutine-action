package kotlinOrJava;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/**
 * @author SouthLight-Lin on 2019/11/17
 */
public class JavaCode {

    public void useKotlinStaticMethod(String msg) {
        Test.say(msg);
    }

    /**
     * 使用Kotlin的Lambda表达式，Java需要实现Function接口
     */
    public void invokeKotlinLambda() {
        KotlinCode.doFunction(() -> {
            useKotlinStaticMethod("invokeKotlinLambda");
            return null;
        });
    }

    public void invokeKotlinLambdaInParam() {
        KotlinCode.doFunction1(integer -> {
            useKotlinStaticMethod("invokeKotlinLambdaInParam");
            return null;
        });
    }

}
