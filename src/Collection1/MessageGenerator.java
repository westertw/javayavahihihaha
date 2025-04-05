package Collection1;

import java.util.*;

public class MessageGenerator {
    private static final String[] TEXTS = {
            "Hello World", "Джава топ", "Использование коллекций", "Рандомное сообщение", "Приоритет",
            "Уникальное сообщение", "Напишите текст", "Другое сообщение", "Тест", "Пример сообщения"
    };
    private static final Random RANDOM = new Random();

    public static List<Message> generateMessages(int count) {
        Set<Message> messages = new LinkedHashSet<>();
        while (messages.size() < count) {
            String code = "Код" + RANDOM.nextInt(100);
            String text = TEXTS[RANDOM.nextInt(TEXTS.length)];
            Message.Priority priority = Message.Priority.values()[RANDOM.nextInt(Message.Priority.values().length)];
            messages.add(new Message(code, text, priority));
        }
        return new ArrayList<>(messages);
    }
}
