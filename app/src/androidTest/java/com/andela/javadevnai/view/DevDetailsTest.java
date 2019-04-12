package com.andela.javadevnai.view;

    import android.content.Intent;
    import android.support.test.espresso.intent.rule.IntentsTestRule;
    import android.support.test.rule.ActivityTestRule;
    import android.support.test.runner.AndroidJUnit4;

    import com.andela.javadevnai.R;

    import org.junit.Before;
    import org.junit.Rule;
    import org.junit.Test;
    import org.junit.runner.RunWith;

    import static android.support.test.espresso.Espresso.onView;
    import static android.support.test.espresso.assertion.ViewAssertions.matches;
    import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
    import static android.support.test.espresso.matcher.ViewMatchers.withId;
    import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class DevDetailsTest {
    @Rule
    public IntentsTestRule<DevDetails> mDetailActivityIntentTestRule
            = new IntentsTestRule<>(DevDetails.class, true, false);

    @Before
    public void customIntentToStartActivity() {
        Intent intent = new Intent();
        intent.putExtra("username", "TheDancerCodes");
        mDetailActivityIntentTestRule.launchActivity(intent);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void display_detail_view_main() {
        onView(withId(R.id.dev_detail_main))
                .check(matches(isDisplayed()));
    }
}
