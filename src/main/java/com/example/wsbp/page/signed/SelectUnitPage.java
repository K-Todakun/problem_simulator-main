package com.example.wsbp.page.signed;

import com.example.wsbp.MySession;
import com.example.wsbp.data.Unit;
import com.example.wsbp.service.IUnitService;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.ArrayList;
import java.util.List;

@AuthorizeInstantiation(Roles.USER)
@MountPath("Unit")
public class SelectUnitPage extends WebPage {

    @SpringBean
    private IUnitService unitService;
    private List<Unit> unitList = new ArrayList<Unit>();
    private Unit selected;

    public SelectUnitPage() {
        unitList = unitService.findUnits();

        var form = new Form<>("unitSelect") {
            @Override
            protected void onSubmit() {
                MySession.get().setUnitId(selected.getUnitId());
                setResponsePage(new PracticePage());
            }
        };

        add(form);
        form.add(new RadioChoice2<>("RadioChoice", new PropertyModel<Unit>(this, "selected")));
        form.add(new Label("selected", new PropertyModel<String>(this, "selected.type")));
        form.add(new Button("submit"));
    }

    protected class RadioChoice2<Unit> extends RadioChoice<Unit> {
        public RadioChoice2(String id, IModel<Unit> model) {
            super(id, model, (List<? extends Unit>) unitList, new ChoiceRenderer<Unit>("unitName", "unitId"));
        }
    }
}
