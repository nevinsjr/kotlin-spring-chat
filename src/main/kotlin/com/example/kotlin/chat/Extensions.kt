package com.example.kotlin.chat

// TODO: I don't like where this file is located nor the lack of encapsulation for the
//  objects these extensions support.  That is all...

import com.example.kotlin.chat.repository.ContentType
import com.example.kotlin.chat.repository.Message
import com.example.kotlin.chat.service.MessageVM
import com.example.kotlin.chat.service.UserVM
import org.intellij.markdown.flavours.commonmark.CommonMarkFlavourDescriptor
import org.intellij.markdown.html.HtmlGenerator
import org.intellij.markdown.parser.MarkdownParser
import java.net.URL

fun MessageVM.asDomainObject(contentType: ContentType = ContentType.MARKDOWN): Message = Message(
    content,
    contentType,
    sent,
    user.name,
    user.avatarImageLink.toString(),
    id
)

fun Message.asViewModel(): MessageVM = MessageVM(
    contentType.render(content),
    UserVM(username, URL(userAvatarImageLink)),
    sent,
    id
)

fun List<Message>.mapToViewModel(): List<MessageVM> = map { it.asViewModel() }

fun ContentType.render(content: String): String = when (this) {
    ContentType.PLAIN -> content
    ContentType.MARKDOWN -> {
        val flavor = CommonMarkFlavourDescriptor()
        HtmlGenerator(content, MarkdownParser(flavor).buildMarkdownTreeFromString(content),
            flavor).generateHtml()
    }
}
