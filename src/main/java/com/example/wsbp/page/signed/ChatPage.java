package com.example.wsbp.page.signed;

import com.example.wsbp.MySession;
import com.example.wsbp.data.Chat;
import com.example.wsbp.service.IChatService;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;
import org.wicketstuff.annotation.mount.MountPath;

@AuthorizeInstantiation(Roles.USER)
@MountPath("Chat")
public class ChatPage extends WebPage {

    @SpringBean
    private IChatService chatService;

    public ChatPage() {
        var userName = MySession.get().getUserName();
        var name = Model.of(userName);
        var userNameLabel = new Label("userName", name);
        add(userNameLabel);

        var chatBodyModel = Model.of("");

        var chatInfoForm = new Form<>("chatInfo") {
            @Override
            protected void onSubmit() {
                var chatBody = chatBodyModel.getObject();
                chatService.registerChat(userName, chatBody);
                setResponsePage(new ChatPage());
            }
        };
        add(chatInfoForm);

        var chatBodyField = new TextField<>("chatBody", chatBodyModel) {
            @Override
            protected void onInitialize() {
                super.onInitialize();
                add(StringValidator.lengthBetween(1, 128));
            }
        };
        chatInfoForm.add(chatBodyField);

        var chatsModel = Model.ofList(chatService.findChats());
        var chatsLV = new ListView<>("chats", chatsModel) {
            @Override
            protected void populateItem(ListItem<Chat> listItem) {
                var itemModel = listItem.getModel();
                var chat = itemModel.getObject();

                var userNameModel = Model.of(chat.getUserName());
                var userNameLabel = new Label("userName", userNameModel);
                listItem.add(userNameLabel);

                var chatBodyModel = Model.of(chat.getMsgBody());
                var chatBodyLabel = new Label("chatBody", chatBodyModel);
                listItem.add(chatBodyLabel);
            }
        };
        add(chatsLV);
    }
}
