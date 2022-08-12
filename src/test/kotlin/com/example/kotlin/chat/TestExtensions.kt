package com.example.kotlin.chat

// TODO: same here re: extension functions

import com.example.kotlin.chat.repository.Message
import com.example.kotlin.chat.service.MessageVM
import java.time.temporal.ChronoUnit.MILLIS

fun MessageVM.prepareForTesting() = copy(id = null, sent = sent.truncatedTo(MILLIS))

fun Message.prepareForTesting() = copy(id = null, sent = sent.truncatedTo(MILLIS))