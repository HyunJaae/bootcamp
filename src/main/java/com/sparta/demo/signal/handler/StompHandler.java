package com.sparta.demo.signal.handler;

import com.sparta.demo.repository.UserRepository;
import com.sparta.demo.security.jwt.JwtDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.logging.Logger;

@Slf4j
@RequiredArgsConstructor
@Component
public class StompHandler implements ChannelInterceptor {

//    private final JwtDecoder jwtDecoder;
//    private final ChatService chatService;
//    private final RedisRepository redisRepository;
//    private final RoomRepository roomRepository;
//    private final UserRepository userRepository;
//    private final EnterUserRepository enterUserRepository;
    private final Long min = 0L;


    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        log.info("accessor.getCommand : {}", accessor.getCommand());
        log.info("accessor.getCommand : {}", accessor.getSessionId());
        log.info("accessor.getCommand : {}", message.getHeaders().get("simpSessionId"));
        // websocket 연결시 헤더의 jwt token 검증
//        if (StompCommand.CONNECT == accessor.getCommand()) {
//            jwtDecoder.decodeUsername(accessor.getFirstNativeHeader("Authorization").substring(7));
//        } else if (StompCommand.SUBSCRIBE == accessor.getCommand()) {
//            String roomId = chatService.getRoomId(Optional.ofNullable((String) message.getHeaders().get("simpDestination")).orElse("InvalidRoomId"));
//            String sessionId = (String) message.getHeaders().get("simpSessionId");
//
//            redisRepository.setUserEnterInfo(sessionId, roomId);
//            redisRepository.plusUserCount(roomId);
////            String name = jwtDecoder.decodeUsername(accessor.getFirstNativeHeader("Authorization").substring(7));
//            String name = jwtDecoder.decodeNickname(accessor.getFirstNativeHeader("Authorization").substring(7));
//
//            redisRepository.setNickname(sessionId, name);
//            try {
//                chatService.sendChatMessage(ChatMessage.builder().type(ChatMessage.MessageType.ENTER).roomId(roomId).sender(name).build());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//
//            if (roomId != null) {
//                Room room = roomRepository.findByroomId(roomId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 방입니다."));
//                room.setUserCount(redisRepository.getUserCount(roomId));
//                if (redisRepository.getUserCount(roomId) < 0) {
//                    room.setUserCount(min);
//                }
//                roomRepository.save(room);
//            }
//
//        } else if (StompCommand.DISCONNECT == accessor.getCommand()) {
//            String sessionId = (String) message.getHeaders().get("simpSessionId");
//
//            String roomId = redisRepository.getUserEnterRoomId(sessionId);
//            String name = redisRepository.getNickname(sessionId);
//
//            if (roomId != null) {
//                redisRepository.minusUserCount(roomId);
//                try {
//                    chatService.sendChatMessage(ChatMessage.builder().type(ChatMessage.MessageType.QUIT).roomId(roomId).sender(name).build());
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                Room room = roomRepository.findByroomId(roomId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 방입니다.(DISCONNECT)"));
//
//
//                room.setUserCount(redisRepository.getUserCount(roomId));
//                System.out.println(redisRepository.getUserCount(roomId));
//                log.info("USERCOUNT {}, {}", roomId, redisRepository.getUserCount(roomId));
//
//                roomRepository.save(room);
//
//                //유튜브 켜고 방 나왔을 때, 방 인원이 0명이면 false로
//                if (redisRepository.getUserCount(roomId) == 0) {
//                    room.setWorkOut(false);
//                    roomRepository.save(room);
//                }
//
//                User user = userRepository.findByNickname(name);
//                if (enterUserRepository.findByRoomAndUser(room, user).getRoom().getRoomId().equals(roomId)) {
//                    EnterUser enterUser = enterUserRepository.findByRoomAndUser(room, user);
//                    enterUserRepository.delete(enterUser);
//                    log.info("USERENTER_DELETE {}, {}", name, roomId);
//                }
//
//                redisRepository.removeUserEnterInfo(sessionId);
//                log.info("DISCONNECTED {}, {}", sessionId, roomId);
//
//                if (room.getUserCount() < 0) {
//                    room.setUserCount(0L);
//                    roomRepository.save(room);
//                    log.info("0이하 보정 {}", roomId);
//
//                }
//            }
//        }
        return message;
    }
}
