package co.kr.han;

import java.io.IOException;



import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import model.board.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

@Controller
public class BoardController {
	
	
	@Autowired
	private SqlSession sqlSession; //변수 선언 앞에 어노테이션 붙이면 자동으로 셋터 작업이 된다.

	@RequestMapping("/list.do")
	public ModelAndView boardList(Integer nowpage){
		//System.out.println("test");
		
		int startRecord=0;
		int endRecord=0;
		int nowPage=0;
		int nowBlock=0;
		int pagePerBlock=10;
		int numberPerPage=10;
		
		if(nowpage != null){
		nowPage= nowpage;
		startRecord= (nowpage * 10)+1;
		nowBlock = (nowpage)/10;
		
		}
		
		//list.do 가 실행 될때 마다, 시작 글, 끝글을 계산해낸다. (매개변수로 받아낸다. GET)
		//nowPage(이것은 GET으로), nowBlock(시작글 번호로 계산해낸다.) 을 계산해낸다.
		
		ModelAndView listModel = new ModelAndView();
		
		List<HashMap<String, String>> list= sqlSession.selectList("board.selectAll");
		Vector<HashMap<String, String>> listIndex= new Vector<HashMap<String, String>>();
		Vector<HashMap<String, String>> listReturn= new Vector<HashMap<String, String>>();
				
		int count=0;
		for(HashMap<String, String> a : list){
			listIndex.add(count, a);
			count++;
		}//모든 게시글에 글 번호를 부여한다. 페이징을 하기 위해서!
		
		//페이지에 게시글이 10보다 작을 경우 아웃오브레인지 익셉션 제어용!
		endRecord=listIndex.size();
		for(int i=startRecord;i<startRecord+numberPerPage;i++){
			if(endRecord==i){break;}
			listReturn.add(listIndex.get(i));
		}
		
		int allPageNumber=(listIndex.size() / numberPerPage) + 1;
		
		//글 리스트 세팅
		listModel.setViewName("/board/list");
		listModel.addObject("listReturn", listReturn);
				
		//페이지 세팅
		listModel.addObject("allRecord", new Integer(listIndex.size()));
		//listModel.addObject("startRecord", startRecord);
		//listModel.addObject("nowPage", new Integer(nowPage));
		//listModel.addObject("nowBlock", new Integer(nowBlock));
		//listModel.addObject("allPageNumber", new Integer(allPageNumber));
		
		
		//전체 게시글 수? listIndex.size(); okok
		//전체 페이지 수? 총 글 / 넘버퍼페이지  allPageNumber= listIndex.size() / numberPerPage
		//전체 블록 수? 전체 페이지 / 페이지 퍼 블록
		//나우블록 현재페이지/10 = 0, 1, 2, ....
		
		//return new ModelAndView("/board/list", "listReturn", listReturn);
		return listModel;
		
	}//home();
	

	//=================답글===============================================================
		@RequestMapping("/reply.do")
		public ModelAndView replyForm(int num, int flag) {
			
			//sqlSession.update("board.readCount", new Integer(num));//조회수
	 		
	 		BoardDto boardDto = (BoardDto)sqlSession.selectOne("board.selectOne", num);
	 		
	 		boardDto.setFlag(flag); 
	 		boardDto.setSubject("답글 : "+boardDto.getSubject());
	 		boardDto.setContent("본문 내용 :\n===========================\n "+boardDto.getContent() +"\n===========================\n");
	 		
	 		
			return new ModelAndView("/board/WriteForm","boardDto",boardDto);

		}//writeForm ()
		
		
		// insert reply
			@RequestMapping(value="/reply.do", method = RequestMethod.POST)
			public String replyPro(@ModelAttribute("BoardDto") BoardDto boardDto, HttpServletRequest request)
					throws NamingException,IOException 
				{
				System.out.println("답글 달기 인서트");
				
				//답글 달기 인서트를 할때는, ref_step 이랑 ref_level 을 설정 해줘야한다.
				BoardDto targetBoardDto = (BoardDto)sqlSession.selectOne("board.selectOne", boardDto.getNum());
				
				int readcount=0;
				String ip=request.getRemoteAddr();//ip구하고 
				
				boardDto.setRef_level(targetBoardDto.getRef_level()+1);
				boardDto.setRef_step(targetBoardDto.getRef_step()+1);
				boardDto.setIp(ip);
				boardDto.setRef(targetBoardDto.getRef());
				boardDto.setReadcount(readcount);
				
				//sqlSession.update("board.updateRef");
				sqlSession.insert("board.insertBoard", boardDto);
				 
				 
				return "redirect:list.do";
			}// ------------------------------------------
		//=========================================================================
		

		@RequestMapping("/WriteForm.do")
		public ModelAndView writeForm(int flag) {
			BoardDto boardDto = new BoardDto();
			boardDto.setFlag(flag);
	       
			return new ModelAndView("/board/WriteForm","boardDto",boardDto);
		}//writeForm ()

		// insert
		@RequestMapping(value="/write.do", method = RequestMethod.POST)
		public String userWritePro(@ModelAttribute("BoardDto") BoardDto boardDto, HttpServletRequest request, int flag)
				throws NamingException,IOException 
			{
			System.out.println("새글 달기 인서트");
			int readcount=0;
			String ip=request.getRemoteAddr();//ip구하고 
			
			boardDto.setIp(ip);
			//boardDto2.setPos(pos);
			//boardDto2.setDepth(depth);
			boardDto.setReadcount(readcount);
			
			
			sqlSession.update("board.updateRef");
			sqlSession.insert("board.insertBoard", boardDto);
			 
			 
			return "redirect:list.do";
		}// ------------------------------------------


		//조회수 증가 ,글내용보기
		@RequestMapping("content.do")
		public ModelAndView getOneBoard(int num) {
	 		sqlSession.update("board.readCount", new Integer(num));//조회수
	 		
	 		BoardDto boardDto = (BoardDto)sqlSession.selectOne("board.selectOne", num);
	 		 
	 		return new ModelAndView("/board/content","boardDto",boardDto);
		 
		}//getOneBoard()
		
		//글수정 폼
			@RequestMapping(value="/edit.do", method = RequestMethod.GET)
			public ModelAndView userEdit(int num) {
				BoardDto boardDto = (BoardDto)sqlSession.selectOne("board.selectOne", num);
			 
		 		return new ModelAndView("/board/edit","boardDto",boardDto);
			}// ---
			
			//글수정 
			@RequestMapping(value = "/edit.do", method = RequestMethod.POST)
			public String updateBoard(@ModelAttribute("boardDto") BoardDto boardDto) 
					throws NamingException,IOException
			{
				sqlSession.update("board.updateBoard", boardDto);
				 
				return "redirect:list.do";
			}

			//글삭제
			@RequestMapping("delete.do")
			public String deleteBoard(int num) throws NamingException,IOException {
				sqlSession.delete("board.deleteBoard", num);
				 
				return "redirect:list.do";
			}

		
	}//class end

