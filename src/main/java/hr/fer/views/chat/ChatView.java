package hr.fer.views.chat;

import com.vaadin.flow.component.Key;
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


@Route(value = "chat", layout = MainView.class)
@PageTitle("Chat")
@CssImport("./styles/views/chat/chat-view.css")
@RouteAlias(value = "", layout = MainView.class)
public class ChatView extends Div {

    private final MessageList messageList = new MessageList();
    private final TextField message = new TextField();
    private final Chat chatSession;

    public ChatView(Bot john) {
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
        String answer = chatSession.multisentenceRespond(text);
        messageList.addMessage( "John", new Avataaar("John"), answer, false);

    }



}
