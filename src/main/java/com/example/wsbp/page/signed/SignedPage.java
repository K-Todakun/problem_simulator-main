package com.example.wsbp.page.signed;

import com.example.wsbp.MySession;
import com.example.wsbp.data.AuthUser;
import com.example.wsbp.page.SignPage;
import com.example.wsbp.page.UserMakerPage;
import com.example.wsbp.service.IUserService;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

@AuthorizeInstantiation(Roles.USER)
@MountPath("Signed")
public class SignedPage extends WebPage {

    @SpringBean
    private IUserService userService;

    public SignedPage() {
        var userName = MySession.get().getUserName();
        var name = Model.of(userName);
        var userNameLabel = new Label("userName", name);
        add(userNameLabel);

        var authUsersModel = Model.ofList(userService.findAuthUsers());
        var usersLV = new ListView<>("users", authUsersModel) {
            @Override
            protected void populateItem(ListItem<AuthUser> listItem) {
                var itemModel = listItem.getModel();
                var authUser = itemModel.getObject();

                var userNameModel = Model.of(authUser.getUserName());
                var userNameLabel = new Label("userName", userNameModel);
                listItem.add(userNameLabel);

                var userPassModel = Model.of(authUser.getUserPass());
                var userPassLabel = new Label("userPass", userPassModel);
                listItem.add(userPassLabel);
            }
        };
        add(usersLV);

        var toChatLink = new BookmarkablePageLink<>("toChat", ChatPage.class);
        add(toChatLink);

        var toTestLink = new BookmarkablePageLink<>("toTest", SelectSubjectPage.class);
        add(toTestLink);

        Link<Void> signoutLink = new Link<Void>("signOut") {

            @Override
            public void onClick() {
                // ????????????????????????
                MySession.get().invalidate();
                // SignPage?????????
                setResponsePage(SignPage.class);
            }
        };
        add(signoutLink);
    }
}
