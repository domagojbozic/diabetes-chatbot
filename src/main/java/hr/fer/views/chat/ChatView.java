package hr.fer.views.chat;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import hr.fer.views.main.MainView;
import com.vaadin.flow.router.RouteAlias;

@Route(value = "chat", layout = MainView.class)
@PageTitle("Chat")
@CssImport("./styles/views/chat/chat-view.css")
@RouteAlias(value = "", layout = MainView.class)
public class ChatView extends Div {

    public ChatView() {
        setId("chat-view");
        add(new Label("Content placeholder"));
    }

}
