package test.homework2.wdj.test;

import android.app.Application;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ApplicationTestCase;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest extends ApplicationTestCase<Application> {
    public ApplicationTest(){
        super(Application.class);
    }
    public void test() throws Exception {
        // Context of the app under test.
        final int expected=1;
        final int reality=2;
        //断言，expected期望的参数值与reality相同
        assertEquals(expected,reality);
    }
}
