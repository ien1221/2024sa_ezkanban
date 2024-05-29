package ntut.csie.sslab.kanban.board.usecase.port.in.create;

import ntut.csie.sslab.ddd.usecase.UseCase;
import ntut.csie.sslab.ddd.usecase.cqrs.Command;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsOutput;

public interface CreateBoardUseCase extends Command<CreateBoardInput, CqrsOutput> {
}
