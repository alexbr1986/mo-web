package sabre.autotests.web.consts;

import jersey.repackaged.com.google.common.collect.Sets;
import org.openqa.selenium.By;

import java.util.Set;

import static org.openqa.selenium.By.cssSelector;

public class TestUsersConsts {

    public static final Set<By> IGNORED_ELEMENTS = Sets.newHashSet(
            cssSelector(".yandex-direct.yandex-direct_row"),
            cssSelector(".direct-aside-public"),
            cssSelector(".ns-view-awaps"),
            cssSelector(".info-public__footer"),
            cssSelector(".b-sharing-invites__input"),
            cssSelector(".comments-header__row"),
            cssSelector(".software-header-promo")
    );
}
