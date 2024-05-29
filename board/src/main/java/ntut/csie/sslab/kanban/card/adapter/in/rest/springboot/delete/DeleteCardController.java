package ntut.csie.sslab.kanban.card.adapter.in.rest.springboot.delete;

import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandPresenter;
import ntut.csie.sslab.ddd.adapter.presenter.CommonViewModel;
import ntut.csie.sslab.kanban.card.usecase.port.in.delete.DeleteCardInput;
import ntut.csie.sslab.kanban.card.usecase.port.in.delete.DeleteCardUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.*;

@RestController
public class DeleteCardController {
	private final DeleteCardUseCase deleteCardUseCase;

	@Autowired
	public DeleteCardController(DeleteCardUseCase deleteCardUseCase) {
		this.deleteCardUseCase = deleteCardUseCase;
	}

	@DeleteMapping(path = "${KANBAN_PREFIX}/cards/{cardId}", produces = "application/json")
	public CommonViewModel deleteCard(@PathVariable("cardId") String cardId,
									  @QueryParam("workflowId") String workflowId,
									  @QueryParam("laneId") String laneId,
									  @QueryParam("userId") String userId,
									  @QueryParam("username") String username,
									  @QueryParam("boardId") String boardId) {

		DeleteCardInput input = new DeleteCardInput();
		input.setWorkflowId(workflowId);
		input.setLaneId(laneId);
		input.setCardId(cardId);
		input.setUserId(userId);
		input.setUsername(username);
		input.setBoardId(boardId);

		CommonViewModel viewModel = CqrsCommandPresenter.newInstance().buildViewModel(deleteCardUseCase.execute(input));

		return viewModel;
	}

}
