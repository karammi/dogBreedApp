package com.asad.dogs

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertAny
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.asad.dogs.app.DogBreedsApp
import com.asad.dogs.app.MainActivity
import com.asad.dogs.core.MockServerDispatcher
import com.asad.dogs.core.di.module.DatabaseModule
import com.asad.dogs.core.di.module.NetworkModule
import com.jakewharton.espresso.OkHttp3IdlingResource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@UninstallModules(
    value = [NetworkModule::class, DatabaseModule::class],
)
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class BreedListFavoriteScenarioTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Inject
    lateinit var okHttpClient: OkHttpClient
    var mockWebServer: MockWebServer = MockWebServer()

    private val serviceMap: Map<String, String> = mapOf(
        Pair("breeds/list/all", "breed_list_success.json"),
        Pair("breed/akita/images", "breed_pictures_success.json"),
        Pair("breed/{breedName}/images", "breed_pictures_error.json"),
    )
    lateinit var idlingResources: OkHttp3IdlingResource

    @Before
    fun setup() {
        hiltRule.inject()
        mockWebServer.start(port = 8001)

//        composeTestRule.mainClock.autoAdvance = true
        composeTestRule.activity.setContent {
            DogBreedsApp()
        }

        idlingResources = OkHttp3IdlingResource.create("okhttp", okHttpClient)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun addBreedPicturesToFavoriteBreedPicturesScenario() {
        mockWebServer.dispatcher = MockServerDispatcher().successDispatcher(serviceMap)
        composeTestRule.apply {
            /** Breed List Screen */
            onNodeWithText(activity.getString(R.string.app_name))
                .assertIsDisplayed()

            onNodeWithContentDescription(activity.getString(R.string.favorite_icon_content_desc))
                .assertIsDisplayed()

            waitUntil(3000) {
                onAllNodesWithContentDescription("breed_list_image")
                    .assertAny(hasContentDescription("breed_list_image"))
                    .fetchSemanticsNodes().size > 1
            }

            onAllNodesWithContentDescription("breed_model_title")
                .assertAny(hasContentDescription("breed_model_title"))
                .fetchSemanticsNodes().size > 1

            onAllNodesWithContentDescription("card_breed_model")
                .assertAny(hasContentDescription("card_breed_model"))
                .fetchSemanticsNodes().size > 1

            /**
             * Inside  Breed List Screen user will click on an item, such as AKITA and should navigate to
             * breed picture screen and see breed pictures
             * */
            onNodeWithText("Akita")
                .performClick()

            onNodeWithText("Akita")
                .assertIsDisplayed()

            /**
             * Here user is on Breed Picture screen
             * */
            onNodeWithContentDescription("BreedPictureScreen")
                .assertExists()
                .assertIsDisplayed()

            waitUntil {
                onAllNodesWithContentDescription("breed_picture_1")
                    .fetchSemanticsNodes()
                    .size == 1
            }

            waitUntil {
                onAllNodesWithContentDescription("breed_picture_2")
                    .fetchSemanticsNodes()
                    .size == 1
            }

            /**
             * user will toggle breed pictures
             * */

            onNodeWithContentDescription("breed_picture_1")
                .performClick()

            onNodeWithContentDescription("breed_picture_2")
                .performClick()

            onNodeWithContentDescription("CustomTouchableScale")
                .assertIsDisplayed()

            onNodeWithContentDescription("CustomTouchableScale")
                .performClick()

            waitUntil(1000) {
                onAllNodesWithContentDescription(activity.getString(R.string.arrow_back_content_desc))
                    .fetchSemanticsNodes()
                    .size == 1
            }

            /**
             * user will navigate back to breed list screen
             * */
            onNodeWithContentDescription(activity.getString(R.string.arrow_back_content_desc))
                .performClick()

            waitUntil(1000) {
                onAllNodesWithContentDescription(activity.getString(R.string.app_name))
                    .fetchSemanticsNodes()
                    .size == 1
            }

            /**
             * User will navigate to favorite breed pictures by clicking the favorite icon
             * */
            waitUntil(1000) {
                onAllNodesWithContentDescription(activity.getString(R.string.favorite_icon_content_desc))
                    .fetchSemanticsNodes()
                    .size == 1
            }

            /**click on favorite icon */
            onNodeWithContentDescription(activity.getString(R.string.favorite_icon_content_desc))
                .performClick()

            /**
             *
             * In Favorite breed picture screen user will see all favorite breed pictures and and their names
             *
             * */
            waitUntil(5000) {
                onAllNodesWithContentDescription(activity.getString(R.string.favorite_title_screen))
                    .fetchSemanticsNodes()
                    .size == 1
            }

            // todo should add check item size is correct
        }
    }
}
