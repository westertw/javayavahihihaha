package Collection1;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        List<Message> generatedMessages = MessageGenerator.generateMessages(20);
        MessageCollection messageCollection = new MessageCollection(generatedMessages);

        // неповторяющиеся сообщения
        List<Message> uniqueMessages = messageCollection.getUniqueMessages();
        System.out.println("Уникальное сообщение: " + uniqueMessages);

        // удалить сообщения с заданным приоритетом
        messageCollection.removeMessagesByPriority(Message.Priority.HIGH);

        // удалить все сообщения, кроме тех, кто имеет заданный приоритет
        messageCollection.retainMessagesByPriority(Message.Priority.LOW);

        // подсчитать количество сообщений для каждого приоритета
        Map<Message.Priority, Long> priorityCount = messageCollection.countMessagesByPriority();
        System.out.println("Количество сообщение по приоритету: " + priorityCount);

        // подсчитать количество сообщений для каждого кода
        Map<String, Long> codeCount = messageCollection.countMessagesByCode();
        System.out.println("Количество сообщение по коду: " + codeCount);

        // подсчитать количество уникальных сообщений
        long uniqueCount = messageCollection.countUniqueMessages();
        System.out.println("Количество уникальных сообщение: " + uniqueCount);

    }
}