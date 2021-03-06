package com.memtrip.exoeasy.player

import android.content.Intent
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

/**
 * Copyright 2013-present memtrip LTD.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
@RunWith(JUnitPlatform::class)
class PlayBackActionTest : Spek({

    given("a player being used in PlayBackAction") {

        val player by memoized { mock<Player>() }

        on("a Play PlayBackAction") {

            val intent = mock<Intent> {
                on {
                    getSerializableExtra("EXTRA_AUDIO_ACTION_TYPE")
                }.thenReturn(PlayBackAction.PLAY)
            }

            PlayBackAction.perform(player, intent)

            it("should trigger `play` on player") {
                verify(player).play()
            }
        }

        on("a Pause PlayBackAction") {

            val intent = mock<Intent> {
                on {
                    getSerializableExtra("EXTRA_AUDIO_ACTION_TYPE")
                }.thenReturn(PlayBackAction.PAUSE)
            }

            PlayBackAction.perform(player, intent)

            it("should trigger `pause` on player") {
                verify(player).pause()
            }
        }

        on("a Stop PlayBackAction") {

            val intent = mock<Intent> {
                on {
                    getSerializableExtra("EXTRA_AUDIO_ACTION_TYPE")
                }.thenReturn(PlayBackAction.STOP)
            }

            PlayBackAction.perform(player, intent)

            it("should trigger `stop` on player") {
                verify(player).stop()
            }
        }

        on("a Seek PlayBackAction") {

            val intent = mock<Intent> {
                on {
                    getSerializableExtra("EXTRA_AUDIO_ACTION_TYPE")
                }.thenReturn(PlayBackAction.SEEK)

                on {
                    getIntExtra("EXTRA_AUDIO_SEEK_PERCENTAGE", 0)
                }.thenReturn(80)
            }

            PlayBackAction.perform(player, intent)

            it("should trigger `seek` on player with a progress of 80") {
                verify(player).seek(80)
            }
        }

        on("a Tickle PlayBackAction") {

            val intent = mock<Intent> {
                on {
                    getSerializableExtra("EXTRA_AUDIO_ACTION_TYPE")
                }.thenReturn(PlayBackAction.TICKLE)
            }

            PlayBackAction.perform(player, intent)

            it("should trigger `tickle` on player") {
                verify(player).tickle()
            }
        }
    }
})