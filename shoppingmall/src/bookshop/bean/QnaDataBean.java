package bookshop.bean;

import java.sql.Timestamp;

public class QnaDataBean {

	private int qna_id; //qna 글번호
	private int book_id; //책의 등록 번호
	private String book_tile; // 책 이름
	private String qna_writer;//qna 작성자
	private String qna_conent;//qna 내용
	private int group_id; //qna 그룹
	private int qora; // qna 그룹내의 순서
	private int reply;//답변 여부
	private Timestamp reg_date;// qna 작성일 
	
	
	
	
	public int getQna_id() {
		return qna_id;
	}
	public void setQna_id(int qna_id) {
		this.qna_id = qna_id;
	}
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public String getBook_tile() {
		return book_tile;
	}
	public void setBook_tile(String book_tile) {
		this.book_tile = book_tile;
	}
	public String getQna_writer() {
		return qna_writer;
	}
	public void setQna_writer(String qna_writer) {
		this.qna_writer = qna_writer;
	}
	public String getQna_conent() {
		return qna_conent;
	}
	public void setQna_conent(String qna_conent) {
		this.qna_conent = qna_conent;
	}
	public int getGroup_id() {
		return group_id;
	}
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
	public int getQora() {
		return qora;
	}
	public void setQora(int qora) {
		this.qora = qora;
	}
	public int getReply() {
		return reply;
	}
	public void setReply(int reply) {
		this.reply = reply;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	
	
	
	
	


}
