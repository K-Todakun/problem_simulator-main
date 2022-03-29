package com.example.wsbp.page.signed;

import com.example.wsbp.MySession;
import com.example.wsbp.data.Choice;
import com.example.wsbp.data.Question;
import com.example.wsbp.service.IChoicesService;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.ArrayList;
import java.util.List;

@AuthorizeInstantiation(Roles.USER)
@MountPath("Practice")
public class PracticePage extends WebPage {

    @SpringBean
    private IChoicesService choicesService;

    private Question sampleQuestion;
    private List<Choice> sampleChoices = new ArrayList<Choice>();
    private Choice selected;

    public PracticePage() {

        var level = MySession.get().getLevel();
        var levelLabel = new Label("level", level);
        add(levelLabel);

        var unitId = MySession.get().getUnitId();

        sampleQuestion = choicesService.question(unitId, level);
        var choices = choicesService.choices(sampleQuestion.getQuestionId());
        for (var choice : choices) {
            sampleChoices.add(choice);
        }

        var questionLabel = new Label("question", sampleQuestion.getQuestionStatement());
        add(questionLabel);

        var form = new Form<>("test") {
            @Override
            protected void onSubmit() {
                var level = MySession.get().getLevel();
                var choiceId = selected.getChoicesId();
                var question = sampleQuestion;
                if (question.getAnswerId() == choiceId) {
                    level++;
                } else {
                    level--;
                }
                MySession.get().setLevel(level);
                MySession.get().addSolvedQuestion(question.getQuestionId(), choiceId);
                if( 0 <= level && level <= 5) {
                    setResponsePage(new PracticePage());
                } else {
                    setResponsePage(new ResultPage());
                }
            }
        };

        add(form);
        form.add(new RadioChoice2<>("RadioChoice", new PropertyModel<Choice>(this, "selected")));
        form.add(new Label("selected", new PropertyModel<String>(this, "selected.type")));
        form.add(new Button("submit"));
    }

    protected class RadioChoice2<Choice> extends RadioChoice<Choice>{
        public RadioChoice2(String id, IModel<Choice> model) {
            super(id, model, (List<? extends Choice>) sampleChoices, new ChoiceRenderer<Choice>("choicesStatement", "choicesId"));
        }
    }
}
