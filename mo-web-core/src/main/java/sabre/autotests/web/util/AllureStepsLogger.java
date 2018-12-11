package sabre.autotests.web.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import ru.yandex.qatools.allure.events.StepCanceledEvent;
import ru.yandex.qatools.allure.events.StepEvent;
import ru.yandex.qatools.allure.events.StepFinishedEvent;
import ru.yandex.qatools.allure.events.StepStartedEvent;
import ru.yandex.qatools.allure.experimental.LifecycleListener;

import java.util.Deque;
import java.util.LinkedList;

public class AllureStepsLogger extends LifecycleListener {

    private final Logger logger = Logger.getLogger(AllureStepsLogger.class);
    private Deque<String> names = new LinkedList();

    public AllureStepsLogger() {
    }

    public void fire(StepStartedEvent event) {
        this.names.push(event.getName());
        this.logger.info(this.getOffset() + " [ -> ] " + (String)StringUtils.defaultIfBlank(event.getTitle(), event.getName()));
    }

    public void fire(StepEvent event) {
        if (event instanceof StepCanceledEvent) {
            this.logger.info(this.getOffset() + " [ ? ] Step Canceled...");
        }

    }

    public void fire(StepFinishedEvent event) {
        this.logger.info(this.getOffset() + " [ <- ] Step Finished!");
        this.names.poll();
    }

    private String getOffset() {
        return (new String(new char[this.names.size() == 0 ? 0 : this.names.size() - 1])).replaceAll("\u0000", "   ");
    }
}
