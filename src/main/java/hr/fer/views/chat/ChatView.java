package hr.fer.views.chat;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import hr.fer.views.main.MainView;
import com.vaadin.flow.router.RouteAlias;
import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.vaadin.artur.Avataaar;

import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Route(value = "chat", layout = MainView.class)
@PageTitle("Chat")
@CssImport("./styles/views/chat/chat-view.css")
@RouteAlias(value = "", layout = MainView.class)
public class ChatView extends Div {

    private final UI ui;
    private final MessageList messageList = new MessageList();
    private final TextField message = new TextField();
    private final Chat chatSession;
    private final ScheduledExecutorService executorService;

    public ChatView(Bot john, ScheduledExecutorService executorService) {
        this.executorService = executorService;
        ui = UI.getCurrent();
        chatSession = new Chat(john);
        message.setPlaceholder("Enter a message...");
        message.setSizeFull();
        Button send = new Button(VaadinIcon.ENTER.create(), event -> sendMessage());
        send.addClickShortcut(Key.ENTER);
        HorizontalLayout inputLayout = new HorizontalLayout(message, send);
        inputLayout.addClassName("inputLayout");
        add(messageList, inputLayout);
        setSizeFull();
    }

    private void sendMessage() {
        String text = message.getValue();
        messageList.addMessage("You", new Avataaar("Name"), text, true);
        message.clear();
        executorService.schedule(() -> {
                String answer = chatSession.multisentenceRespond(text);
                ui.access(() -> messageList.addMessage(
                    "John", new Avataaar("John"), answer, false));
            },new Random().ints(1000, 3000).findFirst().getAsInt(),
            TimeUnit.MILLISECONDS);

    }



}
