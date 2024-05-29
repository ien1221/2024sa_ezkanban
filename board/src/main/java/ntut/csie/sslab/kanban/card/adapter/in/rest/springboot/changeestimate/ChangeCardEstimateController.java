package ntut.csie.sslab.kanban.card.adapter.in.rest.springboot.changeestimate;

import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandPresenter;
import ntut.csie.sslab.ddd.adapter.presenter.CommonViewModel;
import ntut.csie.sslab.kanban.card.usecase.port.in.estimate.ChangeCardEstimateInput;
import ntut.csie.sslab.kanban.card.usecase.port.in.estimate.ChangeCardEstimateUseCase;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.*;

@RestController
public class ChangeCardEstimateController {
    private final ChangeCardEstimateUseCase changeCardEstimateUseCase;

    @Autowired
    public ChangeCardEstimateController(ChangeCardEstimateUseCase changeCardEstimateUseCase) {
        this.changeCardEstimateUseCase = changeCardEstimateUseCase;
    }

    @PutMapping(path = "${KANBAN_PREFIX}/cards/{cardId}/estimate", consumes = "application/json", produces = "application/json")
    public CommonViewModel changeCardEstimate(@PathVariable("cardId") String cardId,
                                              @QueryParam("userId") String userId,
                                              @QueryParam("username") String username,
                                              @QueryParam("boardId") String boardId,
                                              @RequestBody String cardInfo) {

        String estimate = "";

        try {
            JSONObject cardJSON = new JSONObject(cardInfo);
            estimate = cardJSON.getString("estimate");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        ChangeCardEstimateInput input = new ChangeCardEstimateInput();
        input.setCardId(cardId);
        input.setNewEstimate(estimate);
        input.setUserId(userId);
        input.setUsername(username);
        input.setBoardId(boardId);

        CommonViewModel viewModel = CqrsCommandPresenter.newInstance().buildViewModel(changeCardEstimateUseCase.execute(input));

        return viewModel;
    }

}
