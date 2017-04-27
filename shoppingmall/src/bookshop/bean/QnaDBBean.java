package bookshop.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class QnaDBBean {

	private static QnaDBBean instance = new QnaDBBean();

	// .jsp 페이지에서 DB연동빈인 BoardDBBean 클래스의 메소드에 접근시 필요
	public static QnaDBBean getInstance() {
		return instance;
	}

	// 생성자
	private QnaDBBean() {

	}

	// 커넥션 풀로부터 Connection 객체를 얻어냄
	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/test");
		return ds.getConnection();

	}

	// qua 테이블에 글을 추가 - 사용자가 작성하는 글
	@SuppressWarnings("resource")
	public int insertArticle(QnaDataBean article) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		String sql = "";

		int group_id = 1;
		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select max(qna_id) from qna");
			rs = pstmt.executeQuery();

			if (rs.next())
				x = rs.getInt(1);

			if (x > 0)
				group_id = rs.getInt(1) + 1;
			// 쿼리를 작성 :board 테이블에 새로운 레코드 추가

			sql = "insert into qna(qna_id,book_id,book_title,qna_writer,qna_content,";
			sql += "group_id,qora,reply,reg_date) values(qna_sequence.nextval,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, article.getBook_id());
			pstmt.setString(2, article.getBook_tile());
			pstmt.setString(3, article.getQna_writer());
			pstmt.setString(4, article.getQna_conent());
			pstmt.setInt(5, group_id);
			pstmt.setInt(6, article.getQora());
			pstmt.setInt(7, article.getReply());
			pstmt.setTimestamp(8, article.getReg_date());
			pstmt.executeUpdate();

			x = 1; // 레코드 추가 성공
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		return x;
	}

	// qna테이블에 글을 추가 -관리자가 작성한 답변
	@SuppressWarnings("resource")
	public int insertArticle(QnaDataBean article, int qna_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		String sql = "";

		try {
			conn = getConnection();

			// 쿼리를 작성: board테이블에 새로운 레코드 추가
			sql = "insert into qna(qna_id,book_id,book_title,qna_writer, qna_content,";
			sql += "group_id,qora,rely,reg_date) values(qna_sequence.nextval,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, article.getBook_id());
			pstmt.setString(2, article.getBook_tile());
			pstmt.setString(3, article.getQna_writer());
			pstmt.setString(4, article.getQna_conent());
			pstmt.setInt(5, article.getGroup_id());
			pstmt.setInt(6, article.getQora());
			pstmt.setInt(7, article.getReply());
			pstmt.setTimestamp(8, article.getReg_date());
			pstmt.executeUpdate();

			sql = "update qna set reply=? where qna_id=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.setInt(2, qna_id);
			pstmt.executeUpdate();

			x = 1;// 레코드 추가 성공

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}

		}
		return x;
	}

	// qna 테이블에 저장된 전체 글의 수를 얻어냄
	public int getArticleCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;

		try {

			conn = getConnection();

			pstmt = conn.prepareStatement("select count(*) from qna");

			rs = pstmt.executeQuery();

			if (rs.next())
				x = rs.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}

		}
		return x;
	}

	// 특정 책에 대해 작성한 qna글의 수를 얻어냄
	public int getArticleCount(int book_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;

		try {

			pstmt = conn.prepareStatement("select count(*) from qna where book_id=" + book_id);

			rs = pstmt.executeQuery();

			if (rs.next())
				x = rs.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}

		}
		return x;

	}

	// 지정한 수에 해당하는 qna 글의 수를 얻어냄
	public List<QnaDataBean> getArticle(int count) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<QnaDataBean> articleList = null;// 글목록을 저장하는 객체

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select from qna order by group_id desc, qora asc");

			rs = pstmt.executeQuery();

			if (rs.next()) {// ResultSet이 레코드를 가짐
				articleList = new ArrayList<QnaDataBean>(count);
				do {
					QnaDataBean article = new QnaDataBean();
					article.setQna_id(rs.getInt("qna_id"));
					article.setBook_id(rs.getInt("book_id"));
					article.setBook_tile(rs.getString("book_title"));
					article.setQna_writer(rs.getString("qna_writer"));
					article.setQna_conent(rs.getString("qna_content"));
					article.setGroup_id(rs.getInt("group_id"));
					article.setQora(rs.getInt("qora"));
					article.setReply(rs.getInt("reply"));
					article.setReg_date(rs.getTimestamp("reg_date"));
					// List 객체에 데이터
				} while (rs.next());

			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}

		return articleList;// List 객체의 레퍼런스를 리턴
	}

	// 특정 책에 대해 작성한 qna 글을 지정한 수만큼 얻어냄

	public List<QnaDataBean> getArticles(int count, int book_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<QnaDataBean> articleList = null;// 글목록을 저장하는 객체

		try {
			
			conn = getConnection();
			
			pstmt =conn.prepareStatement(
					"select * from qna where book_id="+book_id+" order by group_id desc,"
					+"qora asc");

			rs = pstmt.executeQuery();
			
			if(rs.next()) {//ResultSet이 레코드를 가짐
			
				articleList = new ArrayList<QnaDataBean>(count);
				do{
					QnaDataBean article = new QnaDataBean();
					
					article.setQna_id(rs.getInt("qna_id"));
					article.setBook_id(rs.getInt("book_id"));
					article.setBook_tile(rs.getString("book_title"));
					article.setQna_writer(rs.getString("qna_writer"));
					article.setQna_conent(rs.getString("qna_content"));
					article.setGroup_id(rs.getInt("group_id"));
					article.setQora(rs.getInt("qora"));
					article.setReply(rs.getInt("reply"));
					article.setReg_date(rs.getTimestamp("reg_date"));
				
					//List 객체에 데치너 저장빈인 BoardDataBean 객체를 저장
					
					articleList.add(article);
				}while(rs.next());
			}
		
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		
		return articleList;//List 객체의 레퍼런스를 리턴
	}
	
	//QnA글 수정 폼에서 사용할 글의 내용
	 public QnaDataBean updateGetArticle(int qna_id){
		 Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			QnaDataBean article= null;
			
			try {
				
				conn=getConnection();
				
			pstmt = conn.prepareStatement(
					"select * from qna where qna_id =?");
			pstmt.setInt(1, qna_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				article = new QnaDataBean();
				article.setQna_id(rs.getInt("qna_id"));
				article.setBook_id(rs.getInt("book_id"));
				article.setBook_tile(rs.getString("book_title"));
				article.setQna_writer(rs.getString("qna_writer"));
				article.setQna_conent(rs.getString("qna_content"));
				article.setGroup_id(rs.getInt("group_id"));
				article.setQora(rs.getInt("qora"));
				article.setReply(rs.getInt("reply"));
				article.setReg_date(rs.getTimestamp("reg_date"));
			}
				
				
			} catch (Exception e) {
  e.printStackTrace();
			}finally {
				if (rs != null)
					try {
						rs.close();
					} catch (SQLException e) {
					}
				if (pstmt != null)
					try {
						pstmt.close();
					} catch (SQLException e) {
					}
				if (conn != null)
					try {
						conn.close();
					} catch (SQLException e) {
					}

			}
			return article;
			
	 
	 }
	
	//QnA글수정 처리에서 사용
	  public int updateArticle(QnaDataBean article){
		  Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int x=-1;
			
			try {
				
				conn = getConnection();
				
				String sql="update qna set qna_content=? where qna_id=?";
				pstmt =conn.prepareStatement(sql);
				pstmt.setString(1, article.getQna_conent());
				pstmt.setInt(2, article.getQna_id());
				pstmt.executeUpdate();
				
				x=1;
			} catch (Exception e) {
        e.printStackTrace();
			
			}finally{
				if (rs != null)
					try {
						rs.close();
					} catch (SQLException e) {
					}
				if (pstmt != null)
					try {
						pstmt.close();
					} catch (SQLException e) {
					}
				if (conn != null)
					try {
						conn.close();
					} catch (SQLException e) {
					}
			}
			return x;
	  }
	  
	  //QnA 글 수정 삭제 처리 시 사용
	  
	  public int deleteArticle(int qna_id){
		  Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int x=-1;
			
			try {
				
				conn=getConnection();
				pstmt =conn.prepareStatement(
						"delete from qna where qna_id=?");
				
				pstmt.setInt(1, qna_id);
				pstmt.executeUpdate();
				
			} catch (Exception e) {
                 e.printStackTrace();
			
			}finally{
				if (rs != null)
					try {
						rs.close();
					} catch (SQLException e) {
					}
				if (pstmt != null)
					try {
						pstmt.close();
					} catch (SQLException e) {
					}
				if (conn != null)
					try {
						conn.close();
					} catch (SQLException e) {
					}
			}
			return x;
	  }
}
