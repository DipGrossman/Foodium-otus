package dev.shreyaspatil.foodium.data.local.dao

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.shreyaspatil.foodium.R
import dev.shreyaspatil.foodium.ui.main.MainActivity
import junit.framework.TestCase
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OtusTest: TestCase() {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testSelectPostTest() {
        // Step 1. Открытие карточки
        onView(withId(R.id.postsRecyclerView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        // Expect. Открылся пост
        onView(withId(R.id.post_body)).check(matches(isDisplayed()))
    }

    @Test
    fun sharePostTest() {
        Intents.init()
        // Step 1. Открытие карточки
        onView(withId(R.id.postsRecyclerView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        // Step 2. Тап на кнопку шеринга
        onView(isRoot()).perform(waitFor(1000))
        onView(withId(R.id.action_share)).perform(click())
        // Expect. Открытие боттомменю шеринга
        intended(hasAction(Intent.ACTION_CHOOSER))
        Intents.release()
    }

    private fun waitFor(millis: Long): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isRoot()
            }

            override fun getDescription(): String {
                return "wait for $millis milliseconds"
            }

            override fun perform(uiController: UiController?, view: View?) {
                uiController?.loopMainThreadForAtLeast(millis)
            }
        }
    }
}