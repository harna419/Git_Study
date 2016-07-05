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
	private SqlSession sqlSession; //���� ���� �տ� ������̼� ���̸� �ڵ����� ���� �۾��� �ȴ�.

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
		
		//list.do �� ���� �ɶ� ����, ���� ��, ������ ����س���. (�Ű������� �޾Ƴ���. GET)
		//nowPage(�̰��� GET����), nowBlock(���۱� ��ȣ�� ����س���.) �� ����س���.
		
		ModelAndView listModel = new ModelAndView();
		
		List<HashMap<String, String>> list= sqlSession.selectList("board.selectAll");
		Vector<HashMap<String, String>> listIndex= new Vector<HashMap<String, String>>();
		Vector<HashMap<String, String>> listReturn= new Vector<HashMap<String, String>>();
				
		int count=0;
		for(HashMap<String, String> a : list){
			listIndex.add(count, a);
			count++;
		}//��� �Խñۿ� �� ��ȣ�� �ο��Ѵ�. ����¡�� �ϱ� ���ؼ�!
		
		//�������� �Խñ��� 10���� ���� ��� �ƿ����극���� �ͼ��� �����!
		endRecord=listIndex.size();
		for(int i=startRecord;i<startRecord+numberPerPage;i++){
			if(endRecord==i){break;}
			listReturn.add(listIndex.get(i));
		}
		
		int allPageNumber=(listIndex.size() / numberPerPage) + 1;
		
		//�� ����Ʈ ����
		listModel.setViewName("/board/list");
		listModel.addObject("listReturn", listReturn);
				
		//������ ����
		listModel.addObject("allRecord", new Integer(listIndex.size()));
		//listModel.addObject("startRecord", startRecord);
		//listModel.addObject("nowPage", new Integer(nowPage));
		//listModel.addObject("nowBlock", new Integer(nowBlock));
		//listModel.addObject("allPageNumber", new Integer(allPageNumber));
		
		
		//��ü �Խñ� ��? listIndex.size(); okok
		//��ü ������ ��? �� �� / �ѹ���������  allPageNumber= listIndex.size() / numberPerPage
		//��ü ��� ��? ��ü ������ / ������ �� ���
		//������ ����������/10 = 0, 1, 2, ....
		
		//return new ModelAndView("/board/list", "listReturn", listReturn);
		return listModel;
		
	}//home();
	

	//=================���===============================================================
		@RequestMapping("/reply.do")
		public ModelAndView replyForm(int num, int flag) {
			
			//sqlSession.update("board.readCount", new Integer(num));//��ȸ��
	 		
	 		BoardDto boardDto = (BoardDto)sqlSession.selectOne("board.selectOne", num);
	 		
	 		boardDto.setFlag(flag); 
	 		boardDto.setSubject("��� : "+boardDto.getSubject());
	 		boardDto.setContent("���� ���� :\n===========================\n "+boardDto.getContent() +"\n===========================\n");
	 		
	 		
			return new ModelAndView("/board/WriteForm","boardDto",boardDto);

		}//writeForm ()
		
		
		// insert reply
			@RequestMapping(value="/reply.do", method = RequestMethod.POST)
			public String replyPro(@ModelAttribute("BoardDto") BoardDto boardDto, HttpServletRequest request)
					throws NamingException,IOException 
				{
				System.out.println("��� �ޱ� �μ�Ʈ");
				
				//��� �ޱ� �μ�Ʈ�� �Ҷ���, ref_step �̶� ref_level �� ���� ������Ѵ�.
				BoardDto targetBoardDto = (BoardDto)sqlSession.selectOne("board.selectOne", boardDto.getNum());
				
				int readcount=0;
				String ip=request.getRemoteAddr();//ip���ϰ� 
				
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
			System.out.println("���� �ޱ� �μ�Ʈ");
			int readcount=0;
			String ip=request.getRemoteAddr();//ip���ϰ� 
			
			boardDto.setIp(ip);
			//boardDto2.setPos(pos);
			//boardDto2.setDepth(depth);
			boardDto.setReadcount(readcount);
			
			
			sqlSession.update("board.updateRef");
			sqlSession.insert("board.insertBoard", boardDto);
			 
			 
			return "redirect:list.do";
		}// ------------------------------------------


		//��ȸ�� ���� ,�۳��뺸��
		@RequestMapping("content.do")
		public ModelAndView getOneBoard(int num) {
	 		sqlSession.update("board.readCount", new Integer(num));//��ȸ��
	 		
	 		BoardDto boardDto = (BoardDto)sqlSession.selectOne("board.selectOne", num);
	 		 
	 		return new ModelAndView("/board/content","boardDto",boardDto);
		 
		}//getOneBoard()
		
		//�ۼ��� ��
			@RequestMapping(value="/edit.do", method = RequestMethod.GET)
			public ModelAndView userEdit(int num) {
				BoardDto boardDto = (BoardDto)sqlSession.selectOne("board.selectOne", num);
			 
		 		return new ModelAndView("/board/edit","boardDto",boardDto);
			}// ---
			
			//�ۼ��� 
			@RequestMapping(value = "/edit.do", method = RequestMethod.POST)
			public String updateBoard(@ModelAttribute("boardDto") BoardDto boardDto) 
					throws NamingException,IOException
			{
				sqlSession.update("board.updateBoard", boardDto);
				 
				return "redirect:list.do";
			}

			//�ۻ���
			@RequestMapping("delete.do")
			public String deleteBoard(int num) throws NamingException,IOException {
				sqlSession.delete("board.deleteBoard", num);
				 
				return "redirect:list.do";
			}

		
	}//class end

