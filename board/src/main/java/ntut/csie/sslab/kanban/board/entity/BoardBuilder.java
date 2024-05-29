package ntut.csie.sslab.kanban.board.entity;

import java.util.UUID;

public class BoardBuilder {
	private String userId;
	private String boardId;
	private String name;
	private String teamId;

	public static BoardBuilder newInstance() {
		return new BoardBuilder();
	}

	public BoardBuilder userId(String userId) {
		this.userId = userId;
		return this;
	}

	public BoardBuilder teamId(String teamId) {
		this.teamId = teamId;
		return this;
	}

	public BoardBuilder name(String name) {
		this.name = name;
		return this;
	}
	
	public Board build() {
		boardId = UUID.randomUUID().toString();
		Board board = new Board(teamId, boardId, name, userId);
		return board;
	}
}
