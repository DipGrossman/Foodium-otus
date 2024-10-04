package dev.shreyaspatil.foodium.data.local.dao

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import dev.shreyaspatil.foodium.R
import dev.shreyaspatil.foodium.ui.main.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OtusNegativeTest: TestCase() {

    @Before
    fun setup() {
        device.network.toggleWiFi(false)
        device.network.toggleMobileData(false)
    }
    @After
    fun teardown() {
        device.network.toggleWiFi(true)
        device.network.toggleMobileData(true)
    }

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testNoInternetTest() = run {
        // Expect. Отображение плашки нет интернета
        onView(withId(R.id.networkStatusLayout)).check(matches(isDisplayed()))
    }
}