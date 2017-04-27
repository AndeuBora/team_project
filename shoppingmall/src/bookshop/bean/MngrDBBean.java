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

import work.crypt.BCrypt;
import work.crypt.SHA256;

public class MngrDBBean {
	// dao --보라
	// 전역객체 (한 객체만 생성해서 공유)
	private static MngrDBBean instance = new MngrDBBean();

	private static MngrDBBean get_instance() {
		return instance;
	}

	// 생성자
	private MngrDBBean() {
	};

	// 커넥션풀
	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/test");
		return ds.getConnection();
	}

	// 관리자 인증
	public int user_check(String id, String passwd) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int x = -1; // 초기값
		SHA256 sha = SHA256.getInsatnce();
		try {
			con = getConnection();
			String org_pass = passwd; // 사용자pw
			String sha_pass = sha.getSha256(org_pass.getBytes());

			ps = con.prepareStatement("select managerPasswd from manager where managerId=?");
			ps.setString(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				String db_pass = rs.getString(1);
				if (BCrypt.checkpw(sha_pass, db_pass))
					x = 1;// 인증성공
				else
					x = 0;// 비번틀림
			} else {
				x = -1; // 아이디없음
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return x;
	}

	// 책 등록 메소드
	public void insert_book(MngrDataBean dto) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = getConnection();
			ps = con.prepareStatement("insert into book " + "values(book_seq.nextval,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, dto.getBook_kind());
			ps.setString(2, dto.getBook_title());
			ps.setInt(3, dto.getBook_price());
			ps.setInt(4, dto.getBook_count());
			ps.setString(5, dto.getAuthor());
			ps.setString(6, dto.getPublishing_com());
			ps.setString(7, dto.getPublishing_date());
			ps.setString(8, dto.getBook_image());
			ps.setString(9, dto.getBook_content());
			ps.setInt(10, dto.getDiscount_rate());
			ps.setTimestamp(11, dto.getReg_date());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// 이미 등록된 책을 검증
	public int registed_bookconfirm(String book_kind, String book_title, String author) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int x = -1; // 초기값

		try {
			con = getConnection();
			ps = con.prepareStatement(
					"select book_title from book " + "where book_kind=? and book_title=? and author=?");
			ps.setString(1, book_kind);
			ps.setString(2, book_title);
			ps.setString(3, author);
			rs = ps.executeQuery();

			if (rs.next()) {
				x = 1;// 책 등록되있음
			} else {
				x = 0;// 책 등록안되있음
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return x;
	}

	// 전체 등록된 책 개수 얻어오기 메소드
	public int getBook_count() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int x = 0; // 책 갯수 초기값

		try {
			con = getConnection();
			ps = con.prepareStatement("select count(*) from book");
			rs = ps.executeQuery();

			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return x;
	}

	// 해당 분류 책 개수 얻어오기 메소드
	public int getBook_count(String book_kind) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int x = 0; // 책 갯수 초기값
		int kind = Integer.parseInt(book_kind); // count그룹함수가 숫자이기때문에 숫자형식으로
												// int처리

		try {
			con = getConnection();
			ps = con.prepareStatement("select count(*) from book where book_kind=" + kind);
			rs = ps.executeQuery();

			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return x;
	}

	// 책 제목
	public String getBook_title(int book_id) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String x = ""; // 책 제목

		try {
			con = getConnection();
			ps = con.prepareStatement("select book_title from book where book_id=" + book_id);
			rs = ps.executeQuery();

			if (rs.next()) {
				x = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return x;
	}

	// 분류별 또는 전체 등록된 책의 정보 얻어내는 메소드
	public List<MngrDataBean> getBooks(String book_kind) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<MngrDataBean> list = null;

		try {
			con = getConnection();

			String sql1 = "select * from book";
			String sql2 = "select * from book where book_kind=? order by reg_date desc";

			if (book_kind.equals("all") || book_kind.equals("")) {
				ps = con.prepareStatement(sql1);
			} else {
				ps = con.prepareStatement(sql2);
				ps.setString(1, book_kind);
			}
			rs = ps.executeQuery();

			if (rs.next()) {
				list = new ArrayList<MngrDataBean>();
				do {
					// 내용빼고 다받아옴
					MngrDataBean dto = new MngrDataBean();
					dto.setBook_id(rs.getInt(1));
					dto.setBook_kind(rs.getString(2));
					dto.setBook_title(rs.getString(3));
					dto.setBook_price(rs.getInt(4));
					dto.setBook_count(rs.getInt(5));
					dto.setAuthor(rs.getString(6));
					dto.setPublishing_com(rs.getString(7));
					dto.setPublishing_date(rs.getString(8));
					dto.setBook_image(rs.getString(9));
					dto.setDiscount_rate(rs.getInt("discount_rate"));
					dto.setReg_date(rs.getTimestamp("reg_date"));
					list.add(dto);
				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	// 쇼핑몰 메인에 표시하기위해 사용하는 분류별 신간책 목록 (top)
	public MngrDataBean[] getBooks(String book_kind, int top) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MngrDataBean list[] = null;
		int i = 0;

		try {
			con = getConnection();
			ps = con.prepareStatement(
					"select book_id, book_kind, book_title, book_price, book_count, author,publishing_com,publishing_date,book_image,discount_rate"
							+ "reg_date,rownum from (select * from book where book_kind=? order by reg_date desc) where rownum <= ?");
			ps.setString(1, book_kind);
			ps.setInt(2, top); // top까지만 검색
			rs = ps.executeQuery();

			if (rs.next()) {
				list = new MngrDataBean[top];
				do {
					// 내용빼고 다받아옴
					MngrDataBean dto = new MngrDataBean();
					dto.setBook_id(rs.getInt(1));
					dto.setBook_kind(rs.getString(2));
					dto.setBook_title(rs.getString(3));
					dto.setBook_price(rs.getInt(4));
					dto.setBook_count(rs.getInt(5));
					dto.setAuthor(rs.getString(6));
					dto.setPublishing_com(rs.getString(7));
					dto.setPublishing_date(rs.getString(8));
					dto.setBook_image(rs.getString(9));
					dto.setDiscount_rate(rs.getInt(10));
					dto.setReg_date(rs.getTimestamp(11));
					list[i] = dto;
					i++;
				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	// 책수정&삭제를 위한 책정보검색
	public MngrDataBean getBook(int book_id) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MngrDataBean dto = null;

		try {
			con = getConnection();
			ps = con.prepareStatement("select * from book where book_id=?");
			ps.setInt(1, book_id);
			rs = ps.executeQuery();

			if (rs.next()) {

				dto = new MngrDataBean();
				dto.setBook_id(rs.getInt(1));
				dto.setBook_kind(rs.getString(2));
				dto.setBook_title(rs.getString(3));
				dto.setBook_price(rs.getInt(4));
				dto.setBook_count(rs.getInt(5));
				dto.setAuthor(rs.getString(6));
				dto.setPublishing_com(rs.getString(7));
				dto.setPublishing_date(rs.getString(8));
				dto.setBook_image(rs.getString(9));
				dto.setDiscount_rate(rs.getInt("discount_rate"));
				dto.setReg_date(rs.getTimestamp("reg_date"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dto;
	}
	
	//등록된책 정보 수정시
	public void updateBook(MngrDataBean dto,int book_id) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = getConnection();
			ps = con.prepareStatement("update book set book_kind=?,book_title=?,book_price=?,"
					+ "book_count=?,author=?,publishing_com=?,publishing_date=?,book_image=?,"
					+ "book_content=?,discount_rate=? where book_id=?");
			ps.setString(1, dto.getBook_kind());
			ps.setString(2, dto.getBook_title());
			ps.setInt(3, dto.getBook_price());
			ps.setInt(4, dto.getBook_count());
			ps.setString(5, dto.getAuthor());
			ps.setString(6, dto.getPublishing_com());
			ps.setString(7, dto.getPublishing_date());
			ps.setString(8, dto.getBook_image());
			ps.setString(9, dto.getBook_content());
			ps.setInt(10, dto.getDiscount_rate());
			ps.setInt(11, book_id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
