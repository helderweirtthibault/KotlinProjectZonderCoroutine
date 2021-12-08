package com.example.kotlinperformantieapp

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.logging.Logger
import kotlin.system.measureTimeMillis

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class UITest {
    private val log: Logger = Logger.getGlobal()

    @get:Rule
    var activityRule: ActivityScenarioRule<MapsActivity> =
        ActivityScenarioRule(MapsActivity::class.java)

    @Test
    fun ActivityMapTestChangeCourse() {
        val scenario = activityRule.scenario
        Thread.sleep(2000)
        //test changeCourse
        val totalExecutionTime = measureTimeMillis {
            scenario.onActivity { activity ->
                activity.changeCourse("50.85", "4.35")
            }
        }
        Thread.sleep(3000)
        totalExecutionTime.minus(3000)
        log.info("$totalExecutionTime")
        print("Total Execution Time: $totalExecutionTime")
    }


}