package com.example.wsbp.page.signed;

import com.example.wsbp.MySession;
import com.example.wsbp.data.Result;
import com.example.wsbp.service.IChoicesService;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.ArrayList;

@AuthorizeInstantiation(Roles.USER)
@MountPath("Result")
public class ResultPage extends WebPage {

    @SpringBean
    private IChoicesService choicesService;

    public ResultPage() {

        var sampleResult = new ArrayList<Result>();
        var questionIds = MySession.get().getQuestionIds();
        var choiceIds = MySession.get().getAnswerIds();
        for (int i = 0; i < questionIds.size(); i++) {
            var question = choicesService.questions(questionIds.get(i));
            var questionStatement = question.getQuestionStatement();
            var answerId = question.getAnswerId();
            var choiceId = choiceIds.get(i);
            var answerStatement = choicesService.choice(answerId).getChoicesStatement();
            var choiceStatement = choicesService.choice(choiceId).getChoicesStatement();

            Result re = new Result(questionStatement, choiceStatement, answerStatement);
            sampleResult.add(re);
        }

        var resultModel = Model.ofList(sampleResult);
        var chatsLV = new ListView<>("result", resultModel) {
            @Override
            protected void populateItem(ListItem<Result> listItem) {
                var itemModel = listItem.getModel();
                var result = itemModel.getObject();

                var questionModel = Model.of(result.getQuestionStatement());
                var questionLabel = new Label("question", questionModel);
                listItem.add(questionLabel);

                var answerModel = Model.of(result.getAnswerStatement());
                var answerLabel = new Label("answer", answerModel);
                listItem.add(answerLabel);

                var choiceModel = Model.of(result.getChoiceStatement());
                var choiceLabel = new Label("choice", choiceModel);
                listItem.add(choiceLabel);
            }
        };
        add(chatsLV);
    }
}
