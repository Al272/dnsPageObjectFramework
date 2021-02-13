package ru.burlakov.framework.base;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import ru.burlakov.framework.managers.ManagerInitial;
import ru.burlakov.framework.managers.ManagerPage;
import ru.burlakov.framework.managers.ManagerTest;

import static ru.burlakov.framework.managers.ManagerDriver.getDriver;
import static ru.burlakov.framework.utils.Constant.*;

public class BaseTests {

    /**
     * Менеджер страничек
     * @see ManagerPage#getManagerPage()
     */
    protected ManagerPage app = ManagerPage.getManagerPage();

    @BeforeClass
    public static void beforeAll() {
        ManagerInitial.initFramework();
    }

    @Before
    public void beforeEach() {
        getDriver().get(ManagerTest.getManagerTest().getProperty(APP_URL));
    }

    @AfterClass
    public static void afterAll() {ManagerInitial.quitFramework();
    }
}
