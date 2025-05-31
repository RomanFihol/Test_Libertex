package utils;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Attachment;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.TestResult;
import org.openqa.selenium.OutputType;

public class CustomScreenshotListener implements TestLifecycleListener {
    public void beforeTestStop(TestResult result) {
        if (result.getStatus() == Status.FAILED) {
            makeScreenshot();
        }
    }

    @Attachment(
            value = "Failure Screenshot",
            type = "image/png"
    )
    private byte[] makeScreenshot() {
        byte[] result = null;
        try {
            result = Selenide.screenshot(OutputType.BYTES);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
}
