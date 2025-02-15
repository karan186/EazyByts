package com.spring.chatserver.service;

import com.spring.chatserver.dto.MessageSummaryDto;
import com.spring.chatserver.dto.MessagesDto;
import com.spring.chatserver.dto.UpdateMessageStatusDto;
import com.spring.chatserver.dto.UserDto;
import com.spring.chatserver.model.Messages;
import com.spring.chatserver.model.User;
import com.spring.chatserver.repository.MessagesRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessagesService {

    private final MessagesRepository messagesRepository;
    private final SimpMessagingTemplate messagingTemplate;


    public MessagesService(MessagesRepository messagesRepository, SimpMessagingTemplate messagingTemplate) {
        this.messagesRepository = messagesRepository;
        this.messagingTemplate = messagingTemplate;
    }


    //    SELECT * FROM messages WHERE (sent_by = 4 AND sent_to = 6) OR (sent_by = 6 AND sent_to=4) ORDER BY message_id
    public List<MessageSummaryDto> getUserMessages(Long userId, Integer pageNumber, Integer pageSize) {
        Pageable paginationObject = PageRequest.of(pageNumber, pageSize);

        List<Object[]> messages = messagesRepository.getUserDistinctMessages(userId, paginationObject).getContent();
        return messages.stream()
                .map(messageSummaryObject -> new MessageSummaryDto(userId, Long.valueOf(messageSummaryObject[0].toString()), Long.valueOf(messageSummaryObject[1].toString()), messageSummaryObject[2].toString(), messageSummaryObject[3].toString(), messageSummaryObject[4].toString(), Long.valueOf(messageSummaryObject[5].toString())))
                .toList();
    }

    public List<MessagesDto> getUserMessagesWithUser(Long loggedInUserId, Long chatRecipientId, Integer pageNumber, Integer pageSize) {
        Pageable paginationObject = PageRequest.of(pageNumber, pageSize);

        List<Messages> messages = messagesRepository.getUserMessagesWithUser(loggedInUserId, chatRecipientId, paginationObject);

        return messages.stream().map(message -> MessagesDto.builder()
                .sentBy(message.getSentTo().getId())
                .message(message.getMessage())
                .messageId(message.getId())
                .status(message.getStatus())
                .sentTo(message.getSentTo().getId())
                .build()).toList();
    }

    public MessagesDto postMessage(UserDto loggedInUser, MessagesDto messagesDto) {

        Messages savedMessage = messagesRepository.save(
                ((Object) Messages.builder())
                        .sentBy(new User(loggedInUser.getUserId()))
                        .sentTo(new User(messagesDto.getSentTo()))
                        .message(messagesDto.getMessage())
                        .status("U")
                        .build());


        MessagesDto savedMessagesDto = ((Object) MessagesDto.builder())
                .message(savedMessage.getMessage())
                .sentTo(savedMessage.getSentTo().getId())
                .sentBy(savedMessage.getSentTo().getId())
                .messageId(savedMessage.getId())
                .status(savedMessage.getStatus())
                .build();

        MessageSummaryDto dto = new MessageSummaryDto(savedMessage.getSentTo().getId(),
                loggedInUser.getUserId(),
                savedMessagesDto.getMessageId(),
                loggedInUser.getName(),
                savedMessage.getMessage(),
                savedMessagesDto.getStatus(),
                loggedInUser.getUserId()
        );

        messagingTemplate.convertAndSendToUser(String.valueOf(savedMessagesDto.getSentTo()), "/reply", dto);

        return savedMessagesDto;
    }

    @Transactional
    public void markMessagesReadForUsers(Long loggedInUserId, UpdateMessageStatusDto updateMessageStatusDto) {
        Long chatRecipientId = updateMessageStatusDto.getChatRecipientId();
        messagesRepository.markMessagesReadForUsers(loggedInUserId, chatRecipientId);
    }
}
