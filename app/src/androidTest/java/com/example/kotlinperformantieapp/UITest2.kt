package com.example.kotlinperformantieapp

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import kotlinx.android.synthetic.main.activity_hoofdscherm.*
import kotlinx.coroutines.delay

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import kotlin.system.measureTimeMillis

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class UITest2 {

    @get:Rule
    var activityStartRule: ActivityScenarioRule<Hoofdscherm> =
        ActivityScenarioRule(Hoofdscherm::class.java)


    @Test
    fun ActivityStartTest(){
        val scenario = activityStartRule.scenario
        Thread.sleep(2000)
        //test changeCourse
        val totalExecutionTime = measureTimeMillis {
            scenario.onActivity { activity ->
                activity.mapsButton.performClick()
            }
        }
        Thread.sleep(3000)
        totalExecutionTime.minus(3000)
        print("Total Execution Time: $totalExecutionTime")
    }


}