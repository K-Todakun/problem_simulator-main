package com.example.wsbp.page.signed;

import com.example.wsbp.page.signed.SelectUnitPage;
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
@MountPath("subject")
public class SelectSubjectPage extends WebPage {

    public SelectSubjectPage() {

        //科目一覧表示
        var toSubject = new BookmarkablePageLink<>("toSubject", SelectUnitPage.class);
        add(toSubject);

    }
}

