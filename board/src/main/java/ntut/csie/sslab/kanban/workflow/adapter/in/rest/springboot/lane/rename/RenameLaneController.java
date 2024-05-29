package ntut.csie.sslab.kanban.workflow.adapter.in.rest.springboot.lane.rename;

import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandPresenter;
import ntut.csie.sslab.ddd.adapter.presenter.CommonViewModel;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.lane.rename.RenameLaneInput;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.lane.rename.RenameLaneUseCase;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;

@RestController
public class RenameLaneController {
    private final RenameLaneUseCase renameLaneUseCase;

    @Autowired
    public RenameLaneController(RenameLaneUseCase renameLaneUseCase) {
        this.renameLaneUseCase = renameLaneUseCase;
    }

    @PutMapping(path = "${KANBAN_PREFIX}/workflows/{workflowId}/lanes/{laneId}/name", consumes = "application/json" , produces = "application/json")
    public CommonViewModel renameLane(@PathVariable("workflowId")String workflowId,
                                      @PathVariable("laneId")String laneId,
                                      @QueryParam("boardId") String boardId,
                                      @QueryParam("userId") String userId,
                                      @QueryParam("username") String username,
                                      @RequestBody String laneInfo) {

        String newName = "";
        try {
            JSONObject stageJSON = new JSONObject(laneInfo);
            newName = stageJSON.getString("newName");

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        RenameLaneInput input = new RenameLaneInput();
        input.setBoardId(boardId);
        input.setWorkflowId(workflowId);
        input.setLaneId(laneId);
        input.setNewName(newName);
        input.setUserId(userId);
        input.setUsername(username);

        CommonViewModel viewModel = CqrsCommandPresenter.newInstance().buildViewModel(renameLaneUseCase.execute(input));

        return viewModel;
    }
}
