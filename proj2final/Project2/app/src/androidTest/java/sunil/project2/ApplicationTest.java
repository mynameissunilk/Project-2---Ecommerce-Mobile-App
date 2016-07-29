package sunil.project2;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.app.Application;
import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.startsWith;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ApplicationTestCase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */

@RunWith(AndroidJUnit4.class)
public class ApplicationTest extends ApplicationTestCase<Application> {

    public ApplicationTest() {super(Application.class);}

    @Rule
    public ActivityTestRule<MainActivity>MainRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void navigateTest() throws Exception{
        //onView(withId(R.id.mainlistview)).perform(click());

        //onData(allOf(is(instanceOf(String.class)),is("Bear"))).perform(click());
        onData(hasToString(startsWith("Item Text")))
            .inAdapterView(withId(R.id.mainlistview)).atPosition(1)
            .perform(click());
    }

}